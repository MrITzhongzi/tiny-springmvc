package cn.haitaoss.tinyspringmvc.webappProject.controller;

import cn.haitaoss.tinyioc.beans.annotation.Component;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.HandlerInterceptor;
import cn.haitaoss.tinyspringmvc.framework.modelAndView.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 19:22
 *
 */
@Component
public class LoggerInterceptor implements HandlerInterceptor {
    @Override
    public String[] getPath() {
        return new String[]{"/mvc/person/"};
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoggerInterceptor...preHandle()");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("LoggerInterceptor...afterCompletion()");
    }
}
