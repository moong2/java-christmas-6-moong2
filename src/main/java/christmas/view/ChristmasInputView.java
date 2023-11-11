package christmas.view;

import static christmas.util.instructions.guidance.ORDER_MENU;
import static christmas.util.instructions.guidance.VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;

public class ChristmasInputView implements InputView {
    @Override
    public String getVisitDate() {
        System.out.println(VISIT_DATE.getGuidance());
        return Console.readLine();
    }

    @Override
    public String getOrders() {
        System.out.println(ORDER_MENU.getGuidance());
        return Console.readLine();
    }
}
