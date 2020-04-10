package com.qw.consumerexternal.controller;

import com.qw.consumerexternal.result.Result;
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
    Result blog(int size) ;

    /**
     * 查找所有博客
     * 上一页
     */
    Result upBlog(int size, ObjectId id) ;

    /**
     * 查找所有博客
     * 下一页
     */
    Result downBlog(int size, ObjectId id) ;


    /**
     * 根据search查找博客
     */
    Result findSearch(int size, String search);

    /**
     * 上一页
     * 根据search查找博客
     */
    Result upSearch(int size, String search, ObjectId id) ;

    /**
     * 下一页
     * 根据search查找博客
     */
    Result downSearch(int size, String search, ObjectId id) ;


    /**
     * 根据类别查找博客
     */
    Result findCategory(int size, String category) ;


    /**
     * 上一页
     * 根据类别查找博客
     */
    Result upCategory(int size, String category, ObjectId id) ;

    /**
     * 下一页
     * 根据类别查找博客
     */
    Result downCategory(int size, String category, ObjectId id);


    /**
     * 根据id查找博客
     */
    Result findById(String id);
    /**
     * 添加博客
     */
    Result saveBlog(Object blog) ;
    /**
     * 存为草稿
     */
    Result blogToDraft(String id) ;
    /**
     * 修改博客
     */
    Result update(Object blog) ;

    /**
     * 修改博客信息
     */
    Result updateBlogCollect(Object blog) ;


    /**
     * 删除博客
     */
    Result deleteBlog(String id);


}