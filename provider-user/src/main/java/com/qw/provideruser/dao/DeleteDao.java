package com.qw.provideruser.dao;

import com.mongodb.client.result.UpdateResult;
import com.qw.provideruser.entity.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author Eclair
 */
@Repository
public class DeleteDao {
    private final MongoTemplate mongoTemplate;

    public DeleteDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 用户注销
     * */
    public UpdateResult deleteById(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("delete","true");
        return mongoTemplate.updateFirst(query, update, User.class);
       }


}
