package com.qw.provideruserinformation.dao;

import com.mongodb.client.result.UpdateResult;

import com.qw.provideruserinformation.entity.Collect;
import com.qw.provideruserinformation.entity.Praise;
import com.qw.provideruserinformation.entity.Userinformation;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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
     * 修改收藏
     * */
    public UpdateResult updateByCollect(String userId, Collect collect) {
        Query query=new Query(Criteria.where("userId").is(userId));
        Update update=new Update();
        update.addToSet("collect", collect);
        return mongoTemplate.updateFirst(query, update, Userinformation.class);
    }

    /**
     * 修改点赞
     * */
    public UpdateResult updateByPraise(String userId, Praise praise) {
        Query query=new Query(Criteria.where("userId").is(userId));
        Update update=new Update();
        update.addToSet("praise", praise);
        return mongoTemplate.updateFirst(query, update, Userinformation.class);
    }

}
