package com.qw.consumerexternal.feign.callback;

import com.qw.consumerexternal.feign.UserFeign;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * @Program: cloud-blog
 * @ClassName UserFeignCallback
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-25 15:00
 * @Version 1.0
 **/
@Component
public class UserFeignCallback implements UserFeign {
    @Override
    public Object login(Object user) {
        return null;
    }

    @Override
    public Object user(int size) {
        return null;
    }

    @Override
    public Object upUser(ObjectId id, int size) {
        return null;
    }

    @Override
    public Object downUser(ObjectId id, int size) {
        return null;
    }

    @Override
    public Object admin(int size) {
        return null;
    }

    @Override
    public Object upAdmin(ObjectId id, int size) {
        return null;
    }

    @Override
    public Object downAdmin(ObjectId id, int size) {
        return null;
    }

    @Override
    public boolean findUserExist(String username) {
        return false;
    }

    @Override
    public Object findUsername(String username, int size) {
        return null;
    }

    @Override
    public Object findUpUsername(String username, int size, ObjectId id) {
        return null;
    }

    @Override
    public Object findDownUsername(String username, int size, ObjectId id) {
        return null;
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public boolean update(Object user) {
        return false;
    }

    @Override
    public boolean updateByAvatar(Object user) {
        return false;
    }

    @Override
    public boolean updateUserPassword(Object user) {
        return false;
    }

    @Override
    public boolean findUserPassword(Object user) {
        return false;
    }

    @Override
    public boolean updateByEmail(Object user) {
        return false;
    }

    @Override
    public boolean findUserEmail(Object user) {
        return false;
    }

    @Override
    public boolean updateUserRole(Object user) {
        return false;
    }

    @Override
    public boolean deleteUser(String id) {
        return false;
    }

    @Override
    public boolean saveUser(Object user) {
        return false;
    }

    @Override
    public boolean active(String uuid) {
        return false;
    }
}
