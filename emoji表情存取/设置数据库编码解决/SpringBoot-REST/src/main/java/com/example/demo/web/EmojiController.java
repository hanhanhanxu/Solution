package com.example.demo.web;

import com.example.demo.entity.Testemoji;
import com.example.demo.exception.enums.ExceptionEnum;
import com.example.demo.exception.myexception.SBMException;
import com.example.demo.service.TestemojiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

import java.util.List;

/**
 * @ClassName EmojiController
 * @Description //TODO
 * @Author 123
 * @Date 2019/8/1 17:56
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("emoji")
public class EmojiController {
    @Autowired
    private TestemojiService service;

    //增
    @PostMapping
    public ResponseEntity<Void> add(Testemoji testemoji){
        //如果没有emoji属性的信息，就认为没有参数
        if(StringUtil.isEmpty(testemoji.getEmoji()))
            throw new SBMException(ExceptionEnum.ILLEGAL_REQUEST_DATA);
        service.save(testemoji);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //删
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id")int id){
        service.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //改
    @PutMapping
    public ResponseEntity<Void> modifyById(Testemoji testemoji){
        if (testemoji.getId()==null || testemoji.getEmoji()==null)
            throw new SBMException(ExceptionEnum.ILLEGAL_REQUEST_DATA);
        service.updateById(testemoji);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //查
    @GetMapping
    public ResponseEntity<List<Testemoji>> queryAll(){
        List<Testemoji> list = service.queryAll();
        return ResponseEntity.ok(list);
        //return ResponseEntity.ok().body(list); 一样
    }
}
/**
 * ResponseEntity
 *表示整个HTTP响应：状态代码，标题和正文。
 *
 */