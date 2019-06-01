package com.xdlr.webflux.advice;

import com.xdlr.webflux.exceptions.CheckException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;


/**
 * 异常处理切面
 */
@ControllerAdvice
public class CheckAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<String> handleBindException(WebExchangeBindException e) {
        return new ResponseEntity<>(toStr(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckException.class)
    public ResponseEntity<String> handleCheckException(CheckException e) {
        return new ResponseEntity<>(toStr(e),HttpStatus.BAD_REQUEST);
    }

    private String toStr(CheckException e) {
        return e.getFieldName() + ": 错误的值 " + e.getFieldValue();
    }

    private String toStr(WebExchangeBindException ex) {
        return ex.getFieldErrors().stream()
                .map(e -> e.getField() + ":" + e.getDefaultMessage())
                .reduce("", (s1, s2) -> s1 + "\n" + s2);
    }



}
