package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

//员工管理控制器
@Slf4j
@RestController

@RequestMapping("/emps")
public class EmpController {

    @Autowired
   private EmpService empService;

    /*新增员工*/
    @PostMapping
    public Result save(@RequestBody Emp emp){ //把前端传递的json数据填充到实体类中
//      记录日志
        log.info("新增员工, emp:{}",emp);
        empService.save(emp);
        return Result.success();
    }

    /*根据id查询员工*/
    @GetMapping("/{id}")
    public Result select(Integer id){
        Emp emp = empService.select(id);
        return Result.success(emp);
    }


//    条件分页查询
    @GetMapping
    public Result page(@RequestParam(defaultValue = "1")Integer page,
                       @RequestParam(defaultValue = "10")Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
//        记录日志
        log.info("分页查询，参数：{},{},{},{},{},{}", page, pageSize,name, gender, begin, end);
//        调用业务层分页查询功能
       PageBean pageBean= empService.page(page,pageSize,name, gender, begin, end);
//       响应数据
       return Result.success(pageBean);
    }


//      删除员工
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids){
        empService.delete(ids);
        return Result.success();
    }


//    修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp){
        empService.update(emp);
        return Result.success();
    }
}
