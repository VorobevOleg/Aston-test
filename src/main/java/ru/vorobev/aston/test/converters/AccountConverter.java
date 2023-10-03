package ru.vorobev.aston.test.converters;

import org.springframework.stereotype.Component;
import ru.vorobev.aston.test.dto.SpecInfoDto;
import ru.vorobev.aston.test.entities.Account;

/**
 * Конвертер дто в сущности и обратно
 */
@Component
public class AccountConverter {
    public SpecInfoDto toSpecInfo(Account account) {

        SpecInfoDto specInfoDto = new SpecInfoDto();

        specInfoDto.setName(account.getOwner().getName());
        specInfoDto.setBalance(account.getBalance());

        return specInfoDto;
    }
}
