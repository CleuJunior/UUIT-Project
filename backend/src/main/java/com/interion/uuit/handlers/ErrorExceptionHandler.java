package com.interion.uuit.handlers;

import com.interion.uuit.exceptions.BaseException;
import com.interion.uuit.exceptions.DisciplineClosedException;
import com.interion.uuit.exceptions.FullCapaticyException;
import com.interion.uuit.exceptions.NotFoundException;
import com.interion.uuit.exceptions.StudentAlreadyRegisteredException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ErrorExceptionHandler {

    @ExceptionHandler(DisciplineClosedException.class)
    public ResponseEntity<ErrorInfoJson> disciplineClosedHandler(DisciplineClosedException disciplineClosedException) {
        var err = this.buildErrorInfo(disciplineClosedException);
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(FullCapaticyException.class)
    public ResponseEntity<ErrorInfoJson> notFoundHandler(FullCapaticyException fullCapaticyException) {
        var err = this.buildErrorInfo(fullCapaticyException);
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfoJson> notFoundHandler(NotFoundException notFoundException) {
        var err = this.buildErrorInfo(notFoundException);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(StudentAlreadyRegisteredException.class)
    public ResponseEntity<ErrorInfoJson> disciplineClosedHandler(StudentAlreadyRegisteredException studentAlreadyRegisteredException) {
        var err = this.buildErrorInfo(studentAlreadyRegisteredException);
        return ResponseEntity.badRequest().body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfoJson> validationExceptions(MethodArgumentNotValidException beanValidationException) {
        var err = this.buildErrorInfo(beanValidationException);
        log.info(err.message());
        return ResponseEntity.badRequest().body(err);
    }

    private ErrorInfoJson buildErrorInfo(BaseException exception) {
        return new ErrorInfoJson(exception.httpStatusValue(), exception.getReasonPhrase(), exception.getMessage());
    }

    private ErrorInfoJson buildErrorInfo(MethodArgumentNotValidException exception) {
        return new ErrorInfoJson(
                exception.getStatusCode().value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                this.getFirsDefaultMessage(exception)
        );
    }

    private String getFirsDefaultMessage(BindException exception) {
        return exception
                .getAllErrors()
                .stream()
                .findFirst()
                .orElseThrow()
                .getDefaultMessage();
    }
}
