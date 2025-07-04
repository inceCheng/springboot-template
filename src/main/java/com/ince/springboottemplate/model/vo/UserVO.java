package com.ince.springboottemplate.model.vo;

import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户视图对象 (User View Object)
 * 用于后端向前端返回用户脱敏信息
 */
@Data
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = -5224595512518253566L;

    /**
     * 用户唯一标识符
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String displayName;

    /**
     * 用户邮箱地址 (返回前端时需脱敏，如：'u***@example.com')
     */
    private String email;

    /**
     * 用户电话号码 (返回前端时需脱敏，如：'138****1234')
     */
    private String phone;

    /**
     * 用户头像URL或路径
     */
    private String avatar;

    /**
     * ip归属地
     */
    private String lastLoginIpLocation;

    /**
     * 用户账户状态: 0=inactive, 1=active, 2=suspended
     */
    private Integer status;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 身份证号码 (返回前端时需脱敏，如：'****************XX')
     */
    private String idCard;

    /**
     * 角色: 0=普通用户, 1=管理员
     */
    private Integer role;
}
