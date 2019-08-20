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
