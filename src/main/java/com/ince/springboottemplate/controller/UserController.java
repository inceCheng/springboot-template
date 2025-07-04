package com.ince.springboottemplate.controller;

import com.ince.springboottemplate.common.BaseResponse;
import com.ince.springboottemplate.constant.Constant;
import com.ince.springboottemplate.constant.UserConstant;
import com.ince.springboottemplate.enums.ErrorCode;
import com.ince.springboottemplate.exception.BusinessException;
import com.ince.springboottemplate.model.dto.UserLoginRequest;
import com.ince.springboottemplate.model.dto.UserRegisterRequest;
import com.ince.springboottemplate.model.dto.UserUpdatePasswordRequest;
import com.ince.springboottemplate.model.dto.UserUpdateRequest;
import com.ince.springboottemplate.model.entity.User;
import com.ince.springboottemplate.model.vo.UserVO;
import com.ince.springboottemplate.services.UserService;
import com.ince.springboottemplate.utils.ResultUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author inceChen
 * @description 针对表【users(Table to store user account information)】的数据库操作Controller
 * @createDate 2025-07-03 13:49:10
 */
@RestController
@RequestMapping("/user")
@Tag(name = "用户管理", description = "用户相关的接口，包括注册、登录、信息管理等功能")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    @Operation(summary = "用户注册", description = "用户注册接口，需要提供用户名、密码、邮箱等信息")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "注册成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "409", description = "用户名或邮箱已存在")
    })
    public BaseResponse<User> userRegister(
            @Parameter(description = "用户注册请求对象", required = true)
            @RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        User user = userService.userRegister(userRegisterRequest);
        return ResultUtils.success(user);
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", description = "用户登录接口，登录成功后会创建会话")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登录成功"),
            @ApiResponse(responseCode = "400", description = "用户名或密码错误"),
            @ApiResponse(responseCode = "403", description = "账户被禁用")
    })
    public BaseResponse<User> userLogin(
            @Parameter(description = "用户登录请求对象", required = true)
            @RequestBody @Valid UserLoginRequest userLoginRequest,
            HttpServletRequest request) {
        User user = userService.userLogin(userLoginRequest, request);
        return ResultUtils.success(user);
    }

    @GetMapping("/logout")
    @Operation(summary = "用户登出", description = "用户登出接口，会清除服务端会话和客户端Cookie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "登出成功")
    })
    public BaseResponse<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // 1. 获取当前session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 2. 清除session中的用户信息
            session.removeAttribute(UserConstant.LOGIN_USER);
            // 3. 使session立即失效
            session.invalidate();
        }

        // 4. 清除浏览器端的Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Constant.COOKIE_NAME.equals(cookie.getName())) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0); // 立即过期
                    response.addCookie(cookie);
                    break;
                }
            }
        }

        return ResultUtils.success("退出登录成功");
    }

    /**
     * 获取当前登录用户信息（脱敏）
     */
    @GetMapping("/current")
    @Operation(summary = "获取当前用户信息", description = "获取当前登录用户的基本信息，返回脱敏后的数据")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "获取成功"),
            @ApiResponse(responseCode = "401", description = "未登录"),
            @ApiResponse(responseCode = "404", description = "用户不存在")
    })
    public BaseResponse<UserVO> getCurrentUser(HttpServletRequest request) {
        // 从Session中获取登录用户的基本信息（主要是获取用户ID）
        User sessionUser = userService.getLoginUser(request);

        // 根据用户ID从数据库查询最新的用户信息
        User currentUser = userService.getById(sessionUser.getId());
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }

        // 转换为VO对象（脱敏）
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(currentUser, userVO);
        return ResultUtils.success(userVO);
    }

    @PostMapping("/update/password")
    @Operation(summary = "修改密码", description = "用户修改密码接口，需要提供原密码和新密码")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "密码修改成功"),
            @ApiResponse(responseCode = "400", description = "原密码错误或新密码格式不正确"),
            @ApiResponse(responseCode = "401", description = "未登录")
    })
    public BaseResponse<User> updatePassword(
            @Parameter(description = "修改密码请求对象", required = true)
            @RequestBody @Valid UserUpdatePasswordRequest updatePasswordRequest,
            HttpServletRequest request) {
        User user = userService.updatePassword(updatePasswordRequest, request);
        return ResultUtils.success(user);
    }

    @PostMapping("/update")
    @Operation(summary = "更新用户信息", description = "更新用户基本信息，如昵称、头像、邮箱等")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "更新成功"),
            @ApiResponse(responseCode = "400", description = "请求参数错误"),
            @ApiResponse(responseCode = "401", description = "未登录")
    })
    public BaseResponse<User> updateUserInfo(
            @Parameter(description = "用户信息更新请求对象", required = true)
            @RequestBody @Valid UserUpdateRequest updateRequest,
            HttpServletRequest request) {
        User user = userService.updateUserInfo(updateRequest, request);
        return ResultUtils.success(user);
    }
}
