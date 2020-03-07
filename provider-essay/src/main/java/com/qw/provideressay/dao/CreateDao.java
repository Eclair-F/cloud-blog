package com.qw.provideressay.dao;


import com.qw.provideressay.entity.Essay;
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
    * 创建
    * */
    public Essay createEssay(Essay essay) {
        return mongoTemplate.insert(essay);
    }

}
