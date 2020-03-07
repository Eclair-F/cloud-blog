package com.qw.providercomment.controller;


import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.CommentInfo;
import com.qw.providercomment.entity.PageResult;
import com.qw.providercomment.services.CreateService;
import com.qw.providercomment.services.DeleteService;
import com.qw.providercomment.services.RetrieveService;
import com.qw.providercomment.services.UpdateService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eclair
 */

@RestController
public class CommentController {
    private final CreateService createService;
    private final RetrieveService retrieveService;
    private final DeleteService deleteService;
    private final UpdateService updateService;
    private final HttpServletRequest request;
    private final String Admin = "admin";
    private final String User = "user";

    public CommentController(RetrieveService retrieveService, CreateService createService, DeleteService deleteService, UpdateService updateService, HttpServletRequest request) {
        this.retrieveService = retrieveService;
        this.createService = createService;
        this.deleteService = deleteService;
        this.updateService = updateService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }

    private String getId() {
        return (String) request.getAttribute("id");
    }

    private PageResult<Comment> commentPageResultTool(PageResult<Comment> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 查找所有评论
     */
    @GetMapping(value = "/")
    public PageResult<Comment> comment() {
        return commentPageResultTool(retrieveService.find());
    }


    /**
     * 根据博客id查找评论
     */
    @GetMapping(value = "/{blogId}")
    public PageResult<Comment> findById(@PathVariable String blogId) {
        return commentPageResultTool(retrieveService.findByBlogId(blogId));
    }


    /**
     * 添加评论
     */
    @PostMapping(value = "/")
    public boolean createComment(@RequestBody Comment comment) {
        if (Admin.equals(getRole()) || comment.getUserId().equals(getId())) {
            return createService.createComment(comment);
        }
        return false;
    }

    /**
     * 添加回复
     */
    @PutMapping(value = "/")
    public boolean updateReply(@RequestBody Comment comment) {
        if (Admin.equals(getRole()) || comment.getReplies().get(0).getUserId().equals(getId())) {
            return updateService.updateReply(comment);
        }
        return false;
    }

    /**
     * 删除评论
     */
    @DeleteMapping(value = "/")
    public boolean deleteComment(@RequestBody CommentInfo commentInfo) {

        if (Admin.equals(getRole()) || commentInfo.getUserId().equals(getId())) {
            return deleteService.deleteById(commentInfo.getId());
        }

        return false;
    }

    /**
     * 删除回复
     */
    @DeleteMapping(value = "/reply")
    public boolean deleteReply(@RequestBody CommentInfo commentInfo) {
        if (Admin.equals(getRole()) || commentInfo.getUserId().equals(getId())) {
            return deleteService.deleteById(commentInfo);
        }
        return false;
    }


}
