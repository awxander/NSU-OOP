package OperationsAndFactory.Operations;

import Exceptions.WrongArgumentsAmountException;
import OperationsAndFactory.Operation;

import java.util.HashMap;
import java.util.Stack;

public class PushOperation implements Operation {
    private final int ARGS_AMOUNT = 1;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != ARGS_AMOUNT){
            throw new WrongArgumentsAmountException("PUSH", ARGS_AMOUNT);
        }
        stack.push(Double.parseDouble(args[0]));
    }
}