package com.cardapi.cardapi.helpers;

import com.cardapi.cardapi.exceptions.NotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RequiredArgsConstructor
// based on https://www.baeldung.com/global-error-handler-in-a-spring-rest-api
public class GlobalExceptionAdvice {

    @Data
    public class ErrorDetails {
        private Date timestamp;
        private String message;
        private String details;

        public ErrorDetails(Date timestamp, String message, String details) {
            super();
            this.timestamp = timestamp;
            this.message = message;
            this.details = details;
        }
    }

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionAdvice.class);

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> unhandledExceptionHandler(Exception ex, WebRequest request) throws Exception {
        // we can log here for a severe error
        throw ex;
    }

    // When security is added
//    @ExceptionHandler(BadCredentialsException.class)
//    public final ResponseEntity<ErrorDetails> UnauthorizedHandler401(Exception ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
//                request.getDescription(false));
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
//    }

//    @ExceptionHandler({AccessDeniedException.class})
//    public final ResponseEntity<ErrorDetails> ForbiddenHandler403(Exception ex, WebRequest request) {
//        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
//                request.getDescription(false));
//
//        // we can log here for a error
//
//        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
//    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public final ResponseEntity<ErrorDetails> BadRequestHandler400(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        // we can log here for a error

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorDetails> notFoundHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
                request.getDescription(false));

        // we can log here for a warning

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
