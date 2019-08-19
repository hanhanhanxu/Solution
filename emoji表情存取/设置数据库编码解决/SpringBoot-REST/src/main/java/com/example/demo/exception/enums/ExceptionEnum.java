package com.example.demo.exception.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ExceptionEnum {

    /**
     * 4  客户端出错  - 请求参数有误
     * 5  服务端出错  - 服务器处理错误
     */

    ILLEGAL_REQUEST_DATA(400,"非法请求参数"),
    PRICE_CANNOT_BE_NULL(400,"价格不能为空"),
    NAME_CANNOT_BE_NULL(400,"名称不能为空"),
    FILE_CANNOT_BE_BULL(400,"文件不能位空"),
    NOT_FOUND(404,"查询内容为空"),
    UNALLOW_FILE_TYPE(400,"不被允许的文件类型"),
    //READ_IMAGE_ERROR(400,"读取图片失败"),
    SAVE_ERROR(500,"保存失败"),
    UPLOAD_IMAGE_ERROR(500,"上传图片失败"),
    ;
    private int code;
    private String msg;
}
