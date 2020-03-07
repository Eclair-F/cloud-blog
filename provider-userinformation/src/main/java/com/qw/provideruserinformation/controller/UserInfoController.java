package com.qw.provideruserinformation.controller;


import com.qw.provideruserinformation.entity.PageResult;
import com.qw.provideruserinformation.entity.UserInfo;
import com.qw.provideruserinformation.entity.Userinformation;
import com.qw.provideruserinformation.services.CreateService;
import com.qw.provideruserinformation.services.DeleteService;
import com.qw.provideruserinformation.services.RetrieveService;
import com.qw.provideruserinformation.services.UpdateService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eclair
 */
@RestController
public class UserInfoController {
    private final CreateService createService;
    private final RetrieveService retrieveService;
    private final UpdateService updateService;
    private final DeleteService deleteService;
    private final HttpServletRequest request;
    private final String Admin = "admin";
    private final String User = "user";

    public UserInfoController(CreateService createService, RetrieveService retrieveService, UpdateService updateService, DeleteService deleteService, HttpServletRequest request) {
        this.createService = createService;
        this.retrieveService = retrieveService;
        this.updateService = updateService;
        this.deleteService = deleteService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }
    private String getUserId() {
        return (String) request.getAttribute("id");
    }
    private PageResult<Userinformation> userTool(PageResult<Userinformation> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 查找所有用户信息
     */
    @GetMapping(value = "/")
    public PageResult<Userinformation> userInfo() {
        if (Admin.equals(getRole())) {
            return userTool(retrieveService.findAllUserInfo());
        }
        return null;
    }

    /**
     * 根据userid查找用户
     *
     */
    @GetMapping(value = "/{userId}")
    public Userinformation findByUserId(@PathVariable String userId) {
        if (Admin.equals(getRole()) || userId.equals(getUserId())) {
            return retrieveService.findByUserID(userId);
        }
        return null;
    }

    /**
     * 添加收藏
     *
     */
    @PutMapping(value = "/Collect")
    public boolean updateCollect(@RequestBody UserInfo userInfo) {
        if (Admin.equals(getRole()) || userInfo.getUserId().equals(getUserId())) {
            return updateService.updateByCollect(userInfo);
        }
        return false;
    }


    /**
     * 添加点赞
     *
     */
    @PutMapping(value = "/Praise")
    public boolean updatePraise(@RequestBody UserInfo userInfo) {
        if (Admin.equals(getRole()) || userInfo.getUserId().equals(getUserId())) {
            return updateService.updateByPraise(userInfo);
        }
        return false;
    }

    /**
     * 清空信息
     */
    @DeleteMapping(value = "/{userId}")
    public boolean deleteUserInfo(@PathVariable String userId) {
        if (Admin.equals(getRole()) || userId.equals(getUserId())) {
            return deleteService.deleteById(userId);
        }
        return false;
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/delete/{userId}")
    public boolean deleteUser(@PathVariable String userId) {
        if (Admin.equals(getRole())) {
            return deleteService.delete(userId);
        }
        return false;
    }


    /**
     * 删除收藏
     */
    @DeleteMapping(value = "/Collect")
    public boolean deleteCollect(@RequestBody UserInfo userInfo) {
        if (Admin.equals(getRole()) || userInfo.getUserId().equals(getUserId())) {
            return deleteService.deleteCollectById(userInfo);
        }
        return false;
    }

    /**
     * 删除点赞
     */
    @DeleteMapping(value = "/Praise")
    public boolean deletePraise(@RequestBody UserInfo userInfo) {
        if (Admin.equals(getRole()) || userInfo.getUserId().equals(getUserId())) {

            return deleteService.deletePraiseById(userInfo);
        }
        return false;
    }

    /**
     * 添加用户信息
     */
    @PostMapping(value = "/")
    public boolean saveUser(@RequestBody Userinformation userInformation) {
        return createService.createUserInformation(userInformation) != null;
    }

}
