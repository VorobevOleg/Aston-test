package ru.vorobev.aston.test.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vorobev.aston.test.converters.OwnerConverter;
import ru.vorobev.aston.test.dto.OwnerNewDto;
import ru.vorobev.aston.test.entities.Account;
import ru.vorobev.aston.test.entities.Owner;
import ru.vorobev.aston.test.repositories.OwnerRepository;
import ru.vorobev.aston.test.services.AccountService;
import ru.vorobev.aston.test.services.OwnerService;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OwnerServiceImpl implements OwnerService {

    private final OwnerRepository repository;
    private final AccountService accountService;
    private final OwnerConverter ownerConverter;

    @Override
    @Transactional
    public Owner saveNew(OwnerNewDto ownerNewDto) {

        if (repository.findByName(ownerNewDto.getName()).isPresent()) {
            throw new IllegalArgumentException("Owner exist with name: " + ownerNewDto.getName());
        }

        Owner ownerToSave = ownerConverter.toNew(ownerNewDto);

        Account accountToSave = Account.builder()
                .owner(ownerToSave)
                .number(new Random().nextLong(100000000000000000L, 999999999999999999L))
                .balance(BigDecimal.ZERO)
                .build();

        Account accountFromDb = accountService.saveNew(accountToSave);

        ownerToSave.setAccount(accountFromDb);

        return repository.save(ownerToSave);
    }
}
