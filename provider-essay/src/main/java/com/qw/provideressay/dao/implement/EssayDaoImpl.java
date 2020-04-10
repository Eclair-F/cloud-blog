package com.qw.provideressay.dao.implement;

import com.mongodb.client.result.DeleteResult;
import com.qw.provideressay.dao.EssayDao;
import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @Program: cloud-blog
 * @ClassName EssayDaoImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 23:29
 * @Version 1.0
 **/
@Repository
public class EssayDaoImpl implements EssayDao {
    private final MongoTemplate mongoTemplate;

    public EssayDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    /**
     * 创建
     */
    @Override
    public Essay createEssay(Essay essay) {
        return mongoTemplate.insert(essay);
    }


    /**
     * 删除随笔
     */
    @Override
    public DeleteResult deleteById(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, Essay.class);
    }


    /**
     * 查找所有随笔
     */
    @Override
    public PageResult<Essay> findAll() {
        Query query = new Query();
        long count = mongoTemplate.count(query, Essay.class);
        query.with(Sort.by(Sort.Order.desc("_id")));
        return new PageResult<>(count, mongoTemplate.find(query, Essay.class));
    }

}
