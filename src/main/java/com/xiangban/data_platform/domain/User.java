package com.xiangban.data_platform.domain;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    /**
     * 姓名   			name
     * 性别			    sex
     * 身份证号			idcard
     * 手机号			phone
     * 年龄			    age
     * 注册时间			reg_time
     * 客户地址			client_area
     * 客户平台			client_source
     * 状态			    status
     */

    private Integer id;

    private String name;

    private String sex;

    private String idcard;

    private String phone;

    private Integer age;

    private Date regTime;

    private String clientArea;

    private String clientSource;

    private Integer status;

    private String username;

    private String password;

}
