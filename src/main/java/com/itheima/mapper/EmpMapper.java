package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Mapper
public interface EmpMapper {

    /*
     * 根据id删除数据（支持批量删除）
     * @param id 用户id
     */
    public void delete(List<Integer> ids);

    /*更新 根据id修改员工信息*/
    public void update(Emp emp);


    /*插入新增员工*/
    /*主键返回实现  会自动将生成的主键值，赋值给emp对象的id属性*/
   // @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) values (#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    public void insert(Emp emp);


    /*根据id查询员工信息*/
    @Select("select id,username,password,name,gender,image,job,entrydate,dept_id,create_time,update_time from emp where id=#{id}")
    public Emp getById(Integer id);



    /*条件分页查询*/
/*       //获取总记录数
    @Select("select count(*) from emp")
    public Long count();
      //获取当前页的结果列表
    @Select("select * from emp limit #{start},#{pageSize}")
    public List<Emp> list(Integer start,Integer pageSize);
*/
    //获取当前页的结果列表
    public ArrayList<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);


//    用户登录
    @Select("select id,username,password,name,gender,image,job,entrydate,dept_id,create_time,update_time" +
            " from emp where username=#{username} and password=#{password}")
   public Emp getByUsernameAndPassword(Emp emp);

    //根据部门id删除部门下所有员工
    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer id);
}
