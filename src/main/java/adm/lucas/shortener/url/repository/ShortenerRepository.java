package adm.lucas.shortener.url.repository;

import adm.lucas.shortener.url.model.Shortener;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenerRepository extends JpaRepository<Shortener, String> {
    Shortener findByFullUrl(String url);
}