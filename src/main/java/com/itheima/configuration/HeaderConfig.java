package com.itheima.configuration;

import com.itheima.utils.HeaderGenerator;
import com.itheima.utils.HeaderParser;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HeaderConfig {


    //@ConditionalOnClass(name = "com.itheima.utils.JwtUtils")//环境中存在指定的这个类，才会将该bean加入IOC容器
    //@ConditionalOnMissingBean //不存在该类型的bean，才会将该bean加入IOC容器
    //@ConditionalOnMissingBean(name="deptController2")//不存在指定名称的bean，才会将该bean加入IOC容器
    //@ConditionalOnMissingBean(HeaderConfig.class)//不存在指定类型的bean，才会将bean加入IOC容器
    @Bean
    @ConditionalOnProperty(name = "name",havingValue = "ssx")//配置文件中存在指定属性名与值，才会将bean加入IOC容器
    public HeaderParser headerParser(){
        return new HeaderParser();
    }



    @Bean
    public HeaderGenerator headerGenerator(){
        return new HeaderGenerator();
    }
}
