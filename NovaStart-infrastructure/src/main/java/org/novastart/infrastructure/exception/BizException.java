package org.novastart.infrastructure.exception;

import lombok.Getter;
import lombok.Setter;
import org.novastart.infrastructure.exception.error.ErrorCodeInterface;

@Getter
@Setter
public class BizException extends RuntimeException {
    // 异常码
    private String errorCode;
    // 错误信息
    private String errorMessage;

    public BizException(ErrorCodeInterface errorCodeInterface) {
        this.errorCode = errorCodeInterface.getErrorCode();
        this.errorMessage = errorCodeInterface.getErrorMessage();
    }
}