> idea
>
> jdk1.8
>
> Maven 3.6.1
>
> SpringBoot 
>
> swagger

# 一、创建基于SpringBoot工程

前面部分就不说了，到挑选依赖的时候，选择以下依赖

![1563268377240](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563268377240.png)

![1563268403203](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563268403203.png)

Finish完成。

## 目录结构

![1563268491099](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563268491099.png)

## pom依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>hx.insist</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>

```

解读一下这个依赖文件。

### 父依赖

```xml
 	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.6.RELEASE</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
```

这是这个项目SpringBoot-Mybatis的父项目，在maven中，

### 项目信息

```xml
	<groupId>hx.insist</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot</description>
```

此项目信息

### 依赖版本控制

```xml
 	<properties>
        <java.version>1.8</java.version>
    </properties>
```

### 依赖

```xml
	<dependencies>
        <!--springboot关于web的依赖-->
        <!--包含了spring-web、spring-webmvc、spring-boot-starter、spring-boot-starter-tomcat等依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--mybatis和springboot整合的依赖-->
        <!--包含mybatis、mybatis-spring、mybatis-spring-boot-autoconfigure等依赖
spring2只支持ibatis，spring3在开发结束时，mybatis3还为发布，所以spring无法整合。
而mybatis社区在开发完成mybatis3后，自行将其与spring进行整合，就有了mybatis-spring。
注：mybatis-spring的版本不等于mybatis或spring的版本-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.0</version>
        </dependency>
		<!--mysql驱动，提供对于java操作数据库的支持-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!--lombok工具包，@Data注解，请自行百度，不建议使用。。。-->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
		<!--springboot-test不仅用于test-->
        <!--包含spring-boot-test、spring-boot-test-configure、json-path、jsonassert、asserjt-core、junit、slf4j-api（抽象层）、log4j-to-slf4j、log4j-api、logback-core等依赖-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

### build

```xml
	<build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```



### tips ： 小技巧

![1563268849444](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563268849444.png)

点击-> 点击2-> 点击3可以看到此项目中的依赖信息图

![1563268885438](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563268885438.png)

ctrl+鼠标滑轮上滑，放大。

# 二、配置文件application.yml

```yml
# 服务端口
server.port: 9091

# mysql数据源配置
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springbootdemo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true
    username: root
    password: hanxu
    driver-class-name: com.mysql.cj.jdbc.Driver

# mybatis配置
mybatis:
  config-location: classpath:mybatis/mybatis-config.xml # mybatis核心配置文件
  mapper-locations: classpath:mybatis/mapper/*.xml # mybatis接口对应的xml文件
  type-aliases-package: hx.insist.demo.entity # 配置别名 扫描entity包，在mapper.xml文件中的parameterType可以直接="user" 或"User" 默认以类名称为别名，不区分大小写 ， 若不配置，必须写全类名parameterType = "hx.insist.demo.entity.User"	也可以在mybatis-config.xml中配置typeAliases节点下的<package name="cn.itcast.mybatis.po"/>，同样的效果。
```

**注意：config-location 、type-aliases-package 均为非必需配置，mapper-localtions为必需配置**

- Mybatis源码中默认（约定好）帮我们做了很多映射规则：https://www.cnblogs.com/dongying/p/4037678.html，所以我们一般不需要配置。
- 如果不配置 type-aliases-package 则在xml文件中要写parameterType = "hx.insist.demo.entity.User"全类名，而这正是我们需要的，因为可以让我们清晰的看到返回值类型。因此不建议配置此项。

### 注意：必须配置时区，使用cj

```yml
url: jdbc:mysql://localhost:3306/springbootdemo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true
```

这段url中除了jdbc连接外

```yml
jdbc:mysql://localhost:3306/springbootdemo
```

还要**必须必须**配置**serverTimezone=UTC**时区配置

因为我们使用的是springboot约定的MySQL依赖，点进去依赖你会发现，使用的是mysql8.0.x的驱动版本，而在mysql5的版本使用的驱动类是

driverClassName=com.mysql.jdbc.Driver（驱动类）

在mysql6及以上版本，使用的驱动类是

driverClassName=com.mysql.cj.jdbc.Driver （新版驱动类）

而**MySQL6及以上版本，使用驱动类连接MySQL时必须指定时区**，指定时区为UTC（自行百度），而这个时区比中国早8小时，所以**可以设置serverTimezone=Asia/Shanghai**或者Asia/Hongkong，这两个时间都是中国时间。

*更加详细的url*

```xml
url: 
jdbc:mysql://localhost:3306/dsf-dev-${spring.application.name}?autoReconnect=true&autoReconnectForPools=true&failOverReadOnly=false&useUnicode=true&characterEncoding=UTF-8&useSSL=false&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=Asia/Shanghai
```



# 三、构建项目

## 数据库db

```sql
/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : test1

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2016-11-05 21:17:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `userName` varchar(32) DEFAULT NULL COMMENT '用户名',
  `passWord` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_sex` varchar(32) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

```



## 实体类entity

User.java

```java
package hx.insist.demo.entity;

import hx.insist.demo.enums.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private UserSexEnum userSex;

    private String nickName;

    public User() {
    }
}
```

性别是一个枚举类型：UserSexEnum.java

```java
package hx.insist.demo.enums;

public enum UserSexEnum {
	MAN, WOMAN
}
```

## Dao（也称Mapper层）

接口：UserDao.java

```java
package hx.insist.demo.dao;

import hx.insist.demo.entity.User;

import java.util.List;

//mapper接口文件
public interface UserDao {
    int insertUserSelective(User user);
    int deleteUserByPrimaryKey(Long id);//要判断id不为空，否则就会把所有都删除掉
    Long getId();
    int updateUserByPrimaryKeySelective(User user);//要判断id不为空，否则当id为空是就会把所有都修改掉
    List<User> selectAll();
}

```

接口对应配置文件：UserMapper.xml  路径/resources/mybatis/mapper/UserMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="hx.insist.demo.dao.UserDao" >
    <!--自定义返回结果 映射集合-->
    <resultMap id="BaseResultMap" type="hx.insist.demo.entity.User">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="userName" property="username" jdbcType="VARCHAR"/>
        <result column="passWord" property="password" jdbcType="VARCHAR"/>
        <result column="user_sex" property="userSex" javaType="hx.insist.demo.enums.UserSexEnum"/>
        <result column="nick_name" property="nickName" jdbcType="VARCHAR"/>
    </resultMap>

    <sql id="Base_Column_List">
        id,userName,passWord,user_sex,nick_name
    </sql>

    <insert id="insertUserSelective" parameterType="hx.insist.demo.entity.User">
        insert into users
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="username!=null">
                username,
            </if>
            <if test="password!=null">
                password,
            </if>
            <if test="userSex!=null">
                user_sex,
            </if>
            <if test="nickName!=null">
                nick_name,
            </if>
        </trim>
        values
        <trim prefix="(" suffix=")" suffixOverrides=",">
            <if test="username!=null">
                #{username},
            </if>
            <if test="password!=null">
                #{password},
            </if>
            <if test="userSex!=null">
                #{userSex},
            </if>
            <if test="nickName!=null">
                #{nickName},
            </if>
        </trim>
    </insert>
    <delete id="deleteUserByPrimaryKey" parameterType="long">
        delete from users
        where id = #{id}
    </delete>
    <select id="getId" resultType="long">
        select id
        from users
        limit 1
    </select>
    <update id="updateUserByPrimaryKeySelective" parameterType="hx.insist.demo.entity.User">
        update users
        <set>
            <if test="username!=null">
                username = #{username},
            </if>
            <if test="password!=null">
                password = #{password},
            </if>
            <if test="userSex!=null">
                user_sex = #{userSex},
            </if>
            <if test="nickName!=null">
                nick_name = #{nickName},
            </if>
        </set>
        where id = #{id}
    </update>
    <select id="selectAll" resultMap="BaseResultMap">
        select 
        <include refid="Base_Column_List"/>
        from users
    </select>

</mapper>
```

## controller层

结合swagger，UserController.java

```java
package hx.insist.demo.controller;

import hx.insist.demo.dao.UserDao;
import hx.insist.demo.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api("增删改查")
public class UserController {

    @Autowired
    private UserDao userDao;


    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/user")
    @ApiOperation("增加用户-有选择的增加")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username", value="username", required = true, paramType = "query"),
            @ApiImplicitParam(name="password", value="password", required = true, paramType = "query"),
            @ApiImplicitParam(name="userSex", value="userSex", paramType = "query",defaultValue = "MAN"),
            @ApiImplicitParam(name="nickName", value="昵称", paramType = "query")
    })
    public Integer addUser(User user){
        logger.info("user:"+user);
        return userDao.insertUserSelective(user);
    }

    @DeleteMapping("/user/{id}")
    @ApiOperation("删除用户-通过主键删除")
    @ApiImplicitParam(name = "id" , value = "id" , required = true ,paramType = "path")
    public Integer delUser(@PathVariable("id") String id){
        logger.info("id:"+id);
        return userDao.deleteUserByPrimaryKey(Long.parseLong(id));
    }

    @GetMapping("/userid")
    @ApiOperation("获取任意用户的id")
    public Long getId(){
        return userDao.getId();
    }

    @PutMapping("/user")
    @ApiOperation("修改用户-通过id主键做为标识，有选择性的修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id" , value = "主键-必须存在", required = true , paramType = "query"),
            @ApiImplicitParam(name="username", value="username",  paramType = "query"),
            @ApiImplicitParam(name="password", value="password",  paramType = "query"),
            @ApiImplicitParam(name="userSex", value="userSex", paramType = "query",defaultValue = "MAN"),
            @ApiImplicitParam(name="nickName", value="昵称", paramType = "query")
    })
    public Integer modUser(User user){
        logger.info("修改前user:"+user);
        int i = userDao.updateUserByPrimaryKeySelective(user);
        return i;
    }

    @ApiOperation("查询用户-查出所有")
    @GetMapping("/users")
    public List<User> findUser(){
        return userDao.selectAll();
    }

}
```

## Swagger配置

Swagger2.java

```java
package hx.insist.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger2配置类
 * 在与spring boot集成时，放在与Application.java同级的目录下。
 * 通过@Configuration注解，让Spring来加载该类配置。
 * 再通过@EnableSwagger2注解来启用Swagger2。
 */

@Configuration
@EnableSwagger2
public class Swagger2 {

    /**
     * 创建API应用
     * apiInfo() 增加API相关信息
     * 通过select()函数返回一个ApiSelectorBuilder实例,用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义指定要建立API的目录。
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("hx.insist.demo.controller"))//api接口包扫描路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 创建该API的基本信息（这些基本信息会展现在文档页面中）
     * 访问地址：http://项目实际地址/swagger-ui.html
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")//设置文档的标题
                .description("更多请关注http://hanhanhanxu.coding.me")//设置文档的描述->1.Overview
                .termsOfServiceUrl("http://www.baidu.com")//设置文档的License信息->1.3 License information
                .contact("Han Xu : http://hanhanhanxu.coding.me")//设置文档的联系方式->1.2 Contact information
                .version("1.0")//设置文档的版本信息-> 1.1 Version information
                .build();
    }
}
```

## 运行类

DemoApplication.java

```java
package hx.insist.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("hx.insist.demo.dao")//扫描mapper接口位置
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
```

## 目录结构

![1563330962673](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563330962673.png)

# 四、运行

启动DemoApplication.java主类

访问http://localhost:9091/swagger-ui.html

![1563330422947](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563330422947.png)

先执行查所有：

![1563330450023](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563330450023.png)

增加：

![1563330491973](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563330491973.png)

删除：

![1563330526371](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563330526371.png)

修改：

![1563330559378](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563330559378.png)

![1563330896656](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1563330896656.png)

# 五、注意事项，学习到的内容

- 1、application.yml配置文件里，**config-location和	type-aliases-package都是非必需配置**的

- 2、UserMapper.xml中，**更新操作**时，一定要用这样的格式

  ```xml
  update users 
  <set>
      <if test="nickName!=null">
          nick_name = #{nickName},
      </if>
      ...
  </set>
   where id = #{id}
  ```

  这里面**一定要用<set>标签**，不能像下面这样直接写set

  ```xml
  update users 
  set
      <if test="nickName!=null">
          nick_name = #{nickName},
      </if>
      ...
   where id = #{id}
  ```

  因为这样最后一个if的内容 nick_name = #{nickName},  后面的 , 去不掉 。而<set>标签中自动帮我们做了最后去 , 处理。

- 3、**<trim>标签中， prefix 前置， suffix 后置，  suffixOverrides 后置去重** ， prefixOverrides 前置去重

- 4、resultType 和 resultMap的区别并不是返回值是否是list集合还是map集合。

  - 如果返回值是list集合，使用resultType 或者resultMap代表的是List集合的元素，而不是resultType  = list，这样的写法是错误的，mybatis的返回结果如果是list集合的时会根据我们接口文件中对应方法的返回值类型List<User> selectAll(); ，帮我们自动处理为List，而**resultType 只是指定了集合中元素的类型，并不是指定集合的类型**。

  - **resultMap代表的是自定义返回映射类型**，如果使用多表查询或没有与查询结果对应的pojo，或者某字段和元素对应的属性写法不一样，则可自定义返回元素和具体实体类的映射关系。

    ```xml
     <!--自定义返回结果 映射集合-->
    <resultMap id="BaseResultMap" type="hx.insist.demo.entity.User">
        <id column="id" property="id" jdbcType="BIGINT"/>
        <result column="userName" property="username" jdbcType="VARCHAR"/>
        <result column="passWord" property="password" jdbcType="VARCHAR"/>
        <result column="user_sex" property="userSex" javaType="hx.insist.demo.enums.UserSexEnum"/>
        <result column="nick_name" property="nickName" jdbcType="VARCHAR"/>
    </resultMap>
    ```

    比如这里，把返回的字段user_sex和实体类hx.insist.demo.entity.User中userSex属性映射了起来，并指定了该属性的类型为javaType="hx.insist.demo.enums.UserSexEnum"。

    - 关于**什么时候要写jdbcType或javaType**：

      - mybatis中javaType和jdbcType的对应关系	https://www.cnblogs.com/wenhuang/p/9796625.html

      - 如果你的字段类型和属性类型满足这个对应关系的话，完全不需要写，比如上面也可以写成这样

        ```xml
        <!--自定义返回结果 映射集合-->
        <resultMap id="BaseResultMap" type="hx.insist.demo.entity.User">
            <id column="id" property="id" jdbcType="BIGINT"/>
            <result column="userName" property="username" />
            <result column="passWord" property="password" />
            <result column="user_sex" property="userSex" javaType="hx.insist.demo.enums.UserSexEnum"/>
            <result column="nick_name" property="nickName" jdbcType="VARCHAR"/>
        </resultMap>
        
        ```

        userName和passWord和nick_name的类型对应关系都不需要写的。

      - 但是当不满足映射关系时，就要写**不满足那方**的类型，例如字段为user_sex时类型为VARCHAR，但属性userSex并不为String，所以要特意写出来指定一下属性的类型javaType="hx.insist.demo.enums.UserSexEnum"（必写）

- 将url改为：

- ```
  url: jdbc:mysql://localhost:3306/springbootdemo?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true
  ```

时区**设置为上海时区**。