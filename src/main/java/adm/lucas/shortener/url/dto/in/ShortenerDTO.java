package adm.lucas.shortener.url.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(example = "{\"url\": \"https://www.youtube.com/watch?v=GDvqs_oFD6w\"}")
public record ShortenerDTO(
        @NotBlank(message = "Não pode ser nulo")
        @Pattern(regexp = "^(?:https?://)?(w{3}\\.)?[\\w_-]+((\\.\\w{2,}){1,2})(/([\\w._-]+/?)*(\\?[\\w_-]+=[^?/&]*(&[\\w_-]+=[^?/&]*)*)?)?$", message = "Endereço inválido.")
        String url
) {
}