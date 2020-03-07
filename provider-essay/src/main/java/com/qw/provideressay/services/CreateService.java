package com.qw.provideressay.services;


import com.qw.provideressay.dao.CreateDao;
import com.qw.provideressay.entity.Essay;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Eclair
 */
@Service
public class CreateService {
    private final CreateDao createDao;

    public CreateService(CreateDao createDao) {
        this.createDao = createDao;

    }

    /**
     * 添加随笔
     */
    public boolean createEssay(Essay essay) {

        essay.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return createDao.createEssay(essay) != null;
    }
}
