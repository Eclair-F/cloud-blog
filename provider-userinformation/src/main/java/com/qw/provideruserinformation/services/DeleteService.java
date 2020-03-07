package com.qw.provideruserinformation.services;

import com.qw.provideruserinformation.dao.DeleteDao;
import com.qw.provideruserinformation.entity.Collect;
import com.qw.provideruserinformation.entity.Praise;
import com.qw.provideruserinformation.entity.UserInfo;
import org.springframework.stereotype.Service;

/**
 * @author Eclair
 */
@Service
public class DeleteService {

    private final DeleteDao deleteDao;

    public DeleteService(DeleteDao deleteDao) {

        this.deleteDao = deleteDao;
    }
    /**
     * 根据id删除用户
     */
    public boolean delete(String userId) {
        return deleteDao.delete(userId).getDeletedCount()!=0;
    }
    /**
     * 根据id删除用户
     */
    public boolean deleteById(String userId) {
        return deleteDao.deleteById(userId).getModifiedCount()!=0;
    }
    /**
     * 删除收藏
     * */
    public boolean deleteCollectById(UserInfo userinfo) {
        Collect collect=new Collect();
        collect.setBlogId(userinfo.getBlogId());
        return deleteDao.deleteCollectById(userinfo.getUserId(),collect).getModifiedCount()!=0;
    }
    /**
     * 删除点赞
     * */
    public boolean deletePraiseById(UserInfo userinfo) {
        Praise praise=new Praise();
        praise.setBlogId(userinfo.getBlogId());
        return deleteDao.deletePraiseById(userinfo.getUserId(),praise).getModifiedCount()!=0;
    }

}
