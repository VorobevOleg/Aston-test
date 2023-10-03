package ru.vorobev.aston.test.controllers.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Перехватчик исключений для контроллеров (для удобства отладки на фронте)
 */
@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleConflict(Exception e, WebRequest request) {
        String body = "Error : " + e.getMessage();
        log.error(body);
        return handleExceptionInternal(e, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
