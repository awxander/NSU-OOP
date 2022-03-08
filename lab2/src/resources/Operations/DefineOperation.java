package resources.Operations;

import Exceptions.WrongArgumentsAmountException;
import resources.Operation;

import java.util.HashMap;
import java.util.Stack;

public class DefineOperation implements Operation {
    private int argsAmount = 2;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != argsAmount){
            throw new WrongArgumentsAmountException("DEFINE", argsAmount);
        }
        defineMap.put(args[0],args[1]);
    }
}