package com.qw.consumerexternal.feign;

import com.qw.consumerexternal.feign.callback.UserFeignCallback;
import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eclair
 */
@Service
@FeignClient(name = "provider-user",fallback = UserFeignCallback.class)
public interface UserFeign {
    /**
     * @return java.lang.String
     * @Author Eclair
     * @Description //登陆
     * @Date 2020/3/6 19:40
     * @Param @param user:
     **/
    @PostMapping(value = "/login")
    Object login(@RequestBody Object user);

    /**
     * 查找所有用户
     */
    @GetMapping(value = "/{size}")
    Object user(@PathVariable(value = "size") int size);

    /**
     * 查找上一页用户
     */
    @GetMapping(value = "/up/{size}/{id}")
    Object upUser(@PathVariable(value = "id") ObjectId id, @PathVariable(value = "size") int size);


    /**
     * 查找下一页用户
     */
    @GetMapping(value = "/down/{size}/{id}")
    Object downUser(@PathVariable(value = "id") ObjectId id, @PathVariable(value = "size") int size);

    /**
     * 查找所有用户
     */
    @GetMapping(value = "/admin/{size}")
    Object admin(@PathVariable(value = "size") int size);

    /**
     * 查找上一页用户
     */
    @GetMapping(value = "/admin/up/{size}/{id}")
    Object upAdmin(@PathVariable(value = "id") ObjectId id, @PathVariable(value = "size") int size);


    /**
     * 查找下一页用户
     */
    @GetMapping(value = "/admin/down/{size}/{id}")
    Object downAdmin(@PathVariable(value = "id") ObjectId id, @PathVariable(value = "size") int size);

    /**
     * 查找账户是否存在
     */
    @GetMapping(value = "/exist/{username}")
    boolean findUserExist(@PathVariable(value = "username") String username);

    /**
     * 根据username查找用户
     */
    @GetMapping(value = "/username/{username}/{size}")
    Object findUsername(@PathVariable(value = "username") String username, @PathVariable(value = "size") int size);

    /**
     * 根据username查找用户
     */
    @GetMapping(value = "/up/username/{username}/{size}/{id}")
    Object findUpUsername(@PathVariable(value = "username") String username, @PathVariable(value = "size") int size, @PathVariable(value = "id") ObjectId id);

    /**
     * 根据username查找用户
     */
    @GetMapping(value = "/down/username/{username}/{size}/{id}")
    Object findDownUsername(@PathVariable(value = "username") String username, @PathVariable(value = "size") int size, @PathVariable(value = "id") ObjectId id);

    /**
     * 根据id查找用户
     */

    @GetMapping(value = "/id/{id}")
    Object findById(@PathVariable(value = "id") String id);


    /**
     * 修改用户信息
     */
    @PutMapping(value = "/")
    boolean update(@RequestBody Object user);


    /**
     * 修改头像
     */
    @PutMapping(value = "/avatar")
    boolean updateByAvatar(@RequestBody Object user);


    /**
     * 修改密码
     */
    @PutMapping(value = "/password")
    boolean updateUserPassword(@RequestBody Object user);

    /**
     * 查询旧密码
     */
    @PostMapping(value = "/oldPassword")
    boolean findUserPassword(@RequestBody Object user);


    /**
     * 修改邮箱
     */
    @PutMapping(value = "/email")
    boolean updateByEmail(@RequestBody Object user) ;



    /**
     * 查询旧邮箱
     */
    @PostMapping(value = "/oldEmail")
    boolean findUserEmail(@RequestBody Object user) ;

    /**
     * 修改用户类型
     */
    @PutMapping(value = "/role")
    boolean updateUserRole(@RequestBody Object user);

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/{id}")
    boolean deleteUser(@PathVariable(value = "id") String id);

    /**
     * 添加用户
     */
    @PostMapping(value = "/")
    boolean saveUser(@RequestBody Object user);




    /**
     * 用户激活
     */
    @GetMapping(value = "/active/{uuid}")
    boolean active(@PathVariable(value = "uuid") String uuid);


}
