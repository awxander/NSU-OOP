package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.PopOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class PopOperationTest {
    @Test
    public void test()throws Exception{

        Stack<Double> stack = new Stack<>();
        Operation popOperation = new PopOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[]{};
        stack.push(1.0);
        popOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(0, stack.size());
    }
}
