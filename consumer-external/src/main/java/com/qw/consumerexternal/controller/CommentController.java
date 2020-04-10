package com.qw.consumerexternal.controller;


import com.qw.consumerexternal.result.Result;

/**
 * @author Eclair
 */


public interface CommentController {

    /**
     * 查找所有留言
     */

    Result message();


    /**
     * 根据博客id查找评论
     */
    Result findById(String blogId);


    /**
     * 添加评论
     */
    Result createComment(Object comment);

    /**
     * 添加回复
     */
    Result updateReply(Object comment);

    /**
     * 删除评论
     */
    Result deleteComment(Object commentInfo);

    /**
     * 删除全部评论
     */
    Result deleteAll(String id);

    /**
     * 删除回复
     */
    Result deleteReply(Object commentInfo);
}
