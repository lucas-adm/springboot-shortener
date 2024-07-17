package adm.lucas.shortener.url.dto.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(example = "{\"url\": \"https://www.youtube.com/watch?v=GDvqs_oFD6w\"}")
public record ShortenerDTO(
        @NotBlank(message = "Não pode ser nulo")
        @Pattern(regexp = "^(https://)(www\\.)?[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}(/[\\w.-]*)*(\\?[\\w=&-]*)?(#.*)?$", message = "Endereço inválido.")
        @Size(max = 333, message = "Limite de 333 caracteres.")
        String url
) {
}