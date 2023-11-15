package christmas.controller;

import static christmas.util.instructions.Guidance.WELCOME;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.domain.VisitInformation;
import christmas.model.dto.VisitInformationDto;
import christmas.model.util.event.BadgeCategory;
import christmas.model.util.event.EventCategory;
import christmas.model.util.menu.MenuList;
import christmas.service.InputValidateService;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

/**
 * ViewController는 크리스마스 이벤트의 입출력 관리를 하는 컨트롤러이다. InputView와 OutputView를 이용한다. 사용자에게 입력받은 값을 검증하기 위해
 * InputValidateService를 이용한다.
 */
public class ViewController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidateService inputValidateService = new InputValidateService();

    /**
     * ViewController의 생성자이다.
     *
     * @param inputView  유저의 입력을 받기 위한 View
     * @param outputView 유저에게 정보를 출력하기 위한 View
     */
    public ViewController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    /**
     * 안내 사항과 크리스마스 이벤트 가이드를 출력한다.
     */
    public void sendWelcome() {
        outputView.printPrecaution();
        outputView.printGuidance(WELCOME.getGuidance());
    }

    /**
     * Client가 방문할 날짜와 주문 메뉴를 입력받는다.
     *
     * @return VisitInformationDto 형식의 입력된 방문할 날짜와 주문 메뉴
     */
    public VisitInformationDto getVisitInformation() {
        int visitDate = getValidVisitDate();
        Map<String, Integer> orders = getValidOrders();
        return new VisitInformationDto(visitDate, orders);
    }

    /**
     * Client가 입력한 방문 날짜와 주문 메뉴에 대한 안내를 한다.
     *
     * @param visitInformation Clinet가 입력한 방문 날짜와 주문 메뉴를 담은 객체
     */
    public void guideVisitInformation(VisitInformation visitInformation) {
        outputView.printPreview(visitInformation.getVisitDate().getDayOfMonth());
        outputView.printOrderMenus(visitInformation.getOrders().getOrders());
    }

    /**
     * 주어진 할인 전 총 주문 금액을 출력한다.
     *
     * @param totalAmountBeforeDiscount 출력할 할인 전 총 주문 금액
     */
    public void guideTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        outputView.printTotalAmountBeforeDiscount(totalAmountBeforeDiscount);
    }

    /**
     * 주어진 증정 메뉴를 출력한다.
     *
     * @param menuList 출력할 증정 메뉴
     */
    public void guideGivenMenu(MenuList menuList) {
        outputView.printGivenMenu(menuList);
    }

    /**
     * 주어진 혜택 내역을 출력한다.
     *
     * @param benefits 출력할 혜택 내역
     */
    public void guideBenefitsDetails(Map<EventCategory, Integer> benefits) {
        outputView.printBenefits(benefits);
    }

    /**
     * 주어진 총혜택 금액을 출력한다.
     *
     * @param totalBenefitsAmount 출력할 총혜택 금액
     */
    public void guideBenefitsAmount(int totalBenefitsAmount) {
        outputView.printTotalBenefitsAmount(totalBenefitsAmount);
    }

    /**
     * 주어진 할인 후 예상 결제 금액을 출력한다.
     *
     * @param totalAmountAfterDiscount 출력할 할인 후 예상 결제 금액
     */
    public void guideTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        outputView.printTotalAmountAfterDiscount(totalAmountAfterDiscount);
    }

    /**
     * 주어진 배지를 출력한다.
     *
     * @param badge 출력할 배지
     */
    public void guideBadge(BadgeCategory badge) {
        outputView.printBadge(badge);
    }

    /**
     * 이벤트를 적용할 수 없음을 안내한다.
     *
     * @param guidance 출력할 적용되지 않는 이벤트
     */
    public void guideEventNotApply(String guidance) {
        outputView.printEventNotApply(guidance);
    }

    /**
     * 주어진 이벤트 이용 고객 수를 출력한다.
     *
     * @param totalNumberOfClients 출력할 12월 크리스마스 이벤트 이용 고객 수
     */
    public void reportTotalNumberOfClients(int totalNumberOfClients) {
        outputView.printNumberOfClients(totalNumberOfClients);
    }

    /**
     * 주어진 설날 이벤트 예상 참여 고객 수를 출력한다.
     *
     * @param totalNumberOfNextEventClients 출력할 설날 이벤트 예상 참여 고객 수
     */
    public void reportTotalNumberOfNextEventClients(double totalNumberOfNextEventClients) {
        outputView.printNumberOfNextClients(totalNumberOfNextEventClients);
    }

    /**
     * 주어진 총 판매 금액을 출력한다.
     *
     * @param totalAmountOfOrders 출력할 총 판매 금액
     */
    public void reportTotalAmountOfOrders(long totalAmountOfOrders) {
        outputView.printTotalAmountOfOrders(totalAmountOfOrders);
    }

    /**
     * 입력 콘솔을 닫는다.
     */
    public void closeProcess() {
        Console.close();
    }

    private int getValidVisitDate() {
        while (true) {
            try {
                return inputValidateService.validateAndConvertVisitDate(inputView.getVisitDate());
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
            }
        }
    }

    private Map<String, Integer> getValidOrders() {
        while (true) {
            try {
                return inputValidateService.validateAndConvertOrders(inputView.getOrders());
            } catch (IllegalArgumentException e) {
                outputView.printException(e.getMessage());
            }
        }
    }
}
