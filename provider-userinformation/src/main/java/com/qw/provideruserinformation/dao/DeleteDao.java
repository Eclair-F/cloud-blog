package com.qw.provideruserinformation.dao;


import com.mongodb.client.result.DeleteResult;
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
public class DeleteDao {
    private final MongoTemplate mongoTemplate;

    public DeleteDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    /**
     * 用户注销信息
     **/
    public DeleteResult delete(String userId) {
        Query query=new Query(Criteria.where("userId").is(userId));
        return mongoTemplate.remove(query, Userinformation.class);
    }
    /**
     * 用户注销信息
     * */
    public UpdateResult deleteById(String userId) {
        Query query=new Query(Criteria.where("userId").is(userId));
        Update update=new Update();
        update.unset("collect");
        update.unset("praise");
        return mongoTemplate.updateFirst(query, update, Userinformation.class);
       }
    /**
     * 删除收藏
     * */
    public UpdateResult deleteCollectById(String userId, Collect collect) {
        Query query=new Query(Criteria.where("userId").is(userId));
        Update update=new Update();
        update.pull("collect",collect);
        return mongoTemplate.updateFirst(query,update, Userinformation.class);
    }
    /**
     * 删除点赞
     * */
    public UpdateResult deletePraiseById(String userId, Praise praise) {
        Query query=new Query(Criteria.where("userId").is(userId));
        Update update=new Update();
        update.pull("praise",praise);
        return mongoTemplate.updateFirst(query,update, Userinformation.class);
    }


}
