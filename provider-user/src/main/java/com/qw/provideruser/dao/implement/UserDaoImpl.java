package com.qw.provideruser.dao.implement;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.qw.provideruser.dao.UserDao;
import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Program: cloud-blog
 * @ClassName UserDaoImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 21:24
 * @Version 1.0
 **/
@Repository
public class UserDaoImpl implements UserDao {

    private final MongoTemplate mongoTemplate;

    public UserDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 创建用户
     * */
    @Override
    public User createUser(User user) {
        return mongoTemplate.insert(user);
    }
    /**
     * 用户注销
     **/
    @Override
    public DeleteResult deleteById(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, User.class);
    }




    private PageResult<User> getPageResult(Query query, long count, int size) {
        query.fields().exclude("password");
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(size);
        return new PageResult<>(count, size, mongoTemplate.find(query, User.class));
    }

    private PageResult<User> upPageResult(Query query, long count, int size) {
        query.fields().exclude("password");
        query.with(Sort.by(Sort.Order.asc("_id")));
        query.limit(size);
        List<User> users = mongoTemplate.find(query, User.class);
        Collections.reverse(users);
        return new PageResult<>(count, size, users);
    }

    private PageResult<User> downPageResult(Query query, long count, int size) {
        query.fields().exclude("password");
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(size);
        return new PageResult<>(count, size, mongoTemplate.find(query, User.class));
    }

    /**
     * 查找所有用户
     */
    @Override
    public PageResult<User> findAll(int size, String role) {
        Query query = Query.query(Criteria.where("role").is(role));
        long count = mongoTemplate.count(query, User.class);
        return getPageResult(query, count, size);
    }

    /**
     * 分页 上一页
     * 查找所有用户
     */
    @Override
    public PageResult<User> up(ObjectId id, int size, String role) {
        Query query = Query.query(Criteria.where("role").is(role));
        long count = mongoTemplate.count(query, User.class);
        query.addCriteria(Criteria.where("_id").gt(id));
        return upPageResult(query, count, size);
    }

    /**
     * 分页 下一页
     * 查找所有用户
     */
    @Override
    public PageResult<User> down(ObjectId id, int size, String role) {
        Query query = Query.query(Criteria.where("role").is(role));
        long count = mongoTemplate.count(query, User.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return downPageResult(query, count, size);
    }

    /**
     * 根据id查找用户
     */
    @Override
    public User findById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 根据username查找用户
     */
    @Override
    public User findByUsername(String username) {
        Query query = Query.query(Criteria.where("username").is(username));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * 根据username查找用户
     */
    @Override
    public PageResult<User> findUsername(String username, int size) {
        Query query = Query.query(Criteria.where("username").regex(Pattern.compile("^.*" + username + ".*$",
                Pattern.CASE_INSENSITIVE)));
        long count = mongoTemplate.count(query, User.class);
        return getPageResult(query, count, size);
    }

    /**
     * 根据username查找用户
     * 上一页
     */
    @Override
    public PageResult<User> findUpUsername(String username, int size, ObjectId id) {
        Query query = Query.query(Criteria.where("username").regex(Pattern.compile("^.*" + username + ".*$",
                Pattern.CASE_INSENSITIVE)));
        long count = mongoTemplate.count(query, User.class);
        query.addCriteria(Criteria.where("_id").gt(id));
        return upPageResult(query, count, size);
    }

    /**
     * 根据username查找用户
     * 下一页
     */
    @Override
    public PageResult<User> findDownUsername(String username, int size, ObjectId id) {
        Query query = Query.query(Criteria.where("username").regex(Pattern.compile("^.*" + username + ".*$",
                Pattern.CASE_INSENSITIVE)));
        long count = mongoTemplate.count(query, User.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return downPageResult(query, count, size);
    }


    /**
     * 修改昵称
     * */
    @Override
    public UpdateResult updateByNickname(String id, String nickname) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("nickname", nickname);
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /**
     * 修改性别
     * */
    @Override
    public UpdateResult updateBySex(String id, String sex) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("sex", sex);
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /**
     * 修改邮箱
     * */
    @Override
    public UpdateResult updateByEmail(String id, String email) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("email", email);
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /**
     * 修改电话
     * */
    @Override
    public UpdateResult updateByMobile(String id, String mobile) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("mobile", mobile);
        return mongoTemplate.updateFirst(query, update, User.class);
    }
    /**
     * 修改头像
     * */
    @Override
    public UpdateResult updateByAvatar(String id, String avatar) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("avatar",avatar);
        return mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 修改密码
     * */
    @Override
    public UpdateResult updateByPassword(String id, String password) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("password", password);
        return mongoTemplate.updateFirst(query, update, User.class);
    }

    /**
     * 修改用户权限
     * */
    @Override
    public UpdateResult updateByRole(String id, String role) {
        Query query=new Query(Criteria.where("_id").is(id));
        Update update=new Update();
        update.set("role", role);
        return mongoTemplate.updateFirst(query, update, User.class);
    }


}
