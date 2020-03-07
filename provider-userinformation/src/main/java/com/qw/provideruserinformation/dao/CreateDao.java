package com.qw.provideruserinformation.dao;


import com.qw.provideruserinformation.entity.Userinformation;
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
    * 创建用户信息
    * */
    public Userinformation createUserInformation(Userinformation userInformation) {
        return mongoTemplate.insert(userInformation);
    }

}
