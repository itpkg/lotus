package web

import (
	"bytes"
	"encoding/gob"
	"fmt"

	"github.com/garyburd/redigo/redis"
)

type Cache interface {
	Flush() error
	Keys() ([]string, error)
	Set(key string, val interface{}, ttl uint) error
	Get(key string, val interface{}) error
}

type RedisCache struct {
	Redis *redis.Pool `inject:""`
}

func (p *RedisCache) Flush() error {
	c := p.Redis.Get()
	defer c.Close()
	keys, err := redis.Values(c.Do("KEYS", p.key("*")))
	if err == nil && len(keys) > 0 {
		_, err = c.Do("DEL", keys...)
	}
	return err
}

func (p *RedisCache) Keys() ([]string, error) {
	c := p.Redis.Get()
	defer c.Close()
	return redis.Strings(c.Do("KEYS", p.key("*")))
}

func (p *RedisCache) Set(key string, val interface{}, ttl uint) error {
	c := p.Redis.Get()
	defer c.Close()
	var buf bytes.Buffer
	enc := gob.NewEncoder(&buf)
	if err := enc.Encode(val); err != nil {
		return err
	}
	_, err := c.Do("SET", p.key(key), buf.Bytes(), "EX", ttl)
	return err
}

func (p *RedisCache) Get(key string, val interface{}) error {
	c := p.Redis.Get()
	defer c.Close()
	bys, err := redis.Bytes(c.Do("GET", p.key(key)))
	if err != nil {
		return err
	}
	var buf bytes.Buffer
	dec := gob.NewDecoder(&buf)
	buf.Write(bys)
	return dec.Decode(val)
}

func (p *RedisCache) key(k string) string {
	return fmt.Sprintf("cache://%s", k)
}
