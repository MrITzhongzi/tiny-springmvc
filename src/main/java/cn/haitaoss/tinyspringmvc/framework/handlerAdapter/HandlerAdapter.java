package cn.haitaoss.tinyspringmvc.framework.handlerAdapter;

import cn.haitaoss.tinyspringmvc.framework.modelAndView.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 11:06
 *
 */
public interface HandlerAdapter {
    boolean supports(Object handler);

    ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
}
