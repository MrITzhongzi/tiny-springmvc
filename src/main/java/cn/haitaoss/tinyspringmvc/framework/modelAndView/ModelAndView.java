package cn.haitaoss.tinyspringmvc.framework.modelAndView;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 19:10
 *
 */
public class ModelAndView {
    private Object bean;

    public ModelAndView(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }
}
