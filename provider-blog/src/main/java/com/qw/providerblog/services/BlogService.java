package com.qw.providerblog.services;

import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import org.bson.types.ObjectId;

/**
 * @Program: cloud-blog
 * @ClassName BlogService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-28 14:47
 * @Version 1.0
 **/
public interface BlogService {

    /**
     * 添加博客
     */
    boolean createBlog(Blog blog);

    /**
     * 添加草稿
     */
    boolean createDraft(Blog blog);


    /**
     * 根据id删除博客
     */
    boolean deleteById(String id);

    /**
     * 查找所有博客
     */
    PageResult<Blog> findAllBlog(int size, String bool);

    /**
     * 查找所有博客
     */
    PageResult<Blog> upBlog(int size, ObjectId id, String bool);

    /**
     * 查找所有博客
     */
    PageResult<Blog> downBlog(int size, ObjectId id, String bool);

    /**
     * 根据id查找博客
     */
    Blog findById(String id);


    /**
     * 根据内容查找博客
     */
    PageResult<Blog> findSearch(int size, String search, String bool);

    /**
     * 根据内容查找博客
     * 上一页
     */
    PageResult<Blog> upSearch(int size, String search, ObjectId id, String bool);

    /**
     * 根据内容查找博客
     * 下一页
     */
    PageResult<Blog> downSearch(int size, String search, ObjectId id, String bool);

    /**
     * 根据类别查找博客
     */
    PageResult<Blog> findCategory(int size, String category, String bool);

    /**
     * 根据类别查找博客
     * 上一页
     */
    PageResult<Blog> upCategory(int size, String category, ObjectId id, String bool);

    /**
     * 根据类别查找博客
     * 下一页
     */
    PageResult<Blog> downCategory(int size, String category, ObjectId id, String bool);

    /**
     * 修改博客
     */
    boolean updateBlog(Blog blog);

    /**
     * 修改博客信息
     */
    boolean updateBlogInfo(Blog blog);

    /**
     * 博客发布
     **/
    boolean draftToBlog(String id);

    /**
     * 存为草稿
     **/
    boolean blogToDraft(String id);
}
