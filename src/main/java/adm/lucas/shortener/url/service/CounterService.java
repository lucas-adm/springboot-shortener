package adm.lucas.shortener.url.service;

import adm.lucas.shortener.url.model.Counter;
import adm.lucas.shortener.url.repository.CounterRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final CounterRepository repository;

    public Counter getAnalytics(Long id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void setVisitorAnalytics(Long id) {
        Counter counter = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        counter.setTotalVisitorCount(counter.getTotalVisitorCount() + 1L);
        counter.setMonthlyVisitorCount(counter.getMonthlyVisitorCount() + 1L);
        counter.setDailyVisitorCount(counter.getDailyVisitorCount() + 1L);
        repository.save(counter);
    }

    public void setLinkAnalytics(Long id) {
        Counter counter = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        counter.setTotalLinkCount(counter.getTotalLinkCount() + 1L);
        counter.setMonthlyLinkCount(counter.getMonthlyLinkCount() + 1L);
        counter.setDailyLinkCount(counter.getDailyLinkCount() + 1L);
        repository.save(counter);
    }


    public void resetMonthlyAnalytics(Long id) {
        Counter counter = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        counter.setMonthlyVisitorCount(0L);
        counter.setMonthlyLinkCount(0L);
        repository.save(counter);
    }

    public void resetDailylyAnalytics(Long id) {
        Counter counter = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        counter.setDailyVisitorCount(0L);
        counter.setDailyLinkCount(0L);
        repository.save(counter);
    }

}