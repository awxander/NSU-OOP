package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.SqrtOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class SqrtOperationTest {
    @Test
    public void test() throws Exception{
        Stack<Double> stack = new Stack<>();
        Operation sqrtOperation  = new SqrtOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[]{};
        stack.push(2.25);
        sqrtOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(1.5, stack.peek(), 0);
    }
}
