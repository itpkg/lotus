package redis

import (
	"bytes"
	"encoding/gob"
	"errors"
	"fmt"
	"os"

	_redis "github.com/garyburd/redigo/redis"
	"github.com/itpkg/lotus/jobber"
	logging "github.com/op/go-logging"
)

//Jobber job by readis store
type Jobber struct {
	Redis  *_redis.Pool    `inject:""`
	Logger *logging.Logger `inject:""`

	Timeout  int
	Handlers map[string]jobber.Handler
}

//Register register a job-handler
func (p *Jobber) Register(queue string, handler jobber.Handler) {
	p.Handlers[p.key(queue)] = handler
}

//Push add a job task
func (p *Jobber) Push(queue string, args interface{}) error {
	p.Logger.Infof("push job into %s", queue)
	c := p.Redis.Get()
	defer c.Close()
	var buf bytes.Buffer
	enc := gob.NewEncoder(&buf)
	if err := enc.Encode(args); err != nil {
		return err
	}
	_, err := c.Do("LPUSH", p.key(queue), buf.Bytes())
	return err
}

//Start start to process job
func (p *Jobber) Start() error {
	var err error
	for {
		err = p.run()
		if err != nil && err != _redis.ErrNil {
			break
		}
	}
	return err
}

func (p *Jobber) run() error {
	const stop = ".stop"
	if _, err := os.Stat(stop); err == nil {
		return fmt.Errorf("find file %s, exit", stop)
	}

	if len(p.Handlers) == 0 {
		return errors.New("null handlers")
	}
	c := p.Redis.Get()
	defer c.Close()
	var keys []interface{}
	for k := range p.Handlers {
		keys = append(keys, k)
	}
	keys = append(keys, p.Timeout)
	args, err := _redis.ByteSlices(c.Do("BRPOP", keys...))
	if err != nil {
		return err
	}
	queue := string(args[0])
	p.Logger.Infof("get a job from %s", queue)
	return p.Handlers[queue](args[1])
}

func (p *Jobber) key(queue string) string {
	return fmt.Sprintf("task://%s", queue)
}
