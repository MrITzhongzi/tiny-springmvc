package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:03
 *
 */
public class HandlerExecutionChain {
    private RequestMappingHandler handler;
    private HandleInterceptor[] interceptors;

    public RequestMappingHandler getHandler() {
        return handler;
    }

    public void setHandler(RequestMappingHandler handler) {
        this.handler = handler;
    }

    public HandleInterceptor[] getInterceptors() {
        return interceptors;
    }

    public void setInterceptors(HandleInterceptor[] interceptors) {
        this.interceptors = interceptors;
    }
}
