package cn.haitaoss.tinyspringmvc.framework.servlet;

import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyioc.context.ClassPathXmlApplicationContext;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.AnnotationHandleMapping;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.HandlerExecutionChain;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.HandlerMapping;
import cn.haitaoss.tinyspringmvc.framework.handlerMapping.RequestMappingHandler;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:09
 *
 */
public class DispatcherServlet extends HttpServlet {
    private ApplicationContext mvcContext;
    private HandlerMapping handlerMapping;

    @Override
    public void init() throws ServletException {
        doInit();
        // 默认使用AnnotationHandlerMapping来处理请求
        handlerMapping = new AnnotationHandleMapping(mvcContext);
    }

    private void doInit() throws ServletException {
        super.init();
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 找到请求对应的handler
        HandlerExecutionChain handlerExecutionChain = handlerMapping.getHandler(req);

        // 执行目标方法
        RequestMappingHandler handler = handlerExecutionChain.getHandler();
        // 至于如何传参，就是HandlerAdapter的事情了
        handler.getMethod().invoke(handler.getBean(), null);
    }
}
