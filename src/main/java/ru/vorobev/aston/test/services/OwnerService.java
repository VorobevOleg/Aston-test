package ru.vorobev.aston.test.services;

import ru.vorobev.aston.test.dto.OwnerNewDto;
import ru.vorobev.aston.test.entities.Owner;

public interface OwnerService {

//    List<Account> getAll();
//
//    Account getById(Long id);
//
//    Account getByNumber(Long number);

    Owner saveNew(OwnerNewDto ownerNewDto);
}
