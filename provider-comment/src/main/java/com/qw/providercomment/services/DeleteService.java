package com.qw.providercomment.services;


import com.qw.providercomment.dao.DeleteDao;
import com.qw.providercomment.entity.CommentInfo;
import com.qw.providercomment.entity.Reply;
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
     * 根据id删除评论
     */
    public boolean deleteById(String id) {
        return deleteDao.deleteById(id).getDeletedCount() != 0;
    }
    /**
     * 根据id删除评论
     */
    public boolean deleteById(CommentInfo commentInfo) {
        Reply reply=new Reply();
        reply.setId(commentInfo.getReplyId());
        return deleteDao.deleteById(commentInfo.getId(),reply).getModifiedCount() != 0;
    }

}
