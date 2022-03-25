package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.DivOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class DivOperationTest {
    @Test
    public void test() throws Exception{
        Stack<Double> stack = new Stack<>();
        Operation divOperation = new DivOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[0];
        stack.push(4.0);
        stack.push(2.0);
        divOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(2.0, (double)stack.peek() ,0.0);//  4/2 = 2
    }
}
