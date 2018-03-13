package net.zhuruyi.security.broeser.support;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:31 2018/3/13
 * @Modified By:
 */
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }


    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SimpleResponse{" +
                "content=" + content +
                '}';
    }
}
