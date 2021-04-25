package cn.haitaoss.tinyspringmvc.framework.handlerMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 17:07
 *
 */
public interface HandlerMapping {
    /**
     * 返回 HandlerExecutionChain
     * @author haitao.chen
     * email
     * date 2021/4/24 7:11 下午
     * @param request
     * @return cn.haitaoss.tinyspringmvc.framework.handlerMapping.HandlerExecutionChain
     */
    HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
}