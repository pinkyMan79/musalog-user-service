package one.terenin.exception.children;

import one.terenin.exception.BaseException;
import one.terenin.exception.common.ErrorCode;

public class AuthException extends BaseException {
    public AuthException(ErrorCode message) {
        super(message);
    }
}
