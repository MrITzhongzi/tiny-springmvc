package cn.haitaoss.tinyspringmvc.framework.handlerAdapter;

import cn.haitaoss.tinyioc.beans.converter.ConverterFactory;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 14:22
 *
 */
public class ArgumentResolverUtil {
    public static Object[] resolveRequestParam(HttpServletRequest request, Method method) throws Exception {
        // 获取方法的参数值
        Parameter[] parameters = method.getParameters();
        Object[] args = new Object[parameters.length];
        if (parameters.length == 0) {
            return args;
        }

        // 获取查询参数
        Map<String, String[]> paramMap = request.getParameterMap();
        Map<String, String> argMap = new LinkedHashMap<>();
        for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
            String paramName = entry.getKey();
            String paramValue = "";
            String[] paramValueArr = entry.getValue();
            for (int i = 0; i < paramValueArr.length; i++) {
                if (i == paramValueArr.length - 1)
                    paramValue += paramValueArr[i];
                else
                    paramValue += paramValueArr[i] + ",";
            }
            // 处理后的request键值对
            argMap.put(paramName, paramValue);
        }

        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            if (argMap.containsKey(parameter.getName())) {
                String value = argMap.get(parameter.getName());
                Type type = parameter.getType();
                // 字符串类型，直接赋值
                if (type == String.class)
                    args[i] = value;
                else
                    // 复杂参数类型 通过converter解析在赋值
                    args[i] = ConverterFactory.getConverterMap().get(parameter.getType()).parse(value);
            } else {
                // 没有的参数，就通过反射创建实例
                Type type = parameter.getType();
                Object bean = ((Class) type).newInstance();
                try {
                    // 将map里面的属性 拷贝到新创建的实例中
                    BeanUtils.populate(bean, argMap);
                    args[i] = ((Class) type).cast(bean);
                } catch (Exception e) {
                    args[i] = null;
                }
            }
        }
        return args;
    }
}
