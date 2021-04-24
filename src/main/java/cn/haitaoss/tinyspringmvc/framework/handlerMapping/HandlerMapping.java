package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:07
 *
 */
public interface HandlerMapping {
    HandlerExecutionChain getHandler(HttpServletRequest var1) throws Exception;
}