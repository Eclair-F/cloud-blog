package com.qw.provideruserinformation.services;


import com.qw.provideruserinformation.dao.RetrieveDao;
import com.qw.provideruserinformation.dao.UpdateDao;
import com.qw.provideruserinformation.entity.Collect;
import com.qw.provideruserinformation.entity.Praise;
import com.qw.provideruserinformation.entity.UserInfo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Eclair
 */
@Service
public class UpdateService {
    private final UpdateDao updateDao;
    private final RetrieveDao retrieveDao;


    public UpdateService(UpdateDao updateDao, RetrieveDao retrieveDao) {
        this.updateDao = updateDao;
        this.retrieveDao = retrieveDao;

    }

    /**
     * 修改点赞
     */
    public boolean updateByPraise(UserInfo userInfo) {
        if(retrieveDao.findByPraise(userInfo.getUserId(),userInfo.getBlogId()))
        {
            return true;
        }
        Praise praise=new Praise();
        praise.setBlogId(userInfo.getBlogId());
        praise.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return updateDao.updateByPraise(userInfo.getUserId(), praise).getModifiedCount() != 0;
    }

    /**
     * 修改收藏
     */
    public boolean updateByCollect(UserInfo userInfo) {
        if(retrieveDao.findByCollect(userInfo.getUserId(),userInfo.getBlogId()))
        {
            return true;
        }
        Collect collect = new Collect();
        collect.setBlogId(userInfo.getBlogId());
        collect.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return updateDao.updateByCollect(userInfo.getUserId(), collect).getModifiedCount() != 0;
    }

}
