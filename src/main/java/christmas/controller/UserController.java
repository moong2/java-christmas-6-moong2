package christmas.controller;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.dto.VisitInformationDto;
import christmas.service.UserService;

public class UserController {
    private final UserService userService = new UserService();

    public Client addClient(VisitInformationDto visitInformationDto) {
        return userService.addClient(visitInformationDto);
    }

    public Admin addAdmin() {
        return userService.createAdmin();
    }
}
