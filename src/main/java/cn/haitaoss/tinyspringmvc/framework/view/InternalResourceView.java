package cn.haitaoss.tinyspringmvc.framework.view;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author haitao.chen
 * email haitaoss@aliyun.com
 * date 2021-04-25 16:32
 *
 */
public class InternalResourceView implements View {
    private String path;
    private boolean isRedirect;

    public InternalResourceView() {
    }

    public InternalResourceView(String path, boolean isRedirect) {
        this.path = path;
        this.isRedirect = isRedirect;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 将model 里面的属性保存到request域中
        for (Map.Entry<String, ?> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }
        if (!this.isRedirect)
            request.getRequestDispatcher(path).forward(request, response);
        else
            response.sendRedirect(path);
    }
}
