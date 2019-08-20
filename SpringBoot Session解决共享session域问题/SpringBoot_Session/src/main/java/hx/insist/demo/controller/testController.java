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