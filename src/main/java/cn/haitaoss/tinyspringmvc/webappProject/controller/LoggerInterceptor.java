package cn.haitaoss.tinyspringmvc.webappProject.controller;

import cn.haitaoss.tinyspringmvc.framework.handlerMapping.HandleInterceptor;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.ModelAndView;
import org.aopalliance.intercept.MethodInvocation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 19:22
 * 还没有使用
 */
public class LoggerInterceptor implements HandleInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        return null;
    }
}
