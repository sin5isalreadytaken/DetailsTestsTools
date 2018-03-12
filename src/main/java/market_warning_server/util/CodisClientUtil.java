package market_warning_server.util;

import com.sohu.mrd.framework.redis.client.client.CodisLocalClient;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by byzuse
 * redis 缓存客户端
 */
public class CodisClientUtil {
    private static CodisLocalClient codisLocalClient = null;

    static {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinEvictableIdleTimeMillis(60000);
        config.setTimeBetweenEvictionRunsMillis(30000);
        codisLocalClient = CodisLocalClient
                .create()
                .build();
    }

    public static CodisLocalClient getClient() {
        return codisLocalClient;
    }
}
