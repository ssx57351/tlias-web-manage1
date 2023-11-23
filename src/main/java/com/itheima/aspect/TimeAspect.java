package com.itheima.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Slf4j
@Component//该类交给IOC容器管理
@Aspect//当前类为切面类
public class TimeAspect {

    //切面：Aspect**，描述通知与切入点的对应关系（通知+切入点）
    //切入点表达式
    @Around("execution(* com.itheima.service.impl.DeptServiceImpl.*(..))")
    public Object recordTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        //记录开始时间
        long begin = System.currentTimeMillis();

        //执行原始方法
        Object result = proceedingJoinPoint.proceed();

        //记录方法执行结束时间
        long end = System.currentTimeMillis();

        //计算方法执行耗时
        log.info(proceedingJoinPoint.getSignature()+"执行耗时: {}毫秒",end-begin);

        return result;
    }


}
