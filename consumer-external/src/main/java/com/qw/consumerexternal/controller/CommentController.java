package com.qw.consumerexternal.controller;


import com.qw.consumerexternal.feign.CommentFeign;
import com.qw.consumerexternal.result.Result;
import com.qw.consumerexternal.result.StatusCode;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eclair
 */

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    private final CommentFeign commentFeign;

    public CommentController(CommentFeign commentFeign) {
        this.commentFeign = commentFeign;
    }


    /**
     * 查找所有评论
     */
    @GetMapping(value = "")
    public Result comment() {
        Object comment = commentFeign.comment();
        if (comment == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", comment);
        }

    }


    /**
     * 根据博客id查找评论
     */
    @GetMapping(value = "/{blogId}")
    public Result findById(@PathVariable String blogId) {
        Object comment = commentFeign.findById(blogId);
        if (comment == null) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "查询失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "查询成功", comment);
        }
    }


    /**
     * 添加评论
     */
    @PostMapping(value = "")
    public Result createComment(@RequestBody Object comment) {
        boolean create = commentFeign.createComment(comment);
        if (create) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "添加失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "添加成功");
        }
    }

    /**
     * 添加回复
     */
    @PutMapping(value = "")
    public Result updateReply(@RequestBody Object comment) {
        boolean update = commentFeign.updateReply(comment);
        if (update) {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "添加失败");
        } else {
            return new Result(true, StatusCode.SUCCESS, "添加成功");
        }
    }

    /**
     * 删除评论
     */
    @DeleteMapping(value = "/{id}")
    public Result deleteComment(@PathVariable String id) {
        boolean delete = commentFeign.deleteComment(id);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "删除失败");
        }
    }

    /**
     * 删除回复
     */
    @DeleteMapping(value = "")
    public Result deleteReply(@RequestBody Object commentInfo) {
        boolean delete = commentFeign.deleteReply(commentInfo);
        if (delete) {
            return new Result(true, StatusCode.SUCCESS, "删除成功");
        } else {
            return new Result(false, StatusCode.DATA_NOT_FOUND, "删除失败");
        }
    }

}
