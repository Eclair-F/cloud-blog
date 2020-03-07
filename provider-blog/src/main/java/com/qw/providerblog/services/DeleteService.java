package com.qw.providerblog.services;

import com.qw.providerblog.dao.DeleteDao;
import org.springframework.stereotype.Service;

/**
 * @Program: cloud-blog
 * @ClassName DeleteService
 * @Description:
 * @Author: Eclair
 * @Create: 2020-01-14 15:29
 * @Version 1.0
 **/

@Service
public class DeleteService {

    private final DeleteDao deleteDao;

    public DeleteService(DeleteDao deleteDao) {

        this.deleteDao = deleteDao;
    }

    /**
     * 根据id删除博客
     */
    public boolean deleteById(String id) {
        return deleteDao.deleteById(id).getModifiedCount() != 0;
    }

}
