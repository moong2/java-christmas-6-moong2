package christmas;

import christmas.controller.ChristmasEventController;
import christmas.controller.ViewController;
import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.view.ChristmasInputView;
import christmas.view.ChristmasOutputView;

/**
 * Application은 12월 크리스마스 이벤트의 entry point이다. 필요한 컨트롤러와 뷰를 세팅하고, client와 admin을 생성한다. client는 이벤트에 참여하며, admin은 통계 자료를
 * 분석한다.
 */
public class Application {
    /**
     * applicatoin의 entry point이다. 시스템을 초기화하고, client와 admin을 더하며 client의 크리스마스 이벤트를 적용하고, 이벤트 분석을 진행한 후 이벤트를 종료한다.
     */
    public static void main(String[] args) {
        ChristmasEventController controller = new ChristmasEventController(setView());

        Client client = controller.addClient();
        controller.applyChristmasEvent(client);

        Admin admin = controller.addAdmin();
        controller.analyzeEvent(admin);

        controller.eventClose();
    }

    private static ViewController setView() {
        return new ViewController(new ChristmasInputView(), new ChristmasOutputView());
    }
}
