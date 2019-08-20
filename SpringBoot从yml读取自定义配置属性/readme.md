# 



使用：

在配置文件application.yml中配置自定义属性，然后可在任意类中采用@Autowried方式读取此属性。





# pom

> web依赖
>
> lombok依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.1.7.RELEASE</version>
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
            <artifactId>spring-boot-starter</artifactId>
        </dependency>
        <!--不添加web依赖，则controller层中的注解都没法使用-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
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

# application.yml

```yml
# 简单属性注入
hx:
  ctj:
    name: xxx
    pro: hhh
    hlist:
      - www
      - search
      - manager
      - api
    hstr: strstrstr

# 复杂属性注入
hh:
  xxx:
    name: xxxxx
    sg: 186
    tz: 70
    jj: 18
  hlist:
    - qqq
    - www
    - eee

```

# 启动类

> 启动类不用改动

```java
package hx.insist.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
```

# 自定义属性类

> 要想可以注入，需先将属性映射为对应的类

## 简单属性

> testProperties.java
>
> 都是一层属性，在hx.ctj下面的。

```java
package hx.insist.demo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @ClassName testProperties
 * @Description //TODO 使用application.yml文件注入属性
 * @Author 123
 * @Date 2019/8/12 10:05
 * @Version 1.0
 **/
@Data
@ConfigurationProperties(prefix = "hx.ctj")
public class testProperties {
    private String name;
    private String pro;
    private List hlist;
    private String hstr;
}

```

## controller中注入

```java
package hx.insist.demo.web;

import hx.insist.demo.config.hxProperties;
import hx.insist.demo.config.testProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName testUserController
 * @Description //TODO
 * @Author 123
 * @Date 2019/8/12 10:07
 * @Version 1.0
 **/
@Slf4j
@RestController
@EnableConfigurationProperties(testProperties.class)
public class testUserController {

    @Autowired
    private testProperties prop;

    //-------------------------简单属性-----------------------

    @GetMapping("testN")
    public ResponseEntity<String> testNamepro(){
        return ResponseEntity.ok(prop.getName());
    }

    @GetMapping("testP")
    public ResponseEntity<String> testProPro(){
        return ResponseEntity.ok(prop.getPro());
    }

    @GetMapping("testL")
    public ResponseEntity<List> testListPro(){
        List list = prop.getHlist();
        list.remove(1);//此处移除一个元素
        return ResponseEntity.ok(prop.getHlist());
    }

    @GetMapping("testS")
    public ResponseEntity<String> testStrPro(){
        return ResponseEntity.ok(prop.getHstr());
    }
}

```



- 集合：

![1565577655830](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565577655830.png)

- String
- ![1565577691507](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565577691507.png)

## 复杂属性

> hxProperties.java
>
> 存在两层属性，hx下的xxx可以看作一个类，类下有name、sg、tz、jj等属性；hx下的hlist是一个集合。

## controller中注入

```java
package hx.insist.demo.web;

import hx.insist.demo.config.hhProperties;
import hx.insist.demo.config.hhXXXProperties;
import hx.insist.demo.config.testProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName testUserController
 * @Description //TODO
 * @Author 123
 * @Date 2019/8/12 10:07
 * @Version 1.0
 **/
@Slf4j
@RestController
@EnableConfigurationProperties({testProperties.class, hhProperties.class})
//@EnableConfigurationProperties(hhProperties.class)
public class testUserController {

    @Autowired
    private testProperties prop;
    @Autowired
    private hhProperties fzprop;

    //--------------------fz----------------------------

    @GetMapping("testhxL")
    public ResponseEntity<List> testhxList(){
        return ResponseEntity.ok(fzprop.getHlist());
    }

    @GetMapping("testhhXXP")
    public ResponseEntity<hhXXXProperties> testhhXXXP(){
        log.info(String.valueOf(fzprop.getXxx()));
        return ResponseEntity.ok(fzprop.getXxx());
    }

    //------------------------------------------------

    @GetMapping("testN")
    public ResponseEntity<String> testNamepro(){
        return ResponseEntity.ok(prop.getName());
    }

    @GetMapping("testP")
    public ResponseEntity<String> testProPro(){
        return ResponseEntity.ok(prop.getPro());
    }

    @GetMapping("testL")
    public ResponseEntity<List> testListPro(){
        List list = prop.getHlist();
        list.remove(1);
        return ResponseEntity.ok(prop.getHlist());
    }

    @GetMapping("testS")
    public ResponseEntity<String> testStrPro(){
        return ResponseEntity.ok(prop.getHstr());
    }
}

```

- 内部类属性

![1565578452485](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565578452485.png)

- 一层集合属性

![1565578525848](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565578525848.png)



# 目录结构

![1565578581961](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565578581961.png)

# 总结

## 1、配置文件

在application.yml中自定义属性

## 2、配置类

> getter setter
>
> @ConfigurationProperties(prefix = "xxx.xxx")

编写对应属性类。定义getter setter方法；添加@ConfigurationProperties(prefix = "hx.ctj")注解，指定自定义属性对应的前缀。

- 这里注意，属性中的名字，必须跟配置文件中的**名字完全一致**。

  ```java
  private String name;
  private String pro;
  private List hlist;
  private String hstr;
  ```

  ```yml
  hx:
    ctj:
      name: xxx
      pro: hhh
      hlist:
        - www
        - search
        - manager
        - api
      hstr: strstrstr
  ```

  **name**、**pro**、**hlist**、**hstr**。

- 由于只能添加一个前缀信息（不太确定），所以如果有**多层配置信息**，则需要**定义另一个类**，嵌套放到大类中。

  ```java
  private List hlist;
  private hhXXXProperties xxx;
  ```

  ```yml
  hh:
    xxx:
      name: xxxxx
      sg: 186
      tz: 70
      jj: 18
    hlist:
      - qqq
      - www
      - eee
  ```

  hh下的xxx为一个属性，但是这个属性是一个类，里面包含了name、sg、tz、jj等信息。所以要将这些信息封装为hhXXXProperties类。

  ```java
  @Data
  @ConfigurationProperties(prefix = "hh.xxx")
  public class hhXXXProperties {
      private String name;
      private Integer sg;
      private Integer tz;
      private Integer jj;
  }
  ```

- 在添加注解`@ConfigurationProperties`时，会出现错误警告，点击idea右上角‘扳手’式样的图标，弹出框中第一条：'show xxxx'，将此框**去掉勾选**。

## 3、注入

> @EnableConfigurationProperties(XXX.class)
>
> @Autowired

在需要的地方注入使用。添加`@EnableConfigurationProperties`注解。此注解一个类上只能添加一个，所以如果只有一个自定义属性需要注入，写法：

`@EnableConfigurationProperties(testProperties.class)`

如果有两个或多个自定义属性需要注入，写法：

`@EnableConfigurationProperties({testProperties.class, hhProperties.class})`；

然后使用`@Autowired`注入即可。

```java
	@Autowired
    private testProperties prop;
    @Autowired
    private hhProperties fzprop;
```



