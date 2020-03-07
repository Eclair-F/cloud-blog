package com.qw.providerblog.dao;

import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.regex.Pattern;

/**
 * @Program: cloud-blog
 * @ClassName RetrieveDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:25
 * @Version 1.0
 **/
@Repository
public class RetrieveDao {
    private final MongoTemplate mongoTemplate;

    public RetrieveDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private PageResult<Blog> getBlogPageResult(Query query, long count) {
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(20);
        List<Blog> users = mongoTemplate.find(query, Blog.class);
        return new PageResult<>(count, users);
    }
    private Query getBlogBoolean(boolean bool) {
        Query query = Query.query(Criteria.where("delete").exists(bool));
        query.addCriteria(Criteria.where("draft").exists(false));
        return query;
    }
    /**
     * 查找所有博客
     */
    public PageResult<Blog> findAllBlog(String id,boolean bool) {
        Query query = getBlogBoolean(bool);
        long count = mongoTemplate.count(query, Blog.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return getBlogPageResult(query, count);

    }
    /**
     * 查找所有博客
     */
    public PageResult<Blog> findAllBlog(boolean bool) {
        Query query = getBlogBoolean(bool);
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);

    }
    /**
     * 根据id查找博客
     */
    public Blog findById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Blog.class);
    }

    /**
     * 根据题目查找博客
     */
    public PageResult<Blog> findByTitle(String title,boolean bool) {
        Query query = getBlogBoolean(bool);
        query.addCriteria(Criteria.where("title").regex(Pattern.compile("^.*" + title + ".*$",
                Pattern.CASE_INSENSITIVE)));
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);

    }

    /**
     * 根据内容查找博客
     */
    public PageResult<Blog> findByContent(String content,boolean bool) {
        Query query = getBlogBoolean(bool);
        query.addCriteria(Criteria.where("content").regex(Pattern.compile("^.*" + content + ".*$",
                Pattern.CASE_INSENSITIVE)));
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);

    }

    /**
     * 根据内容,题目查找博客
     */
    public PageResult<Blog> findByText(String text,boolean bool) {
        Query query = getBlogBoolean(bool);
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("title").regex(Pattern.compile("^.*" + text + ".*$", Pattern.CASE_INSENSITIVE)),
                Criteria.where("content").regex(Pattern.compile("^" + ".*" + text + ".*$", Pattern.CASE_INSENSITIVE)));
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);

    }

    /**
     * 根据类别查找博客
     */
    public PageResult<Blog> findByCategory(String category,boolean bool) {
        Query query = getBlogBoolean(bool);
        query.addCriteria(Criteria.where("category").is(category));
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);

    }

    /**
     * 根据时间查找博客
     * 小于等于
     */
    public PageResult<Blog>findByLTEDate(String date,boolean bool) {
        Query query = getBlogBoolean(bool);
        query.addCriteria(Criteria.where("date").lte(date));
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);

    }

    /**
     * 根据时间查找博客
     * 大于等于
     */
    public PageResult<Blog>findByGTEDate(String date,boolean bool) {
        Query query = getBlogBoolean(bool);
        query.addCriteria(Criteria.where("date").gte(date));
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);

    }

    /**
     * 根据时间查找博客
     * 时间区间查询
     */
    public PageResult<Blog> findByLGDate(String gtedate, String ltedate,boolean bool) {
        Query query = getBlogBoolean(bool);
        query.addCriteria(Criteria.where("date").gte(gtedate).lte(ltedate));
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);
    }
    /**
     * 查找草稿
     */
    public PageResult<Blog> findDraft()  {
        Query query = Query.query(Criteria.where("draft").exists(true));
        long count = mongoTemplate.count(query, Blog.class);
        return getBlogPageResult(query, count);
    }


}
