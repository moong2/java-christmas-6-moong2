package christmas;

import christmas.controller.ChristmasEventController;
import christmas.controller.UserController;
import christmas.model.dto.ClientDto;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

public class Application {
    public static void main(String[] args) {
        ChristmasEventController controller = new ChristmasEventController(setView());

        ClientDto clientDto = controller.addClient();
        controller.christmasEvent(clientDto);
        controller.eventClose();
    }

    private static UserController setView() {
        return new UserController(new ChristmasInputView(), new ChristmasOutputView());
    }
}
