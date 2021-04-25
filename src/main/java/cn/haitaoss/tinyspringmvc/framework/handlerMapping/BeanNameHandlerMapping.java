package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import cn.haitaoss.tinyioc.context.ApplicationContext;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 11:17
 * 这个类用于bean的id与相应uri的匹配，比如/mvc/person 就匹配Person这个类
 */
public class BeanNameHandlerMapping extends AbstractHandlerMapping {
    public BeanNameHandlerMapping(ApplicationContext mvcContext) {
        super(mvcContext);
    }
}
