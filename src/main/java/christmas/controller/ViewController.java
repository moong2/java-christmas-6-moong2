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

public class ViewController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidateService inputValidateService = new InputValidateService();

    public ViewController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void sendWelcome() {
        outputView.printPrecaution();
        outputView.printGuidance(WELCOME.getGuidance());
    }

    public VisitInformationDto getVisitInformation() {
        int visitDate = getValidVisitDate();
        Map<String, Integer> orders = getValidOrders();
        return new VisitInformationDto(visitDate, orders);
    }

    public void guideVisitInformation(VisitInformation visitInformation) {
        outputView.printPreview(visitInformation.getVisitDate().getDayOfMonth());
        outputView.printOrderMenus(visitInformation.getOrders().getOrders());
    }

    public void guideTotalAmountBeforeDiscount(int totalAmountBeforeDiscount) {
        outputView.printTotalAmountBeforeDiscount(totalAmountBeforeDiscount);
    }

    public void guideGivenMenu(MenuList menuList) {
        outputView.printGivenMenu(menuList);
    }

    public void guideBenefitsDetails(Map<EventCategory, Integer> benefits) {
        outputView.printBenefits(benefits);
    }

    public void guideBenefitsAmount(int totalBenefitsAmount) {
        outputView.printTotalBenefitsAmount(totalBenefitsAmount);
    }

    public void guideTotalAmountAfterDiscount(int totalAmountAfterDiscount) {
        outputView.printTotalAmountAfterDiscount(totalAmountAfterDiscount);
    }

    public void guideBadge(BadgeCategory badge) {
        outputView.printBadge(badge);
    }

    public void guideEventNotApply(String guidance) {
        outputView.printEventNotApply(guidance);
    }

    public void reportTotalNumberOfClients(int totalNumberOfClients) {
        outputView.printNumberOfClients(totalNumberOfClients);
    }

    public void reportTotalNumberOfNextEventClients(double totalNumberOfNextEventClients) {
        outputView.printNumberOfNextClients(totalNumberOfNextEventClients);
    }

    public void reportTotalAmountOfOrders(long totalAmountOfOrders) {
        outputView.printTotalAmountOfOrders(totalAmountOfOrders);
    }

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
