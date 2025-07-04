package com.ince.springboottemplate.enums;

import lombok.Getter;

/**
 * 用户角色枚举
 * 定义了用户账户的各种角色，包括整型代码和对应的描述。
 */
@Getter
public enum UserRole {

    /**
     * 0: 系统管理员
     */
    SYSTEM_ADMIN(0, "system_admin"),

    /**
     * 1: 普通用户
     */
    REGULAR_USER(1, "user"),

    /**
     * 2: 管理员
     */
    ADMIN(2, "admin");

    /**
     * 角色码 (int 类型)
     */
    private final int code;

    /**
     * 角色
     */
    private final String role;

    /**
     * 构造函数
     *
     * @param code 角色码
     * @param role 角色
     */
    UserRole(int code, String role) {
        this.code = code;
        this.role = role;
    }

    /**
     * 根据角色码获取对应的枚举
     *
     * @param code 角色码
     * @return 对应的UserRole枚举，如果不存在则返回null
     */
    public static UserRole fromCode(int code) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.getCode() == code) {
                return userRole;
            }
        }
        return null; // 或者抛出IllegalArgumentException
    }

    /**
     * 根据英文描述获取对应的枚举
     *
     * @param roleName 角色名称
     * @return 对应的UserRole枚举，如果不存在则返回null
     */
    public static UserRole fromEnglishDescription(String roleName) {
        for (UserRole role : UserRole.values()) {
            if (role.getRole().equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        return null; // 或者抛出IllegalArgumentException
    }
}
