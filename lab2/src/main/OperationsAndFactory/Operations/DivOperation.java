package OperationsAndFactory.Operations;

import Exceptions.WrongArgumentsAmountException;
import OperationsAndFactory.Operation;

import java.util.HashMap;
import java.util.Stack;

public class DivOperation implements Operation {
    private final int ARGS_AMOUNT = 0;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != ARGS_AMOUNT){
            throw new WrongArgumentsAmountException("DIV", ARGS_AMOUNT);
        }
        double a = stack.pop();
        double b = stack.pop();
        stack.add(b / a);
    }
}