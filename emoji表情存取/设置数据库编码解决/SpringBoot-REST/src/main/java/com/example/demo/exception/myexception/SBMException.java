package com.example.demo.exception.myexception;

import com.example.demo.exception.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 通用异常
 * 抛异常时抛这个异常
 *
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SBMException extends RuntimeException {
    private ExceptionEnum exceptionEnum;
}
