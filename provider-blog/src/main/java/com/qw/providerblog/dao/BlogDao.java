package com.qw.providerblog.dao;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import org.bson.types.ObjectId;

/**
 * @Program: cloud-blog
 * @ClassName BlogDao
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:40
 * @Version 1.0
 **/
public interface BlogDao {
    /**
     * 创建博客
     * */
    Blog createBlog(Blog blog);

    /**
     * 查找所有博客
     */
    DeleteResult deleteById(String id) ;


    /**
     * 查找所有博客
     */
    PageResult<Blog> findAll(int size, String bool) ;

    /**
     * 分页 上一页
     * 查找所有博客
     */
    PageResult<Blog> up(int size, ObjectId id, String bool) ;

    /**
     * 分页 下一页
     * 查找所有博客
     */
    PageResult<Blog> down(int size, ObjectId id, String bool) ;

    /**
     * 根据id查找博客
     */
    Blog findById(String id);


    /**
     * 根据内容,题目查找博客
     */
    PageResult<Blog> findBySearch(int size, String search, String bool) ;

    /**
     * 根据内容,题目查找博客
     * 上一页
     */
    PageResult<Blog> upSearch(int size, String search, ObjectId id, String bool) ;

    /**
     * 根据内容,题目查找博客
     * 下一页
     */
    PageResult<Blog> downSearch(int size, String search, ObjectId id, String bool) ;


    /**
     * 根据类别查找博客
     */
    PageResult<Blog> findByCategory(int size, String category, String bool) ;
    /**
     * 根据类别查找博客
     * 上一页
     */
    PageResult<Blog> upCategory(int size, String category, ObjectId id, String bool);

    /**
     * 根据类别查找博客
     * 下一页
     */
    PageResult<Blog> downCategory(int size, String category, ObjectId id, String bool) ;
    /**
     * 修改博客题目
     */
    UpdateResult updateByTitle(String id, String title) ;


    /**
     * 修改博客内容
     */
    UpdateResult updateByContent(String id, String content);
    /**
     * 修改博客封面
     */
    UpdateResult updateByCover(String id, String cover) ;


    /**
     * 修改博客类别
     */
    UpdateResult updateByCategory(String id, String category) ;



    /**
     * 修改博客评论数
     */
    UpdateResult updateByCommentNum(String id, Integer commentNum) ;

    /**
     * 修改博客点赞
     */
    UpdateResult updateByPraise(String id, Integer praise);

    /**
     * 修改博客查看数量
     */
    UpdateResult updateByView(String id, Integer view) ;


    /**
     * 博客发布
     */
    UpdateResult draftToBlog(String id) ;
    /**
     * 存为草稿
     */
    UpdateResult blogToDraft(String id);
}
