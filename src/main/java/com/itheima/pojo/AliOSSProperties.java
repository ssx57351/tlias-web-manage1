package com.itheima.pojo;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
//第二种
@ConfigurationProperties(prefix = "aliyun.oss")//`@ConfigurationProperties`注解，并通过perfect属性来指定配置参数项的前缀
public class AliOSSProperties {
    //区域
    private String endpoint;
    //身份ID
    private String accessKeyId ;
    //身份密钥
    private String accessKeySecret ;
    //存储空间
    private String bucketName;
}
