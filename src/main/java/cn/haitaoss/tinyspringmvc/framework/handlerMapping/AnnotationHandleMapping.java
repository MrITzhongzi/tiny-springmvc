package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import cn.haitaoss.tinyioc.beans.BeanDefinition;
import cn.haitaoss.tinyioc.context.ApplicationContext;
import cn.haitaoss.tinyspringmvc.framework.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 18:47
 *
 */
public class AnnotationHandleMapping extends AbstractHandlerMapping {

    public AnnotationHandleMapping(ApplicationContext mvcContext) {
        super(mvcContext);
    }


    @Override
    protected void init() {
        // 注册map，保存url和handler的对应关系
        registryURLAndHandler();
        // 找到容器中定义的HandlerInterceptor
        initHandlerInterceptors();
    }

    /**
     * 收集容器中所有的HandlerInterceptor 的实现，保存到父类handlerInterceptors属性中
     * @author haitao.chen
     * email
     * date 2021/4/25 10:11 上午
     */
    private void initHandlerInterceptors() {
        try {
            handlerInterceptors = mvcContext.getBeanFactory().getBeansForType(HandlerInterceptor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 遍历容器中所有的bean，找到@RequestMapping 标注的类和方法，注册到HandleRegistry中。
     * 注意：这里写的比较简单，只有类也标注了@RequestMapping 注解我们才能扫描到
     * @author haitao.chen
     * email
     * date 2021/4/24 6:54 下午
     */
    private void registryURLAndHandler() {
        // 找到容器中所有标注了 @RequestMapping 的类
        for (Map.Entry<String, BeanDefinition> entry : mvcContext.getBeanFactory().getBeanDefinitionMap().entrySet()) {
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
                handlerRegistry.put(prefix + suffix, new RequestMappingHandler(bean, declaredMethod, null));
            }

        }
    }

}
