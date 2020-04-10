package com.qw.provideruser.dao.implement;

import com.alibaba.fastjson.JSON;
import com.qw.provideruser.dao.RedisDao;
import com.qw.provideruser.entity.User;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Program: cloud-blog
 * @ClassName Redis
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-24 13:26
 * @Version 1.0
 **/
@Repository
public class RedisDaoImpl  implements RedisDao {
    private final RedisTemplate<String, String> redisTemplate;

    public RedisDaoImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String set(User user) {
        String uuid = getUUID(3);
        redisTemplate.opsForValue().set(uuid,
                JSON.toJSONString(user),
                3,
                TimeUnit.HOURS);
        return uuid;
    }

    @Override
    public User get(String uuid) {
        if (redisTemplate.opsForValue().get(uuid) != null) {
            User user = JSON.parseObject(redisTemplate.opsForValue().get(uuid), User.class);
            redisTemplate.delete(uuid);
            return user;
        }
        return null;
    }

    public String getUUID(int number) {
        if (number < 1) {
            return null;
        }
        StringBuilder retArray = new StringBuilder();
        for (int i = 0; i < number; i++) {
            retArray.append(UUID.randomUUID().toString().replaceAll("-", ""));
        }
        return retArray.toString();
    }

}
