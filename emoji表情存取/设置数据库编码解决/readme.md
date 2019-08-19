SpringBoot+mybatis，遵循rest风格，controller返回ResponseEntity<T>，使用通用mapper2.0.3，自定义异常处理，在service层解决emoji表情入库取库乱码问题。

### 解决方案

> 如果你的mysql配置文件My.ini没有更改过，那么储存emoji表情是有问题的，使用springboot在程序中储存中文也是有问题的，会显示? ，
>
> 有一种解决方案可解决以上两个问题：
>
> 修改My.ini：
>
> ```ini
>  [mysql]
>  default-character-set=utf8
>  [mysqld]
>  character-set-server=utf8
> ```

> 如果你进行上述修改，那么在Mysql数据库中可以看到正常的emoji表情
>
> 如果你没有上述修改，那么使用我的代码储存emoji后，MySQL中看到的emoji是修改后的字符串。

> 如果你进行上述修改，那么使用 或 不使用 我的代码都能正常储存，使用我的代码并不会造成你的原emoji乱码。
>
> 如果你没有上述修改，那么一定要使用我的代码才能储存emoji。



**本文不使用上述方式**解决emoji乱码，而是**在代码中解决**，加了一个Utilf类，在入库前处理带有emoji的字段，转换为其他，入库。在取库时，取出后立马反转换过来，变为emoji，在存入相对应的属性。



### postman测试

> 代码直接运行

数据库截图

![1564717718070](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717718070.png)



#### 查

![1564717733240](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717733240.png)

#### 增

![1564717783500](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717783500.png)

![1564717802192](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717802192.png)

#### 删

![1564717842177](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717842177.png)

![1564717855522](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717855522.png)

#### 改

#### ![1564717894370](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717894370.png)

![1564717927439](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717927439.png)

![1564717945224](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717945224.png)

#### 数据库截图

![1564717962319](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564717962319.png)

#### 异常处理

![1564718019082](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564718019082.png)

### pom

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
    <groupId>com.example</groupId>
    <artifactId>demo</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>demo</name>
    <description>Demo project for Spring Boot</description>

    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!-- web相关，controller层中注解需要这个依赖 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
		<!-- 通用mapper，不用再写mapper接口和xml -->
        <dependency>
            <groupId>tk.mybatis</groupId>
            <artifactId>mapper-spring-boot-starter</artifactId>
            <version>2.0.3</version>
        </dependency>
        <!-- 数据库驱动，mysql版本5.7 springboot默认使用驱动版本8.0+，默认HikariPool连接池。这些都不用配置 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>
        <!-- 工具类，pojo注解，Slf4j注解，使用log日志，抽象层lombok提供，实现层springboottest提供 -->
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





### emojiUtil：

```java
package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmojiUtil {

    /**
     * @Description emoji表情转换
     *
     * @param str 待转换字符串
     * @return 转换后字符串
     * @throws UnsupportedEncodingException
     */
    public static String emojiConvertToUtf(String str)
            throws UnsupportedEncodingException {
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(
                        sb,
                        "[[" + URLEncoder.encode(matcher.group(1),
                                "UTF-8") + "]]");
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * @Description 还原emoji表情的字符串
     *
     * @param str 转换后的字符串
     * @return 转换前的字符串
     * @throws UnsupportedEncodingException
     */
    public static String utfemojiRecovery(String str)
            throws UnsupportedEncodingException {
        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb,
                        URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}

```





### Rest

controller中响应请求返回响应结果：

> 增加post成功：返回 201  

```java
return ResponseEntity.status(HttpStatus.CREATED).build();
```

> 删除delete成功：返回204

```java
return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
```

> 修改put成功：返回200（操作成功） 或 204（操作已成功执行，但无数据返回）

```java
return ResponseEntity.status(HttpStatus.OK).build();
```

> 查询get成功：返回200 

```java
return ResponseEntity.ok(list);
```





![1564119565028](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1564119565028.png)

