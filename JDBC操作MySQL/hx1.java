package July.JDBC;


import java.sql.*;

/*
PreparedStatement 对象：
- executeUpdate 执行insert update delete DDL 语句
                返回int类型，操作成功的语句数
- executeQuery  执行select 语句
                返回ResultSet 对象，不会返回Null
- execute       执行任何种类的SQL语句，也可处理复杂语句。
                execute 方法返回一个 boolean 值，指示第一个结果的形式。
                必须调用 getResultSet 或 getUpdateCount 方法获取该结果，必须调用 getMoreResults 获取任何后续结果。
                返回：
                如果第一个结果是 ResultSet 对象，则返回 true；如果第一个结果是更新计数或者没有结果，则返回 false
 */



public class hx1 {

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/springbootdemo?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "hanxu";

    public static void main(String[] args) {

        try{
            //注册驱动程序,注意：需要导入mysql-connector-java.jar，或者pom引入依赖。
            // 版本是8的话，Driver使用cj，版本是5的话就不用cj
            Class.forName(JDBC_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("class not fount! please check your JDBC_DRIVER.");
        }

        try {
            //获取连接
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            //选择方法执行
            //add(conn);
            //delete(conn);
            //update(conn);
            //select(conn);

            //关闭资源
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void add(Connection conn) throws SQLException {
        String sql = "insert into users (userName,passWord,user_sex,nick_name) values (?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"test");
        ps.setString(2,"test");
        ps.setString(3,"test");
        ps.setString(4,"test");
        int i = ps.executeUpdate();
        if(i>=0)
            System.out.println("===============执行 *插入* 成功："+i+"===============");
        else
            System.out.println("===============执行 *插入* 失败："+i+"===============");
        //关闭资源
        ps.close();
    }

    public static void delete(Connection conn) throws SQLException {
        String sql = "delete from users where id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,41);
        int i = ps.executeUpdate();
        if(i>=0)
            System.out.println("===============执行 *删除* 成功："+i+"===============");
        else
            System.out.println("===============执行 *删除* 失败："+i+"===============");
        //关闭资源
        ps.close();
    }

    public static void update(Connection conn) throws SQLException {
        String sql = "update users set userName = ? where id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"hanxu");
        ps.setInt(2,42);
        int i = ps.executeUpdate();
        if(i>=0)
            System.out.println("===============执行 *更改* 成功："+i+"===============");
        else
            System.out.println("===============执行 *更改* 失败："+i+"===============");
        //关闭资源
        ps.close();
    }

    public static void select(Connection conn) throws SQLException {
        String sql = "select * from users";

        //预编译sql
        PreparedStatement ps = conn.prepareStatement(sql);
        //执行查询，得到结果
        ResultSet rs = ps.executeQuery();
        //遍历结果
        int i=1;
        while(rs.next()){
            System.out.println("===============第"+ i +"条数据===============");
            System.out.println("id:"+rs.getString("id"));
            System.out.println("userName:"+rs.getString("userName"));
            System.out.println("passWord:"+rs.getString("passWord"));
            System.out.println("user_sex:"+rs.getString("user_sex"));
            System.out.println("nick_name:"+rs.getString("nick_name"));
            i++;
        }
        //关闭资源
        rs.close();
        ps.close();
    }

    //一系列过程
    public static void test(String[] args) {
        String sql = "select * from users";

        try{
            //注册驱动程序,注意：需要导入mysql-connector-java.jar，或者pom引入依赖。
            // 版本是8的话，Driver使用cj，版本是5的话就不用cj
            Class.forName(JDBC_DRIVER);
        }catch (ClassNotFoundException e){
            System.out.println("class not fount! please check your JDBC_DRIVER.");
        }

        try {
            //获取连接
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            //预编译sql
            PreparedStatement ps = conn.prepareStatement(sql);
            //执行查询，得到结果
            ResultSet rs = ps.executeQuery();
            //遍历结果
            int i=1;
            while(rs.next()){
                System.out.println("===============第"+ i +"条数据===============");
                System.out.println("id:"+rs.getString("id"));
                System.out.println("userName:"+rs.getString("userName"));
                System.out.println("passWord:"+rs.getString("passWord"));
                System.out.println("user_sex:"+rs.getString("user_sex"));
                System.out.println("nick_name:"+rs.getString("nick_name"));
                i++;
            }
            //关闭资源
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            //System.out.println("GetConnection is error, please check your URL / USERNAME / PASSWORD .");
        }


    }


}