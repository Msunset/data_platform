package com.xiangban.data_platform.controller;

import com.xiangban.data_platform.domain.User;
import com.xiangban.data_platform.service.UserService;
import com.xiangban.data_platform.utils.JsonData;
import com.xiangban.data_platform.utils.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@Api( tags = "数据平台接口控制")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/getUser")
    @ApiOperation("查询用户列表")
    public JsonData getUser(){
        List<User> userList = userService.getUserList();
        return JsonData.buildSuccess(userList);
    }

    @PostMapping("/userList")
    @ApiOperation("根据id查找用户")
    public JsonData userList(@RequestBody User user){

        List<User> users = userService.selectAll(user);
        return  JsonData.buildSuccess(users);
    }

    @PostMapping("/findByName")
    @ApiOperation("姓名模糊查询")
    public List<User> findByName(@RequestBody User user){

        List<User> users = userService.findByName(user);
        return users;
    }

    @PostMapping("/saveUser")
    @ApiOperation("用户添加")
    public JsonData saveUser(@RequestBody User user){

        int users = userService.save(user);

        if (users==1){
            return JsonData.buildSuccess("添加成功！");
        }else {
            return JsonData.buildError("添加失败！");
        }

    }

    @PostMapping("/deleteAll")
    @ApiOperation("用户删除")
    public JsonData deleteAll(@RequestBody User user){

        userService.deleteAll(user);
        return JsonData.buildSuccess("删除成功！");
    }


    @PostMapping("/update")
    @ApiOperation("用户修改")
    public JsonData update(@RequestBody User user){


        int i = userService.updateUser(user);
        if (i==1){

            return JsonData.buildSuccess("修改成功！");
        }else {
            return JsonData.buildError("修改失败！");
        }
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public JsonData login(@RequestBody User user){
        User login = userService.login(user.getUsername(),user.getPassword());

        if (login!=null){
            return JsonData.buildSuccess("登陆成功！");
        }else {
            return JsonData.buildError("登录失败！");
        }
    }


    @PostMapping(value="/findPage")
    @ApiOperation("分页查询")
    public Object findPage(@RequestBody PageRequest pageQuery) {
        return userService.findPage(pageQuery);
    }
}
