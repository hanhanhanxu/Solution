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
