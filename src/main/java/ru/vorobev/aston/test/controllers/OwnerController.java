package ru.vorobev.aston.test.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.vorobev.aston.test.dto.OwnerNewDto;
import ru.vorobev.aston.test.entities.Owner;
import ru.vorobev.aston.test.services.OwnerService;

@RestController
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService serevice;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Owner saveNewOwner(@RequestBody @Valid OwnerNewDto ownerNewDto) {
        return serevice.saveNew(ownerNewDto);
    }
}
