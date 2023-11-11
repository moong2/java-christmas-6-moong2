package christmas;

import static christmas.util.instructions.guidance.WELCOME;

import christmas.view.ChristmasOutputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new ChristmasOutputView();
        outputView.precaution();
        outputView.guidance(WELCOME.getGuidance());
    }
}
