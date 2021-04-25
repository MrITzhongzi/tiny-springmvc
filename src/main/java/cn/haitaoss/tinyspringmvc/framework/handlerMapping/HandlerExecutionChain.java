package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import java.util.List;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:03
 *
 */
public class HandlerExecutionChain {
    private RequestMappingHandler handler;
    private List<HandlerInterceptor> interceptors;

    public RequestMappingHandler getHandler() {
        return handler;
    }

    public void setHandler(RequestMappingHandler handler) {
        this.handler = handler;
    }

    public List<HandlerInterceptor> getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(List<HandlerInterceptor> interceptors) {
        this.interceptors = interceptors;
    }
}
