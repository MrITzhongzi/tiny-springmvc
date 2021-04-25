package cn.haitaoss.tinyspringmvc.framework.handlerAdapter;

import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.RequestMappingHandler;
import cn.haitaoss.tinyspringmvc.framework.modelAndView.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 14:20
 *
 */
public class AnnotationHandlerAdapter extends AbstractHandlerAdapter {
    public AnnotationHandlerAdapter(ApplicationContext mvcContext) {
        super(mvcContext);
    }

    @Override
    public boolean supports(Object handler) {
        if (handler instanceof RequestMappingHandler) {
            return true;
        }
        return false;
    }

    @Override
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestMappingHandler rmHandler = (RequestMappingHandler) handler;
        // 获取方法的执行参数的值
        Object[] args = ArgumentResolverUtil.resolveRequestParam(request, rmHandler.getMethod());
        // 执行方法
        Object obj = rmHandler.getMethod().invoke(rmHandler.getBean(), args);
        return new ModelAndView(obj);
    }
}
