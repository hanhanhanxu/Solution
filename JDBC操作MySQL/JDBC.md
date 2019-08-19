![img](https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/sign=9e9cfb9ed63f8794c7f2407cb3726591/6c224f4a20a44623052ebd449a22720e0cf3d72b.jpg)

# JDBC

JDBC（Java DataBase Connectivity,java数据库连接）是一种用于**执行SQL语句的Java API**，可以为多种关系数据库提供统一访问，它由一组用[Java语言](https://baike.baidu.com/item/Java语言)编写的类和接口组成。JDBC提供了一种基准，据此可以构建更高级的工具和接口，使数据库开发人员能够编写数据库应用程序

JDBC相关Api是java语言封装好的接口，直接引入 **import java.sql.*** 即可使用（JDBC接口及相关类在**java.sql**包和**javax.sql**包里）。但是它只是一个接口，需要具体的数据库厂商实现接口。所以要引入相关驱动，例如java操作mysql的依赖：mysql-connector-java-8.0.16.jar



相关推荐：

https://blog.csdn.net/y277an/article/details/96937010