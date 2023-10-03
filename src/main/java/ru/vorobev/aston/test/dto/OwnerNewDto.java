package ru.vorobev.aston.test.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerNewDto {

    @NotBlank
    private String name;

    @Pattern(regexp = "^\\d{4}$")
    private String pin;
}
