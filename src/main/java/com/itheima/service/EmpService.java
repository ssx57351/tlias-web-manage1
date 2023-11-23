package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;

import java.time.LocalDate;
import java.util.List;

//员工的业务规则
public interface EmpService {

 //    根据id查询员工
    public Emp select(Integer id);


//    根据id批量删除
    public void delete(List<Integer> ids);

//    新增员工
    public void save(Emp emp);

//    更新员工
    public void update(Emp emp);



//    条件分页查询员工
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

//    用户登录
    Emp login(Emp emp);
}
