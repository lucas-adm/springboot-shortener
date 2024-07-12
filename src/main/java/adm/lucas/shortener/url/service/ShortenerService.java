package adm.lucas.shortener.url.service;

import adm.lucas.shortener.url.model.Shortener;
import adm.lucas.shortener.url.repository.ShortenerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShortenerService {

    private final ShortenerRepository repository;

    public String shortenUrl(String Url) {
        String id;
        do {
            id = RandomStringUtils.randomAlphabetic(5, 6);
        } while (repository.existsById(id));
        return repository.save(new Shortener(id, Url)).getId();
    }

    public Shortener getData(String id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}