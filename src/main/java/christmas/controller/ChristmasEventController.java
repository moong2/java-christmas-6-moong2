package christmas.controller;

import static christmas.model.util.event.EventDetails.EVENT_STANDARD;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.dto.VisitInformationDto;
import christmas.strategy.BenefitsAvailableStrategy;
import christmas.strategy.BenefitsUnavailableStrategy;
import christmas.strategy.EventHandlingStrategy;

/**
 * ChristmasEventController는 다양한 컨트롤러의 통신을 관리한다. 입력을 받고, 이벤트를 처리하는 역할을 한다. Client와 Admin을 추가하고 Client를 이벤트에 등록하고, Admin이
 * 이벤트의 전체적인 데이터를 분석하는 총 흐름들을 지휘한다.
 */
public class ChristmasEventController {
    private final UserController userController = new UserController();
    private final EventController eventController = new EventController();
    private final ViewController viewController;

    /**
     * ChristmasEventController의 생성자다.
     *
     * @param viewController 입력과 출력을 담당하는 컨트롤러
     */
    public ChristmasEventController(ViewController viewController) {
        this.viewController = viewController;
    }

    /**
     * viewController에서 받아온 정보를 통해 새로운 client를 생성한다.
     *
     * @return 새롭게 추가된 Client 객체
     */
    public Client addClient() {
        VisitInformationDto visitInformationDto = viewController.getVisitInformation();
        return userController.addClient(visitInformationDto);
    }

    /**
     * 새로운 admin을 생성한다.
     *
     * @return 새롭게 추가된 Admin 객체
     */
    public Admin addAdmin() {
        return userController.addAdmin();
    }

    /**
     * Client가 크리스마스 이벤트에 참가할 수 있도록 돕는다. 정보 입력에 대한 가이드를 하고 입력 받은 정보를 토대로 이벤트에 참가시킨다.
     *
     * @param client 크리스마스에 참여할 고객
     */
    public void applyChristmasEvent(Client client) {
        viewController.sendWelcome();
        viewController.guideVisitInformation(client.getVisitInformation());
        int getTotalAmountBeforeDiscount = eventController.getTotalAmountBeforeDiscount(client);
        viewController.guideTotalAmountBeforeDiscount(getTotalAmountBeforeDiscount);

        EventHandlingStrategy eventHandlingStrategy = applyStrategy(getTotalAmountBeforeDiscount);
        eventHandlingStrategy.handleEvent(client, viewController, eventController);
    }

    /**
     * Admin이 크리스마스 이벤트의 전체적인 데이터에 대한 분석을 할 수 있도록 돕는다. 총 고객 수와 총 판매금액에 따른 통계를 분석한다.
     *
     * @param admin 이벤트를 분석할 관리자
     */
    public void analyzeEvent(Admin admin) {
        viewController.reportTotalNumberOfClients(eventController.analyzeTotalParticipants(admin));
        viewController.reportTotalNumberOfNextEventClients(eventController.analyzeNextEventParticipants(admin));
        viewController.reportTotalAmountOfOrders(eventController.analyzeTotalEventOrders(admin));
    }

    /**
     * 이벤트를 종료한다.
     */
    public void eventClose() {
        viewController.closeProcess();
    }

    private EventHandlingStrategy applyStrategy(int getTotalAmountBeforeDiscount) {
        if (getTotalAmountBeforeDiscount >= EVENT_STANDARD.getDetails()) {
            return new BenefitsAvailableStrategy();
        }
        return new BenefitsUnavailableStrategy();
    }
}
