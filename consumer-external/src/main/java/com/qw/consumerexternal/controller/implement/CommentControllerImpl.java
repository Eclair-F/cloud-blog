package com.qw.consumerexternal.controller.implement;


import com.qw.consumerexternal.controller.CommentController;
import com.qw.consumerexternal.feign.CommentFeign;
import com.qw.consumerexternal.result.Result;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eclair
 */

@RestController
@RequestMapping(value = "/comment")
public class CommentControllerImpl implements CommentController {
    private final CommentFeign commentFeign;
    private final HttpServletRequest request;

    public CommentControllerImpl(CommentFeign commentFeign, HttpServletRequest request) {
        this.commentFeign = commentFeign;
        this.request = request;
    }


    private boolean Admin() {
        String admin = "admin";
        return admin.equals(request.getAttribute("role"));
    }

    /**
     * 查找所有留言
     */
    @Override


    @GetMapping(value = "/message")
    public Result message() {
        Object message = commentFeign.message();
        if (message != null) {
            return new Result(true, "查询成功", message);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 根据博客id查找评论
     */
    @Override
    @GetMapping(value = "/{blogId}")
    public Result findById(@PathVariable String blogId) {
        Object comment = commentFeign.findById(blogId);
        if (comment != null) {

            return new Result(true, "查询成功", comment);
        }
        return new Result(false, "查询失败");

    }


    /**
     * 添加评论
     */
    @Override
    @PostMapping(value = "")
    public Result createComment(@RequestBody Object comment) {

        boolean create = commentFeign.createComment(comment);
        if (create) {
            return new Result(true, "添加成功");
        }
        return new Result(false, "添加失败");
    }

    /**
     * 添加回复
     */
    @Override
    @PutMapping(value = "")
    public Result updateReply(@RequestBody Object comment) {
        Object update = commentFeign.updateReply(comment);
        if (update != null) {
            return new Result(true, "添加成功", update);
        }
        return new Result(false, "添加失败");
    }

    /**
     * 删除评论
     */
    @Override
    @DeleteMapping(value = "")
    public Result deleteComment(@RequestBody Object commentInfo) {
        boolean delete = commentFeign.deleteComment(commentInfo);
        if (delete) {
            return new Result(true, "删除成功");
        }
        return new Result(false, "删除失败");

    }

    /**
     * 删除全部评论
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public Result deleteAll(@PathVariable String id) {
        if (Admin()) {
            boolean delete = commentFeign.deleteAll(id);
            if (delete) {
                return new Result(true, "删除成功");
            }
        }
        return new Result(false, "删除失败");

    }

    /**
     * 删除回复
     */
    @Override
    @DeleteMapping(value = "/reply")
    public Result deleteReply(@RequestBody Object commentInfo) {
        boolean delete = commentFeign.deleteReply(commentInfo);
        if (delete) {
            return new Result(true, "删除成功");
        }
        return new Result(false, "删除失败");

    }

}
