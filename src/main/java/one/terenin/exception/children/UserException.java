package one.terenin.exception.children;

import one.terenin.exception.BaseException;
import one.terenin.exception.common.ErrorCode;

public class UserException extends BaseException {
    public UserException(ErrorCode message) {
        super(message);
    }
}
