package com.qw.providercomment.controller;

import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.CommentInfo;
import com.qw.providercomment.services.CreateService;
import com.qw.providercomment.services.DeleteService;
import com.qw.providercomment.services.RetrieveService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Program: cloud-blog
 * @ClassName MessageController
 * @Description:
 * @Author: Eclair
 * @Create: 2019-12-31 14:46
 * @Version 1.0
 **/
@RestController
public class MessageController {
    private final CreateService createService;
    private final RetrieveService retrieveService;
    private final DeleteService deleteService;
    private final HttpServletRequest request;
    private final String Admin = "admin";
    private String Role;
    private final String User = "user";

    public MessageController(RetrieveService retrieveService, CreateService createService, DeleteService deleteService, HttpServletRequest request) {
        this.retrieveService = retrieveService;
        this.createService = createService;
        this.deleteService = deleteService;
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

    @GetMapping(value = "/message")
    public Object message() {
        return retrieveService.findAllMessage();
    }

    /**
     * 添加留言
     */
    @PostMapping(value = "/message")
    public boolean saveMessage(@RequestBody Comment comment) {
        if (Admin.equals(getRole()) || comment.getUserId().equals(getId())) {
            return createService.createComment(comment);
        }
        return false;
    }


    /**
     * 删除留言
     */
    @DeleteMapping(value = "/message/")
    public boolean deleteMessage(@RequestBody CommentInfo commentInfo) {
        if (Admin.equals(getRole()) || commentInfo.getUserId().equals(getId())) {
            return deleteService.deleteById(commentInfo.getId());
        }
        return false;
    }

}
