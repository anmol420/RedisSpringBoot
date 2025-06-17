package com.anmol420.redis_springboot.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private UUID id;

    @NotBlank(message = "Book Name Must Not Be Empty")
    @Size(min = 1, max = 100, message = "Book Name Should Be Between {min} and {max} characters")
    private String name;

    private String description;

    @NotBlank(message = "Author Name Must Not Be Empty")
    @Size(min = 1, max = 100, message = "Author Name Should Be Between {min} and {max} characters")
    private String authorName;

}
