package com.qw.consumerexternal.controller;


import com.qw.consumerexternal.result.Result;
import org.bson.types.ObjectId;

/**
 * @author Eclair
 */
public interface UserController {


    /**
     * 查找所有用户
     */
    Result user(int size);

    /**
     * 查找所有用户
     */
    Result upUser(ObjectId id, int size);

    /**
     * 查找所有用户
     */
    Result downUser(ObjectId id, int size);

    /**
     * 查找所有管理员
     */
    Result admin(int size);

    /**
     * 查找所有管理员
     */
    Result upAdmin(ObjectId id, int size);

    /**
     * 查找所有管理员
     */
    Result downAdmin(ObjectId id, int size);

    /**
     * 登陆
     */
    Result login(Object user);

    /**
     * 查找账户是否存在
     */
    Result findUserExist(String username);

    /**
     * 根据username查找用户
     */
    Result findUsername(String username, int size);

    /**
     * 根据username查找用户
     */
    Object findUpUsername(String username, int size, ObjectId id);

    /**
     * 根据username查找用户
     */
    Object findDownUsername(String username, int size, ObjectId id);

    /**
     * 根据id查找用户
     */
    Result findById(String id);


    /**
     * 修改头像
     */
    Result updateByAvatar(Object user);

    /**
     * 修改
     */
    Result updateUser(Object user);

    /**
     * 查询旧密码
     */
    Object checkPassword(Object user);

    /**
     * 修改密码
     */
    Object updateUserPassword(Object user);

    /**
     * 修改邮箱
     */
    Result updateByEmail(Object user);

    /**
     * 查询邮箱
     */
    Result findUserEmail(Object user);


    /**
     * 修改用户类型
     */
    Result updateUserRole(Object user);


    /**
     * 删除用户
     */
    Result deleteUser(String id);

    /**
     * 添加用户
     */
    Result saveUser(Object user);

    /**
     * 用户激活
     */
    String active(String uuid);

}
