package com.qw.provideressay.services;

import com.qw.provideressay.dao.DeleteDao;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {

    private final DeleteDao deleteDao;

    public DeleteService(DeleteDao deleteDao) {

        this.deleteDao = deleteDao;
    }

    /**
     * 根据id删除随笔
     */
    public boolean deleteById(String id) {
        return deleteDao.deleteById(id).getDeletedCount()!= 0;
    }

}
