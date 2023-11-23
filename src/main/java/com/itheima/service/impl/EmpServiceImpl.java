package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.anno.Log;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;



    /*批量删除员工*/
    @Override
    @Log
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    /*新增员工*/
    @Override
    @Log
    public void save(Emp emp) {
        //补全数据
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //调用添加方法
        empMapper.insert(emp);
    }

    /*修改员工*/
    @Override
    @Log
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);

    }

    @Override
    public Emp select(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        设置分页参数
        PageHelper.startPage(page,pageSize);

//        执行分页查询
        ArrayList<Emp> empList = empMapper.list(name, gender, begin, end);
 //       获取分页结果
        Page<Emp> p = (Page<Emp>) empList;
//        封装PageBean对象
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

//    用户登录
    @Override
    @Transactional
    public Emp login(Emp emp) {
        //调用dao层功能：登录
      Emp loginEmp=  empMapper.getByUsernameAndPassword(emp);
        //返回查询结果给Controller
      return loginEmp;
    }
}
