package adm.lucas.shortener.url.controller;

import adm.lucas.shortener.url.dto.in.ShortenerDTO;
import adm.lucas.shortener.url.dto.out.CounterDTO;
import adm.lucas.shortener.url.dto.out.ShortenerResponseDTO;
import adm.lucas.shortener.url.service.CounterService;
import adm.lucas.shortener.url.service.ShortenerService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin
@Tag(name = "Controller", description = "URL Shortener")
@RequestMapping
@RequiredArgsConstructor
public class ShortenerController {

    private final ShortenerService service;
    private final CounterService counterService;

    @Hidden
    @GetMapping("/")
    public Resource toIndex() {
        counterService.setVisitorAnalytics(1L);
        return new ClassPathResource("public/index.html");
    }

    @Operation(summary = "Insert the URL", description = "Shorten your URL")
    @Transactional
    @PostMapping("/shorten")
    public ResponseEntity<ShortenerResponseDTO> shortenUrl(@RequestBody @Valid ShortenerDTO dto) {
        counterService.setLinkAnalytics(1L);
        String shortenedUrl = service.shortenUrl(dto.url());
        String response = "https://xisyz.xyz/" + shortenedUrl;
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShortenerResponseDTO(response));
    }

    @GetMapping("/analytics")
    public ResponseEntity<CounterDTO> getAnalytics() {
        return ResponseEntity.status(HttpStatus.OK).body(new CounterDTO(counterService.getAnalytics(1L)));
    }

    @Hidden
    @GetMapping("/{id}")
    public RedirectView navigate(@PathVariable String id) {
        try {
            String fullUrl = service.getData(id).getFullUrl();
            return new RedirectView(fullUrl);
        } catch (EntityNotFoundException exception) {
            return new RedirectView("https://www.youtube.com/watch?v=GDvqs_oFD6w&pp=ygUaYSBiYXJyZWlyYSB2YWkgdmlyYXIgYmFpbGU%3D");
        }
    }

    @Scheduled(cron = "0 0 0 * * ?", zone = "America/Sao_Paulo")
    public void cleanDailyHistory() {
        counterService.resetDailylyAnalytics(1L);
    }

    @Scheduled(cron = "0 0 0 1 * ?", zone = "America/Sao_Paulo")
    public void cleanMonthlyHistory() {
        counterService.resetMonthlyAnalytics(1L);
    }

}