package christmas.controller;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.dto.VisitInformationDto;
import christmas.service.UserService;

/**
 * UserController는 유저와 관련된 행동을 관리하는 컨트롤러이다.
 * 유저를 생성하는 일을 하기 위해 UserService를 이용한다.
 */
public class UserController {
    private final UserService userService = new UserService();

    /**
     * 제공된 visitInformation을 기반으로 새 유저를 생성한다.
     *
     * @param visitInformationDto Client에게 입력 받은 방문 정보
     * @return 새 Client 객체
     */
    public Client addClient(VisitInformationDto visitInformationDto) {
        return userService.addClient(visitInformationDto);
    }

    /**
     * 새 admin을 추가한다.
     *
     * @return 새 Admin 객체
     */
    public Admin addAdmin() {
        return userService.createAdmin();
    }
}
