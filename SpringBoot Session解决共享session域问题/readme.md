> 本文参考https://mp.weixin.qq.com/s/xs67SzSkMLz6-HgZVxTDFw

# 问题：

在微服务中，我们的项目是多个、分开的，而有时候需要用到这样的需求：当用户访问一个服务，需要验证session，然后用户将其session储存进入服务器后，下次需要访问另一个微服务，而用户已经验证过身份了，所以不应该再需要验证session了，但是因为我们的微服务是分开的，多个微服务之间的session域是不共享的，相当于分离的几个项目，所以就会导致访问不同的微服务用户需要一次次的验证session的问题。



这时候就需要将session做成共享的。

# 解决方案：

我们的解决方案是使用redis，将session信息储存到redis中，每个微服务都去redis中储存、获取session，这样session就变为共享的。

![img](https://mmbiz.qpic.cn/mmbiz_png/GvtDGKK4uYkb715jWrJyFUIbMJ8jdG2Xtkfia7icnAJMRicNj1ZOefGokybC5taCPCEGSC0sWfuPKaTzysSUhm0iaw/640?wx_fmt=png&tp=webp&wxfrom=5&wx_lazy=1&wx_co=1)

# 操作：

## pom

创建springboot工程，勾选依赖：spring session 、 spring web 、 redis 

如果使用的springboot starter parent父工程的版本为2.1.4及以下，当前依赖就够了：

```xml

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
```

如果使用springboot starter parent父工程的版本为2.1.5及以上，就要额外添加spring security的依赖，

完整依赖如下：（下面是本次案例的pom）

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
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
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

## application.properties

```properties
server.port=

spring.redis.host=127.0.0.1
spring.redis.port=6379
spring.redis.password=liyalong
spring.redis.database=0
```

端口默认为6379, database默认为0 ， 所以这两个可以不配置。

server.port等会要使用它为变量注入值，所以必须要写上key



## 启动类

启动类不用改，默认就行

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

## controller

> 注意注解使用的包，不要导错包。

```java

package hx.insist.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @ClassName testController
 * @Description //TODO
 * @Author 123
 * @Date 2019/8/6 13:57
 * @Version 1.0
 **/
@RestController
public class testController {
    @Value("${server.port}")
    Integer port;

    @GetMapping("set")
    public String set(HttpSession session){
        //将user 储存入session域
        session.setAttribute("user","hanxu");
        return String.valueOf(port);
    }

    @GetMapping("get")
    public String get(HttpSession session){
        //从session域中拿到
        return session.getAttribute("user")+":"+port;
    }

}
```

现在，代码已经全部写好了。

## 启动redis

### server端

进入redis解压（安装）目录

![1565078704588](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565078704588.png)

在地址栏输入cmd，回车，在dos窗口输入

redis-server.exe redis.windows.conf

加载redis.windows.conf配置文件的方式启动redis的服务server端



![1565078684534](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565078684534.png)

启动成功

### client端

依旧是上述方式打开dos窗口，输入

redis-cli.exe

打开客户端

auth liyalong

验证密码 ， liyalong是密码。可以进入你的redis.windows.conf中查看：requirepass liyalong

![1565078855630](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565078855630.png)



## package打包

> 如果我们现在在idea中启动项目的话，默认SpringBoot的”约定“配置是8080端口，所以我们启动的项目只有8080端口的，但是我们要做两个微服务之前的session域共享， 一个项目肯定是不行的，所以我们要将项目打包后用两个端口分别启动，两个服务。

我们必须先运行一遍项目，才能正常打包。直接打包的话没有jar文件。

debug方式运行项目。（运行成功）

停掉项目。

用maven工具打包。（打包成功）

maven package

![1565078120142](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565078120142.png)

进入路径：D:\IntelliJ IDEA 2019.1.3\workspace\SpringBoot_Session\target（项目路径下的target目录里）

有两个jar文件。

![1565079298833](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565079298833.png)

## 启动项目

> 我们以两个端口启动两次项目

### 9090

在此地址栏输入cmd，进入dos，运行以下命令以9090端口启动项目。

```xml
java -jar demo-0.0.1-SNAPSHOT.jar --server.port=9090
```

启动成功

![1565079473368](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565079473368.png)

### 9091

上述方式再打开一个dos，运行以下命令以9091端口启动项目。

```xml
java -jar demo-0.0.1-SNAPSHOT.jar --server.port=9091
```

启动成功

![1565079584480](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565079584480.png)

## 访问

### 获取user

在浏览器输入地址访问：

http://localhost:9091/get

我们先访问9091，访问get接口，试图拿到session域中的user信息：

![1565079703874](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565079703874.png)

访问后自动跳到上述页面，这是由于我们引入spring security的关系，帮我们加了权限验证。

账号：user

密码：aaac609d-927a-47eb-9931-dd55cf769e93（去刚才启动9091的dos窗口查找）

复制密码时千万不要按ctrl+c，ctrl+c在当前窗口是强制停止服务的命令。正确的复制方法是：鼠标左键选中，然后点击鼠标右键即可。

![1565079800124](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565079800124.png)



这个账号密码也可以在application配置文件中配置

```properties
spring.security.user.name=hanxu
spring.security.user.password=hanxu
```





登陆：

![1565079923990](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565079923990.png)

可以看到此时session域中是没有user信息的，所以才显示出null。

### 储存user

我们再开一个浏览器标签，输入

http://localhost:9090/set

![1565079988633](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565079988633.png)

我们是访问端口为9090的服务，执行的端口为9090的set方法，此方法将user信息储存到了9090服务对应的session域中，如果是单个服务的话，session域只有一个。可现在我们是两个服务，session域按理说应该是不共享的，在9090中保存的user信息，在9091中是不会得到的，因为两个session域不是同一个。

那到底是不是这样呢？



### 再次获取user

我们再次访问http://localhost:9091/get

![1565080213965](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565080213965.png)

可以看到输出的已经是 hanxu:9091了，

从9091的session域中拿到了user信息。那么我们现在就做到了session域共享。怎么样，使用起来是不是跟单一session没什么区别！

这一切都是spring session，帮我们完成的，它将这个过程做了透明化，我们使用起来没有负担也是没什么感觉的，它自动监控session域中的变化，并将其储存到redis中，我们获取session域中的信息时，它再帮我们去redis中获取。

## redis

我们在redis客户端查看是否储存了session域信息：

keys *

![1565080990428](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565080990428.png)

可以看到之前是空的(empty list or set)

现在有了session域中的信息



# 验证

如果你不相信这是spring session帮我们做到的，你可以使用控制变量法验证：

将spring session的依赖注释掉

```xml
      <!--<dependency>
            <groupId>org.springframework.session</groupId>
            <artifactId>spring-session-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>-->
```

然后按照上面的步骤，写代码、启动redis、打包、访问等，最后访问，看看9091服务是否能够得到user中的信息

我做了验证，得不到，访问出错。



# tips：springboot的默认配置

本文中controller的接口可以将访问路径省略：

```java
@RestController
public class testController {
    @Value("${server.port}")
    Integer port;

    @GetMapping
    public String set(HttpSession session){
        //将user 储存入session域
        session.setAttribute("user","hanxu");
        return String.valueOf(port);
    }

    @GetMapping
    public String get(HttpSession session){
        //从session域中拿到
        return session.getAttribute("user")+":"+port;
    }

}
```

springboot默认”约定“配置了controller中，方法的访问路径为：方法名。所以我们只需给出请求方式即可，不用手动添加访问路径了。 

当我们添加

@GetMapping("set")

 @GetMapping("get")

时，set会替换掉默认的访问路径。





如果是这种情况

```java
@RestController
public class testController {

    //此方法的访问路径为world
    @GetMapping
    public String world(){
        return "world";
    }
    //此方法的访问路径为world
    @GetMapping("world")
    public String world2(){
        return "world";
    }
}
```

启动项目时，就会启动不起来，报testController的错误。
