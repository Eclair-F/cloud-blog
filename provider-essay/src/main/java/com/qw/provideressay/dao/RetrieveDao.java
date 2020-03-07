package com.qw.provideressay.dao;

import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Eclair
 */
@Repository
public class RetrieveDao {
    private final MongoTemplate mongoTemplate;

    public RetrieveDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private PageResult<Essay> getUserPageResult(Query query, long count) {
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(20);
        return new PageResult<>(count, mongoTemplate.find(query, Essay.class));
    }


    /**
     * 查找所有随笔
     */
    public PageResult<Essay> findAllEssay() {
        Query query = new Query();
        long count = mongoTemplate.count(query, Essay.class);
        return getUserPageResult(query, count);
    }


    /**
     * 根据时间查找随笔
     * 大于等于
     */
    public PageResult<Essay> findByDate(String date) {
        Query query = Query.query(Criteria.where("date").gte(date));
        long count = mongoTemplate.count(query, Essay.class);
        return getUserPageResult(query, count);
    }

    /**
     * 根据时间查找随笔
     * 时间区间查询
     */
    public PageResult<Essay> findByDate(String gtedate, String ltedate) {
        Query query = Query.query(Criteria.where("date").gte(gtedate).lte(ltedate));
        long count = mongoTemplate.count(query, Essay.class);
        return getUserPageResult(query, count);
    }

}
