package com.itheima.service.impl;

import com.itheima.anno.Log;
import com.itheima.mapper.DeptLogMapper;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Dept;
import com.itheima.pojo.DeptLog;
import com.itheima.pojo.Result;
import com.itheima.service.DeptLogService;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service//包含@Component
@Slf4j
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private DeptLogService deptLogService;

    /*查询部门信息*/
    @Override
    public List<Dept> list() {
      List<Dept> deptList=  deptMapper.list();
        return deptList;
    }

    /*根据id删除部门并删除该部门下的所有员工*/
    @Override
    @Log
   // @Transactional//当前方法添加了事物管理 如果中间出现异常(默认只有运行时异常 RuntimeException)，事务会自动回滚
   @Transactional(rollbackFor = Exception.class)//只要出现异常就会回滚事务
   //propagation 属性来指定传播行为
//    主要的两个：1）REQUIRED【默认值】需要事务，有则加入，无则创建新事务
//              2）REQUIRES_NEW需要新事务，无论有无，总是创建新事务

    public void delete(Integer id) throws Exception{

        //删除部门
        try {
            deptMapper.deleteById(id);

            //如果在这里出现异常，代码下面就不在执行，会导致数据不同（部门删除，但该部门下员工还在）
            //解决方案：在方法运行之前，开启事务，如果方法成功执行，就提交事务，如果方法执行的过程当中出现异常了，就回滚事务。

            //模拟异常
            if (true){
                throw new Exception("出现异常了-------");
            }
            //删除部门下的所有员工信息
            empMapper.deleteByDeptId(id);
        } finally {
//            无论是否有异常，最终都要执行以下代码往数据库中记录日志
            DeptLog deptLog = new DeptLog();
            deptLog.setCreateTime(LocalDateTime.now());
            deptLog.setDescription("执行了解散部门的操作，此时解散的是"+id+"号部门");


            //调用其他业务类中的方法
            deptLogService.insert(deptLog );
        }

    }

    /*新增部门*/
    @Override
    @Log
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    /*根据id查询部门*/
    @Override
    public Dept select(Integer id) {
        Dept dept = deptMapper.selectById(id);
        return dept;
    }

    /*更新部门*/
    @Override
    @Log
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }




}
