import Exceptions.NoSuchOperationException;
import Exceptions.WrongArgumentsAmountException;
import org.jetbrains.annotations.NotNull;
import resources.Operation;
import resources.OperationFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;


public class Calculator {
    static HashMap<String, String> defineMap = new HashMap<>();
    static Stack<Double> stack = new Stack<>();


    private static void checkDefinition(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String str = defineMap.get(args[i]);
            if (str != null) {
                args[i] = str;
            }
        }
    }


    public static void calculate(@NotNull BufferedReader reader) {
        String str;
        String[] res;
        try {
            OperationFactory operationFactory = OperationFactory.GetInstance();


            while ((str = reader.readLine()) != null) {
                if (str.contains("#")) {//if there are comments
                    str = str.substring(0, str.indexOf("#"));//delete comments
                }
                if (!str.isEmpty()) {

                    System.out.println(str);
                    res = str.split(" ");
                    String[] args = new String[res.length - 1];
                    //get arguments
                    if (res.length - 1 >= 0) System.arraycopy(res, 1, args, 0, res.length - 1);
                    Operation operation = operationFactory.GetOperation(res[0]);//res[0] - Operation name
                    checkDefinition(args);
                    operation.Execute(stack, defineMap, args);
                }
            }

        } catch (IOException | NoSuchMethodException | IllegalAccessException |
                InvocationTargetException | InstantiationException | ClassNotFoundException |
                NoSuchOperationException | WrongArgumentsAmountException e) {
            e.printStackTrace();
        }

    }
}
