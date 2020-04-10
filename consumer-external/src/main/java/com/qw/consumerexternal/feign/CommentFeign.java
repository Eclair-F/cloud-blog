package com.qw.consumerexternal.feign;


import com.qw.consumerexternal.feign.callback.CommentFeignCallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/**
 * @author Eclair
 */
@Service
@FeignClient(name = "provider-comment" , fallback= CommentFeignCallback.class)
public interface CommentFeign {


    /**
     * 查找所有评论
     */
    @GetMapping(value = "/")
    Object comment();


    /**
     * 根据博客id查找评论
     */
    @GetMapping(value = "/{blogId}")
    Object findById(@PathVariable(value = "blogId") String blogId);


    /**
     * 添加评论
     */
    @PostMapping(value = "/")
    boolean createComment(@RequestBody Object comment);

    /**
     * 添加回复
     */
    @PutMapping(value = "/")
    Object updateReply(@RequestBody Object comment);

    /**
     * 删除评论
     */
    @DeleteMapping(value = "/")
    boolean deleteComment(@RequestBody Object commentInfo);

    /**
     * 删除全部评论
     */
    @DeleteMapping(value = "/{id}")
    boolean deleteAll(@PathVariable(value = "id") String id);

    /**
     * 删除回复
     */
    @DeleteMapping(value = "/reply")
    boolean deleteReply(@RequestBody Object commentInfo);


    /**
     * 查找所有留言
     */

    @GetMapping(value = "/message")
    Object message();


}
