package christmas;

import christmas.controller.ChristmasEventController;
import christmas.controller.VisitInformationController;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasEventController controller = new ChristmasEventController(setView());
        controller.welcome();
        controller.christmasEvent();
        controller.eventClose();
    }

    private static VisitInformationController setView() {
        return new VisitInformationController(new ChristmasInputView(), new ChristmasOutputView());
    }
}
