package christmas.model.dto;

import java.util.Map;

/**
 * 방문 날짜와 주문 내역을 저장하는 VisitInformation의 DTO이다.
 */
public record VisitInformationDto(
        int visitDate,
        Map<String, Integer> orders
) {
}
