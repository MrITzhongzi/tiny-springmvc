package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import java.lang.reflect.Method;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 18:50
 *
 */
public class RequestMappingHandler {
    private Object bean; // 调用者
    private Method method; // 调用的方法
    private Object[] args;// 调用参数

    public RequestMappingHandler() {
    }

    public RequestMappingHandler(Object bean, Method method, Object[] args) {
        this.bean = bean;
        this.method = method;
        this.args = args;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }
}
