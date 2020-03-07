package com.qw.consumerexternal.controller;

import com.qw.consumerexternal.feign.CommentFeign;
import com.qw.consumerexternal.result.Result;
import com.qw.consumerexternal.result.StatusCode;
import org.springframework.web.bind.annotation.*;

/**
 * @Program: cloud-blog
 * @ClassName MessageController
 * @Description:
 * @Author: Eclair
 * @Create: 2019-12-31 14:46
 * @Version 1.0
 **/
@RestController
@RequestMapping(value = "/message")
public class MessageController {
private final CommentFeign messageFeign;

    public MessageController(CommentFeign messageFeign) {
        this.messageFeign = messageFeign;
    }


    /**
     * 查找所有留言
     */

    @GetMapping(value = "/message")
    public Result message() {
        Object message= messageFeign.message();
        if (message == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", message);
        }
    }

    /**
     * 添加留言
     **/
    @PostMapping(value = "/message")
    public Result saveMessage(@RequestBody Object comment) {
        boolean delete =messageFeign.saveMessage(comment);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "删除失败");
        }
    }


    /**
     * 删除留言
     **/
    @DeleteMapping(value = "/message/{id}")
    public Result deleteMessage(@PathVariable String id) {
        boolean delete = messageFeign.deleteMessage(id);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "删除失败");
        }
    }

}
