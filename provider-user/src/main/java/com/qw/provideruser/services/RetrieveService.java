package com.qw.provideruser.services;

import com.qw.provideruser.dao.RetrieveDao;
import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import org.bson.types.ObjectId;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * @author Eclair
 */
@Service
public class RetrieveService {
    private final RetrieveDao retrieveDao;
    private final BCryptPasswordEncoder encoder;

    public RetrieveService(RetrieveDao retrieveDao, BCryptPasswordEncoder encoder) {
        this.retrieveDao = retrieveDao;
        this.encoder = encoder;
    }

    /**
     *查找所有用户
     */
    public PageResult<User> findAllUser(Boolean bool) {
        return retrieveDao.findAllUser(bool);
    }   /**
     *查找所有用户
     */
    public PageResult<User> findAdmin() {
        return retrieveDao.findAdmin();
    }
    /**
     * 分页
     *查找所有用户 下一页
     */
    public PageResult<User> findAllUser(ObjectId id,Boolean bool) {
        return retrieveDao.findAllUser(id,bool);
    }


    /**
     * 根据id查找用户
     */
    public User findById(String id,Boolean bool) {
        return retrieveDao.findById(id,bool);
    }

    /**
     * 读取图片
     * @param path
     * @param response
     * @return
     */
    public  HttpServletResponse findImage(String path , HttpServletResponse response) {

        try {
            System.out.println(path);
            FileInputStream hFile;
            hFile = new FileInputStream(path);
            //得到文件大小
            int i = hFile.available();
            byte[] data = new byte[i];
            //读数据
            hFile.read(data);
            response.setContentType("image/jpeg");
            OutputStream toClient = response.getOutputStream();
            //得到向客户端输出二进制数据的对象
            //输出数据
            toClient.write(data);
            toClient.flush();
            toClient.close();
            hFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return response;
    }
    /**
     * 根据username查找用户存不存在
     * */
    public boolean findByUsernameExists(String username) {
        return retrieveDao.findByUsername(username) != null;
    }
    /**
     * 根据username查找用户
     * */
    public User findByUsername(String username,Boolean bool) {
        User byUsername = retrieveDao.findByUsername(username,bool);
        byUsername.setPassword(null);
        return byUsername;
    }


    /**
     * 根据密码比对
     * */
    public boolean findByPassword(String id,String password) {
        User pass = retrieveDao.findById(id);
        return pass != null && encoder.matches(password, pass.getPassword());
    }



    /**
     * login
     * */
    public boolean login(User user,Boolean bool) {
        User login = retrieveDao.findByUsername(user.getUsername(),bool);
        return login != null && encoder.matches(user.getPassword(), login.getPassword());
    }

}
