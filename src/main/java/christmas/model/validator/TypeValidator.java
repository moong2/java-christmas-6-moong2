package christmas.model.validator;

public class TypeValidator {
    protected static void checkIsNumeric(String input) {
        if (!input.matches("-?\\d+")) {
            throw new NumberFormatException();
        }
    }
}
