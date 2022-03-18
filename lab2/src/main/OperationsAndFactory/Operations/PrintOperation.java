package OperationsAndFactory.Operations;

import Exceptions.WrongArgumentsAmountException;
import OperationsAndFactory.Operation;

import java.util.HashMap;
import java.util.Stack;

public class PrintOperation implements Operation {
    private int argsAmount = 0;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != argsAmount){
            throw new WrongArgumentsAmountException("PRINT", argsAmount);
        }
        System.out.println(stack.peek());
    }
}
