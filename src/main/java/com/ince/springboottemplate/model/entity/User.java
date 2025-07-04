package com.ince.springboottemplate.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * Table to store user account information
 *
 * @TableName users
 */
@TableName(value = "users")
@Data
public class User {
    /**
     * Unique identifier for the user
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * User's chosen username
     */
    private String username;

    /**
     * User's display name (optional)
     */
    private String displayName;

    /**
     * Hashed password for the user
     */
    private String password;

    /**
     * User's email address
     */
    private String email;

    /**
     * User's phone number
     */
    private String phone;

    /**
     * User's physical address
     */
    private String address;

    /**
     * URL or path to the user's avatar image
     */
    private String avatar;

    /**
     * IP address from which the user last logged in
     */
    private String lastLoginIp;

    /**
     * Timestamp of the user's last login
     */
    private Date lastLoginTime;

    /**
     * User's account status: 0=inactive, 1=active, 2=frozen, 3=blocked
     */
    private Integer status;

    /**
     * Timestamp when the user account was created
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;

    /**
     * Additional flexible information about the user
     */
    private Object additionalInfo;

    /**
     * 个人简介
     */
    private String bio;

    /**
     * 身份证号码
     */
    private String idCard;

    /**
     * ip归属地
     */
    private String lastLoginIpLocation;

    /**
     * 用户角色(1=普通用户, 2= 管理员, 0= 系统管理员)
     */
    private Integer role;
}
