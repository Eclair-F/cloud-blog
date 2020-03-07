package com.qw.provideruser.controller;

import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import com.qw.provideruser.services.CreateService;
import com.qw.provideruser.services.DeleteService;
import com.qw.provideruser.services.RetrieveService;
import com.qw.provideruser.services.UpdateService;
import com.qw.provideruser.token.JwtUtil;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


/**
 * @author Eclair
 */
@RestController
public class UserController {
    private final CreateService createService;
    private final RetrieveService retrieveService;
    private final UpdateService updateService;
    private final DeleteService deleteService;
    private final HttpServletRequest request;
    private final String Admin = "admin";
    private String Role;
    private final String User = "user";

    /**
     * 头像保存路径
     */
    public static final String WINDOWS_PROFILES_PATH = "D:/blog/image/blog/";
    public static final String LINUX_PROFILES_PATH = "/usr/software/blog/image/user/";

    public UserController(RetrieveService retrieveService, CreateService createService,
                          UpdateService updateService, DeleteService deleteService,
                          HttpServletRequest request) {
        this.retrieveService = retrieveService;
        this.createService = createService;
        this.updateService = updateService;
        this.deleteService = deleteService;
        this.request = request;
    }

    private String getRole() {
        return (String) request.getAttribute("role");
    }

    private String getId() {
        return (String) request.getAttribute("id");
    }

    private String getOSPath() {
        // 根据Windows和Linux配置不同的头像保存路径
        return System.getProperty("os.name").toLowerCase().startsWith("win") ? WINDOWS_PROFILES_PATH
                : LINUX_PROFILES_PATH;
    }

    private PageResult<User> userTool(PageResult<User> pageResult) {
        if (pageResult.getRows().isEmpty()) {
            return null;
        } else {
            return pageResult;
        }
    }

    /**
     * 登陆
     */
    @PostMapping(value = "/login")
    public String login(@RequestBody User user) throws InterruptedException {
        if (retrieveService.login(user, false)) {
            user = retrieveService.findByUsername(user.getUsername(), false);
            if (user != null) {
                return JwtUtil.jwtCreateToken(user);
            }
        }
        return null;
    }

    /**
     * 查找所有用户
     */
    @GetMapping(value = "/")
    public PageResult<User> user() {
        if (Admin.equals(getRole())) {
            System.out.println(getRole());
            return userTool(retrieveService.findAllUser(false));
        }
        return null;
    }

    /**
     * 查找所有管理员
     */
    @GetMapping(value = "/admin")
    public PageResult<User> admin() {
        if (Admin.equals(getRole())) {
            return userTool(retrieveService.findAdmin());
        }
        return null;

    }

    /**
     * 查找下一页用户
     */
    @GetMapping(value = "/down/{id}")
    public PageResult<User> downUser(@PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return userTool(retrieveService.findAllUser(id, false));
        }
        return null;

    }

    /**
     * 查找账户是否存在
     */
    @GetMapping(value = "/exist/{username}")
    public boolean findUserExist(@PathVariable String username) {
        return retrieveService.findByUsernameExists(username);
    }

    /**
     * 根据id查找用户
     */

    @GetMapping(value = "/{id}")
    public User findById(@PathVariable String id) {
        if (Admin.equals(getRole()) || id.equals(getId())) {
            return retrieveService.findById(id, false);
        }
        return null;
    }


    /**
     * @param response:
     * @return void
     * @Author Eclair
     * @Description //获取图片
     * @Date 2020/3/6 16:50
     * @Param @param id:
     **/
    @GetMapping(value = "/image/{id}")
    public  void findImage(@PathVariable String id, HttpServletResponse response) {

        if (Admin.equals(getRole()) || id.equals(getId())) {
            String proPath = getOSPath();
            User user = retrieveService.findById(id, false);
            String path = proPath + id + "/" + user.getAvatar();
          response=retrieveService.findImage(path, response);
        }
    }

    /**
     * 修改用户信息
     */
    @PutMapping(value = "/")
    public boolean update(@RequestBody User user) {

        return false;
    }

    /**
     * @param id:
     * @return boolean
     * @Author Eclair
     * @Description //
     * @Date 2020/3/6 17:11
     * @Param @param image:
     **/
    @PutMapping(value = "/images/")
    public boolean updateImages(@RequestParam("images") MultipartFile[] images, @RequestParam("id") String id) throws IOException {
        if (images.length > 0) {
            String proPath = getOSPath();
            for (MultipartFile m : images
            ) {

                String name = images[0].getOriginalFilename();
                String imageName = "Avatar".concat(name.substring(name.lastIndexOf(".")));
                String path = proPath + id + "/";
//                User user = retrieveService.findById(id, false);
//                if (!user.getAvatar().equals(imageName)) {
//                    deleteService.deleteImage(path, user.getAvatar());
//                    updateService.updateByAvatar(id, imageName);
//                }
                updateService.updateImage(m, path, m.getOriginalFilename());
            }
        }
        return false;
    }

    /**
     * @return boolean
     * @Author Eclair
     * @Description //
     * @Date 2020/3/6 17:11
     * @Param @param image:
     **/
    @PutMapping(value = "/image")
    public boolean updateImage(@RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
//            String id = image.getOriginalFilename();
//            id = (id.substring(0, id.lastIndexOf(".")));
            String id="11";
            System.out.println(id);
            if (Admin.equals(getRole()) || id.equals(getId())) {
                String proPath = getOSPath();
                String name = image.getOriginalFilename();
                String imageName = "Avatar".concat(name.substring(name.lastIndexOf(".")));
                String path = proPath + id + "/";
                User user = retrieveService.findById(id, false);
                if (user != null && !user.getAvatar().isEmpty() && !imageName.equals(user.getAvatar())) {
                    deleteService.deleteImage(path, user.getAvatar());
                    updateService.updateByAvatar(id, imageName);
                }
                updateService.updateImage(image, path, imageName);
                return true;
            }
        }
        return false;
    }


    /**
     * 修改昵称
     */
    @PutMapping(value = "/nickname")
    public boolean updateUserNickname(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            return updateService.updateByNickname(user.getId(), user.getNickname());
        }
        return false;

    }


    /**
     * 修改密码
     */
    @PutMapping(value = "/password")
    public boolean updateUserPassword(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            return updateService.updateByPassword(user.getId(), user.getPassword());
        }
        return false;
    }

    /**
     * 查询旧密码
     */
    @PostMapping(value = "/oldPassword")
    public boolean findUserPassword(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            return retrieveService.findByPassword(user.getId(), user.getPassword());
        }
        return false;

    }


    /**
     * 修改用户类型
     */
    @PutMapping(value = "/type")
    public boolean updateUserType(@RequestBody User user) {
        if (Admin.equals(getRole())) {
            return updateService.updateByType(user.getId(), user.getType());
        }
        return false;

    }

    /**
     * 修改用户类型
     */
    @PutMapping(value = "/role")
    public boolean updateUserRole(@RequestBody User user) {
        if (Admin.equals(getRole())) {
            return updateService.updateByType(user.getId(), user.getRole());
        }
        return false;

    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/{id}")
    public boolean deleteUser(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return deleteService.deleteById(id);
        }
        return false;
    }


    /**
     * 添加用户
     */
    @PostMapping(value = "/")
    public boolean saveUser(@RequestBody User user) {
        if (retrieveService.findByUsernameExists(user.getUsername())) {
            return false;
        }
        user = createService.createUser(user);
        String proPath = getOSPath();
        String imageName = "Avatar.jpg";
        String oldPath = proPath + "default" + "/";
        String path = proPath + user.getId() + "/";
        return createService.createImage(oldPath, path, imageName);
    }

    /**
     * 查找所有注销用户
     */
    @GetMapping(value = "/delete")
    public PageResult<User> userIsDelete() {
        if (Admin.equals(getRole())) {
            return userTool(retrieveService.findAllUser(true));
        }
        return null;

    }


    /**
     * 账户还原
     */
    @PutMapping(value = "/delete/{id}")
    public boolean recoveryUser(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return updateService.recoveryById(id);
        }
        return false;

    }
}
