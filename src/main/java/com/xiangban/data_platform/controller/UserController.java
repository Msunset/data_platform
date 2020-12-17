package com.xiangban.data_platform.controller;

import com.xiangban.data_platform.domain.User;
import com.xiangban.data_platform.domain.dto.Answer;
import com.xiangban.data_platform.domain.dto.AnswerDto;
import com.xiangban.data_platform.domain.dto.CustomerServiceDto;
import com.xiangban.data_platform.domain.dto.UserDto;

import com.xiangban.data_platform.domain.vo.AnswerVo;
import com.xiangban.data_platform.service.UserService;
import com.xiangban.data_platform.utils.JsonData;
import com.xiangban.data_platform.utils.page.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
@Api( tags = "数据平台接口控制")
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/getUser")
//    @ApiOperation("查询用户列表")
//    public JsonData getUser(){
//        List<User> userList = userService.getUserList();
//        return JsonData.buildSuccess(userList);
//    }

//    @PostMapping("/userList")
//    @ApiOperation("根据id查找用户")
//    public JsonData userList(@RequestBody User user){
//
//        List<User> users = userService.selectAll(user);
//        return  JsonData.buildSuccess(users);
//    }
//
//    @PostMapping("/findByName")
//    @ApiOperation("姓名模糊查询")
//    public List<User> findByName(@RequestBody User user){
//
//        List<User> users = userService.findByName(user);
//        return users;
//    }

    @PostMapping("/saveUser")
    @ApiOperation("用户添加")
    public JsonData saveUser(@RequestBody User user){

        int users = userService.save(user);

        if (users == -1){
            return JsonData.buildError("添加失败，同一平台信息重复！");

        }else {
            return JsonData.buildSuccess("已添加！");
        }
    }






    @PostMapping("/deleteAll")
    @ApiOperation("用户删除")
    public JsonData deleteAll(@RequestBody User userid){

        int deleteAll = userService.deleteAll(userid);
        if (deleteAll==1){
            return JsonData.buildSuccess("删除成功！");
        }else {
            return JsonData.buildSuccess("删除失败！，无此人信息");
        }



    }


    @PostMapping("/update")
    @ApiOperation("用户修改")
    public JsonData update(@RequestBody User user){
        int i = userService.updateUser(user);
        if (i == 1){
            return JsonData.buildSuccess("修改成功！");
        }else {
            return JsonData.buildError("修改失败，与同平台信息重复！");
        }
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public JsonData login(@RequestBody User user){
        String token = userService.login(user.getUsername(),user.getPassword());

        if (token!=null){
            return JsonData.buildSuccess(token);
        }else {
            return JsonData.buildError("登录失败,账号密码错误！");
        }
    }


//    @PostMapping(value="/findPage")
//    @ApiOperation("分页查询")
//    public Object findPage(@RequestBody PageRequest pageQuery) {
//
//              return userService.findPage(pageQuery);
//
//    }

    @PostMapping(value="/getList")
    @ApiOperation("分页查询")
    public JsonData getList(@RequestBody User user) {
        return JsonData.buildSuccess(userService.getList(user));
    }

    @PostMapping(value="/getUserList")
    @ApiOperation("查询所有用户")
    public JsonData getUserList(@RequestBody User user) {
        return JsonData.buildSuccess(userService.getUserList(user));

    }

    @GetMapping(value="/selectGsource")
    @ApiOperation("查询街道来源")
    public JsonData selectGsource() {
        return JsonData.buildSuccess(userService.findGsource());

    }
    @PostMapping(value="/updateCustomerService")
    @ApiOperation("根据id批量修改")
    public JsonData updateCustomerService(@RequestBody CustomerServiceDto customerServiceDto) {

        userService.updateCustomerService(customerServiceDto);
        return JsonData.buildSuccess(null);
    }

    @PostMapping(value="/selectUser")
    @ApiOperation("根据id查询用户")
    public JsonData selectUser(@RequestBody User user) {


        return JsonData.buildSuccess(userService.selectUser(user));
    }

    @PostMapping(value="/selectUserByPhone")
    @ApiOperation("根据手机号查询用户")
    public JsonData selectUserByPhone(@RequestBody User user) {
        return JsonData.buildSuccess(userService.selectUserByPhone(user.getPhone()));
    }


    @PostMapping(value="/selectAnswerByUserIdQuenstionId")
    @ApiOperation("根据用户id，选项id查询选项")
    public JsonData selectAnswerByUserIdAndQuenstionId(@RequestBody AnswerDto answerDto) {
        Object o = userService.selectAnswerByUserIdAndQuenstionId(answerDto);

        return JsonData.buildSuccess(o);
    }





}
