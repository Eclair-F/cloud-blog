package com.qw.provideruser.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import org.bson.types.ObjectId;

/**
 * @Program: cloud-blog
 * @ClassName UserDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 21:23
 * @Version 1.0
 **/

public interface UserDao {

    /**
     * 创建用户
     */
    User createUser(User user);

    /**
     * 用户注销
     */
    DeleteResult deleteById(String id);

    /**
     * 查找所有用户
     */
    PageResult<User> findAll(int size, String role);

    /**
     * 分页 上一页
     * 查找所有用户
     */
    PageResult<User> up(ObjectId id, int size, String role);

    /**
     * 分页 下一页
     * 查找所有用户
     */
    PageResult<User> down(ObjectId id, int size, String role);

    /**
     * 根据id查找用户
     */
    User findById(String id);


    /**
     * 根据username查找用户
     */
    User findByUsername(String username);

    /**
     * 根据username查找用户
     */
    PageResult<User> findUsername(String username, int size);

    /**
     * 根据username查找用户
     * 上一页
     */
    PageResult<User> findUpUsername(String username, int size, ObjectId id);

    /**
     * 根据username查找用户
     * 下一页
     */
    PageResult<User> findDownUsername(String username, int size, ObjectId id);


    /**
     * 修改昵称
     */
    UpdateResult updateByNickname(String id, String nickname);

    /**
     * 修改性别
     */
    UpdateResult updateBySex(String id, String sex);

    /**
     * 修改邮箱
     */
    UpdateResult updateByEmail(String id, String email);

    /**
     * 修改电话
     */
    UpdateResult updateByMobile(String id, String mobile);

    /**
     * 修改头像
     */
    UpdateResult updateByAvatar(String id, String avatar);

    /**
     * 修改密码
     */
    UpdateResult updateByPassword(String id, String password);

    /**
     * 修改用户权限
     */
    UpdateResult updateByRole(String id, String role);
}