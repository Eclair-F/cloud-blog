package com.qw.providercomment.services;


import com.qw.providercomment.dao.RetrieveDao;
import com.qw.providercomment.dao.UpdateDao;
import com.qw.providercomment.entity.Comment;
import com.qw.providercomment.entity.Reply;
import org.bson.types.ObjectId;
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

    public boolean updateReply(Comment comment) {
        Reply reply = comment.getReplies().get(0);
        reply.setId(new ObjectId().toHexString());
        reply.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return updateDao.updateReply(comment.getId(), reply).getModifiedCount() != 0;
    }


}
