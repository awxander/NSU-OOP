package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.MulOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class MulOperationTest {
    @Test
    public void test() throws Exception{
        Stack<Double> stack = new Stack<>();
        Operation mulOperation = new MulOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[]{};
        stack.push(4.0);
        stack.push(5.0);
        mulOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(20,(double)stack.peek(), 0);
    }
}
