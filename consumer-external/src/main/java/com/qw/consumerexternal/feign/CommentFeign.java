package com.qw.consumerexternal.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eclair
 */



@FeignClient(name = "provider-comment")
public interface CommentFeign {



    /**
     * 查找所有评论
     */
    @GetMapping(value = "/")
    Object  comment();


    /**
     * 根据博客id查找评论
     */
    @GetMapping(value = "/{blogId}")
    Object findById(@PathVariable(value = "blogId") String blogId) ;


    /**
     * 添加评论
     */
    @PostMapping(value = "/")
    boolean createComment(@RequestBody Object comment);

    /**
     * 添加回复
     */
    @PutMapping(value = "/")
    boolean updateReply(@RequestBody Object comment);

    /**
     * 删除评论
     */
    @DeleteMapping(value = "/{id}")
    boolean deleteComment(@PathVariable(value = "id") String id);

    /**
     * 删除回复
     */
    @DeleteMapping(value = "/")
    boolean deleteReply(@RequestBody Object commentInfo);


    /**
     * 查找所有留言
     */

    @GetMapping(value = "/message")
    Object message();

    /**
     * 添加留言
     */
    @PostMapping(value = "/message")
    boolean saveMessage(@RequestBody Object comment) ;


    /**
     * 删除留言
     */
    @DeleteMapping(value = "/message/{id}")
    boolean deleteMessage(@PathVariable(value = "id") String id);

}
