package christmas.model.dto;

import java.util.Map;

public record VisitInformationDto(
        int visitDate,
        Map<String, Integer> orders
) {
}
