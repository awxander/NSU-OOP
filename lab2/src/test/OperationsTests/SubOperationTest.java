package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.SubOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class SubOperationTest {
    @Test
    public void test()throws Exception{
        Stack<Double> stack = new Stack<>();
        Operation subOperation  = new SubOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[]{};
        stack.push(2.25);
        stack.push(3.25);
        subOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(-1,(double)stack.peek(), 0);
    }
}
