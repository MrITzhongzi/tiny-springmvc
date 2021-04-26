package cn.haitaoss.tinyspringmvc.framework.servlet;

import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyioc.context.ClassPathXmlApplicationContext;
import cn.haitaoss.tinyspringmvc.framework.handlerAdapter.AnnotationHandlerAdapter;
import cn.haitaoss.tinyspringmvc.framework.handlerAdapter.ControllerHandlerAdapter;
import cn.haitaoss.tinyspringmvc.framework.handlerAdapter.HandlerAdapter;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.*;
import cn.haitaoss.tinyspringmvc.framework.modelAndView.ModelAndView;
import cn.haitaoss.tinyspringmvc.framework.view.View;
import cn.haitaoss.tinyspringmvc.framework.view.ViewResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:09
 *
 */
public class DispatcherServlet extends HttpServlet {
    private ApplicationContext mvcContext;
    private HandlerMapping handlerMapping;
    private List<HandlerAdapter> handlerAdapters = null;
    private ViewResolver resolver = null;

    @Override
    public void init() {
        try {
            doInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doInit() throws Exception {
        // 初始化mvc容器
        initSpringMVC();
        // 初始化HandlerMapping
        initHandlerMappings(mvcContext);
        // 初始化handlerAdapter
        initHandlerAdapters(mvcContext);
        // 初始化ViewResolver
        initViewResolver(mvcContext);
    }

    private void initSpringMVC() {
        System.out.println("DispatcherServlet...init\tspring子容器(mvc)");
        // 获取mvc配置文件
        String mvcXmlPath = this.getInitParameter("contextConfigLocation");
        if (mvcXmlPath == null || mvcXmlPath.length() == 0) {
            return;
        }
        if (mvcXmlPath.startsWith("classpath:")) {
            mvcXmlPath = mvcXmlPath.substring(10);
        }
        // 获取全局上下文
        ServletContext servletContext = this.getServletContext();
        try {
            // 获取父容器
            ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
            // 创建mvc的容器，里面整合和spring作为父容器
            mvcContext = new ClassPathXmlApplicationContext(springContext, mvcXmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViewResolver(ApplicationContext mvcContext) throws Exception {
        List<ViewResolver> resolvers = mvcContext.getBeanFactory().getBeansForType(ViewResolver.class);
        if (resolvers.size() != 1) {
            throw new Exception("未配置视图解析器或配置多个");
        }
        this.resolver = resolvers.get(0);
    }

    private void initHandlerAdapters(ApplicationContext mvcContext) {
        handlerAdapters = new ArrayList<>();
        handlerAdapters.add(new AnnotationHandlerAdapter(mvcContext));
        handlerAdapters.add(new ControllerHandlerAdapter(mvcContext));
    }

    private void initHandlerMappings(ApplicationContext mvcContext) {
        // 默认的handlerMapping 是 AnnotationHandlerMapping
        handlerMapping = new AnnotationHandlerMapping(mvcContext);
        ((AnnotationHandlerMapping) handlerMapping).init();
        new ControllerHandlerMapping(mvcContext).init();
        new SimpleUrlHandlerMapping(mvcContext).init();
        new BeanNameHandlerMapping(mvcContext).init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 找到请求对应的handlerMapping
        HandlerExecutionChain handlerExecutionChain = doHandlerMapping(req);
        Object handler = handlerExecutionChain.getHandler();
        List<HandlerInterceptor> handlerInterceptors = handlerExecutionChain.getInterceptors();

        // 进行前置处理，执行handlerInterceptor.preHandle
        doInterceptorsPreHandle(req, resp, handlerInterceptors, handler);

        // 进入HandlerAdapter模块(里面会执行req 对应的 method)
        // modelAndView 是执行方法的返回结果
        ModelAndView mv = doHandlerAdapter(req, resp, handler);

        // 进行POST处理，执行handlerInterceptor.postHandle
        doInterceptorsPostHandle(req, resp, handlerInterceptors, handler, mv);

        if (mv != null) {
            // 视图解析器解析mv，返回view
            View view = resolver.resolveViewName(mv.getView());
            // 页面渲染，渲染其实就是返回信息给客户端
            view.render(mv.getModel(), req, resp);
        }

        doInterceptorsAfterCompletion(req, resp, handlerInterceptors, handler, new Exception());
    }

    private void doInterceptorsPostHandle(HttpServletRequest request, HttpServletResponse response, List<HandlerInterceptor> handlerInterceptors, Object handler, ModelAndView mv) throws Exception {
        for (int i = handlerInterceptors.size() - 1; i >= 0; i--) {
            HandlerInterceptor interceptor = handlerInterceptors.get(i);
            interceptor.postHandle(request, response, handler, mv);
        }
    }

    private void doInterceptorsAfterCompletion(HttpServletRequest request, HttpServletResponse response, List<HandlerInterceptor> handlerInterceptors, Object handler, Exception ex) throws Exception {
        for (int i = handlerInterceptors.size() - 1; i >= 0; i--) {
            HandlerInterceptor interceptor = handlerInterceptors.get(i);
            interceptor.afterCompletion(request, response, handler, ex);
        }
    }

    private ModelAndView doHandlerAdapter(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
        HandlerAdapter handlerAdapter = getHandlerAdapter(handler);
        ModelAndView mv = handlerAdapter.handle(req, resp, handler);
        return mv;
    }

    private HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter adapter : this.handlerAdapters) {
            if (adapter.supports(handler))
                return adapter;
        }
        return null;
    }

    private HandlerExecutionChain doHandlerMapping(HttpServletRequest request) throws Exception {
        return handlerMapping.getHandler(request);
    }

    private void doInterceptorsPreHandle(HttpServletRequest req, HttpServletResponse resp, List<HandlerInterceptor> handlerInterceptors, Object handler) throws Exception {
        // 拦截器执行特点： 先执行全部的preHandler方法。只要执行的preHandler方法没有返回false 那么对应的afterCompletion一定会执行。
        for (int i = 0; i < handlerInterceptors.size(); i++) {
            HandlerInterceptor handlerInterceptor = handlerInterceptors.get(i);
            if (!handlerInterceptor.preHandle(req, resp, handler)) {
                for (int j = i - 1; j >= 0; j--) {
                    handlerInterceptors.get(j).afterCompletion(req, resp, handler, new Exception());
                }
                break;
            }
        }
    }
}
