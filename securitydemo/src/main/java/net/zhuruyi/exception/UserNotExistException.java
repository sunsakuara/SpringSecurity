package net.zhuruyi.exception;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 16:01 2018/3/10
 * @Modified By:
 */
public class UserNotExistException extends RuntimeException {

    private static final long serialVersionUID = -61135325125554L;
    private Long id;
    private String message;

    public UserNotExistException() {
        super("user not exist");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
