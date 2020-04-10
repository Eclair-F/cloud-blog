package com.qw.provideruser.services.implement;

import com.qw.provideruser.dao.RedisDao;
import com.qw.provideruser.dao.UserDao;
import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import com.qw.provideruser.services.UserService;
import com.qw.provideruser.token.JwtUtil;
import lombok.SneakyThrows;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Base64.Encoder;

import static com.qw.provideruser.services.SendMail.sendMail;

/**
 * @Program: cloud-blog
 * @ClassName UserServiceImpl
 * @Description:
 * @Author: Eclair
 * @Create: 2020-03-27 21:31
 * @Version 1.0
 **/
@Service
public class UserServiceImpl implements UserService {
    private final RedisDao redisDao;
    private final UserDao userDao;

    private final BCryptPasswordEncoder encoder;
    private final String defaultAvatar = "data:image/jpeg;base64,";

    private final String admin = "admin";
    private final String user = "user";


    /**
     * 头像保存路径
     */
    @Value("${WINDOWS.BLOG.PATH}")
    public String WINDOWS_BLOG_PATH;
    @Value("${LINUX.BLOG.PATH}")
    public String LINUX_BLOG_PATH;

    public UserServiceImpl(RedisDao redisDao, UserDao userDao, BCryptPasswordEncoder encoder) {
        this.redisDao = redisDao;
        this.userDao = userDao;
        this.encoder = encoder;
    }


    private String getOSPath() {
        // 根据Windows和Linux配置不同的头像保存路径
        return System.getProperty("os.name").toLowerCase().startsWith("win") ? WINDOWS_BLOG_PATH
                : LINUX_BLOG_PATH;
    }

    private PageResult<User> userTool(PageResult<User> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 添加用户
     */
    @Override
    public boolean createUser(User user) {
        if (userDao.findByUsername(user.getUsername()) == null) {
            //设置用户默认头像
            user.setAvatar(defaultAvatar + imageToBase64());
            //设置用户权限
            user.setRole("user");
            //密码加密
            user.setPassword(encoder.encode(user.getPassword()));
            //设置注册时间
            user.setDate(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
            if (user.getEmail() == null) {
                return userDao.createUser(user) != null;
            }
            //发送验证邮件
            return sendMail(user.getEmail(), redisDao.set(user));
        }
        return false;

    }

    /**
     * 激活用户
     */
    @Override
    public boolean active(String uuid) {
        User user = redisDao.get(uuid);
        if (user != null) {
            if (user.getId() != null) {
                return userDao.updateByEmail(user.getId(), user.getEmail()).getModifiedCount() != 0;
            }
            if (userDao.findByUsername(user.getUsername()) == null) {
                return userDao.createUser(user) != null;
            }
        }
        return false;
    }


    /**
     * 读取图片
     */
    public String imageToBase64() {

        byte[] data = null;
        // 读取图片字节数组
        try {
            InputStream in = new FileInputStream(getOSPath());
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        Encoder encoder = Base64.getEncoder();
        // 返回Base64编码过的字节数组字符串
        return encoder.encodeToString(data);
    }


    /**
     * 根据id删除用户
     */
    @Override
    public boolean deleteById(String id) {
        return userDao.deleteById(id).getDeletedCount() != 0;
    }


    /**
     * 查找所有用户
     */
    @Override
    public PageResult<User> findAllUser(int size) {
        return userTool(userDao.findAll(size, user));
    }

    /**
     * 分页
     * 查找所有用户 上一页
     */
    @Override
    public PageResult<User> upUser(ObjectId id, int size) {
        return userTool(userDao.up(id, size, user));
    }

    /**
     * 分页
     * 查找所有用户 下一页
     */
    @Override
    public PageResult<User> downAllUser(ObjectId id, int size) {
        return userTool(userDao.down(id, size, user));
    }

    /**
     * 查找所有管理员
     */
    @Override
    public PageResult<User> admin(int size) {
        return userTool(userDao.findAll(size, admin));
    }

    /**
     * 分页
     * 查找所有管理员
     * 上一页
     */
    @Override
    public PageResult<User> upAdmin(ObjectId id, int size) {
        return userTool(userDao.up(id, size, admin));
    }

    /**
     * 分页
     * 查找所有管理员
     * 下一页
     */
    @Override
    public PageResult<User> downAdmin(ObjectId id, int size) {
        return userTool(userDao.down(id, size, admin));
    }


    /**
     * 根据id查找用户
     */
    @Override
    public User findById(String id) {
        User user = userDao.findById(id);
        user.setPassword(null);
        return user;
    }


    /**
     * 根据username查找用户存不存在
     */
    @Override
    public boolean findByUsernameExists(String username) {
        return userDao.findByUsername(username) != null;
    }


    /**
     * 根据username查找用户

     */
    @Override
    public PageResult<User> findUsername(String username, int size) {
        return userDao.findUsername(username, size);
    }

    /**
     * 根据username查找用户

     */
    @Override
    public PageResult<User> findUpUsername(String username, int size, ObjectId id) {

        return userDao.findUpUsername(username, size, id);
    }

    /**
     * 根据username查找用户

     */
    @Override
    public PageResult<User> findDownUsername(String username, int size, ObjectId id) {

        return userDao.findDownUsername(username, size, id);
    }

    /**
     * 根据密码比对
     */
    @Override
    public boolean findByPassword(String id, String password) {
        User pass = userDao.findById(id);
        return pass != null && encoder.matches(password, pass.getPassword());
    }

    /**
     * 根据邮箱比对
     */
    @Override
    public boolean findByEmail(String id, String email) {
        User user = userDao.findById(id);
        return user != null && user.getEmail().equals(email);
    }

    /**
     * login
     */
    @Override
    public User login(User user) {
        User login = userDao.findByUsername(user.getUsername());
        if (login != null && encoder.matches(user.getPassword(), login.getPassword())) {
            login.setToken(JwtUtil.jwtCreateToken(login));
            login.setPassword(null);
            return login;
        }
        return null;
    }

    /**
     * 修改昵称,电话，性别
     */
    @Override
    public boolean update(User user) {
        if (userDao.findById(user.getId()) != null) {
            //昵称
            if (user.getNickname() != null) {
                userDao.updateByNickname(user.getId(), user.getNickname());
            }
            //性别
            if (user.getSex() != null) {
                userDao.updateBySex(user.getId(), user.getSex());
            }
            //电话
            if (user.getMobile() != null) {
                userDao.updateByMobile(user.getId(), user.getMobile());
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * 修改邮箱
     */
    @Override
    @SneakyThrows
    public boolean updateEmail(User user) {
        if (userDao.findById(user.getId()) != null) {
            String uuid = redisDao.set(user);
            sendMail(user.getEmail(), uuid);
            return true;
        }
        return false;

    }

    /**
     * 修改邮箱
     */
    @Override
    @SneakyThrows
    public boolean updateEmailByAdmin(User user) {
        return userDao.updateByEmail(user.getId(), user.getEmail()).getModifiedCount() != 0;

    }

    /**
     * 修改头像
     */
    @Override
    public boolean updateByAvatar(String id, String avatar) {
        return userDao.updateByAvatar(id, avatar).getModifiedCount() != 0;
    }

    /**
     * 修改密码
     */
    @Override
    public boolean updateByPassword(String id, String password) {
        return userDao.updateByPassword(id, encoder.encode(password)).getModifiedCount() != 0;
    }


    /**
     * 修改用户权限
     */
    @Override
    public boolean updateByRole(String id, String role) {
        return userDao.updateByRole(id, role).getModifiedCount() != 0;
    }


}
