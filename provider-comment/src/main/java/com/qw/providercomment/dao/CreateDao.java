package com.qw.providercomment.dao;


import com.qw.providercomment.entity.Comment;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author Eclair
 */
@Repository
public class CreateDao {
    private final MongoTemplate mongoTemplate;

    public CreateDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
    * 新增评论
    * */
    public Comment createComment(Comment comment) {
        return mongoTemplate.insert(comment);
    }

}
