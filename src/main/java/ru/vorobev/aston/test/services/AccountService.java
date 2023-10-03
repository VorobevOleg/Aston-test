package ru.vorobev.aston.test.services;

import ru.vorobev.aston.test.dto.DepositRequest;
import ru.vorobev.aston.test.dto.SpecInfoDto;
import ru.vorobev.aston.test.dto.TransferRequest;
import ru.vorobev.aston.test.dto.WithdrawRequest;
import ru.vorobev.aston.test.entities.Account;

import java.math.BigDecimal;
import java.util.List;

/**
 * Сервис для работы со счетами
 */
public interface AccountService {

    /**
     * Получение списка всех счетов из базы
     *
     * @return список {@link Account}
     */
    List<Account> getAll();

    /**
     * Получение счета {@link Account} по id
     *
     * @param id id счета
     * @return {@link Account}, найденный по id
     */
    Account getById(Long id);

    /**
     * Получение счета {@link Account} по номеру
     *
     * @param number номер счета
     * @return {@link Account}, найденный по номеру
     */
    Account getByNumber(Long number);

    /**
     * Сохранение нового счета в базу
     *
     * @param account пришедший с контроллера объект с инфой для создания нового счета
     * @return {@link Account} - успешно сохранненный в базу счет
     */
    Account saveNew(Account account);

    List<SpecInfoDto> getSpecInfo();

    void deposit(DepositRequest depositRequest);

    BigDecimal withdraw(WithdrawRequest withdrawRequest);

    void transfer(TransferRequest transferRequest);
}