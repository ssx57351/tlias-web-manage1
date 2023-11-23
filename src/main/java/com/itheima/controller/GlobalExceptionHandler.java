package com.itheima.controller;

import com.itheima.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*@RestControllerAdvice = @ControllerAdvice + @ResponseBody
处理异常的方法返回值会转换为json后再响应给前端
**/
@RestControllerAdvice//表示当前类为全局异常处理器类
public class GlobalExceptionHandler {

//    处理异常
    @ExceptionHandler(Exception.class)//指定能够处理的异常类
    public Result ex(Exception e){
        e.printStackTrace();//打印堆栈中的异常信息

//        捕获异常之后，响应统一结果
        return Result.error("对不起,操作失败,请联系Miss宋");
    }
}
