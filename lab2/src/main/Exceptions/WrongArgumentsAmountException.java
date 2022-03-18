package Exceptions;

public class WrongArgumentsAmountException extends Exception {
    public WrongArgumentsAmountException(String operationName, int argsAmount){
        super("in operation " + operationName + " expected "+ argsAmount + " arguments");
    }
}
