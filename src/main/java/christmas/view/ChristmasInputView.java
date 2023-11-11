package christmas.view;

import static christmas.util.instructions.guidance.VISIT_DATE;

import camp.nextstep.edu.missionutils.Console;

public class ChristmasInputView implements InputView{
    @Override
    public String getVisitDate() {
        System.out.println(VISIT_DATE.getGuidance());
        return Console.readLine();
    }
}
