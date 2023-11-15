package christmas.util.exceptions;

import static christmas.util.exceptions.Exceptions.AUTHENTICATION_INVALID;

/**
 * UnauthorizedAccessException은 RuntimeException을 확장한 클래스다. Admin이 아닌 사용자가 Admin 권한에 접근하는 경우 반환한다.
 */
public class UnauthorizedAccessException extends RuntimeException {
    /**
     * RuntimeException의 생성자를 이용한다. 관련 에러 메시지를 포함한다.
     */
    public UnauthorizedAccessException() {
        super(AUTHENTICATION_INVALID.getMessage());
    }
}
