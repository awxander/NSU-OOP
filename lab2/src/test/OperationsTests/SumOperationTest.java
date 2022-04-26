package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.SumOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class SumOperationTest {
    @Test
    public void test() throws Exception{
        Stack<Double> stack = new Stack<>();
        Operation sumOperation = new SumOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[0];
        stack.push(4.0);
        stack.push(3.0);
        sumOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(7.0, (double)stack.peek() ,0.0);
    }
}
