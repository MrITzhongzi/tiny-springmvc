package cn.haitaoss.tinyspringmvc.framework.contextlistener;

import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyioc.context.ClassPathXmlApplicationContext;

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
        // 获取web.xml的配置信息
        String springXmlPath = sce.getServletContext().getInitParameter("contextConfigLocation");
        if (springXmlPath.startsWith("classpath:")) {
            springXmlPath = springXmlPath.substring(10);
        }
        // 创建ioc容器
        ApplicationContext applicationContext = null;
        try {
            applicationContext = new ClassPathXmlApplicationContext(springXmlPath);
            Object person = applicationContext.getBean("person");
            System.out.println(person);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
