package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import org.aopalliance.intercept.MethodInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:05
 */
public interface HandleInterceptor extends MethodInterceptor {
    /**
     * 该方法在请求处理之前调用，返回true表示交给下一个拦截器，返回false表示到此为止
     * @author haitao.chen
     * email
     * date 2021/4/24 7:10 下午
     * @param request
     * @param response
     * @param handler
     * @return boolean
     */
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;

    /**
     * 视图返回之后，渲染之前被调用
     * @author haitao.chen
     * email
     * date 2021/4/24 7:10 下午
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception;

    void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception;

}
