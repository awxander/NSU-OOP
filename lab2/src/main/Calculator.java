import Exceptions.NoSuchOperationException;
import Exceptions.WrongArgumentsAmountException;
import org.jetbrains.annotations.NotNull;
import OperationsAndFactory.Operation;
import OperationsAndFactory.OperationFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Stack;
import java.util.logging.Logger;


public class Calculator {

    private static final Logger log = Logger.getLogger(Calculator.class.getName());
    private final HashMap<String, String> defineMap = new HashMap<>();
    private final Stack<Double> stack = new Stack<>();


    private  void checkDefinition(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String str = defineMap.get(args[i]);
            if (str != null) {
                args[i] = str;
            }
        }
    }


    public void calculate(@NotNull BufferedReader reader) {
        String str;
        String[] res;
        try {

            OperationFactory operationFactory = OperationFactory.GetInstance();
            while ((str = reader.readLine()) != null) {
                try {


                    if (str.contains("#")) {//if there are comments
                        str = str.substring(0, str.indexOf("#"));//delete comments
                    }
                    if (!str.isEmpty()) {
                        res = str.split(" ");//split operation from arguments
                        String[] args = new String[res.length - 1];
                        //get arguments
                        if (res.length - 1 >= 0) System.arraycopy(res, 1, args, 0, res.length - 1);
                        Operation operation = operationFactory.GetOperation(res[0]);//res[0] - Operation name
                        checkDefinition(args);
                        operation.Execute(stack, defineMap, args);
                    }
                } catch (IOException | WrongArgumentsAmountException | ClassNotFoundException
                        | NoSuchOperationException | NoSuchMethodException | IllegalAccessException
                        | InvocationTargetException | InstantiationException e) {
                    log.warning("calculating error: " + e.getLocalizedMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            log.warning("calculating error: " + e.getLocalizedMessage());
            e.printStackTrace();
        }

        log.info("calculating was successful");


    }
}
