package christmas.model.converter;

public class StringToInteger implements StringConverter<Integer>{
    @Override
    public Integer toType(String value) {
        return Integer.parseInt(value);
    }
}
