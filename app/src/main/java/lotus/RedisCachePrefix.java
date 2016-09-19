package lotus;

/**
 * Created by flamen on 16-9-19.
 */
public class RedisCachePrefix implements org.springframework.data.redis.cache.RedisCachePrefix {
    @Override
    public byte[] prefix(String cacheName) {
        return String.format("cache://%s", cacheName).getBytes();
    }
}
