package ru.vorobev.aston.test.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.aston.test.dto.DepositRequest;
import ru.vorobev.aston.test.dto.SpecInfoDto;
import ru.vorobev.aston.test.dto.TransferRequest;
import ru.vorobev.aston.test.dto.WithdrawRequest;
import ru.vorobev.aston.test.entities.Account;
import ru.vorobev.aston.test.services.AccountService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService serevice;

    @GetMapping
    public List<Account> getAllAccount() {
        return serevice.getAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return serevice.getById(id);
    }

    @GetMapping("/spec-call")
    public List<SpecInfoDto> getSpecInfo() {
        return serevice.getSpecInfo();
    }

    @PostMapping("/deposit")
    public void deposit(@RequestBody @Valid DepositRequest depositRequest) {
        serevice.deposit(depositRequest);
    }

    @PostMapping("/withdraw")
    public BigDecimal withdraw(@RequestBody @Valid WithdrawRequest withdrawRequest) {
        return serevice.withdraw(withdrawRequest);
    }

    @PostMapping("/transfer")
    public void transfer(@RequestBody @Valid TransferRequest transferRequest) {
        serevice.transfer(transferRequest);
    }
}
