package com.qw.providercomment.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.PageResult;
import com.qw.providercomment.entity.Reply;

import java.util.List;

/**
 * @Program: cloud-blog
 * @ClassName CommentDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:11
 * @Version 1.0
 **/
public interface CommentDao {

    /**
     * 新增评论
     * */
    Comment createComment(Comment comment) ;
    /**
     * 删除评论
     * */
    DeleteResult deleteById(String id);
    /**
     * 删除评论
     **/
    List<Comment> deleteAll(String blogId) ;
    /**
     * 删除回复
     * */
    UpdateResult deleteById(String id, Reply reply) ;

    /**
     * 根据博客id查找评论
     */
    PageResult<Comment> findByBlogId(String blogId) ;

    /**
     * 查找留言
     */
    PageResult<Comment> findMessage() ;

    /**
     *
     */
    UpdateResult updateReply(String id, Reply reply);
}
