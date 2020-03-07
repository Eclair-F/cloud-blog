package com.qw.consumerexternal.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eclair
 */
@FeignClient(name = "provider-userInformation")
public interface UserInfoFeign {

    /**
     * 查找所有用户信息
     */
    @GetMapping(value = "/")
    Object userInfo();

    /**
     * 根据userid查找用户
     */
    @GetMapping(value = "/{userId}")
    Object findByUserId(@PathVariable(value = "userId") String userId);

    /**
     * 添加收藏
     */
    @PutMapping(value = "/Collect")
    boolean updateCollect(@RequestBody Object userInfo);


    /**
     * 添加点赞
     *
     */
    @PutMapping(value = "/Praise")
    boolean updatePraise(@RequestBody Object userInfo);


    /**
     * 清空信息
     */
    @DeleteMapping(value = "/{userId}")
    boolean deleteUserInfo(@PathVariable(value = "userId") String userId);

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/delete/{userId}")
    boolean deleteUser(@PathVariable(value = "userId") String userId);

    /**
     * 删除收藏
     */
    @DeleteMapping(value = "/Collect")
    boolean deleteCollect(@RequestBody Object userInfo);

    /**
     * 删除点赞
     */
    @DeleteMapping(value = "/Praise")
    boolean deletePraise(@RequestBody Object userInfo);

    /**
     * 添加用户信息
     */
    @PostMapping(value = "/")
    boolean saveUser(@RequestBody Object userInformation);

}
