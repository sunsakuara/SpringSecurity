package net.zhuruyi.dto;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 22:19 2018/3/10
 * @Modified By:
 */
public class FileInfo {

    private String path;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "path='" + path + '\'' +
                '}';
    }
}
