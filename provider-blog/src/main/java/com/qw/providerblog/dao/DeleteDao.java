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
 * @ClassName DeleteDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:23
 * @Version 1.0
 **/

@Repository
public class DeleteDao {
    private final MongoTemplate mongoTemplate;

    public DeleteDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 删除博客
     * */
    public UpdateResult deleteById(String id) {
        return mongoTemplate.updateFirst(new Query(Criteria.where("_id").is(id)),new Update().set("delete","true"), Blog.class);
    }


}
