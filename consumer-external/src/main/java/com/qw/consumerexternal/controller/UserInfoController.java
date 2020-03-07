package com.qw.consumerexternal.controller;


import com.qw.consumerexternal.feign.UserInfoFeign;
import com.qw.consumerexternal.result.Result;
import com.qw.consumerexternal.result.StatusCode;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eclair
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {
    private final UserInfoFeign userInfoFeign;

    public UserInfoController(UserInfoFeign userInfoFeign) {
        this.userInfoFeign = userInfoFeign;
    }


    /**
     * 查找所有用户信息
     */
    @GetMapping(value = "")
    public Result userInfo() {
        Object userInfo = userInfoFeign.userInfo();
        if (userInfo == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", userInfo);
        }
    }

    /**
     * 根据userId查找用户
     **/
    @GetMapping(value = "/{userId}")
    public Result findByUserId(@PathVariable String userId) {
        Object userInfo = userInfoFeign.findByUserId(userId);
        if (userInfo == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", userInfo);
        }
    }

    /**
     * 添加收藏
     **/
    @PutMapping(value = "/Collect")
    public Result updateCollect(@RequestBody Object userInfo) {
        boolean update = userInfoFeign.updateCollect(userInfo);
        if (update) {
            return new Result(true, StatusCode.SUCCESS, "添加成功");

        } else {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "添加失败");
        }
    }


    /**
     * 添加点赞
     **/
    @PutMapping(value = "/Praise")
    public Result updatePraise(@RequestBody Object userInfo) {
        boolean update = userInfoFeign.updatePraise(userInfo);
        if (update) {
            return new Result(true, StatusCode.SUCCESS, "添加成功");
        } else {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "添加失败");
        }
    }


    /**
     * 清空信息
     */
    @DeleteMapping(value = "/{userId}")
    public Result deleteUserInfo(@PathVariable String userId) {
        boolean delete = userInfoFeign.deleteUserInfo(userId);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "删除失败");
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/delete/{userId}")
    public Result deleteUser(@PathVariable String userId) {
        boolean delete = userInfoFeign.deleteUser(userId);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "删除失败");
        }
    }

    /**
     * 删除收藏
     */
    @DeleteMapping(value = "/Collect")
    public Result deleteCollect(@RequestBody Object userInfo) {
        boolean delete = userInfoFeign.deleteCollect(userInfo);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "删除失败");
        }
    }

    /**
     * 删除点赞
     */
    @DeleteMapping(value = "/Praise")
    public Result deletePraise(@RequestBody Object userInfo) {
        boolean delete = userInfoFeign.deletePraise(userInfo);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "删除失败");
        }
    }


    /**
     * 添加用户信息
     */
    @PostMapping(value = "")
    public Result saveUser(@RequestBody Object userInformation) {
        boolean create = userInfoFeign.saveUser(userInformation);
        if (create) {
            return new Result(true, StatusCode.SUCCESS, "添加成功");
        } else {
            return new Result(false, StatusCode.SYSTEM_INNER_ERROR, "添加失败");
        }
    }

}
