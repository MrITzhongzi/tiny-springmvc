package cn.haitaoss.tinyspringmvc.framework.handlerAdapter;

import cn.haitaoss.tinyioc.context.ApplicationContext;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 14:19
 *
 */
public abstract class AbstractHandlerAdapter implements HandlerAdapter {
    protected ApplicationContext mvcContext;

    public AbstractHandlerAdapter(ApplicationContext mvcContext) {
        this.mvcContext = mvcContext;
    }
}
