package com.qw.providercomment.dao;

import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.PageResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eclair
 */
@Repository
public class RetrieveDao {
    private final MongoTemplate mongoTemplate;

    public RetrieveDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private PageResult<Comment> getCommentPageResult(Query query, long count) {
        query.with(Sort.by(Sort.Order.desc("_id")));
        return new PageResult<>(count, mongoTemplate.find(query, Comment.class));
    }
    /**
     * 查找全部评论
     */
    public PageResult<Comment> findAll() {
        Query query = new Query();
        long count = mongoTemplate.count(query, Comment.class);
        return getCommentPageResult(query, count);
    }

    /**
     * 根据博客id查找评论
     */
    public PageResult<Comment> findByBlogId(String blogId) {
        Query query = Query.query(Criteria.where("blogId").is(blogId));
        long count = mongoTemplate.count(query, Comment.class);
        return getCommentPageResult(query, count);
    }

    /**
     * 根据id查找评论
     */
    public Comment findById(String id) {
        Query query = Query.query(Criteria.where("id").is(id));
        return mongoTemplate.findOne(query,Comment.class);
    }
    /**
     * 查找留言
     */
    public PageResult<Comment> findMessage() {
        Query query = Query.query(Criteria.where("blogId").exists(false));
        long count = mongoTemplate.count(query, Comment.class);
        return getCommentPageResult(query, count);
    }


}
