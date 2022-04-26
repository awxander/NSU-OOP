package OperationsAndFactory.Operations;

import Exceptions.WrongArgumentsAmountException;
import OperationsAndFactory.Operation;

import java.util.HashMap;
import java.util.Stack;

public class SqrtOperation implements Operation {
    private final int ARGS_AMOUNT = 0;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != ARGS_AMOUNT){
            throw new WrongArgumentsAmountException("SQRT", ARGS_AMOUNT);
        }
        double val  = stack.pop();
        stack.push(Math.sqrt(val));

    }

}
