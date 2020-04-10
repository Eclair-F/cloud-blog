package com.qw.provideruser.controller;

import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import org.bson.types.ObjectId;

/**
 * @Program: cloud-blog
 * @ClassName UserController
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 21:58
 * @Version 1.0
 **/
public interface UserController {

    /**
     * 登陆
     */
    User login(User user);

    /**
     * 查找所有用户
     */
    PageResult<User> user(int size);

    /**
     * 查找上一页用户
     */
    PageResult<User> upUser(ObjectId id, int size);

    /**
     * 查找下一页用户
     */
    PageResult<User> downUser(ObjectId id, int size);

    /**
     * 查找所有管理员
     */
    PageResult<User> admin(int size);

    /**
     * 查找上一页管理员
     */
    PageResult<User> upAdmin(ObjectId id, int size);

    /**
     * 查找下一页管理员
     */
    PageResult<User> downAdmin(ObjectId id, int size);

    /**
     * 根据username查找用户
     */
    PageResult<User> findUsername(String username, int size);

    /**
     * 根据username查找用户
     */
    PageResult<User> findUpUsername(String username, int size, ObjectId id);

    /**
     * 根据username查找用户
     */
    PageResult<User> findDownUsername(String username, int size, ObjectId id);

    /**
     * 查找账户是否存在
     */
    boolean findUserExist(String username);

    /**
     * 根据id查找用户
     */

    User findById(String id);

    /**
     * 根据id查找用户基本信息
     */

//    User findByInfo( String id) ;

    /**
     * 修改用户信息
     */
    boolean update(User user);


    /**
     * 修改头像
     */
    boolean updateByAvatar(User user);

    /**
     * 修改密码
     */
    boolean updateUserPassword(User user);

    /**
     * 查询旧密码
     */
    boolean findUserPassword(User user);

    /**
     * 查询旧邮箱
     */
    boolean findUserEmail(User user);

    /**
     * 修改用户类型
     */
    boolean updateUserRole(User user);

    /**
     * 修改邮箱
     */
    boolean updateByEmail(User user);


    /**
     * 删除用户
     */
    boolean deleteUser(String id);


    /**
     * 添加用户
     */
    boolean saveUser(User user);

    /**
     * 用户激活
     */
    boolean active(String uuid);
}
