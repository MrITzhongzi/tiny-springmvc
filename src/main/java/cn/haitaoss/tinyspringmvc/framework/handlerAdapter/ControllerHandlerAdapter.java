package cn.haitaoss.tinyspringmvc.framework.handlerAdapter;

import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.Controller;
import cn.haitaoss.tinyspringmvc.framework.modelAndView.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 14:24
 *
 */
public class ControllerHandlerAdapter extends AbstractHandlerAdapter {
    public ControllerHandlerAdapter(ApplicationContext mvcContext) {
        super(mvcContext);
    }

    @Override
    public boolean supports(Object handler) {
        if (handler instanceof Controller)
            return true;
        return false;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return null;
    }
}