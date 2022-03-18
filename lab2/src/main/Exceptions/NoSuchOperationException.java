package Exceptions;

public class NoSuchOperationException extends Exception {
    public NoSuchOperationException(String operationName){
        super(operationName);
    }
}
