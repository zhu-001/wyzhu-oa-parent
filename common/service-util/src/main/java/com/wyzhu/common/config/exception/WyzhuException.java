package com.wyzhu.common.config.exception;

import com.wyzhu.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * Date:2023/3/27 0027
 * Author:wyzhu
 * Description:
 */
@Data
public class WyzhuException extends RuntimeException{
    private Integer code;

    private String message;

    /**
     * 通过状态码和错误消息创建异常对象
     * @param code
     * @param message
     */
    public WyzhuException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 接收枚举类型对象
     * @param resultCodeEnum
     */
    public WyzhuException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
    }

    @Override
    public String toString() {
        return "WyzhuException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }

}
