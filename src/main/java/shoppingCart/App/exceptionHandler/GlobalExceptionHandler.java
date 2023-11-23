package shoppingCart.App.exceptionHandler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFountException.class)
    public String HandelResourceNotFoundException(ResourceNotFountException ex){
        return ex.getMessage();
    }
}
