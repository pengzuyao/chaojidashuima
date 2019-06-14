package com.pzy.study.base.commons.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/*
 * @Description:
 * @Author: pengzuyao
 * @Time: 2019/06/14
 */

@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<String , Object> redisTemplate;



    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    public void del(String key){
        if (StringUtils.isNotBlank(key)) {
            redisTemplate.delete(key);
        }
        return;
    }

    public boolean expire(String key, Object value, long time) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean expireKey(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public long getExpireKey(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

}
