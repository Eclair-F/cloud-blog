package com.qw.consumerexternal.controller.implement;


import com.qw.consumerexternal.controller.UserController;
import com.qw.consumerexternal.feign.UserFeign;
import com.qw.consumerexternal.result.Result;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eclair
 */
@RestController
@RequestMapping(value = "/user")
public class UserControllerImpl implements UserController {
    private final UserFeign userFeign;
    private final HttpServletRequest request;

    public UserControllerImpl(UserFeign userFeign, HttpServletRequest request) {
        this.userFeign = userFeign;
        this.request = request;
    }


    private boolean Admin() {
        String admin = "admin";
        return admin.equals(request.getAttribute("role"));
    }

    private boolean Id(String id) {
        return id.equals(request.getAttribute("id"));
    }


    /**
     * 查找所有用户
     */
    @Override
    @GetMapping(value = "/{size}")
    public Result user(@PathVariable int size) {
        if (Admin()) {
            Object user = userFeign.user(size);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有用户
     */
    @Override
    @GetMapping(value = "/up/{size}/{id}")
    public Result upUser(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin()) {
            Object user = userFeign.upUser(id, size);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有用户
     */
    @Override
    @GetMapping(value = "/down/{size}/{id}")
    public Result downUser(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin()) {
            Object user = userFeign.downUser(id, size);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有管理员
     */
    @Override
    @GetMapping(value = "/admin/{size}")
    public Result admin(@PathVariable int size) {
        if (Admin()) {
            Object user = userFeign.admin(size);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有管理员
     */
    @Override
    @GetMapping(value = "/admin/up/{size}/{id}")
    public Result upAdmin(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin()) {
            Object user = userFeign.upAdmin(id, size);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 查找所有管理员
     */
    @Override
    @GetMapping(value = "/admin/down/{size}/{id}")
    public Result downAdmin(@PathVariable ObjectId id, @PathVariable int size) {
        if (Admin()) {
            Object user = userFeign.downAdmin(id, size);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 登陆
     */
    @Override
    @PostMapping(value = "/login")
    public Result login(@RequestBody Object user) {
        Object login = userFeign.login(user);
        if (login == null) {
            return new Result(false, "登陆失败");
        } else {
            return new Result(true, "登陆成功", login);
        }
    }

    /**
     * 查找账户是否存在
     */
    @Override
    @GetMapping(value = "/exist/{username}")
    public Result findUserExist(@PathVariable String username) {
        boolean user = userFeign.findUserExist(username);
        if (user) {
            return new Result(true, "查询成功");
        }
        return new Result(false, "查询失败");

    }

    /**
     * 根据username查找用户
     */
    @Override
    @GetMapping(value = "/username/{username}/{size}")
    public Result findUsername(@PathVariable String username, @PathVariable int size) {
        if (Admin()) {
            Object user = userFeign.findUsername(username, size);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 根据username查找用户
     */
    @Override
    @GetMapping(value = "/up/username/{username}/{size}/{id}")
    public Object findUpUsername(@PathVariable String username, @PathVariable int size, @PathVariable ObjectId id) {
        if (Admin()) {
            Object user = userFeign.findUpUsername(username, size, id);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 根据username查找用户
     */
    @Override
    @GetMapping(value = "/down/username/{username}/{size}/{id}")
    public Object findDownUsername(@PathVariable String username, @PathVariable int size, @PathVariable ObjectId id) {
        if (Admin()) {
            Object user = userFeign.findDownUsername(username, size, id);
            if (user != null) {
                return new Result(true, "查询成功", user);
            }
        }
        return new Result(false, "查询失败");
    }

    /**
     * 根据id查找用户
     */
    @Override
    @GetMapping(value = "/id/{id}")
    public Result findById(@PathVariable String id) {
        Object user = userFeign.findById(id);
        if (user != null) {
            return new Result(true, "查询成功", user);
        }
        return new Result(false, "查询失败");
    }


    /**
     * 修改头像
     **/
    @Override
    @PutMapping(value = "/avatar")
    public Result updateByAvatar(@RequestBody Object user) {

        boolean update = userFeign.updateByAvatar(user);
        if (update) {
            return new Result(true, "修改成功");
        }
        return new Result(false, "修改失败");

    }

    /**
     * 修改
     **/
    @Override
    @PutMapping(value = "")
    public Result updateUser(@RequestBody Object user) {
        boolean update = userFeign.update(user);
        if (update) {
            return new Result(true, "修改成功");
        }
        return new Result(false, "修改失败");

    }


    /**
     * 查询旧密码
     **/
    @Override
    @PostMapping(value = "/oldPassword")
    public Object checkPassword(@RequestBody Object user) {
        boolean update = userFeign.findUserPassword(user);
        if (update) {
            return new Result(true, "查询成功");
        }
        return new Result(false, "查询失败");

    }

    /**
     * 修改密码
     */
    @Override
    @PutMapping(value = "/password")
    public Object updateUserPassword(@RequestBody Object user) {
        boolean update = userFeign.updateUserPassword(user);
        if (update) {
            return new Result(true, "修改成功");
        }
        return new Result(false, "修改失败");

    }

    /**
     * 修改邮箱
     */
    @Override
    @PutMapping(value = "/email")
    public Result updateByEmail(@RequestBody Object user) {
        boolean update = userFeign.updateByEmail(user);
        if (update) {
            return new Result(true, "修改成功");
        }
        return new Result(false, "修改失败");

    }

    /**
     * 查询邮箱
     */
    @Override
    @PostMapping(value = "/oldEmail")
    public Result findUserEmail(@RequestBody Object user) {
        boolean update = userFeign.findUserEmail(user);
        if (update) {
            return new Result(true, "查询成功");
        }
        return new Result(false, "查询失败");

    }


    /**
     * 修改用户类型
     */
    @Override
    @PutMapping(value = "/role")
    public Result updateUserRole(@RequestBody Object user) {
        boolean update = userFeign.updateUserRole(user);
        if (update) {
            return new Result(true, "修改成功");
        }
        return new Result(false, "修改失败");

    }


    /**
     * 删除用户
     */
    @Override
    @DeleteMapping(value = "/{id}")
    public Result deleteUser(@PathVariable String id) {
        if (Admin()) {
            boolean deleteUser = userFeign.deleteUser(id);
            if (deleteUser) {
                return new Result(true, "删除成功");
            }
        }
        return new Result(false, "删除失败");

    }

    /**
     * 添加用户
     */
    @Override
    @PostMapping()
    public Result saveUser(@RequestBody Object user) {
        boolean register = userFeign.saveUser(user);
        if (register) {
            return new Result(true, "注册成功");
        }
        return new Result(false, "注册失败");
    }


    /**
     * 用户激活
     */
    @Override
    @GetMapping(value = "/active/{uuid}", produces = "text/html;charset=UTF-8")
    public String active(@PathVariable String uuid) {
        boolean update = userFeign.active(uuid);
        if (update) {
            return "<h1>激活成功<h1>";
        }
        return "<h1>激活失败<h1>";

    }

}
