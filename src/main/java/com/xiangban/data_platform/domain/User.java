package com.xiangban.data_platform.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @author 181****0667
 * @date 2020-11-10 15:21:51
 * @email
 */
@Data
@ApiModel("")
public class User {

    @ApiModelProperty(value = "id")
    private Integer userid;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "联系电话")
    private String phone;

    @ApiModelProperty(value = "性别")
    private String sex;

    @ApiModelProperty(value = "身份证号码")
    private String idcard;

    @ApiModelProperty(value = "年龄")
    private String age;

    @ApiModelProperty(value = "状态：1正常，0是禁用，-1删除，")
    private String status;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "紧急联系人")
    private String warnusername;

    @ApiModelProperty(value = "紧急联系电话")
    private String warnusermobile;

    @ApiModelProperty(value = "街道来源（金阊或者是莲花等）")
    private String gsource;

    @ApiModelProperty(value = "是否盲人")
    private String isBlind;

    @ApiModelProperty(value = "现地址")
    private String address;

    @ApiModelProperty(value = "兴趣爱好")
    private String hobby;

    @ApiModelProperty(value = "家庭服务")
    private String homemakingService;

    @ApiModelProperty(value = "饭点")
    private String eatPoint;

    @ApiModelProperty(value = "账户金额")
    private BigDecimal price;

    @ApiModelProperty(value = "1用户 2护工 3分销商（推荐人）4护工公司 5医院 6护理院 7志愿者 8常护险 9非急救转运司机 10家政公司员工 11非急救转运护士 12调查问卷员")
    private String type;

    @ApiModelProperty(value = "账户积分")
    private String point;

    @ApiModelProperty(value = "生日")
    private Date birthday;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "注册地址")
    private String regAddress;

    @ApiModelProperty(value = "1:相伴医路，2：护工联盟，3：养老大街")
    private String platform;

}
