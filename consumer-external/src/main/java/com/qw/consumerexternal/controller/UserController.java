package com.qw.consumerexternal.controller;


import com.qw.consumerexternal.feign.UserFeign;
import com.qw.consumerexternal.result.Result;
import com.qw.consumerexternal.result.StatusCode;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eclair
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserFeign userFeign;
    private final HttpServletRequest request;

    private String Role;

    public UserController(UserFeign userFeign, HttpServletRequest request) {
        this.userFeign = userFeign;
        this.request = request;
    }


    private boolean Admin() {
        String admin = "admin";
        return admin.equals(request.getAttribute("role"));
    }

    private boolean Id(String id) {
        return id.equals(request.getAttribute("id"));
    }


    /**
     * 查找所有用户
     */
    @GetMapping(value = "")
    public Result user() {
        if (Admin()) {
            Object user = userFeign.user();
            if (user != null) {
                return new Result(true, StatusCode.SUCCESS, "查询成功", user);
            }
        }
        return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
    }

    /**
     * 查找所有用户
     */
    @GetMapping(value = "/down/{id}")
    public Result downUser(@PathVariable ObjectId id) {
        if (Admin()) {
            Object user = userFeign.downUser(id);
            if (user != null) {
                return new Result(true, StatusCode.SUCCESS, "查询成功", user);
            }
        }
        return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
    }

    /**
     * 登陆
     */
    @PostMapping(value = "/login")
    public Result login(@RequestBody Object user) {
        String login = userFeign.login(user);
        if (login == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", login);
        }
    }

    /**
     * 查找账户是否存在
     */
    @GetMapping(value = "/exist/{username}")
    public Result findUserExist(@PathVariable String username) {
        boolean user = userFeign.findUserExist(username);
        if (user) {
            return new Result(true, StatusCode.SUCCESS, "查询成功");
        } else {
            return new Result(false, StatusCode.USER_HAS_EXIST, "查询失败");
        }
    }


    /**
     * 根据id查找用户
     */
    @GetMapping(value = "/{id}")
    public Result findById(@PathVariable String id) {
        if (Admin() || Id(id)) {
            Object user = userFeign.findById(id);
            if (user != null) {
                return new Result(true, StatusCode.SUCCESS, "查询成功", user);
            }
        }
        return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
    }

    /**
     * 修改昵称
     */
    @PutMapping()
    public Result updateUser(@RequestBody Object user) {
        boolean update = userFeign.update(user);
        if (update) {
            return new Result(true, StatusCode.SUCCESS, "修改成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "修改失败");
        }
    }


    /**
     * 修改昵称
     */
    @PutMapping(value = "/nickname")
    public Result updateUserNickname(@RequestBody Object user) {
        boolean update = userFeign.update(user);
        if (update) {
            return new Result(true, StatusCode.SUCCESS, "修改成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "修改失败");
        }
    }


    /**
     * 修改密码
     */
    @PutMapping(value = "/password")
    public Object updateUserPassword(@RequestBody Object user) {
        boolean update = userFeign.update(user);
        if (update) {
            return new Result(true, StatusCode.SUCCESS, "修改成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "修改失败");
        }
    }

    /**
     * 修改用户类型
     */
    @PutMapping(value = "/type")
    public Result updateUserType(@RequestBody Object user) {
        boolean update = userFeign.update(user);
        if (update) {
            return new Result(true, StatusCode.SUCCESS, "修改成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "修改失败");
        }
    }

    /**
     * @param id :
     * @return boolean
     * @Author Eclair
     * @Description //
     * @Date 2020/3/6 17:11
     * @Param @param image:
     **/
    @PutMapping(value = "/image")
    public Result updateImage(@RequestParam("image") MultipartFile image ,@RequestParam("id") String id) {
        System.out.println(id);
        System.out.println(image.getOriginalFilename());
        boolean update = userFeign.updateImage(image);
        if (update) {
            return new Result(true, StatusCode.SUCCESS, "修改成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "修改失败");
        }
    }


    /**
     * 删除用户
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteUser(@PathVariable String id) {
        boolean deleteUser = userFeign.deleteUser(id);
        if (deleteUser) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "删除失败");
        }
    }

    /**
     * 添加用户
     */
    @PostMapping()
    public Result saveUser(@RequestBody Object user) {
        boolean register = userFeign.saveUser(user);
        if (register) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "注册失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "注册成功");
        }

    }

    /**
     * 查找所有注销用户
     */
    @GetMapping(value = "/delete")
    public Result userIsDelete() {
        Object user = userFeign.userIsDelete();
        if (user == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", user);
        }

    }


    /**
     * 账户还原
     */
    @PutMapping(value = "/delete/{id}")
    public Result recoveryUser(@PathVariable String id) {
        boolean recovery = userFeign.recoveryUser(id);
        if (recovery) {
            return new Result(true, StatusCode.SUCCESS, "还原成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "还原失败");
        }
    }

}
