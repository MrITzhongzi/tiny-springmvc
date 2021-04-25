package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import cn.haitaoss.tinyioc.context.ApplicationContext;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 11:17
 * 该类用于注册所有实现了Controller接口的实例,xml中该类id应该配置为"/"开头的，以作为uri的key
 */
public class ControllerHandlerMapping extends AbstractHandlerMapping {
    public ControllerHandlerMapping(ApplicationContext mvcContext) {
        super(mvcContext);
    }
}
