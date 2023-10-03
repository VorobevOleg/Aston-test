package ru.vorobev.aston.test.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WithdrawRequest {

    @NotNull
    private Long accountNumber;

    @NotNull
    @Positive
    private BigDecimal amount;

    @Pattern(regexp = "^\\d{4}$")
    private String pin;
}
