package com.xiangban.data_platform.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiangban.data_platform.domain.User;
import com.xiangban.data_platform.mapper.UserMapper;
import com.xiangban.data_platform.utils.HttpClientUtil;
import com.xiangban.data_platform.utils.JWTUtils;
import com.xiangban.data_platform.utils.JsonData;
import com.xiangban.data_platform.utils.page.PageRequest;
import com.xiangban.data_platform.utils.page.PageResult;
import com.xiangban.data_platform.utils.page.PageUtil;
import com.xiangban.data_platform.utils.page.PageUtils;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PageUtil pageUtil;

//    /**
//     * 查找所有用户信息
//     * @return
//     */
//    public List<User> getUserList(){
//        return userMapper.findAll();
//    }

//    /**
//     * 查询所有
//     * @param user
//     * @return
//     */
//    public List<User> selectAll(User user){
//
//        return userMapper.findById(user.getId());
//    }


    /**
     * 增加用户
     *
     * @param user
     * @return
     */
    public int save(User user) {

        User byPhone = userMapper.findByPhone(user.getPhone());
        if (byPhone != null) {
            return -1;
        } else {
//            User user1 = new User();
            Date date = new Date();
            user.setCreateTime(date);
//            Map<String,String> map = new HashMap<>();
//            map.put("nickname",user.getNickName());
//            map.put("phone",user.getPhone());
//            String s = HttpClientUtil.doPost("192.168.1.24:8082/api/v1/user/saveUser", map);
//            System.out.println(s);
            return userMapper.saveUser(user);
        }
    }


    /**
     * 删除用户
     *
     * @param userid
     */
    public void deleteAll(User userid) {

        userMapper.deleteAll(userid);
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    public int updateUser(User user) {
        User byPhone = userMapper.findByPhone(user.getPhone());
        if (byPhone != null) {
            if (byPhone.getUserid().equals(user.getUserid())) {
                return userMapper.updateUser(user);
            } else {
                return -1;
            }
        } else {
            return userMapper.updateUser(user);
        }

//        return userMapper.updateUser(user);
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public String login(String username, String password) {

        List<User> user = userMapper.login(username, password);
        if (user!=null) {
            for (int i = 0; i < user.size(); i++) {
                String token = JWTUtils.getJsonWebToken(user.get(i));
                return token;
            }
        }
            return "查无此账号信息";
    }
//        if (user == null){
//            return  "查无此账号信息！";
//        }else {
//
//            String token = JWTUtils.getJsonWebToken(user);
//            return token;
//        }


//    /**
//     * 模糊查询
//     * @param user
//     * @return
//     */
//    public List<User> findByName(User user){
//
//        return userMapper.findByName(user.getName());
//
//
//    }

//    private PageInfo<User> getPageInfon(PageRequest pageRequest,User user) {
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> sysMenus = userMapper.findByName(user.getName());
//        return new PageInfo<User>(sysMenus);
//    }
//    /**
//     * 分页
//     * @param pageRequest
//     * @return
//     */
//    public PageResult findPage(PageRequest pageRequest) {
//
//        User user = new User();
//
//        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
//    }
//    /**
//     * 调用分页插件完成分页
//     * @param
//     * @return
//     */
//    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
//        int pageNum = pageRequest.getPageNum();
//        int pageSize = pageRequest.getPageSize();
//        PageHelper.startPage(pageNum, pageSize);
//        List<User> sysMenus = userMapper.selectPage();
//        return new PageInfo<User>(sysMenus);
//    }

        //分页查询
        public PageInfo<User> getList(User user){
            PageHelper.startPage(pageUtil.getPage().getPageNum(), pageUtil.getPage().getPageSize());
            return new PageInfo<>(userMapper.selectPage(user));
        }



    }

