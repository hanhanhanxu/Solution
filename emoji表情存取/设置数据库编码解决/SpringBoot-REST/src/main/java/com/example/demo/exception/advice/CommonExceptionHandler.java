package com.example.demo.exception.advice;

import com.example.demo.exception.myexception.SBMException;
import com.example.demo.exception.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice//默认 自动拦截所有加了@Controller注解的类
public class CommonExceptionHandler {

    //自定义方法拦截异常，此方法拦截SBMException异常
    @ExceptionHandler(SBMException.class)
    public ResponseEntity<ExceptionResult> handleException(SBMException e){
        return ResponseEntity.status(e.getExceptionEnum().getCode())
                .body(new ExceptionResult(e.getExceptionEnum()));
        //body里面不再时e.getMsg()。而是我们自定义的ExceptionResult，只是将异常相关数据包装起来而已。
    }
}