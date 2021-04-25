package jar;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 15:56
 *
 */
public class TestArguments {
    public void testMethod(int age, Integer month, Car car) {
        System.out.println(age);
        System.out.println(month);
        System.out.println(car);
    }

    @Test
    public void test() throws Exception {
        // 模拟请求参数
        HashMap<String, String> reqParameterMap = new HashMap<>();
        reqParameterMap.put("age", "10");
        reqParameterMap.put("month", "12");
        reqParameterMap.put("name", "haitao");
        reqParameterMap.put("price", "100");

        //  需要解析参数的Method
        Method method = null;
        for (Method declaredMethod : this.getClass().getDeclaredMethods()) {
            if (declaredMethod.getName().equals("testMethod")) {
                method = declaredMethod;
                break;
            }
        }

        //  获取方法的执行参数
        Object[] arguments = getMethodArguments(reqParameterMap, method.getParameters());

        method.invoke(this, arguments);
    }

    private Object[] getMethodArguments(HashMap<String, String> reqParameterMap, Parameter[] parameters) throws Exception {
        Object[] result = new Object[parameters.length];
        //  遍历方法的参数
        for (int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Class<?> type = parameter.getType();
            String paraValue = reqParameterMap.get(parameter.getName());
            if (paraValue != null) {
                Object converterVal = null;
                if (type == String.class) {
                    converterVal = paraValue;
                } else if (type == Integer.class || type == Integer.TYPE) {
                    converterVal = Integer.parseInt(paraValue);
                }
                result[i] = converterVal;
                continue;
            }
            //  反射创建实例
            Object obj = type.newInstance();
            BeanUtils.populate(obj, reqParameterMap);
            result[i] = obj;
        }
        return result;
    }
}
