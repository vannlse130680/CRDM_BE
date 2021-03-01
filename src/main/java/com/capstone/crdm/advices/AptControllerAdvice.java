package com.capstone.crdm.advices;


import com.capstone.crdm.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@SuppressWarnings("unused")
@Slf4j
@ControllerAdvice
public class AptControllerAdvice {

    /**
     * Handles illegal argument exceptions (bad request).
     * @param ex the exception represents the bad request error
     * @param request the original request which caused the error
     * @return a response entity that contains the description of the error
     */
    @ExceptionHandler(CrdmIllegalArgumentException.class)
    public final ResponseEntity<ExceptionContent> handleIllegalArgumentException(CrdmIllegalArgumentException ex, WebRequest request) {
        this.logError(ex, false);
        this.completeErrorContent(ex.getContent(), request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getContent());
    }

    /**
     * Handles illegal access exceptions (access forbidden).
     * @param ex the exception represents the access denied error
     * @param request the original request which caused the error
     * @return a response entity that contains the description of the error
     */
    @ExceptionHandler(CrdmIllegalAccessException.class)
    public final ResponseEntity<ExceptionContent> handleIllegalAccessException(CrdmIllegalAccessException ex, WebRequest request) {
        this.logError(ex, false);
        this.completeErrorContent(ex.getContent(), request);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getContent());
    }

    /**
     * Handles illegal state exceptions.
     * @param ex the exception represents the state error
     * @param request the original request which caused the error
     * @return a response entity that contains the description of the error
     */
    @ExceptionHandler(CrdmIllegalStateException.class)
    public final ResponseEntity<ExceptionContent> handleIllegalStateException(CrdmIllegalStateException ex, WebRequest request) {
        this.logError(ex, false);
        this.completeErrorContent(ex.getContent(), request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getContent());
    }

    /**
     * Handles not found exceptions (404).
     * @param ex the exception represents the state error
     * @param request the original request which caused the error
     * @return a response entity that contains the description of the error
     */
    @ExceptionHandler(CrdmResourceNotFoundException.class)
    public final ResponseEntity<ExceptionContent> handleResourceNotFoundException(CrdmResourceNotFoundException ex, WebRequest request) {
        this.logError(ex, false);
        this.completeErrorContent(ex.getContent(), request);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getContent());
    }

    /**
     * Handles unauthorized exceptions (401).
     * @param ex the exception represents the state error
     * @param request the original request which caused the error
     * @return a response entity that contains the description of the error
     */
    @ExceptionHandler(CrdmUnauthorizedException.class)
    public final ResponseEntity<ExceptionContent> handleUnauthorizedException(CrdmUnauthorizedException ex, WebRequest request) {
        this.logError(ex, false);
        this.completeErrorContent(ex.getContent(), request);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).header("WWW-Authenticate", ex.getLocalizedMessage()).body(ex.getContent());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public final ResponseEntity<ExceptionContent> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        this.logError(ex, false);
        ExceptionContent exceptionContent = new ExceptionContent();
        exceptionContent.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionContent.setMessage(ex.getLocalizedMessage());
        this.completeErrorContent(exceptionContent, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionContent);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionContent> handleRSQLParserException(AccessDeniedException ex, WebRequest request) {
        this.logError(ex, false);
        ExceptionContent exceptionContent = new ExceptionContent();
        exceptionContent.setErrorCode(HttpStatus.FORBIDDEN.value());
        exceptionContent.setMessage(ex.getLocalizedMessage());
        this.completeErrorContent(exceptionContent, request);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exceptionContent);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ResponseEntity<ExceptionContent> handleHttpNotReadableException(HttpMessageNotReadableException ex, WebRequest request) {
        this.logError(ex, false);
        ExceptionContent exceptionContent = new ExceptionContent();
        exceptionContent.setErrorCode(HttpStatus.BAD_REQUEST.value());
        exceptionContent.setMessage(ex.getLocalizedMessage());
        this.completeErrorContent(exceptionContent, request);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionContent);
    }

    @ExceptionHandler(NullPointerException.class)
    public final ResponseEntity<ExceptionContent> handleGeneralException(NullPointerException ex, WebRequest request) {
        this.logError(ex, true);
        ExceptionContent exceptionContent = new ExceptionContent();
        exceptionContent.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionContent.setMessage(ex.getLocalizedMessage());
        this.completeErrorContent(exceptionContent, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionContent);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionContent> handleGeneralException(Exception ex, WebRequest request) {
        this.logError(ex, true);
        ExceptionContent exceptionContent = new ExceptionContent();
        exceptionContent.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exceptionContent.setMessage(ex.getLocalizedMessage());
        this.completeErrorContent(exceptionContent, request);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionContent);
    }

    private void logError(Exception ex, boolean fullExceptionRequired) {
        log.warn("Resolved exception in type {}. Message: {}", ex.getClass().getName(), ex.getLocalizedMessage());
        if (fullExceptionRequired) {
            log.debug("Exception for debugging:", ex);
        }
    }

    private void completeErrorContent(ExceptionContent errorContent,WebRequest request) {
        errorContent.setTimestamp(Instant.now().toString());
        errorContent.setPath(request.getDescription(false).substring("uri:".length()));
    }

}
