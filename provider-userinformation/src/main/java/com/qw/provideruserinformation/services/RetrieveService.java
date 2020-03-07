package com.qw.provideruserinformation.services;


import com.qw.provideruserinformation.dao.RetrieveDao;
import com.qw.provideruserinformation.entity.Collect;
import com.qw.provideruserinformation.entity.PageResult;
import com.qw.provideruserinformation.entity.Userinformation;
import org.springframework.stereotype.Service;

/**
 * @author Eclair
 */
@Service
public class RetrieveService {
    private final RetrieveDao retrieveDao;
    public RetrieveService(RetrieveDao retrieveDao) {
        this.retrieveDao = retrieveDao;

    }

    /**
     *查找所有用户
     */
    public PageResult<Userinformation> findAllUserInfo() {
        return retrieveDao.findAllUserInfo();
    }

    /**
     * 根据userid查找用户存不存在
     * */
    public boolean findByUserIDExists(String userId){
        return retrieveDao.findByUserID(userId) != null;
    }

    /**
     * 根据userId查找用户信息
     **/
    public Userinformation findByUserID(String userId) {
        return  retrieveDao.findByUserID(userId);
    }



}
