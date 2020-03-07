package com.qw.provideressay.services;


import com.qw.provideressay.dao.RetrieveDao;
import com.qw.provideressay.entity.Essay;
import com.qw.provideressay.entity.PageResult;
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
     * 日期格式化
     */
    private String dateFormatter(String date) {
        String temp = "0000-01-01 00:00:00";
        String year = "^\\d*$";
        String yearMonth = "^\\d{4}-\\d*$";
        String yearMonthDay = "^\\d{4}-\\d{2}-\\d*$";
        if ((date.length() > 9) && date.matches(yearMonthDay)) {
            date = date.substring(0, 10);
        } else if ((date.length() > 6) && date.matches(yearMonth)) {
            date = date.substring(0, 7);
        } else if ((date.length() > 3) && date.matches(year)) {
            date = date.substring(0, 4);
        }
        return date + temp.substring(date.length());


    }

    /**
     * 查找所有随笔
     */
    public PageResult<Essay> findAllEssay() {
        return retrieveDao.findAllEssay();
    }


    /**
     * 根据时间查找随笔
     * 大于等于
     */
    public PageResult<Essay> findByDate(String gteDate) {

        return retrieveDao.findByDate(dateFormatter(gteDate));
    }

    /**
     * 根据时间查找随笔
     * 时间区间查询
     */
    public PageResult<Essay> findByDate(String gteDate, String lteDate) {

        return retrieveDao.findByDate(dateFormatter(gteDate), dateFormatter(lteDate));
    }


}
