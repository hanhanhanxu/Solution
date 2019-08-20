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

    @GetMapping("testhhL")
    public ResponseEntity<List> testhhList(){
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
