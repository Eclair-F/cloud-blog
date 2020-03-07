package com.qw.provideruser.services;

import com.qw.provideruser.dao.CreateDao;
import com.qw.provideruser.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Eclair
 */
@Service
public class CreateService {
    private final CreateDao createDao;

    private final BCryptPasswordEncoder encoder;

    public CreateService(CreateDao createDao, BCryptPasswordEncoder encoder) {
        this.createDao = createDao;
        this.encoder = encoder;
    }

    /**
     * 添加用户
     *
     */
    public User createUser(User user) {
        user.setAvatar("Avatar.jpg");
        user.setRole("user");
        //密码加密
        user.setPassword(encoder.encode(user.getPassword()));
        //设置时间
        user.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        return createDao.createUser(user);

    }

    /**
     * 添加用户
     *
     */
    public boolean createImage(String oldPath, String path, String imageName) {

        try {
            System.out.println(oldPath);
            System.out.println(path);
            FileInputStream hFile = new FileInputStream(oldPath+imageName);
            //得到文件大小
            int i = hFile.available();
            byte[] data = new byte[i];
            //读数据
            hFile.read(data);
            BufferedOutputStream out;
            File folder = new File(path);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            out = new BufferedOutputStream(new FileOutputStream(path + imageName));
            // 写入新文件
            out.write(data);
            out.flush();
            out.close();
            hFile.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
}