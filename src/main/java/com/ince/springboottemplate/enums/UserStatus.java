package com.ince.springboottemplate.enums;

/**
 * 用户状态枚举
 * 定义了用户账户的各种状态，包括整型代码和对应的描述。
 */
public enum UserStatus {

    /**
     * 0: 非活跃用户
     */
    INACTIVE(0, "inactive", "非活跃"),

    /**
     * 1: 活跃用户
     */
    ACTIVE(1, "active", "活跃"),

    /**
     * 2: 冻结用户
     */
    FROZEN(2, "frozen", "冻结"),

    /**
     * 3: 封禁用户
     */
    BLOCKED(3, "blocked", "封禁");

    /**
     * 状态码 (int 类型)
     */
    private final int code;

    /**
     * 状态描述 (英文 string 类型)
     */
    private final String englishDescription;

    /**
     * 状态描述 (中文 string 类型)
     */
    private final String chineseDescription;

    /**
     * 构造函数
     * @param code 状态码
     * @param englishDescription 英文描述
     * @param chineseDescription 中文描述
     */
    UserStatus(int code, String englishDescription, String chineseDescription) {
        this.code = code;
        this.englishDescription = englishDescription;
        this.chineseDescription = chineseDescription;
    }

    /**
     * 获取状态码
     * @return 状态码
     */
    public int getCode() {
        return code;
    }

    /**
     * 获取英文状态描述
     * @return 英文状态描述
     */
    public String getEnglishDescription() {
        return englishDescription;
    }

    /**
     * 获取中文状态描述
     * @return 中文状态描述
     */
    public String getChineseDescription() {
        return chineseDescription;
    }

    /**
     * 根据状态码获取对应的枚举
     * @param code 状态码
     * @return 对应的UserStatus枚举，如果不存在则返回null
     */
    public static UserStatus fromCode(int code) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        return null; // 或者抛出IllegalArgumentException
    }

    /**
     * 根据英文描述获取对应的枚举
     * @param description 英文状态描述
     * @return 对应的UserStatus枚举，如果不存在则返回null
     */
    public static UserStatus fromEnglishDescription(String description) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getEnglishDescription().equalsIgnoreCase(description)) {
                return status;
            }
        }
        return null; // 或者抛出IllegalArgumentException
    }

    /**
     * 根据中文描述获取对应的枚举
     * @param description 中文状态描述
     * @return 对应的UserStatus枚举，如果不存在则返回null
     */
    public static UserStatus fromChineseDescription(String description) {
        for (UserStatus status : UserStatus.values()) {
            if (status.getChineseDescription().equalsIgnoreCase(description)) {
                return status;
            }
        }
        return null; // 或者抛出IllegalArgumentException
    }
}