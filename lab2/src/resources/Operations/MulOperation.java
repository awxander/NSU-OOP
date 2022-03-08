package resources.Operations;

import Exceptions.WrongArgumentsAmountException;
import resources.Operation;

import java.util.HashMap;
import java.util.Stack;

public class MulOperation implements Operation {
    private int argsAmount = 0;
    @Override
    public void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException {
        if(args.length != argsAmount){
            throw new WrongArgumentsAmountException("DEFINE", argsAmount);
        }
        double a = stack.pop();
        double b = stack.pop();
        stack.add(b * a);
    }
}
