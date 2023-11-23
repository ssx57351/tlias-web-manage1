package com.itheima.controller;

import com.itheima.mapper.DeptMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Lazy//延迟加载（第一次使用bean对象时，才会创建bean对象并交给ioc容器管理）
@Scope("prototype")//bean作用域为非单例
@Slf4j//加上该注解会自动生成logger对象 对象名：log
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*查询部门信息*/
    //@RequestMapping(value = "/depts" , method = RequestMethod.GET)
    @GetMapping
      public Result list(){
        log.info("查询所有部门数据");
//        1.调用service层，获取数据
        List<Dept> deptList = deptService.list();
//        2.相应数据
        return Result.success(deptList);
      }


    /*根据id删除部门数据*/
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) throws Exception{
        log.info("根据id删除部门");
        deptService.delete(id);
        return Result.success();
    }


    /*新增部门*/
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门：{}",dept);
        deptService.add(dept);
        return Result.success();
    }

    /*根据id查询部门*/
    @GetMapping("/{id}")
    public Result select(@PathVariable Integer id){
        log.info("根据id查询部门：{}",id);
        Dept dept = deptService.select(id);
        return Result.success(dept);
    }

  /*  修改部门信息*/
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("更新部门：{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
