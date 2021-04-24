package cn.haitaoss.tinyspringmvc.webappProject.controller;

import cn.haitaoss.tinyioc.beans.annotation.Component;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 19:24
 * 这个是增强方法
 */
// 注入到容器中
@Component
public class TimerInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long time = System.nanoTime();
        System.out.println("Invocation of Method " + invocation.getMethod().getName() + " start!");
        Object proceed = invocation.proceed();
        System.out.println("Invocation of Method " + invocation.getMethod().getName() + " end! takes " + (System.nanoTime() - time)
                + " nanoseconds.");
        return proceed;
    }
}
