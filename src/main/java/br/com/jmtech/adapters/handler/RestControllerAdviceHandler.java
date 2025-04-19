package br.com.jmtech.adapters.handler;

import br.com.jmtech.application.dto.DetailDTO;
import br.com.jmtech.domain.enums.ErrorType;
import br.com.jmtech.adapters.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;


@ControllerAdvice
@Slf4j
public class RestControllerAdviceHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorType errorType = ErrorType.INTERNAL_SERVER_ERROR;
        String detail = "Um erro inesperado aconteceu. Entre em contato com o administrador do sistema.";
        log.error(detail, ex);
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFound(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorType errorType = ErrorType.NOT_FOUND;
        String detail = ex.getMessage();
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler({BadRequestException.class, MethodArgumentTypeMismatchException.class, JpaObjectRetrievalFailureException.class})
    public ResponseEntity<Object> handleDatabaseCreateException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail = ex.getMessage();
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataBaseCreateException.class)
    public ResponseEntity<Object> handleDatabaseCreateRetException(DataBaseCreateException e, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail = e.getMessage();
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ExpiredQRCodeException.class)
    public ResponseEntity<Object> handleExpiredQRCodeException(ExpiredQRCodeException e, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorType errorType = ErrorType.UNPROCESSABLE_ENTITY;
        String detail = "O QR Code informado está expirado";
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(e, apiError, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handlePageLimitException(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorType errorType = ErrorType.UNPROCESSABLE_ENTITY;
        String detail = ex.getMessage();
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail;
        if (ex.getConstraintViolations().isEmpty()) {
            detail = ex.getMessage();
        } else {
            detail = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessageTemplate).collect(Collectors.joining("; "));
        }
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail = "Erro ao processar requisição";
        log.error(detail, ex);
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorType errorType = ErrorType.BAD_REQUEST;
        String detail = ex.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("; "));
        DetailDTO apiError = createApiErrorBuilder(status, errorType, detail);
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), status, request);
    }

    private DetailDTO createApiErrorBuilder(HttpStatus status, ErrorType errorType, String detail) {
        return DetailDTO.builder()
                .status(status.value())
                .title(errorType.getTitle())
                .detail(detail)
                .build();
    }


}
