package shoppingCart.App.exceptionHandler;

public class ResourceNotFountException extends RuntimeException{
    public ResourceNotFountException(){
        super();
    }
    public ResourceNotFountException(String message){
        super(message);
    }
}
