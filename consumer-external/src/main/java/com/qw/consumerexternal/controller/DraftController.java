package com.qw.consumerexternal.controller;

import com.qw.consumerexternal.result.Result;
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
    Result draft(int size);

    /**
     * 查找所有草稿
     */
    Result upDraft(int size, ObjectId id);

    /**
     * 查找所有草稿
     */
    Result downDraft(int size, ObjectId id);


    /**
     * 题目
     * 根据search查找草稿
     */
    Result findDraftSearch(int size, String search);


    /**
     * 题目
     * 根据search查找草稿
     */
    Result upDraftSearch(int size, String search,
                         ObjectId id);

    /**
     * 题目
     * 根据search查找草稿
     */
    Result downDraftSearch(int size, String search,
                           ObjectId id);

    /**
     * 题目
     * 根据类别查找草稿
     */
    Result findDraftCategory(int size, String category);


    /**
     * 题目
     * 根据类别查找草稿
     */
    Result upDraftCategory(int size,
                           String category, ObjectId id);

    /**
     * 题目
     * 根据类别查找草稿
     */
    Result downDraftCategory(int size,
                             String category,
                             ObjectId id);

    /**
     * 添加草稿
     */
    Result saveBlog(Object draft);


    /**
     * 草稿发布
     */
    Result draftToBlog(String id);


}