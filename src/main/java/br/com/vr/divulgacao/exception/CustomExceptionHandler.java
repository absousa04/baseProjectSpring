package br.com.vr.divulgacao.exception;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import br.com.vr.divulgacao.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

    private MessageSource messageSource;

    public CustomExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale) {

        return ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError ->
                        ErrorResponse.builder().field(fieldError.getField())
                                .message( messageSource.getMessage(fieldError, locale))
                                .build())
                .collect(Collectors.toList());

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public List<ErrorResponse> handle(ResourceNotFoundException ex, Locale locale) {

        ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).build();

        return Arrays.asList(error);
    }

   
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> handle(MethodArgumentTypeMismatchException ex, Locale locale) {

        ErrorResponse error = ErrorResponse.builder().field(ex.getName()).message(ex.getMessage()).build();

        return Arrays.asList(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> handle(NumberFormatException ex, Locale locale) {

        ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).build();

        return Arrays.asList(error);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public List<ErrorResponse> handle(Exception ex, Locale locale) {

        log.error("UNEXPECTE ERROR",ex);

        ErrorResponse error = ErrorResponse.builder().message(ex.getMessage()).build();

        return Arrays.asList(error);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex, Locale locale) {

        ErrorResponse error = ErrorResponse.builder()
                .field(ex.getParameterName())
                .message(ex.getMessage())
                .build();

        return Arrays.asList(error);
    }

}