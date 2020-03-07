package com.qw.providerblog.dao;

import com.mongodb.client.result.UpdateResult;
import com.qw.providerblog.entity.Blog;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @Program: cloud-blog
 * @ClassName CreateDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:22
 * @Version 1.0
 **/

@Repository
public class CreateDao {
    private final MongoTemplate mongoTemplate;

    public CreateDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 创建博客
     * */
    public Blog createBlog(Blog blog) {
        return mongoTemplate.insert(blog);
    }
    /**
     * 博客发布
     */
    public UpdateResult createDraft(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("draft","true");
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }

}
