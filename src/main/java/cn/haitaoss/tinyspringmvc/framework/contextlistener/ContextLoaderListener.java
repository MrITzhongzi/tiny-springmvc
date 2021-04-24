package cn.haitaoss.tinyspringmvc.framework.contextlistener;

import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyioc.context.ClassPathXmlApplicationContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 15:28
 *
 */
public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContextListener...contextInitialized\tspring父容器");
        ServletContext servletContext = sce.getServletContext();
        // 获取web.xml的配置信息
        String springXmlPath = servletContext.getInitParameter("contextConfigLocation");
        if (springXmlPath.startsWith("classpath:")) {
            springXmlPath = springXmlPath.substring(10);
        }
        // 创建ioc容器
        ApplicationContext applicationContext = null;
        try {
            applicationContext = new ClassPathXmlApplicationContext(springXmlPath);
            // 将applicationContext 保存到web应用上下文中
            servletContext.setAttribute("springContext", applicationContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
