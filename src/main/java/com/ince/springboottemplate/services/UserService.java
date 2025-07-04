package com.ince.springboottemplate.services;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ince.springboottemplate.model.dto.UserLoginRequest;
import com.ince.springboottemplate.model.dto.UserRegisterRequest;
import com.ince.springboottemplate.model.dto.UserUpdatePasswordRequest;
import com.ince.springboottemplate.model.dto.UserUpdateRequest;
import com.ince.springboottemplate.model.entity.User;
import jakarta.servlet.http.HttpServletRequest;


/**
 * @author HP
 * @description 针对表【users(Table to store user account information)】的数据库操作Service
 * @createDate 2025-07-03 13:49:10
 */
public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @param userLoginRequest 登录请求
     * @param request HTTP请求
     * @return 登录成功的用户信息
     */
    User userLogin(UserLoginRequest userLoginRequest, HttpServletRequest request);

    /**
     * 用户注册
     * @param userRegisterRequest 注册请求
     * @return 注册成功的用户信息
     */
    User userRegister(UserRegisterRequest userRegisterRequest);

    /**
     * 获取当前登录用户
     * @param request HTTP请求
     * @return 当前登录用户信息
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 修改密码
     * @param updatePasswordRequest 修改密码请求
     * @param request HTTP请求
     * @return 更新后的用户信息
     */
    User updatePassword(UserUpdatePasswordRequest updatePasswordRequest, HttpServletRequest request);

    /**
     * 更新用户信息
     * @param updateRequest 更新信息请求
     * @param request HTTP请求
     * @return 更新后的用户信息
     */
    User updateUserInfo(UserUpdateRequest updateRequest, HttpServletRequest request);
}
