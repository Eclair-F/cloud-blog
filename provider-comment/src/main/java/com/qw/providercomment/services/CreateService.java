package com.qw.providercomment.services;


import com.qw.providercomment.dao.CreateDao;
import com.qw.providercomment.entity.Comment;
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
     * 添加评论
     */
    public boolean createComment(Comment comment) {
        comment.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return createDao.createComment(comment) != null;
    }
}
