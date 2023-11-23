package com.itheima.service;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;

import java.util.List;

//部门业务规则
public interface DeptService {

    /*查询所有部门数据*/
    List<Dept> list();

    /*根据id删除部门*/
    void delete (Integer id) throws Exception;


    /*新增部门*/
    void add(Dept dept);

    /*根据id查询部门*/
    Dept select(Integer id);

    /*更新部门*/
    void update(Dept dept);


}
