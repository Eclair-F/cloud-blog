package com.qw.providerblog.dao;

import com.mongodb.client.result.UpdateResult;
import com.qw.providerblog.entity.Blog;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Program: cloud-blog
 * @ClassName UpdateDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:26
 * @Version 1.0
 **/

@Repository
public class UpdateDao {
    private final MongoTemplate mongoTemplate;

    public UpdateDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 修改博客题目
     */
    public UpdateResult updateByTitle(String id, String title) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("title", title);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }

    /**
     * 修改博客内容
     */
    public UpdateResult updateByContent(String id, String content) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("content", content);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }

    /**
     * 修改博客类别
     */
    public UpdateResult updateByCategory(String id, String category) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("category", category);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }



    /**
     * 修改博客收藏
     */
    public UpdateResult updateByCollect(String id, Integer collect) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("collect", collect);
        return mongoTemplate.updateFirst(query, update, Blog.class);

    }

    /**
     * 修改博客点赞
     */
    public UpdateResult updateByPraise(String id,Integer praise) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("praise",praise);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }

    /**
     * 修改博客查看数量
     */
    public UpdateResult updateByView(String id,Integer view) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("view", view);
        return mongoTemplate.updateFirst(query, update, Blog.class);

    }

    /**
     * 博客找回
     */
    public UpdateResult recoveryById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset("delete");
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }

    /**
     * 博客发布
     */
    public UpdateResult Draft(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset("draft");
        update.set("date", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }
}
