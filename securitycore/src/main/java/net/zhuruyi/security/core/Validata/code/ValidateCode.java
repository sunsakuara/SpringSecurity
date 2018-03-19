package net.zhuruyi.security.core.Validata.code;

import java.time.LocalDateTime;

/**
 * @Author :zhuruyi
 * @Description:
 * @Date:Create in 9:35 2018/3/17
 * @Modified By:
 */
public class ValidateCode {

    private String code;
    private LocalDateTime expireTime;

    public ValidateCode() {
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }


    public ValidateCode(String code, int expireIn) {
        this.code = code;
        expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "ImageCode{" +
                ", code='" + code + '\'' +
                ", expireTime=" + expireTime +
                '}';
    }

}
