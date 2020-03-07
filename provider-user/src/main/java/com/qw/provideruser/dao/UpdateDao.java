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
public class UpdateDao {
    private final MongoTemplate mongoTemplate;

    public UpdateDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 修改昵称
     * */
    public UpdateResult updateByNickname(String id, String nickname) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("nickname", nickname);
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /**
     * 修改头像
     * */
    public UpdateResult updateByAvatar(String id, String avatar) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("avatar",avatar);
        return mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 修改密码
     * */
    public UpdateResult updateByPassword(String id, String password) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("password", password);
        return mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 修改用户类型
     * */
    public UpdateResult updateByType(String id, String type) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("type", type);
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /**
     * 修改用户权限
     * */
    public UpdateResult updateByRole(String id, String role) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("role", role);
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /**
     * 用户找回
     * */
    public UpdateResult recoveryById(String username) {
        Query query=new Query(Criteria.where("username").is(username));
        Update update=new Update();
        update.unset("delete");
        return mongoTemplate.updateFirst(query, update, User.class);
    }


}
