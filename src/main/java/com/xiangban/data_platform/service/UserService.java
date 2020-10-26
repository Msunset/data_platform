package com.xiangban.data_platform.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiangban.data_platform.domain.User;
import com.xiangban.data_platform.mapper.UserMapper;
import com.xiangban.data_platform.utils.JsonData;
import com.xiangban.data_platform.utils.page.PageRequest;
import com.xiangban.data_platform.utils.page.PageResult;
import com.xiangban.data_platform.utils.page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class UserService {


    @Autowired
    private UserMapper userMapper;

    /**
     * 查找所有用户信息
     * @return
     */
    public List<User> getUserList(){
        return userMapper.findAll();
    }

    /**
     * 查询所有
     * @param user
     * @return
     */
    public List<User> selectAll(User user){

        return userMapper.findById(user.getId());
    }

    /**
     * 模糊查询
     * @param user
     * @return
     */
    public List<User> findByName(User user){

      return   userMapper.findByName(user.getName());
    }

    /**
     * 增加用户
     * @param user
     * @return
     */
    public int save(User user){
        User byPhone = userMapper.findByPhone(user.getPhone());
        if (byPhone!=null){
            return -1;
        }else {
//            User user1 = new User();
            Date date = new Date();
            user.setRegTime(date);
           return userMapper.saveUser(user);
        }
    }

    /**
     * 删除用户
     * @param user
     */
    public void deleteAll(User user){

        userMapper.deleteAll(user.getId());
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int updateUser(User user){
        User byPhone = userMapper.findByPhone(user.getPhone());

        if (byPhone!=null){
            return -1;
        }else {
            return userMapper.updateUser(user);
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    public User login(String username,String password){

       return userMapper.login(username,password);
    }


    /**
     * 分页
     * @param pageRequest
     * @return
     */
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /**
     * 调用分页插件完成分页
     * @param
     * @return
     */
    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> sysMenus = userMapper.selectPage();
        return new PageInfo<User>(sysMenus);
    }

}
