package com.qw.provideruser.dao;

import com.qw.provideruser.entity.User;
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
    * 创建用户
    * */
    public User createUser(User user) {
        return mongoTemplate.insert(user);
    }

}
