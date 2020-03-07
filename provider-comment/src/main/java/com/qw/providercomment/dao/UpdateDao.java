package com.qw.providercomment.dao;


import com.mongodb.client.result.UpdateResult;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.Reply;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Eclair
 */
@Repository
public class UpdateDao {
    private final MongoTemplate mongoTemplate;

    public UpdateDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    /**
     *
     */
    public UpdateResult updateReply(String id, Reply reply) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.addToSet("replies", reply);
        return mongoTemplate.updateFirst(query, update, Comment.class);
    }


}
