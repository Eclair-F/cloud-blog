package com.qw.provideruserinformation.dao;

import com.qw.provideruserinformation.entity.Collect;
import com.qw.provideruserinformation.entity.PageResult;
import com.qw.provideruserinformation.entity.Praise;
import com.qw.provideruserinformation.entity.Userinformation;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


/**
 * @author Eclair
 */
@Repository
public class RetrieveDao {
    private final MongoTemplate mongoTemplate;

    public RetrieveDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    private PageResult<Userinformation> getUserPageResult(Query query, long count) {
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(20);
        return new PageResult<>(count, mongoTemplate.find(query, Userinformation.class));
    }

    /**
     * 查找所有用户
     */
    public PageResult<Userinformation> findAllUserInfo() {
        Query query = new Query();
        long count = mongoTemplate.count(query, Userinformation.class);
        return getUserPageResult(query, count);
    }



    /**
     * 根据userID查找用户
     **/
    public Userinformation findByUserID(String userId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, Userinformation.class);
    }

    /**
     * 根据userID查找用户收藏
     **/
    public boolean findByCollect(String userId, String blogId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("collect.blogId").is(blogId));
        return mongoTemplate.findOne(query, Userinformation.class)!=null;
    }
    /**
     * 根据userID查找用户点赞
     **/
    public boolean findByPraise(String userId, String blogId) {
        Query query = Query.query(Criteria.where("userId").is(userId));
        query.addCriteria(Criteria.where("praise.blogId").is(blogId));
        return mongoTemplate.findOne(query, Userinformation.class)!=null;
    }
}
