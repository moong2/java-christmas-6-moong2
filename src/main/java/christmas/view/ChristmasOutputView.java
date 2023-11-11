package christmas.view;

import christmas.util.instructions.Precautions;

public class ChristmasOutputView implements OutputView {
    @Override
    public void precaution() {
        System.out.println(Precautions.getPrecautions());
    }

    @Override
    public void guidance(String message) {
        System.out.println(message);
    }
}
