在**虚拟机配置安装好测试成功**后，使用本机java客户端连接FastDFS使用文件上传服务。

代码：FastDFS_Demo

> jdk1.8
>
> idea2019.1.3
>
> fastdfs-client1.26.1-RELEASE
>
> spring-boot-starter-parent2.1.7.RELEASE
>
> hosts文件添加域名ip映射：192.168.1.205 image.leyou.com
>
> 192.168.1.205 为虚拟机ip

# pom

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
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>com.github.tobato</groupId>
            <artifactId>fastdfs-client</artifactId>
            <version>1.26.1-RELEASE</version>
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

# config

> 路径：hx.insist.demo.config.FastClientImporter.java

```java
package hx.insist.demo.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

//自动帮我们注入FastDFS客户端
@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastClientImporter {
}
```

# application.yml

```yml

fdfs:
  so-timeout: 1501
  connect-timeout: 601
  thumb-image: # 缩略图
    width: 60
    height: 60
  tracker-list: # tracker地址 你的虚拟机ip+tracker端口
    - 192.168.1.205:22122
```

# 编写测试类

```java
package hx.insist.demo;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@RunWith(SpringRunner.class)
@SpringBootTest//(classes = LyUploadService.class)
public class FdfsTest {

    @Autowired
    private FastFileStorageClient storageClient;//storage客户端，我们主要使用storage上传文件

    @Autowired
    private ThumbImageConfig thumbImageConfig;//支持附带缩略图上传的类

    @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("F:\\images\\ert.jpg");
        // 上传并且生成缩略图
        StorePath storePath = this.storageClient.uploadFile(
                new FileInputStream(file), file.length(), "jpg", null);
        // 带分组的路径
        System.out.println(storePath.getFullPath());
        // 不带分组的路径
        System.out.println(storePath.getPath());
    }

    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException {
        File file = new File("F:\\images\\asdf.jpg");
        // 上传并且生成缩略图
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                new FileInputStream(file), file.length(), "jpg", null);
        // 带分组的路径
        System.out.println(storePath.getFullPath());
        // 不带分组的路径
        System.out.println(storePath.getPath());
        // 获取缩略图路径
        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);
    }
}
```

# 本文总目录

![1565341743222](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565341743222.png)

其中：

- pom里配置了FastDFS客户端的依赖

- application.yml里配置了客户端的相关参数，主要是tracker地址
- FastClientImporter类帮我们注入FastDFS客户端
- FdfsTest类为测试类。

# 测试

## 文件上传：

**uploadFile**

- 运行testUpload()方法：

```java
 @Test
    public void testUpload() throws FileNotFoundException {
        File file = new File("F:\\images\\ert.jpg");
        // 上传并且生成缩略图
        StorePath storePath = this.storageClient.uploadFile(
                new FileInputStream(file), file.length(), "jpg", null);
        // 带分组的路径
        System.out.println(storePath.getFullPath());
        // 不带分组的路径
        System.out.println(storePath.getPath());
    }
```

- 结果：

![1565341869757](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565341869757.png)

第一行是全id，第二行是不带组的id。

- 访问

http://image.leyou.com/group1/M00/00/00/wKgBzV1NN3SACNaOAAekOLOwbiE252.jpg

![1565341588481](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565341588481.png)





## 图片上传：

**uploadImageAndCrtThumbImage**

> 图片上传可附带缩略图

- 运行testUploadAndCreateThumb()方法：

```java
@Test
    public void testUploadAndCreateThumb() throws FileNotFoundException {
        File file = new File("F:\\images\\asdf.jpg");
        // 上传并且生成缩略图
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(
                new FileInputStream(file), file.length(), "jpg", null);
        // 带分组的路径
        System.out.println(storePath.getFullPath());
        // 不带分组的路径
        System.out.println(storePath.getPath());
        // 获取缩略图路径
        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);
    }
```

- 结果：

![1565342201723](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565342201723.png)

第三行是缩略图的不带组名的id



- 访问

http://image.leyou.com/group1/M00/00/00/wKgBzV1NOTqAYG4GAA32pyzpD50127.jpg

![1565342268570](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565342268570.png)





http://image.leyou.com/group1/M00/00/00/wKgBzV1NOTqAYG4GAA32pyzpD50127_60x60.jpg

![1565342313536](C:\Users\123\AppData\Roaming\Typora\typora-user-images\1565342313536.png)

