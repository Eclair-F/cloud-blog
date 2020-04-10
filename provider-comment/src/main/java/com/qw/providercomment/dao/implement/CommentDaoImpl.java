package com.qw.providercomment.dao.implement;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.qw.providercomment.dao.CommentDao;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.PageResult;
import com.qw.providercomment.entity.Reply;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Program: cloud-blog
 * @ClassName CommentDaoImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:12
 * @Version 1.0
 **/
@Repository
public class CommentDaoImpl implements CommentDao {
    private final MongoTemplate mongoTemplate;

    public CommentDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private PageResult<Comment> getCommentPageResult(Query query, long count) {
        query.with(Sort.by(Sort.Order.desc("_id")));
        return new PageResult<>(count, mongoTemplate.find(query, Comment.class));
    }

    /**
     * 新增评论
     */
    @Override
    public Comment createComment(Comment comment) {
        return mongoTemplate.insert(comment);
    }

    /**
     * 删除评论
     */
    @Override
    public DeleteResult deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, Comment.class);
    }

    /**
     * 删除评论
     */
    @Override
    public List<Comment> deleteAll(String blogId) {
        Query query = new Query(Criteria.where("blogId").is(blogId));
        return mongoTemplate.findAllAndRemove(query, Comment.class);
    }

    /**
     * 删除回复
     */
    @Override
    public UpdateResult deleteById(String id, Reply reply) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.pull("replies", reply);
        return mongoTemplate.updateFirst(query, update, Comment.class);
    }


    /**
     * 根据博客id查找评论
     */
    @Override
    public PageResult<Comment> findByBlogId(String blogId) {
        Query query = Query.query(Criteria.where("blogId").is(blogId));
        long count = mongoTemplate.count(query, Comment.class);
        return getCommentPageResult(query, count);
    }


    /**
     * 查找留言
     */
    @Override
    public PageResult<Comment> findMessage() {
        Query query = Query.query(Criteria.where("blogId").exists(false));
        long count = mongoTemplate.count(query, Comment.class);
        return getCommentPageResult(query, count);
    }

    /**
     *添加回复
     */
    @Override
    public UpdateResult updateReply(String id, Reply reply) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.addToSet("replies", reply);
        return mongoTemplate.updateFirst(query, update, Comment.class);
    }

}
