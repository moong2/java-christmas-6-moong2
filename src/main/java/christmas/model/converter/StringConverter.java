package christmas.model.converter;

public interface StringConverter<T> {
    T toType(String value);
}
