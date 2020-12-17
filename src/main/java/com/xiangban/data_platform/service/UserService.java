package com.xiangban.data_platform.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiangban.data_platform.domain.User;
import com.xiangban.data_platform.domain.dto.*;

import com.xiangban.data_platform.domain.vo.AnswerVo;
import com.xiangban.data_platform.mapper.UserMapper;
import com.xiangban.data_platform.utils.JWTUtils;
import com.xiangban.data_platform.utils.page.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        User byphone = userMapper.findByPhone(user);//通过手机号和平台查询出的sql语句。
        if (byphone!=null ){
            return -1;
        }
        return userMapper.saveUser(user);
    }


    /**
     * 删除用户
     *
     * @param user
     */
    public int deleteAll(User user) {

        int delete = userMapper.delete(user);
        return delete;
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    public int updateUser(User user) {
        User byPhone = userMapper.findByPhone(user);
        if (byPhone != null) {
                if (byPhone.getId().equals(user.getId())) {
                            return userMapper.updateUser(user);
                }
                    return -1;
            }


//        User byidcard= userMapper.findByidcard(user);
//        if (byidcard.getIdcard().equals(user.getIdcard()) | byidcard.getPhone().equals(user.getPhone())){
//            return -1;
//        }
            return userMapper.updateUser(user);
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
    //分页查询
    public PageInfo<User> getList(User user){
        PageHelper.startPage(pageUtil.getPage().getPageNum(), pageUtil.getPage().getPageSize());
        return new PageInfo<>(userMapper.selectPage(user));
    }
    //直接查询所有
    public List<User> getUserList(User user){
        return userMapper.selectPage(user);
    }
    public List<Object> findGsource(){
        List<Object> list = userMapper.selectGsource();
//        List<Object> listTemp = new ArrayList();
//        for (int i = 0 ;i <list.size();i++){
//            if (list.get(i)!=null){
//                listTemp.add(list.get(i));
//                System.out.println(listTemp);
//            }
//        }
//        return listTemp;
//        list.removeAll(Collections.singleton(null));
        return list;
    }
    //批量单表修改
    public void updateCustomerService(CustomerServiceDto customerServiceDto){

        userMapper.resetting(customerServiceDto);
        userMapper.updateCustomerService(customerServiceDto);
    }

    public User selectUser(User user){
        return userMapper.selectUser(user.getId());
    }



    public User selectUserByPhone(String phone){//根据手机查询所有信息
       return userMapper.selectUserByPhone(phone);
    }

    public Object selectAnswerByUserIdAndQuenstionId(AnswerDto answerDto){
        List<AnswerVo> answerVos = userMapper.selectAnswerByUserIdAndQuenstionId(answerDto.getUserId(), answerDto.getQuenstionId());
        Object str="";
        if (answerVos!=null){
            for (AnswerVo answerVo : answerVos) {
                str+=answerVo.getAnswer()+",";
            }
            if(str!="")
            {
                str= ((String) str).substring(0, ((String) str).length()-1);

            }
        }
        return str;
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

    }

