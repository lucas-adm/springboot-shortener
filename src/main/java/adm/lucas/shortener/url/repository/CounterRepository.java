package adm.lucas.shortener.url.repository;

import adm.lucas.shortener.url.model.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {
}