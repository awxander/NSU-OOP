package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.PushOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class PushOperationTest {
    @Test
    public void test() throws Exception{
        Stack<Double> stack = new Stack<>();
        Operation pushOperation  = new PushOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[]{"2.3"};
        pushOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(2.3, (double)stack.peek(),0);
    }
}
