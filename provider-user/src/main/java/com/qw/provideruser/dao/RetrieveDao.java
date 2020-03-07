package com.qw.provideruser.dao;

import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import org.bson.types.ObjectId;
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


    private PageResult<User> getUserPageResult(Query query, long count) {
        query.fields().exclude("password");
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(20);
        return new PageResult<>(count, mongoTemplate.find(query, User.class));
    }
    /**
     * 查找所有用户
     */
    public PageResult<User> findAllUser(Boolean bool) {
        Query query = Query.query(Criteria.where("delete").exists(bool));
        query.addCriteria(Criteria.where("role").is("user"));
        long count = mongoTemplate.count(query, User.class);
        query.fields().exclude("password");
        query.with(Sort.by(Sort.Order.desc("_id")));
        return new PageResult<>(count, mongoTemplate.find(query, User.class));
    }
    /**
     * 查找所有用户
     */
    public PageResult<User> findAdmin() {
        Query query = Query.query(Criteria.where("role").is("admin"));
        long count = mongoTemplate.count(query, User.class);
        return getUserPageResult(query, count);
    }
    /**
     * 分页 下一页
     * 查找所有用户
     */
    public PageResult<User> findAllUser(ObjectId id,Boolean bool) {
        Query query = Query.query(Criteria.where("delete").exists(bool));
        query.addCriteria(Criteria.where("role").is("user"));
        long count = mongoTemplate.count(query, User.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return getUserPageResult(query, count);
    }


    /**
     * 根据id查找用户
     */
    public User findById(String id,Boolean bool) {
        Query query = Query.query(Criteria.where("delete").exists(bool));
        query.fields().exclude("password");
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 根据id查找用户
     */
    public User findById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 根据username查找用户是否存在
     */
    public User findByUsername(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 根据username查找用户
     */
    public User findByUsername(String username,Boolean bool) {
        Query query = Query.query(Criteria.where("delete").exists(bool));
        query.addCriteria(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }



    /**
     * 根据类型查找用户
     */
    public PageResult<User> findByType(String type,Boolean bool) {
        Query query = Query.query(Criteria.where("delete").exists(bool));
        query.addCriteria(Criteria.where("type").is(type));
        long count = mongoTemplate.count(query, User.class);
        return getUserPageResult(query, count);
    }
    /**
     * 根据类型查找用户
     * 下一页
     */
    public PageResult<User> findByType(String type,ObjectId id,Boolean bool) {
        Query query = Query.query(Criteria.where("delete").exists(bool));
        query.addCriteria(Criteria.where("type").is(type));
        long count = mongoTemplate.count(query, User.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return getUserPageResult(query, count);
    }
}
