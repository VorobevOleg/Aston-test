package ru.vorobev.aston.test.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransferRequest {

    @NotNull
    private Long accountNumberFrom;

    @NotNull
    private Long accountNumberTo;

    @NotNull
    private BigDecimal amount;

    @Pattern(regexp = "^\\d{4}$")
    private String pin;
}
