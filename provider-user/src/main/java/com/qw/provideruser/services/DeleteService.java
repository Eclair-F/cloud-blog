package com.qw.provideruser.services;

import com.qw.provideruser.dao.DeleteDao;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
     * 根据id删除用户
     */
    public boolean deleteById(String id) {
        return deleteDao.deleteById(id).getModifiedCount() != 0;
    }

    public boolean deleteImage(String path, String imageName) {

        File file = new File(path.concat(imageName));
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + imageName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + imageName + "失败！");
            }
        } else {
            System.out.println("删除单个文件失败：" + imageName + "不存在！");
        }
        return false;
    }
}

