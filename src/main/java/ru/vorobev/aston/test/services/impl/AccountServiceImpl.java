package ru.vorobev.aston.test.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.aston.test.converters.AccountConverter;
import ru.vorobev.aston.test.dto.DepositRequest;
import ru.vorobev.aston.test.dto.SpecInfoDto;
import ru.vorobev.aston.test.dto.TransferRequest;
import ru.vorobev.aston.test.dto.WithdrawRequest;
import ru.vorobev.aston.test.entities.Account;
import ru.vorobev.aston.test.repositories.AccountRepository;
import ru.vorobev.aston.test.services.AccountService;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;
    private final AccountConverter accountConverter;

    @Override
    public List<Account> getAll() {
        return repository.findAll();
    }

    @Override
    public Account getById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Account not found by id: " + id));
    }

    @Override
    public Account getByNumber(Long number) {
        return repository.findByNumber(number).orElseThrow(
                () -> new EntityNotFoundException("Account not found by number: " + number));
    }

    @Override
    public Account saveNew(Account account) {
        return repository.save(account);
    }

    @Override
    public List<SpecInfoDto> getSpecInfo() {

        List<Account> accountList = getAll();

        return accountList.stream().map(accountConverter::toSpecInfo).toList();
    }

    @Override
    public void deposit(DepositRequest depositRequest) {
        Account account = getByNumber(depositRequest.getAccountNumber());
        BigDecimal newBalance = account.getBalance().add(depositRequest.getAmount());
        account.setBalance(newBalance);
        repository.save(account);
    }

    @Override
    public BigDecimal withdraw(WithdrawRequest withdrawRequest) {

        Account account = getByNumber(withdrawRequest.getAccountNumber());

        if (!account.getOwner().getPin().equals(withdrawRequest.getPin())) {
            throw new IllegalArgumentException("Wrong PIN");
        }

        if (account.getBalance().compareTo(withdrawRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Not enough money");
        }

        BigDecimal newBalance = account.getBalance().subtract(withdrawRequest.getAmount());
        account.setBalance(newBalance);

        repository.save(account);

        return withdrawRequest.getAmount();
    }

    @Override
    @Transactional
    public void transfer(TransferRequest transferRequest) {

        if (transferRequest.getAccountNumberFrom().equals(transferRequest.getAccountNumberTo())) {
            throw new IllegalArgumentException("Account numbers must not be equals");
        }

        Account accountFrom = getByNumber(transferRequest.getAccountNumberFrom());
        Account accountTo = getByNumber(transferRequest.getAccountNumberTo());

        if (!accountFrom.getOwner().getPin().equals(transferRequest.getPin())) {
            throw new IllegalArgumentException("Wrong PIN");
        }

        if (accountFrom.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            throw new IllegalArgumentException("Not enough money");
        }

        BigDecimal newBalanceFrom = accountFrom.getBalance().subtract(transferRequest.getAmount());
        accountFrom.setBalance(newBalanceFrom);

        BigDecimal newBalanceTo = accountTo.getBalance().add(transferRequest.getAmount());
        accountTo.setBalance(newBalanceTo);

        repository.save(accountFrom);
        repository.save(accountTo);
    }
}
