package com.itheima.configuration;

import com.itheima.service.DeptService;
import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//配置类 （在配置类当中对第三方bean进行集中管理）
              //底层就是@Component，所以配置类最终也是SpringIOC容器当中的一个bean对象
public class CommonConfig {

    //声明第三方bean

    @Bean(name = "saxReader")//将当前方法的返回值对象交给IOC容器管理, 成为IOC容器bean
         //通过@Bean注解的name/value属性指定bean名称, 如果未指定, 默认是方法名
         //如果第三方bean需要依赖其它bean对象，直接在bean定义方法中设置形参即可，容器会根据类型自动装配。
    public SAXReader reader(DeptService deptService){
        System.out.println(deptService);
        return new SAXReader();
    }
}
