package com.itheima;

import com.google.gson.Gson;
import com.itheima.controller.DeptController;
import com.itheima.mapper.DeptMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.utils.HeaderGenerator;
import com.itheima.utils.HeaderParser;
import com.itheima.utils.TokenParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class TliasWebManage1ApplicationTests {

    @Autowired//从Spring的IOC容器中，获取类型是EmpMapper的对象并注入
    private EmpMapper empMapper;

    @Autowired
    private DeptMapper deptMapper;

    /*    根据id删除员工*/

    /* 新增员工*/
    @Test
     public void testInsert(){
         //     创建员工对象
            Emp emp=new Emp();
            emp.setUsername("tom");
            emp.setName("汤姆");
            emp.setImage("1.jpg");
            emp.setGender((short)1);
            emp.setJob((short)1);
            emp.setEntrydate(LocalDate.of(2000,1,1));
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            emp.setDeptId(1);

         //  调用添加员工的方法
            empMapper.insert(emp);
            System.out.println(emp.getDeptId());
    }

    /*     修改员工信息*/

    @Test
    public void testUpdate(){
        //要修改的员工信息
        Emp emp = new Emp();
        emp.setId(23);
        emp.setUsername("songdaxia");
        emp.setPassword(null);
        emp.setName("小宋");
        emp.setImage("2.jpg");
        emp.setGender((short)1);
        emp.setJob((short)2);
        emp.setEntrydate(LocalDate.of(2012,1,1));
        emp.setCreateTime(null);
        emp.setUpdateTime(LocalDateTime.now());
        emp.setDeptId(2);
        //调用方法，修改员工数据
        empMapper.update(emp);
    }

/*
    @Test
    public void testUpdate2(){
        //要修改的员工信息
        Emp emp = new Emp();
        emp.setId(23);
        emp.setUsername("songdaxia");
        emp.setUpdateTime(LocalDateTime.now());
        //调用方法，修改员工数据
        //   此时会报错  解决方案：<set>：动态的在SQL语句中插入set关键字，并会删掉额外的逗号。（用于update语句中）
        empMapper.update(emp);
    }
*//*


     */
    /*根据id查询员工信息*//*

    @Test
    public void testGetById(){
        Emp emp=empMapper.getById(1);
        System.out.println(emp);
    }

    */
    /*根据条件查询员工信息*//*

     */
/*
    @Test
    public void testList(){
        //性别数据为null、开始时间和结束时间也为null
        List<Emp> list = empMapper.list("张", null, null, null);
        //姓名为null 此时会报错 预编译的sql语句会多一个and关键字
        // List<Emp> list = empMapper.list(null, (short)1, null, null);
        //传递的数据全部为null 此时会报错 预编译的sql语句会多一个where关键字
        //List<Emp> list = empMapper.list(null, null, null, null);
//      以上问题的解决方案：使用`<where>`标签代替SQL语句中的where关键字
        for (Emp emp : list) {
            System.out.println(emp);
        }
    }
*/

    @Test//生成Jwt令牌
    public void genJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "tom");

        String jwt = Jwts.builder()
                .setClaims(claims) //自定义内容(载荷)
                .signWith(SignatureAlgorithm.HS256, "itheima") //签名算法
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 3600 * 1000)) //有效期
                .compact();

        System.out.println(jwt);
    }

    @Test//解析Jwt令牌
    public void parseJwt() {
        Claims claims = Jwts.parser().setSigningKey("itheima")//指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjk5NjAwODU1LCJ1c2VybmFtZSI6InRvbSJ9.GF4puU_NQyM0e4oFdGHq2T1aGkwFSOJvI6sWfIJw0H4")
                .getBody();
        System.out.println(claims);//{id=1, exp=1699600855, username=tom}


    }


    @Autowired
    private ApplicationContext applicationContext;//IOC容器对象

//    获取Bean对象
//默认bean的作用域为：singleton (单例)
    @Test
    public void testGetBean(){
        //方式一根据bean的名称获取Bean对象
        DeptController bean1 =(DeptController) applicationContext.getBean("deptController");
        System.out.println(bean1);
        //方式二根据bean的类型获取Bean对象
        DeptController bean2 = applicationContext.getBean(DeptController.class);
        System.out.println(bean2);
        //方式三根据bean的名称 及 类型获取Bean对象
        DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
        System.out.println(bean3);

    }

    //Bean的作用域 加注解@lazy延迟加载bean对象
    @Test
    public void testScope(){
        for (int i = 0; i < 10; i++) {
            DeptController deptController = applicationContext.getBean(DeptController.class);
            System.out.println(deptController);
        }
    }


    @Test//Bean的作用域为非单例
    public void testScope1(){

        for (int i = 0; i < 10; i++) {
            DeptController deptController = applicationContext.getBean(DeptController.class);
            System.out.println(deptController);
        }
    }

    //第三方bean管理
    @Autowired
    private SAXReader saxReader;
    @Test
    public void testThirdBean() throws DocumentException {
        Document document = saxReader.read(new File("E:\\SSX\\tlias-web-manage1\\src\\main\\resources\\static\\1.xml"));
        Element rootElement = document.getRootElement();
        String name = rootElement.element("name").getText();
        String age = rootElement.element("age").getText();
        System.out.println(name + " : " + age);

    }

    @Test
    public void testTokenParse(){
        System.out.println(applicationContext.getBean(TokenParser.class));
    }

    @Test
    public void testHeaderParser(){
        System.out.println(applicationContext.getBean(HeaderParser.class));
    }

    @Test
    public void testHeaderGenerator(){
        System.out.println(applicationContext.getBean(HeaderGenerator.class));
    }




}



