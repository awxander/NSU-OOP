package OperationsTests;

import OperationsAndFactory.Operation;
import OperationsAndFactory.Operations.PrintOperation;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Stack;

public class PrintOperationTest {
    @Test
    public void test() throws Exception{
        Stack<Double> stack = new Stack<>();
        Operation printOperation  = new PrintOperation();
        HashMap<String, String> hashMap = new HashMap<>();
        String[] args = new String[]{};
        stack.push(1.0);
        ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        printOperation.Execute(stack, hashMap, args);
        Assert.assertEquals(out.toString(), "1.0\r\n");
    }
}
