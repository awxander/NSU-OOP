package OperationsAndFactory;

import Exceptions.WrongArgumentsAmountException;

import java.util.HashMap;
import java.util.Stack;

public interface Operation {
    void Execute(Stack<Double> stack, HashMap<String, String> defineMap, String[] args)
            throws WrongArgumentsAmountException;
}
