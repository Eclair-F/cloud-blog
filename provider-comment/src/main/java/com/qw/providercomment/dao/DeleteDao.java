package com.qw.providercomment.dao;


import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.Reply;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteDao {
    private final MongoTemplate mongoTemplate;

    public DeleteDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * 删除评论
     * */
    public DeleteResult deleteById(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, Comment.class);
    }
    /**
     * 删除回复
     * */
    public UpdateResult deleteById(String id, Reply reply) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.pull("replies",reply);
        return mongoTemplate.updateFirst(query,update, Comment.class);
    }

}
