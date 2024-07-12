package adm.lucas.shortener.url.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "shorteners")
@Data
@NoArgsConstructor
public class Shortener {

    @Id
    String id;
    LocalDateTime dateTime = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
    String fullUrl;

    public Shortener(String id, String fullUrl) {
        this.id = id;
        this.fullUrl = fullUrl;
    }

}