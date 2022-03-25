package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.DefineOperation;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class DefineOperationTest {
    @Test
    public void test() throws Exception {
        Stack<Double> stack = new Stack<>();
        Operation defineOperation = new DefineOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[]{"a", "5"};
        defineOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(hashMap.get("a"), "5");

    }
}
