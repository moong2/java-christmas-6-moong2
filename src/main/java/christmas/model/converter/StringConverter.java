package christmas.model.converter;

/**
 * String에서 특정한 형식으로 변환해주는 메소드를 정의한 interface이다.
 *
 * @param <T> String에서 변환할 자료형
 */
public interface StringConverter<T> {

    /**
     * String을 특정 자료형으로 변환한다.
     *
     * @param value 변환될 문자열
     * @return T 자료형의 변환된 값
     */
    T toType(String value);
}
