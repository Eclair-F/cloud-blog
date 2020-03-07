package com.qw.provideruser.services;

import com.qw.provideruser.dao.RetrieveDao;
import com.qw.provideruser.dao.UpdateDao;
import com.qw.provideruser.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Eclair
 */
@Service
public class UpdateService {
    private final UpdateDao updateDao;
    private final RetrieveDao retrieveDao;

    private final BCryptPasswordEncoder encoder;

    public UpdateService(UpdateDao updateDao, RetrieveDao retrieveDao, BCryptPasswordEncoder encoder) {
        this.updateDao = updateDao;
        this.retrieveDao = retrieveDao;
        this.encoder = encoder;
    }

    /**
     * 修改昵称
     */
    public boolean update(User user) {
        if (retrieveDao.findById(user.getId(), false) != null) {
            if (user.getNickname() != null) {
                updateDao.updateByNickname(user.getId(), user.getNickname());
            }
            if (user.getPassword() != null) {
                updateDao.updateByPassword(user.getId(), encoder.encode(user.getPassword()));
            }
            if (user.getType() != null) {
                updateDao.updateByType(user.getId(), user.getType());
            }
            return true;
        } else {
            return false;
        }
    }
    /**
     * @Author Eclair
     * @Description //上传图片
     * @Date 2020/3/6 17:10
     * @Param @param image:
     * @param path:
     * @param imageName:
     * @return boolean
     **/
    public boolean updateImage(MultipartFile image, String path,String imageName){

        if (!image.isEmpty()) {
               BufferedOutputStream out = null;
               try {
                   File folder = new File(path);
                   if (!folder.exists()) {
                       folder.mkdirs();
                   }
                   out = new BufferedOutputStream(new FileOutputStream(path + imageName));
                   // 写入新文件
                   out.write(image.getBytes());
                   out.flush();
                   out.close();
               } catch (Exception e) {
                   e.printStackTrace();
               }
            return true;
           }

       return false;
   }

    /**
     * 修改昵称
     */
    public boolean updateByNickname(String id, String nickname) {
        return updateDao.updateByNickname(id, nickname).getModifiedCount() != 0;
    }

    /**
     * 修改昵称
     */
    public boolean updateByAvatar(String id, String avatar) {
        return updateDao.updateByAvatar(id, avatar).getModifiedCount() != 0;
    }

    /**
     * 修改密码
     */
    public boolean updateByPassword(String id, String password) {

        return updateDao.updateByPassword(id, encoder.encode(password)).getModifiedCount() != 0;
    }

    /**
     * 修改用户类型
     */
    public boolean updateByType(String id, String type) {
        return updateDao.updateByType(id, type).getModifiedCount() != 0;
    }

    /**
     * 修改用户类型
     */
    public boolean updateByRole(String id, String role) {
        return updateDao.updateByType(id, role).getModifiedCount() != 0;
    }

    /**
     * 用户找回
     */
    public boolean recoveryById(String id) {
        return updateDao.recoveryById(id).getModifiedCount() != 0;
    }

}
