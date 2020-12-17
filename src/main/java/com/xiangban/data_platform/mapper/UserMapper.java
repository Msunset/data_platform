package com.xiangban.data_platform.mapper;

import com.xiangban.data_platform.domain.User;
import com.xiangban.data_platform.domain.dto.*;
import com.xiangban.data_platform.domain.vo.AnswerVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {

    /**
     * 手机号和平台同时查询
     * @param user
     * @return
     */
     User findByPhone (User user);
    /**
     * 手机号和平台同时查询
     * @param user
     * @return
     */
    User findByid (User user);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 删除用户
     * @param userid
     * @return
     */
    int delete(User userid);

    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    List<User> login(@Param("username") String username, @Param("password") String password);

//    /**
//     * 分页查询用户
//     * @return
//     */
//    List<User> selectPage();
    /**
     * 分页查询用户
     * @return
     */
    List<User> selectPage(User user);
    //查询街道来源
    List<Object> selectGsource();
    //修改客服归属根据传来的人员id
    void updateCustomerService(CustomerServiceDto customerServiceDto);
    //重置客服归属信息
    void resetting(CustomerServiceDto customerServiceDto );

    User selectUser(Integer id);

    User selectUserByPhone(String phone);

    List<AnswerVo> selectAnswerByUserIdAndQuenstionId(Integer userId, Integer quenstionId);



}
