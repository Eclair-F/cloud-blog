package com.qw.provideruser.dao;

import com.qw.provideruser.entity.User;

/**
 * @Program: cloud-blog
 * @ClassName RedisDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 21:45
 * @Version 1.0
 **/
public interface RedisDao {
    String set(User user) ;

    User get(String uuid) ;

}
