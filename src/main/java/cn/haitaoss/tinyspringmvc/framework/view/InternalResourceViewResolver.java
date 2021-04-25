package cn.haitaoss.tinyspringmvc.framework.view;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 16:35
 *
 */
public class InternalResourceViewResolver extends InternalResourceView implements ViewResolver {
    private String viewClass;
    private String prefix;
    private String suffix;

    public String getViewClass() {
        return viewClass;
    }

    public void setViewClass(String viewClass) {
        this.viewClass = viewClass;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public View resolveViewName(String viewName) throws Exception {
        if (viewClass.equals(InternalResourceView.class.getName())) {
            if (viewName.startsWith("redirect:"))
                return new InternalResourceView(viewName.substring(9), true);
            else
                return new InternalResourceView(prefix + viewName + suffix, false);
        }
        return null;
    }
}
