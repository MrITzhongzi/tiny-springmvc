package cn.haitaoss.tinyspringmvc.framework.modelAndView;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-24 19:10
 *
 */
public class ModelAndView {
    private Model model;
    private String view;

    public ModelAndView() {
    }

    public ModelAndView(Model model) {
        this.model = model;
    }

    public ModelAndView(Model model, String view) {
        this.model = model;
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }
}
