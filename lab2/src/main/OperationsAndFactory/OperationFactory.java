package OperationsAndFactory;


import Exceptions.NoSuchOperationException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;


public class OperationFactory {
    private static volatile OperationFactory operationFactory;
    private final Properties operationsProperties = new Properties();


    private OperationFactory() throws IOException {
        operationsProperties.load(OperationFactory.class.getClassLoader().
                getResourceAsStream("operations.properties"));
    }


    public static OperationFactory GetInstance() throws IOException {
        //Double-check with synchronized block for thread saving
        if (operationFactory == null) {
            synchronized (OperationFactory.class) {
                if (operationFactory == null) {
                    operationFactory = new OperationFactory();
                }
            }
        }
        return operationFactory;
    }


    public Operation GetOperation(String operationName)
            throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, IOException, NoSuchOperationException {

        Properties config = new Properties();
        config.load(OperationFactory.class.getClassLoader().getResourceAsStream("config.properties"));

        if (!operationsProperties.containsKey(operationName)) {
            throw new NoSuchOperationException("Operation " + operationName + " not found");
        }
        return (Operation) Class.forName(config.getProperty("OPERATIONS_CLASS_PATH") +
                operationsProperties.get(operationName)).getConstructor().newInstance();
    }
}