package christmas.model.converter;

/**
 * StringToInteger는 StringConverter의 인터페이스의 구현부이다. String을 Integer로 변환하기 위해 Integer.parseInt를 이용한다.
 */
public class StringToInteger implements StringConverter<Integer> {
    /**
     * String값을 Integer로 변환한다. 문자열이 integer로 변환되지 않는다면 NumberFormatException을 반환한다.
     *
     * @param value 변환될 문자열
     * @return Integer 형식의 변환된 값
     * @throws NumberFormatException 문자열이 integer로 변환될 수 없을 때
     */
    @Override
    public Integer toType(String value) {
        return Integer.parseInt(value);
    }
}
