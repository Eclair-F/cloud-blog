package com.qw.providerblog.controller;

import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import org.bson.types.ObjectId;

/**
 * @Program: cloud-blog
 * @ClassName BlogController
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:30
 * @Version 1.0
 **/


public interface BlogController {


    /**
     * 查找所有博客
     */

    PageResult<Blog> blog(int size);

    /**
     * 查找所有博客
     * 上一页
     */

    PageResult<Blog> upBlog(int size, ObjectId id);

    /**
     * 查找所有博客
     * 下一页
     */

    PageResult<Blog> downBlog(int size, ObjectId id);



    /**
     * 根据search查找博客
     */

    PageResult<Blog> findSearch(int size, String search);

    /**
     * 上一页
     * 根据search查找博客
     */

    PageResult<Blog> upSearch(int size, String search, ObjectId id);

    /**
     * 下一页
     * 根据search查找博客
     */

    PageResult<Blog> downSearch(int size, String search, ObjectId id);


    /**
     * 根据类别查找博客
     */

    PageResult<Blog> findCategory(int size, String category);

    /**
     * 上一页
     * 根据类别查找博客
     */

    PageResult<Blog> upCategory(int size, String category, ObjectId id);

    /**
     * 下一页
     * 根据类别查找博客
     */

    PageResult<Blog> downCategory(int size, String category, ObjectId id);


    /**
     * 根据id查找博客
     */

    Blog findById(String id);

    /**
     * 添加博客
     */

    boolean saveBlog(Blog blog);

    /**
     * 存为草稿
     **/

    boolean blogToDraft(String id);

    /**
     * 修改博客
     */

    boolean update(Blog blog);

    /**
     * 修改博客信息
     */
    boolean updateBlogCollect(Blog blog);


    /**
     * 删除博客
     */
    boolean deleteBlog(String id);


}
