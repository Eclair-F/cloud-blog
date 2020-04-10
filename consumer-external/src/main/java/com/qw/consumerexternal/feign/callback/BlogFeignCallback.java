package com.qw.consumerexternal.feign.callback;

import com.qw.consumerexternal.feign.BlogFeign;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

/**
 * @Program: cloud-blog
 * @ClassName BlogFeignCallback
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-25 14:58
 * @Version 1.0
 **/
@Component
public class BlogFeignCallback implements BlogFeign {
    @Override
    public Object blog(int size) {
        return null;
    }

    @Override
    public Object upBlog(int size, ObjectId id) {
        return null;
    }

    @Override
    public Object downBlog(int size, ObjectId id) {
        return null;
    }

    @Override
    public Object findSearch(int size, String search) {
        return null;
    }

    @Override
    public Object upSearch(int size, String search, ObjectId id) {
        return null;
    }

    @Override
    public Object downSearch(int size, String search, ObjectId id) {
        return null;
    }

    @Override
    public Object findCategory(int size, String category) {
        return null;
    }

    @Override
    public Object upCategory(int size, String category, ObjectId id) {
        return null;
    }

    @Override
    public Object downCategory(int size, String category, ObjectId id) {
        return null;
    }

    @Override
    public Object findById(String id) {
        return null;
    }

    @Override
    public boolean saveBlog(Object blog) {
        return false;
    }

    @Override
    public boolean blogToDraft(String id) {
        return false;
    }

    @Override
    public boolean update(Object blog) {
        return false;
    }

    @Override
    public boolean updateBlog(Object blog) {
        return false;
    }

    @Override
    public boolean deleteBlog(String id) {
        return false;
    }

    @Override
    public Object draft(int size) {
        return null;
    }

    @Override
    public Object upDraft(int size, ObjectId id) {
        return null;
    }

    @Override
    public Object downDraft(int size, ObjectId id) {
        return null;
    }

    @Override
    public Object findDraftSearch(int size, String search) {
        return null;
    }

    @Override
    public Object upDraftSearch(int size, String search, ObjectId id) {
        return null;
    }

    @Override
    public Object downDraftSearch(int size, String search, ObjectId id) {
        return null;
    }

    @Override
    public Object findDraftCategory(int size, String category) {
        return null;
    }

    @Override
    public Object upDraftCategory(int size, String category, ObjectId id) {
        return null;
    }

    @Override
    public Object downDraftCategory(int size, String category, ObjectId id) {
        return null;
    }

    @Override
    public boolean saveDraft(Object draft) {
        return false;
    }

    @Override
    public boolean draftToBlog(String id) {
        return false;
    }
}
