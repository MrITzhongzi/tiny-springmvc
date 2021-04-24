package cn.haitaoss.tinyspringmvc.framework.servlet;

import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyioc.context.ClassPathXmlApplicationContext;
import cn.haitaoss.tinyspringmvc.webappProject.controller.PersonController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:09
 *
 */
public class DispatcherServlet extends HttpServlet {
    public DispatcherServlet() {
        System.out.println("DispatcherServlet...constructor");
    }

    @Override
    public void init() throws ServletException {
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
        ApplicationContext mvcContext = null;
        try {
            // 获取父容器
            ApplicationContext springContext = (ApplicationContext) servletContext.getAttribute("springContext");
            // 创建mvc的容器，里面整合和spring作为父容器
            mvcContext = new ClassPathXmlApplicationContext(springContext, mvcXmlPath);

            // 测试
            PersonController personController = (PersonController) mvcContext.getBean("personController");
            System.out.println(personController.getPersonService());

        } catch (Exception e) {
            // e.printStackTrace();
        }
    }
}
