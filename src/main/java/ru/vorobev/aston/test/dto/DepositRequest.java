package ru.vorobev.aston.test.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequest {

    @NotNull
    private Long accountNumber;

    @NotNull
    @Positive
    private BigDecimal amount;
}
