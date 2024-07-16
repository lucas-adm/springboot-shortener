package adm.lucas.shortener.url.dto.out;

import adm.lucas.shortener.url.model.Counter;

public record CounterDTO(
        Long totalVisitorCount,
        Long totalLinkCount,
        Long monthlyVisitorCount,
        Long monthlyLinkCount,
        Long dailyVisitorCount,
        Long dailyLinkCount
) {
    public CounterDTO(Counter counter) {
        this(
                counter.getTotalVisitorCount(),
                counter.getTotalLinkCount(),
                counter.getMonthlyVisitorCount(),
                counter.getMonthlyLinkCount(),
                counter.getDailyVisitorCount(),
                counter.getDailyLinkCount()
        );
    }
}