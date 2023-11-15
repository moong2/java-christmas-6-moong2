package christmas;

import christmas.controller.ChristmasEventController;
import christmas.controller.UserController;
import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasEventController controller = new ChristmasEventController(setView());

        Client client = controller.addClient();
        controller.applyChristmasEvent(client);

        Admin admin = controller.addAdmin();
        controller.analyzeEvent(admin);

        controller.eventClose();
    }

    private static UserController setView() {
        return new UserController(new ChristmasInputView(), new ChristmasOutputView());
    }
}
