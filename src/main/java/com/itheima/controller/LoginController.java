package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import com.itheima.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private EmpService empService;

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
       Emp loginEmp= empService.login(emp);
       if (loginEmp!=null){
//           自定义信息
           Map<String,Object> claims=new HashMap<>();
           claims.put("id",loginEmp.getId());
           claims.put("username",loginEmp.getUsername());
           claims.put("name",loginEmp.getName());

//           使用Jwt工具类。生成身份令牌
           String token= JwtUtils.generateJwt(claims);
           return Result.success(token);
       }
       return Result.error("用户名或密码错误");

    }

}
