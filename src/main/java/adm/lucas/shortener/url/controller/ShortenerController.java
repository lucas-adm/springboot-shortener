package adm.lucas.shortener.url.controller;

import adm.lucas.shortener.url.dto.in.ShortenerDTO;
import adm.lucas.shortener.url.dto.out.ShortenerResponseDTO;
import adm.lucas.shortener.url.service.ShortenerService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@CrossOrigin
@Tag(name = "Controller", description = "URL Shortener")
@RequestMapping
@RequiredArgsConstructor
public class ShortenerController {

    private final ShortenerService service;

    @Operation(summary = "Insert the URL", description = "Shorten your URL")
    @Transactional
    @PostMapping(value = "/shorten")
    public ResponseEntity<ShortenerResponseDTO> shortenUrl(@RequestBody @Valid ShortenerDTO dto, HttpServletRequest request) {
        String shortenedUrl = service.shortenUrl(dto.url());
        String response = request.getRequestURL().toString().replace("shorten", shortenedUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ShortenerResponseDTO(response));
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

}