package com.arka.app_services.errors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object>handleExceptionInternal(
        Exception ex,
        Object body,
        HttpHeaders headers,
        HttpStatusCode status,
        WebRequest request
    ){
        return buildErrorResponse(ex, status);
    }

    @ExceptionHandler( value = {Exception.class})
    protected ResponseEntity<Object> handleAllException( Exception ex ){
        ResponseStatus responseStatus = ex.getClass().getAnnotation(ResponseStatus.class);

        if( responseStatus != null )
            return buildErrorResponse(ex, responseStatus.value());
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    private ResponseEntity<Object> buildErrorResponse( Exception exception, HttpStatusCode httpStatus ){
        ErrorResponse errorResponse = ErrorResponse.createInstance()
                                        .setStatusCode( httpStatus.value() );
        
        if( exception instanceof MethodArgumentNotValidException ){
            errorResponse.setMessage( ((MethodArgumentNotValidException) exception).getFieldErrors().stream()
                .map( err -> err.getField() + " " + err.getDefaultMessage() ).toList()
            );
            // errorResponse.setMessage( ((MethodArgumentNotValidException) exception).getBindingResult().getAllErrors().stream()
            //     .map( err -> err.getDefaultMessage()).collect(Collectors.toList())
            // );
        }else{
            errorResponse.addMessage( exception.getMessage() );
        }

        // if (printStackTrace) {
        //     errorResponse.setStackTrace(getStackTrace(exception));
        // }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }
}
