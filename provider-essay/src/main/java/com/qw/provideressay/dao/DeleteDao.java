package com.qw.provideressay.dao;


import com.mongodb.client.result.DeleteResult;
import com.qw.provideressay.entity.Essay;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
     * 删除随笔
     *
     * */
    public DeleteResult deleteById(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, Essay.class);
    }


}
