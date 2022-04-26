package OperationsAndFactory.Operations;

import Exceptions.WrongArgumentsAmountException;
import OperationsAndFactory.Operation;

import java.util.HashMap;
import java.util.Stack;

public class PrintOperation implements Operation {
    private final int ARGS_AMOUNT = 0;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != ARGS_AMOUNT){
            throw new WrongArgumentsAmountException("PRINT", ARGS_AMOUNT);
        }
        System.out.println(stack.peek());
    }
}
