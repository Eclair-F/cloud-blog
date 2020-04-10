package com.qw.provideruser.controller.implement;

import com.qw.provideruser.controller.UserController;
import com.qw.provideruser.entity.PageResult;
import com.qw.provideruser.entity.User;
import com.qw.provideruser.services.UserService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


/**
 * @author Eclair
 */

@RestController
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final HttpServletRequest request;
    private final String Admin = "admin";

    public UserControllerImpl(UserService userService, HttpServletRequest request) {
        this.userService = userService;
        this.request = request;
    }


    private String getRole() {
        return (String) request.getAttribute("role");
    }

    private String getId() {
        return (String) request.getAttribute("id");
    }


    /**
     * 登陆
     */
    @Override
    @PostMapping(value = "/login")
    public User login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * 查找所有用户
     */
    @Override
    @GetMapping(value = "/{size}")
    public PageResult<User> user(@PathVariable int size) {
        if (Admin.equals(getRole())) {
            return userService.findAllUser(size);
        }
        return null;
    }

    /**
     * 查找上一页用户
     */
    @Override
    @GetMapping(value = "/up/{size}/{id}")
    public PageResult<User> upUser(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin.equals(getRole())) {
            return userService.upUser(id, size);
        }
        return null;

    }

    /**
     * 查找下一页用户
     */
    @Override
    @GetMapping(value = "/down/{size}/{id}")
    public PageResult<User> downUser(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin.equals(getRole())) {
            return userService.downAllUser(id, size);
        }
        return null;
    }

    /**
     * 查找所有管理员
     */
    @Override
    @GetMapping(value = "/admin/{size}")
    public PageResult<User> admin(@PathVariable int size) {
        if (Admin.equals(getRole())) {
            return userService.admin(size);
        }
        return null;
    }

    /**
     * 查找上一页管理员
     */
    @Override
    @GetMapping(value = "/admin/up/{size}/{id}")
    public PageResult<User> upAdmin(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin.equals(getRole())) {
            return userService.upAdmin(id, size);
        }
        return null;

    }

    /**
     * 查找下一页管理员
     */
    @Override
    @GetMapping(value = "/admin/down/{size}/{id}")
    public PageResult<User> downAdmin(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin.equals(getRole())) {
            return userService.downAdmin(id, size);
        }
        return null;
    }

    /**
     * 根据username查找用户

     */
    @Override
    @GetMapping(value = "/username/{username}/{size}")
    public PageResult<User> findUsername(@PathVariable String username, @PathVariable int size) {
        if (Admin.equals(getRole())) {
            return userService.findUsername(username, size);
        }
        return null;
    }

    /**
     * 根据username查找用户

     */
    @Override
    @GetMapping(value = "/up/username/{username}/{size}/{id}")
    public PageResult<User> findUpUsername(@PathVariable String username, @PathVariable int size, @PathVariable ObjectId id) {
        if (Admin.equals(getRole())) {
            return userService.findUpUsername(username, size, id);
        }
        return null;
    }

    /**
     * 根据username查找用户
     */
    @Override
    @GetMapping(value = "/down/username/{username}/{size}/{id}")
    public PageResult<User> findDownUsername(@PathVariable String username, @PathVariable int size, @PathVariable ObjectId id) {

        if (Admin.equals(getRole())) {
            return userService.findDownUsername(username, size, id);
        }
        return null;
    }


    /**
     * 查找账户是否存在
     */
    @Override
    @GetMapping(value = "/exist/{username}")
    public boolean findUserExist(@PathVariable String username) {
        return userService.findByUsernameExists(username);
    }

    /**
     * 根据id查找用户
     */
    @Override

    @GetMapping(value = "/id/{id}")
    public User findById(@PathVariable String id) {
        if (Admin.equals(getRole()) || id.equals(getId())) {
            return userService.findById(id);
        }
        return null;
    }

    /**
     * 修改用户信息
     */
    @Override
    @PutMapping(value = "/")
    public boolean update(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            if (Admin.equals(getRole()) && user.getEmail() != null) {
                userService.updateEmailByAdmin(user);
            }
            return userService.update(user);
        }
        return false;
    }


    /**
     * 修改头像
     */
    @Override
    @PutMapping(value = "/avatar")
    public boolean updateByAvatar(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            return userService.updateByAvatar(user.getId(), user.getAvatar());
        }
        return false;
    }


    /**
     * 修改密码
     */
    @Override
    @PutMapping(value = "/password")
    public boolean updateUserPassword(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            return userService.updateByPassword(user.getId(), user.getPassword());
        }
        return false;
    }

    /**
     * 查询旧密码
     */
    @Override
    @PostMapping(value = "/oldPassword")
    public boolean findUserPassword(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            return userService.findByPassword(user.getId(), user.getPassword());
        }
        return false;
    }

    /**
     * 查询旧邮箱
     */
    @Override
    @PostMapping(value = "/oldEmail")
    public boolean findUserEmail(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {

            return userService.findByEmail(user.getId(), user.getEmail());
        }
        return false;
    }

    /**
     * 修改用户类型
     */
    @Override
    @PutMapping(value = "/role")
    public boolean updateUserRole(@RequestBody User user) {
        if (Admin.equals(getRole())) {
            return userService.updateByRole(user.getId(), user.getRole());
        }
        return false;
    }

    /**
     * 修改邮箱
     */
    @Override
    @PutMapping(value = "/email")
    public boolean updateByEmail(@RequestBody User user) {
        if (Admin.equals(getRole()) || user.getId().equals(getId())) {
            return userService.updateEmail(user);
        }
        return false;
    }


    /**
     * 删除用户
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public boolean deleteUser(@PathVariable String id) {
        if (Admin.equals(getRole())) {
            return userService.deleteById(id);
        }
        return false;
    }


    /**
     * 添加用户
     */
    @Override
    @PostMapping(value = "/")
    public boolean saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    /**
     * 用户激活
     */
    @Override
    @GetMapping(value = "/active/{uuid}")
    public boolean active(@PathVariable String uuid) {
        return userService.active(uuid);
    }


}
