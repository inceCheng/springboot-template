# Spring Boot 项目模板

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.3-brightgreen.svg)
![Java](https://img.shields.io/badge/Java-21-orange.svg)
![MyBatis Plus](https://img.shields.io/badge/MyBatis%20Plus-3.5.12-blue.svg)
![Redis](https://img.shields.io/badge/Redis-Session-red.svg)
![MySQL](https://img.shields.io/badge/Database-MySQL-blue.svg)

这是一个基于 Spring Boot 3.2.3 的企业级项目模板，集成了常用的开发组件和最佳实践，可以作为新项目开发的起始模板。

## 🚀 项目特性

### 核心功能
- ✅ **用户管理系统** - 完整的用户注册、登录、信息管理功能
- ✅ **会话管理** - 基于 Redis 的分布式会话管理
- ✅ **权限控制** - AOP 注解式权限检查
- ✅ **API 文档** - 集成 Knife4j 自动生成接口文档
- ✅ **异常处理** - 全局异常处理机制
- ✅ **多环境配置** - 支持开发、测试、生产环境配置
- ✅ **连接池监控** - Druid 连接池性能监控
- ✅ **IP 地址解析** - 支持 IP 归属地查询

### 技术特性
- 🔥 **最新技术栈** - Spring Boot 3.2.3 + Java 21
- 🏗️ **模块化设计** - 清晰的分层架构
- 📊 **性能优化** - Druid 连接池 + Redis 缓存
- 🛡️ **安全机制** - 密码加密 + 会话管理
- 📝 **代码规范** - 统一的编码规范和最佳实践

## 🛠️ 技术栈

| 技术 | 版本 | 说明 |
|------|------|------|
| Spring Boot | 3.2.3 | 基础框架 |
| Java | 21 | 开发语言 |
| MyBatis Plus | 3.5.12 | ORM 框架 |
| MySQL | 8.0+ | 关系数据库 |
| Redis | 7.0+ | 缓存数据库 |
| Druid | 1.2.23 | 数据库连接池 |
| Knife4j | 4.4.0 | API 文档工具 |
| Lombok | - | 代码简化工具 |
| ip2region | 2.7.0 | IP 地址解析 |

## 📁 项目结构

```
springboot-template/
├── sql/                           # 数据库脚本
│   └── init.sql                   # 初始化脚本
├── src/main/java/com/ince/springboottemplate/
│   ├── annotation/                # 自定义注解
│   │   └── AuthCheck.java         # 权限检查注解
│   ├── aop/                       # AOP 切面
│   │   └── AuthInterceptor.java   # 权限拦截器
│   ├── common/                    # 通用类
│   │   └── BaseResponse.java      # 统一响应格式
│   ├── config/                    # 配置类
│   │   ├── MybatisPlusConfig.java # MyBatis Plus 配置
│   │   ├── RedisConfig.java       # Redis 配置
│   │   └── SessionConfig.java     # Session 配置
│   ├── constant/                  # 常量定义
│   ├── controller/                # 控制器层
│   │   └── UserController.java    # 用户管理接口
│   ├── enums/                     # 枚举类
│   ├── exception/                 # 异常处理
│   ├── mapper/                    # 数据访问层
│   ├── model/                     # 数据模型
│   │   ├── dto/                   # 数据传输对象
│   │   ├── entity/                # 实体类
│   │   └── vo/                    # 视图对象
│   ├── services/                  # 业务逻辑层
│   └── utils/                     # 工具类
└── src/main/resources/
    ├── application.yml             # 主配置文件
    ├── application-local.yml      # 本地环境配置
    ├── application-test.yml       # 测试环境配置
    └── application-prod.yml       # 生产环境配置
```

## 🚀 快速开始

### 环境要求
- JDK 21+
- Maven 3.6+
- MySQL 8.0+
- Redis 7.0+

### 1. 克隆项目
```bash
git clone <repository-url>
cd springboot-template
```

### 2. 数据库配置
```bash
# 1. 创建数据库
mysql -u root -p < sql/init.sql

# 2. 修改数据库连接配置
# 编辑 src/main/resources/application-local.yml
# 修改数据库连接信息
```

### 3. Redis 配置
确保 Redis 服务正在运行，默认配置：
- 主机：localhost
- 端口：6379
- 数据库：0

### 4. 启动项目
```bash
# 使用 Maven 启动
mvn spring-boot:run

# 或者使用包装器
./mvnw spring-boot:run    # Linux/Mac
mvnw.cmd spring-boot:run  # Windows
```

### 5. 访问应用
- 应用首页：http://localhost:8080/api
- API 文档：http://localhost:8080/api/doc.html
- Druid 监控：http://localhost:8080/api/druid （admin/admin123）

## 📖 API 文档

项目集成了 Knife4j，启动后访问 http://localhost:8080/api/doc.html 查看完整的 API 文档。

### 主要接口

| 接口 | 方法 | 说明 |
|------|------|------|
| `/api/user/register` | POST | 用户注册 |
| `/api/user/login` | POST | 用户登录 |
| `/api/user/logout` | GET | 用户登出 |
| `/api/user/current` | GET | 获取当前用户信息 |
| `/api/user/update` | POST | 更新用户信息 |
| `/api/user/update/password` | POST | 修改密码 |

## ⚙️ 配置说明

### 环境配置
项目支持多环境配置，通过 `spring.profiles.active` 切换：

- `local` - 本地开发环境
- `test` - 测试环境  
- `prod` - 生产环境

### 数据库配置
在对应环境的配置文件中修改数据库连接：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC
    username: root
    password: your_password
```

### Redis 配置
```yaml
spring:
  data:
    redis:
      host: localhost
      port: 6379
      database: 0
```

## 🔧 开发指南

### 添加新功能模块
1. 在 `model/entity` 中创建实体类
2. 在 `mapper` 中创建数据访问接口
3. 在 `services` 中实现业务逻辑
4. 在 `controller` 中创建 REST 接口
5. 使用 `@AuthCheck` 注解进行权限控制

### 权限控制
使用 `@AuthCheck` 注解控制接口访问权限：

```java
@AuthCheck(mustRole = UserRole.ADMIN)
@GetMapping("/admin/users")
public BaseResponse<List<User>> getAllUsers() {
    // 仅管理员可访问
}
```

### 异常处理
统一使用 `BusinessException` 抛出业务异常：

```java
if (user == null) {
    throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
}
```

## 🗄️ 数据库设计

### 用户表 (users)
| 字段 | 类型 | 说明 |
|------|------|------|
| id | bigint | 主键 |
| username | varchar(50) | 用户名 |
| password | varchar(100) | 密码（加密） |
| email | varchar(100) | 邮箱 |
| phone | varchar(20) | 手机号 |
| role | int | 用户角色 |
| status | int | 账户状态 |
| create_time | datetime | 创建时间 |
| update_time | datetime | 更新时间 |

## 🔍 监控

### Druid 监控
访问 http://localhost:8080/api/druid 查看：
- SQL 监控
- 连接池监控  
- URL 监控
- Spring 监控

登录账号：admin / admin123

## 📝 日志

项目使用 Spring Boot 默认的日志配置：
- 开发环境：控制台输出，DEBUG 级别
- 生产环境：文件输出，INFO 级别

## 🤝 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 打开 Pull Request

## 📄 许可证

本项目采用 MIT 许可证 - 查看 [LICENSE](LICENSE) 文件了解详情。

## 📞 联系方式

如有问题或建议，请通过以下方式联系：

- 项目 Issues：[GitHub Issues](https://github.com/your-repo/issues)
- 邮箱：chg99925@gmail.com

---

⭐ 如果这个项目对你有帮助，请给个 Star 支持一下！ 