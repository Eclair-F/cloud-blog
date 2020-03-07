package com.qw.consumerexternal.feign;

import org.bson.types.ObjectId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;

/**
 * @author Eclair
 */
@FeignClient(name = "provider-user")
public interface UserFeign {
    /**
     * @return java.lang.String
     * @Author Eclair
     * @Description //登陆
     * @Date 2020/3/6 19:40
     * @Param @param user:
     **/
    @PostMapping(value = "/login")
    String login(@RequestBody Object user);

    /**
     * 查找所有用户
     */
    @GetMapping(value = "/")
    Object user();

    /**
     * 查找所有管理员
     */
    @GetMapping(value = "/admin")
    Object admin();

    /**
     * 查找下一页用户
     */
    @GetMapping(value = "/down/{id}")
    Object downUser(@PathVariable(value = "id") ObjectId id);


    /**
     * 查找账户是否存在
     */
    @GetMapping(value = "/exist/{username}")
    boolean findUserExist(@PathVariable(value = "username") String username);

    /**
     * 根据id查找用户
     */

    @GetMapping(value = "/{id}")
    Object findById(@PathVariable(value = "id") String id);

    /**
     * @param response:
     * @return void
     * @Author Eclair
     * @Description //获取图片
     * @Date 2020/3/6 16:50
     * @Param @param id:
     **/
    @GetMapping(value = "/image/{id}")
    HttpServletResponse findImage(@PathVariable(value = "id") String id, HttpServletResponse response);

    /**
     * 修改用户信息
     */
    @PutMapping(value = "/")
    boolean update(@RequestBody Object user);

    /**
     * @return boolean
     * @Author Eclair
     * @Description //
     * @Date 2020/3/6 17:11
     * @Param @param image:
     **/
    @PutMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    boolean updateImage(@RequestParam("image") MultipartFile image);

    /**
     * 修改昵称
     */
    @PutMapping(value = "/nickname")
    boolean updateUserNickname(@RequestBody Object user);


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
     * 修改用户类型
     */
    @PutMapping(value = "/type")
    boolean updateUserType(@RequestBody Object user);

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
     * 查找所有注销用户
     */
    @GetMapping(value = "/delete")
    Object userIsDelete();


    /**
     * 账户还原
     */
    @PutMapping(value = "/delete/{id}")
    boolean recoveryUser(@PathVariable(value = "id") String id);


}
