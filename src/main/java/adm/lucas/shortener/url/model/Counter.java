package adm.lucas.shortener.url.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "counter")
@Data
@NoArgsConstructor
public class Counter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long dailyVisitorCount = 0L;
    private Long dailyLinkCount = 0L;
    private Long monthlyVisitorCount = 0L;
    private Long monthlyLinkCount = 0L;
    private Long totalVisitorCount = 0L;
    private Long totalLinkCount = 0L;

}