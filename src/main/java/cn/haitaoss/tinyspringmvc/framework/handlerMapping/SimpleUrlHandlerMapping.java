package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import cn.haitaoss.tinyioc.context.ApplicationContext;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 11:17
 * 该类作用为，在xml中配置key=某uri，value=某具体注解@Controller的类，读取xml后加入handlerRegistry
 */
public class SimpleUrlHandlerMapping extends AbstractHandlerMapping {
    public SimpleUrlHandlerMapping(ApplicationContext mvcContext) {
        super(mvcContext);
    }
}
