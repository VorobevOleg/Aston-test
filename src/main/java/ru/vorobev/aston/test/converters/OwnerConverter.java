package ru.vorobev.aston.test.converters;

import org.springframework.stereotype.Component;
import ru.vorobev.aston.test.dto.OwnerNewDto;
import ru.vorobev.aston.test.entities.Owner;

/**
 * Конвертер дто в сущности и обратно
 */
@Component
public class OwnerConverter {
    public Owner toNew(OwnerNewDto dto) {

        Owner owner = new Owner();

        owner.setName(dto.getName());
        owner.setPin(dto.getPin());

        return owner;
    }
}
