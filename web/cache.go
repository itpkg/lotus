package web

import (
	"fmt"

	"github.com/garyburd/redigo/redis"
)

//Cache cache model
type Cache struct {
	Redis  *redis.Pool
	Prefix string
}

//Keys cache items
func (p *Cache) Keys() ([]string, error) {
	c := p.Redis.Get()
	defer c.Close()
	return redis.Strings(c.Do("KEYS", fmt.Sprintf("%s://*", p.Prefix)))
}

//Flush clear cache
func (p *Cache) Flush() error {
	c := p.Redis.Get()
	defer c.Close()
	keys, err := redis.Values(c.Do("KEYS", fmt.Sprintf("%s://*", p.Prefix)))
	if err == nil && len(keys) > 0 {
		_, err = c.Do("DEL", keys...)
	}
	return err
}
