package com.qw.providercomment.controller.implement;

import com.qw.providercomment.controller.CommentController;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.CommentInfo;
import com.qw.providercomment.entity.PageResult;
import com.qw.providercomment.services.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: cloud-blog
 * @ClassName CommentControllerImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:11
 * @Version 1.0
 **/
@RestController
public class CommentControllerImpl implements CommentController {
    private final CommentService commentService;
    private final HttpServletRequest request;
    private final String Admin = "admin";

    public CommentControllerImpl(CommentService commentService, HttpServletRequest request) {
        this.commentService = commentService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }

    private String getId() {
        return (String) request.getAttribute("id");
    }



    /**
     * 查找所有留言
     */

    @Override
    @GetMapping(value = "/message")
    public  PageResult<Comment> message() {
        return commentService.findAllMessage();
    }


    /**
     * 根据博客id查找评论
     */
    @Override
    @GetMapping(value = "/{blogId}")
    public PageResult<Comment> findById(@PathVariable String blogId) {
        return commentService.findByBlogId(blogId);
    }


    /**
     * 添加评论
     */
    @Override
    @PostMapping(value = "/")
    public boolean createComment(@RequestBody Comment comment) {
        if (Admin.equals(getRole()) || comment.getUserId().equals(getId())) {
            return commentService.createComment(comment);
        }
        return false;
    }

    /**
     * 添加回复
     */
    @Override
    @PutMapping(value = "/")
    public boolean updateReply(@RequestBody Comment comment) {
        if (Admin.equals(getRole()) || comment.getReplies().get(0).getFromId().equals(getId())) {
                return commentService.updateReply(comment);
        }
        return false;
    }

    /**
     * 删除评论
     */
    @Override
    @DeleteMapping(value = "/")
    public boolean deleteComment(@RequestBody CommentInfo commentInfo) {
        if (Admin.equals(getRole()) || commentInfo.getUserId().equals(getId())) {
            return commentService.deleteById(commentInfo.getId());
        }

        return false;
    }

    /**
     * 删除全部评论
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public boolean deleteAll(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return commentService.deleteAll(id);
        }
        return false;
    }

    /**
     * 删除回复
     */
    @Override
    @DeleteMapping(value = "/reply")
    public boolean deleteReply(@RequestBody CommentInfo commentInfo) {
        if (Admin.equals(getRole()) || commentInfo.getUserId().equals(getId())) {
            return commentService.deleteById(commentInfo);
        }
        return false;
    }

}
