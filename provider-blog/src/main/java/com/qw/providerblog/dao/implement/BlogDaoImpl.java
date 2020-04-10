package com.qw.providerblog.dao.implement;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.qw.providerblog.dao.BlogDao;
import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Program: cloud-blog
 * @ClassName BlogDaoImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:42
 * @Version 1.0
 **/
@Repository
public class BlogDaoImpl implements BlogDao {
    private final MongoTemplate mongoTemplate;

    public BlogDaoImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private PageResult<Blog> getPageResult(Query query, long count, int size) {
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(size);
        return new PageResult<>(count, size, mongoTemplate.find(query, Blog.class));
    }

    private PageResult<Blog> upPageResult(Query query, long count, int size) {
        query.with(Sort.by(Sort.Order.asc("_id")));
        query.limit(size);
        List<Blog> blog = mongoTemplate.find(query, Blog.class);
        Collections.reverse(blog);
        return new PageResult<>(count, size, blog);
    }

    private PageResult<Blog> downPageResult(Query query, long count, int size) {
        query.with(Sort.by(Sort.Order.desc("_id")));
        query.limit(size);
        return new PageResult<>(count, size, mongoTemplate.find(query, Blog.class));
    }
    /**
     * 创建博客
     * */
    @Override
    public Blog createBlog(Blog blog) {
        return mongoTemplate.insert(blog);
    }

    @Override
    public DeleteResult deleteById(String id) {
        Query query=new Query(Criteria.where("_id").is(id));
        return mongoTemplate.remove(query, Blog.class);
    }


    /**
     * 查找所有博客
     */    @Override
    public PageResult<Blog> findAll(int size, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        long count = mongoTemplate.count(query, Blog.class);
        return getPageResult(query, count, size);
    }

    /**
     * 分页 上一页
     * 查找所有博客
     */    @Override
    public PageResult<Blog> up(int size, ObjectId id, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        long count = mongoTemplate.count(query, Blog.class);
        query.addCriteria(Criteria.where("_id").gt(id));
        return upPageResult(query, count, size);
    }

    /**
     * 分页 下一页
     * 查找所有博客
     */    @Override
    public PageResult<Blog> down(int size, ObjectId id, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        long count = mongoTemplate.count(query, Blog.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return downPageResult(query, count, size);
    }

    /**
     * 根据id查找博客
     */    @Override
    public Blog findById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Blog.class);
    }



    /**
     * 根据内容,题目查找博客
     */    @Override
    public PageResult<Blog> findBySearch(int size, String search, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("title").regex(Pattern.compile("^.*" + search + ".*$", Pattern.CASE_INSENSITIVE)),
                Criteria.where("content").regex(Pattern.compile("^" + ".*" + search + ".*$", Pattern.CASE_INSENSITIVE)));
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, Blog.class);
        return getPageResult(query, count, size);
    }

    /**
     * 根据内容,题目查找博客
     * 上一页
     */    @Override
    public PageResult<Blog> upSearch(int size, String search, ObjectId id, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("title").regex(Pattern.compile("^.*" + search + ".*$", Pattern.CASE_INSENSITIVE)),
                Criteria.where("content").regex(Pattern.compile("^" + ".*" + search + ".*$", Pattern.CASE_INSENSITIVE)));
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, Blog.class);
        query.addCriteria(Criteria.where("_id").gt(id));
        return upPageResult(query, count, size);
    }

    /**
     * 根据内容,题目查找博客
     * 下一页
     */    @Override
    public PageResult<Blog> downSearch(int size, String search, ObjectId id, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("title").regex(Pattern.compile("^.*" + search + ".*$", Pattern.CASE_INSENSITIVE)),
                Criteria.where("content").regex(Pattern.compile("^" + ".*" + search + ".*$", Pattern.CASE_INSENSITIVE)));
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, Blog.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return downPageResult(query, count, size);
    }


    /**
     * 根据类别查找博客
     */    @Override
    public PageResult<Blog> findByCategory(int size, String category, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        query.addCriteria(Criteria.where("category").is(category));
        long count = mongoTemplate.count(query, Blog.class);
        return getPageResult(query, count, size);

    }

    /**
     * 根据类别查找博客
     * 上一页
     */    @Override
    public PageResult<Blog> upCategory(int size, String category, ObjectId id, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        query.addCriteria(Criteria.where("category").is(category));
        long count = mongoTemplate.count(query, Blog.class);
        query.addCriteria(Criteria.where("_id").gt(id));
        return upPageResult(query, count, size);

    }

    /**
     * 根据类别查找博客
     * 下一页
     */    @Override
    public PageResult<Blog> downCategory(int size, String category, ObjectId id, String bool) {
        Query query = Query.query(Criteria.where("draft").is(bool));
        query.addCriteria(Criteria.where("category").is(category));
        long count = mongoTemplate.count(query, Blog.class);
        query.addCriteria(Criteria.where("_id").lt(id));
        return downPageResult(query, count, size);

    }
    /**
     * 修改博客题目
     */    @Override
    public UpdateResult updateByTitle(String id, String title) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("title", title);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }

    /**
     * 修改博客内容
     */    @Override
    public UpdateResult updateByContent(String id, String content) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("content", content);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }
    /**
     * 修改博客封面
     */    @Override
    public UpdateResult updateByCover(String id, String cover) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("cover", cover);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }


    /**
     * 修改博客类别
     */    @Override
    public UpdateResult updateByCategory(String id, String category) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("category", category);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }



    /**
     * 修改博客评论数
     */    @Override
    public UpdateResult updateByCommentNum(String id, Integer commentNum) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("commentNum",  commentNum);
        return mongoTemplate.updateFirst(query, update, Blog.class);

    }

    /**
     * 修改博客点赞
     */    @Override
    public UpdateResult updateByPraise(String id,Integer praise) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("praise",praise);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }

    /**
     * 修改博客查看数量
     */    @Override
    public UpdateResult updateByView(String id,Integer view) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("view", view);
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }


    /**
     * 博客发布
     */    @Override
    public UpdateResult draftToBlog(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("view",0);
        update.set("praise",0);
        update.set("commentNum",0);
        update.set("draft","false");
        update.set("date", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }
    /**
     * 存为草稿
     */    @Override
    public UpdateResult blogToDraft(String id) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.unset("view");
        update.unset("praise");
        update.unset("commentNum");
        update.set("date",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        update.set("draft","true");
        return mongoTemplate.updateFirst(query, update, Blog.class);
    }
}
