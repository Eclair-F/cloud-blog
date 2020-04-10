package com.qw.providerblog.controller;

import com.qw.providerblog.entity.Blog;
import com.qw.providerblog.entity.PageResult;
import org.bson.types.ObjectId;

/**
 * @Program: cloud-blog
 * @ClassName DraftController
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-14 14:06
 * @Version 1.0
 **/

public interface DraftController {


    /**
     * 查找所有草稿
     */
    PageResult<Blog> draft(int size);

    /**
     * 查找所有草稿
     */
    PageResult<Blog> upDraft(int size, ObjectId id);

    /**
     * 查找所有草稿
     */
    PageResult<Blog> downDraft(int size, ObjectId id);

    /**
     * 题目
     * 根据search查找草稿
     */
    PageResult<Blog> findDraftSearch(int size, String search);


    /**
     * 题目
     * 根据search查找草稿
     */
    PageResult<Blog> upDraftSearch(int size, String search, ObjectId id);

    /**
     * 题目
     * 根据search查找草稿
     */
    PageResult<Blog> downDraftSearch(int size, String search, ObjectId id);


    /**
     * 题目
     * 根据类别查找草稿
     */
    PageResult<Blog> findDraftCategory(int size, String category);


    /**
     * 题目
     * 根据类别查找草稿
     */
    PageResult<Blog> upDraftCategory(int size, String category, ObjectId id);

    /**
     * 题目
     * 根据类别查找草稿
     */
    PageResult<Blog> downDraftCategory(int size, String category, ObjectId id);

    /**
     * 添加草稿
     */
    boolean saveDraft(Blog draft);

    /**
     * 草稿发布
     */
    boolean draftToBlog(String id);

}