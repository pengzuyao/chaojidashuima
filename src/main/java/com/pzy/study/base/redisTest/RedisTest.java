package com.pzy.study.base.redisTest;

import redis.clients.jedis.Jedis;

/**
 * Destription:
 * Author: pengzuyao
 * Time: 2019-06-17
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("117.48.201.140" , 6379);
        System.out.println(jedis.ping());
    }
}
