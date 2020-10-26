package com.xiangban.data_platform.mapper;

import com.xiangban.data_platform.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserMapper {


    /**
     * 查找所有用户信息
     * @return
     */
    List<User> findAll();

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     */
    List<User> findById(Integer id);

    /**
     * 姓名模糊查询
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 手机号查询
     * @param phone
     * @return
     */
    User findByPhone (String phone);

    /**
     * 添加用户
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteAll(Integer id);

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
    User login(String username,String password);

    /**
     * 分页查询用户
     * @return
     */
    List<User> selectPage();
}
