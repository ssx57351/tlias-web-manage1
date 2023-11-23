package com.itheima;

import com.itheima.configuration.HeaderConfig;
import com.itheima.utils.EnableHeaderConfig;
import com.itheima.utils.MyImportSelector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

//1.使用@import导入普通类
//@Import(TokenParser.class)
//2.使用@import导入配置类
//@Import(HeaderConfig.class)
//3.使用@Import导入ImportSelector接口实现类
//@Import(MyImportSelector.class)//导入ImportSelector接口实现类
@EnableHeaderConfig//加上该注解表示使用第三方依赖提供的Enable开头的注解


@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.itheima.service.impl","com.itheima.service","com.itheima.pojo","com.itheima.mapper"})
@ServletComponentScan//开启SpringBoot项目对于Servlet组件的支持
public class TliasWebManage1Application {

    public static void main(String[] args) {
        SpringApplication.run(TliasWebManage1Application.class, args);
    }
/*

    //怎么使用并定义第三方的bean？
    //解决方案1：在启动类上添加@Bean标识的方法(不建议用)
    //解决方案2：在配置类中定义@Bean标识的方法
    @Bean//将当前方法的返回值对象交给IOC容器管理, 成为IOC容器bean
    public SAXReader saxReader(){
        return new SAXReader();
    }
*/


}
