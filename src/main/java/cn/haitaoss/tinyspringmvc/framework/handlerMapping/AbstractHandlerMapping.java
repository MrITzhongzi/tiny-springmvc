package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import cn.haitaoss.tinyioc.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 21:26
 *
 */
public abstract class AbstractHandlerMapping implements HandlerMapping {
    protected static HashMap<String, Object> handlerRegistry = new HashMap<>();
    protected static List<HandlerInterceptor> handlerInterceptors = new ArrayList<>();
    protected ApplicationContext mvcContext;

    public AbstractHandlerMapping(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
    }

    public ApplicationContext getMvcContext() {
        return mvcContext;
    }

    public void setMvcContext(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
    }

    public HashMap<String, Object> getHandlerRegistry() {
        return handlerRegistry;
    }

    public void setHandlerRegistry(HashMap<String, Object> handlerRegistry) {
        this.handlerRegistry = handlerRegistry;
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
        HandlerExecutionChain handlerExecutionChain = new HandlerExecutionChain();
        // 设置此次请求 对应的处理方法
        handlerExecutionChain.setHandler(handlerRegistry.get(request.getServletPath()));
        // 获取此次请求匹配的拦截器
        List<HandlerInterceptor> interceptors = filterWithUrl(handlerInterceptors, request.getServletPath());
        handlerExecutionChain.setInterceptors(interceptors);
        // 返回构建好的HandleExecutionChain
        return handlerExecutionChain;
    }

    /**
     * 拦截器的匹配规则很简单，只要requestURI 包含了 handlerInterceptor里面的其中一个path 就算匹配成功
     * @author haitao.chen
     * email
     * date 2021/4/24 9:38 下午
     * @param handlerInterceptors
     * @param requestURI
     * @return java.util.List<cn.haitaoss.tinyspringmvc.framework.handlerMapping.HandlerInterceptor>
     */
    private List<HandlerInterceptor> filterWithUrl(List<HandlerInterceptor> handlerInterceptors, String requestURI) {
        List<HandlerInterceptor> result = new ArrayList<>();
        // 找到匹配的拦截器
        boolean isMatch = false;
        for (HandlerInterceptor handlerInterceptor : handlerInterceptors) {
            isMatch = false;
            for (String path : handlerInterceptor.getPath()) {
                if (requestURI.contains(path)) {
                    isMatch = true;
                    break;
                }
            }
            // 该请求路径 符合拦截器配置的路径规则
            if (isMatch) {
                result.add(handlerInterceptor);
            }
        }
        return result;
    }

    /**
     * registryURLAndHandler 是模板方法，子类可以重写这个方法，来做一些初始化的操作
     * @author haitao.chen
     * email
     * date 2021/4/25 9:59 上午
     */
    protected void registryURLAndHandler() {
    }

    public void init() {
        registryURLAndHandler();// 注册map，保存url和handler的对应关系
        initHandlerInterceptors(); // 收集容器中所有的HandlerInterceptor
    }

    /**
     * 收集容器中所有的HandlerInterceptor 的实现，保存到父类handlerInterceptors属性中
     * @author haitao.chen
     * email
     * date 2021/4/25 11:23 上午
     */
    private void initHandlerInterceptors() {
        try {
            if (handlerInterceptors.isEmpty())
                handlerInterceptors = mvcContext.getBeanFactory().getBeansForType(HandlerInterceptor.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
