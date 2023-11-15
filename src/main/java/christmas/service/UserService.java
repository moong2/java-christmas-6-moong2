package christmas.service;

import static christmas.model.util.event.EventPeriod.EVENT_MONTH;
import static christmas.model.util.event.EventPeriod.EVENT_YEAR;

import christmas.model.domain.Admin;
import christmas.model.domain.Client;
import christmas.model.domain.Orders;
import christmas.model.domain.Users;
import christmas.model.domain.VisitInformation;
import christmas.model.dto.VisitInformationDto;
import java.time.LocalDate;

/**
 * UserService는 users에 client와 admin을 추가하는 기능을 제공한다.
 */
public class UserService {
    private final Users users = Users.getInstance();

    /**
     * 주어진 VisitInformation을 기반으로 새로운 유저를 생성하고 추가한다. DTO로부터 VisitInformation의 인스턴스를 얻고 Users 컬렉션에 Client를 추가한다.
     *
     * @param visitInformationDto Client의 방문 내역을 담은 객체를 전달 데이터로 변환한 것
     * @return 새로 추가한 Client 인스턴스
     */
    public Client addClient(VisitInformationDto visitInformationDto) {
        Client client = Client.of(createVisitInformation(visitInformationDto));
        users.addUser(client);

        return client;
    }

    private VisitInformation createVisitInformation(VisitInformationDto visitInformationDto) {
        return VisitInformation.of(
                LocalDate.of(EVENT_YEAR.getDate(), EVENT_MONTH.getDate(), visitInformationDto.visitDate()),
                Orders.of(visitInformationDto.orders())
        );
    }

    /**
     * 새로운 Admin 인스턴스를 Users 컬렉션에 추가한다.
     *
     * @return 새로 추가한 Admin 인스턴스
     */
    public Admin createAdmin() {
        Admin admin = Admin.of();
        users.addAdmin(admin);

        return admin;
    }
}
