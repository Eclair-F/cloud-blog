package com.qw.providercomment.controller;


import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.CommentInfo;
import com.qw.providercomment.entity.PageResult;

/**
 * @author Eclair
 */

public interface CommentController {

    /**
     * 查找所有留言
     */

    PageResult<Comment> message();



    /**
     * 根据博客id查找评论
     */
    PageResult<Comment> findById(String blogId);


    /**
     * 添加评论
     */
    boolean createComment(Comment comment);

    /**
     * 添加回复
     */
    boolean updateReply(Comment comment);

    /**
     * 删除评论
     */
    boolean deleteComment(CommentInfo commentInfo);

    /**
     * 删除全部评论
     */
    boolean deleteAll(String id);

    /**
     * 删除回复
     */
    boolean deleteReply(CommentInfo commentInfo);


}
