package com.qw.provideruser.services;

import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import org.bson.types.ObjectId;


/**
 * @Program: cloud-blog
 * @ClassName UserService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 21:31
 * @Version 1.0
 **/
public interface UserService {
    /**
     * 添加用户
     *
     */
    boolean createUser(User user) ;

    /**
     * 激活用户
     *
     */
    boolean active(String uuid);

    /**
     * 根据id删除用户
     */
    boolean deleteById(String id);

    /**
     * 查找所有用户
     */
    PageResult<User> findAllUser(int size);

    /**
     * 分页
     * 查找所有用户 上一页
     */
    PageResult<User> upUser(ObjectId id, int size);

    /**
     * 分页
     * 查找所有用户 下一页
     */
    PageResult<User> downAllUser(ObjectId id, int size);

    /**
     * 查找所有管理员
     */
    PageResult<User> admin(int size);

    /**
     * 分页
     * 查找所有管理员 上一页
     */
    PageResult<User> upAdmin(ObjectId id, int size);

    /**
     * 分页
     * 查找所有管理员 下一页
     */
    PageResult<User> downAdmin(ObjectId id, int size);


    /**
     * 根据id查找用户
     */
    User findById(String id);

//    /**
//     * 根据id查找用户
//     */
//    User findByInfo(String id);
//

    /**
     * 根据username查找用户存不存在
     */
    boolean findByUsernameExists(String username);


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
     * 根据密码比对
     */
    boolean findByPassword(String id, String password);

    /**
     * 根据邮箱比对
     */
    boolean findByEmail(String id, String email);

    /**
     * login
     */
    User login(User user);

    /**
     * 修改昵称,电话，性别
     */
    boolean update(User user);

    /**
     * 修改邮箱
     */

    boolean updateEmail(User user);

    /**
     * 修改邮箱
     */

    boolean updateEmailByAdmin(User user);

    /**
     * 修改头像
     */
    boolean updateByAvatar(String id, String avatar);

    /**
     * 修改密码
     */
    boolean updateByPassword(String id, String password);


    /**
     * 修改用户权限
     */
    boolean updateByRole(String id, String role);


}
