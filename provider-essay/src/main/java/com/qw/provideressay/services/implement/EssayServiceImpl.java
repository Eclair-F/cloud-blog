package com.qw.provideressay.services.implement;

import com.qw.provideressay.dao.EssayDao;
import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;
import com.qw.provideressay.services.EssayService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Program: cloud-blog
 * @ClassName EssayServiceImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 23:31
 * @Version 1.0
 **/
@Service
public class EssayServiceImpl implements EssayService {
    private final EssayDao essayDao;

    public EssayServiceImpl(EssayDao essayDao) {
        this.essayDao = essayDao;
    }

    private PageResult<Essay> essayPageResult(PageResult<Essay> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 添加随笔
     */
    @Override
    public boolean createEssay(Essay essay) {
        essay.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return essayDao.createEssay(essay) != null;
    }

    /**
     * 根据id删除随笔
     */
    @Override
    public boolean deleteById(String id) {
        return essayDao.deleteById(id).getDeletedCount() != 0;
    }


    /**
     * 查找所有随笔
     */
    @Override
    public PageResult<Essay> findAllEssay() {
        return essayPageResult(essayDao.findAll());
    }

}
