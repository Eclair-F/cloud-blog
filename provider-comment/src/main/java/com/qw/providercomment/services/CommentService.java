package com.qw.providercomment.services;

import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.CommentInfo;
import com.qw.providercomment.entity.PageResult;

/**
 * @Program: cloud-blog
 * @ClassName CommentService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:16
 * @Version 1.0
 **/
public interface CommentService {
    /**
     * 添加评论
     */
    boolean createComment(Comment comment) ;

    /**
     * 根据id删除评论
     */
    boolean deleteById(String id);

    /**
     * 根据博客id删除评论
     */
    boolean deleteAll(String id);

    /**
     * 根据id删除回复
     */
    boolean deleteById(CommentInfo commentInfo);

    /**
     * 根据博客id查找评论
     */
    PageResult<Comment> findByBlogId(String blogId);

    /**
     * 查找全部留言
     **/
    PageResult<Comment> findAllMessage();
    /**
     *添加回复
     */
    boolean updateReply(Comment comment);
}
