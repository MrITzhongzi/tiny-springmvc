package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import cn.haitaoss.tinyioc.beans.BeanDefinition;
import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyspringmvc.framework.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 18:47
 *
 */
public class AnnotationHandleMapping implements HandlerMapping {
    private ApplicationContext mvcContext;
    private Map<String, RequestMappingHandler> handlerRegistry;

    public AnnotationHandleMapping(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
        this.handlerRegistry = new HashMap<>();
        init();
    }

    public ApplicationContext getMvcContext() {
        return mvcContext;
    }

    public void setMvcContext(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
    }

    public Map<String, RequestMappingHandler> getHandlerRegistry() {
        return handlerRegistry;
    }

    public void setHandlerRegistry(Map<String, RequestMappingHandler> handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain();
        // 根据请求uri 找到对应的handle
        RequestMappingHandler requestMappingHandler = handlerRegistry.get(request.getServletPath());
        handlerExecutionChain.setHandler(requestMappingHandler);
        return handlerExecutionChain;
    }

    /**
     * 遍历容器中所有的bean，找到@RequestMapping 标注的类和方法，注册到HandleRegistry中。
     * 注意：这里写的比较简单，只有类也标注了@RequestMapping 注解我们才能扫描到
     * @author haitao.chen
     * email
     * date 2021/4/24 6:54 下午
     */
    @Override
    public void init() {
        // 找到容器中所有标注了 @RequestMapping 的类
        for (Map.Entry<String, BeanDefinition> entry : this.mvcContext.getBeanFactory().getBeanDefinitionMap().entrySet()) {
            Class clazz = entry.getValue().getBeanClass();
            Object bean = entry.getValue().getBean();

            Annotation annotation = clazz.getAnnotation(RequestMapping.class);
            if (annotation == null) {
                continue;
            }
            String prefix = null;
            String suffix = null;
            prefix = ((RequestMapping) annotation).value();

            // 获取方法的映射路径
            for (Method declaredMethod : clazz.getDeclaredMethods()) {
                annotation = declaredMethod.getAnnotation(RequestMapping.class);
                if (annotation == null) {
                    continue;
                }
                suffix = ((RequestMapping) annotation).value();

                // 构建RequestMappingHandle 然后注册到handlerRegistry 中
                this.handlerRegistry.put(prefix + suffix, new RequestMappingHandler(bean, declaredMethod, null));
            }

        }
    }

}
