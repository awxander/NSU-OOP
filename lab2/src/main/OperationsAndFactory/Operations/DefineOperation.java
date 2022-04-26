package OperationsAndFactory.Operations;

import Exceptions.WrongArgumentsAmountException;
import OperationsAndFactory.Operation;

import java.util.HashMap;
import java.util.Stack;

public class DefineOperation implements Operation {
    private final int ARGS_AMOUNT = 2;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != ARGS_AMOUNT){
            throw new WrongArgumentsAmountException("DEFINE", ARGS_AMOUNT);
        }
        defineMap.put(args[0],args[1]);
    }
}