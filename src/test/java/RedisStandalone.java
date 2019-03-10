import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Created by IntelliJ IDEA.<br/>
 * User: eugene<br/>
 * Date: 2019/3/8<br/>
 * Time: 16:38<br/>
 * To change this template use File | Settings | File Templates.
 * this requires classpath to be specified, both compile time ( javac source.java )
 * and runtime
 * package name removed to make javac works
 */
public class RedisStandalone {
    public static void main(String[] args) {
        System.err.println("RedisStandalone.main");
        jedisPool.getResource().set("standalone", "Hello");
    }

    private static final JedisPoolConfig poolConfig = buildPoolConfig();
    private static JedisPool jedisPool = new JedisPool(poolConfig, "localhost", 6379, 36000, "1234");

    private static JedisPoolConfig buildPoolConfig() {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(128);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
