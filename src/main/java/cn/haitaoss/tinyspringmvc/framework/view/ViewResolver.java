package cn.haitaoss.tinyspringmvc.framework.view;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 16:31
 *
 */
public interface ViewResolver {
    View resolveViewName(String viewName) throws Exception;
}