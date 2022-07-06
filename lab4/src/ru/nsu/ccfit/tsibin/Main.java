package ru.nsu.ccfit.tsibin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import ru.nsu.ccfit.tsibin.factory.Controller;
import ru.nsu.ccfit.tsibin.factory.Dealer;
import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;
import ru.nsu.ccfit.tsibin.factory.Suppliers.AccessorySupplier;
import ru.nsu.ccfit.tsibin.factory.Suppliers.BodySupplier;
import ru.nsu.ccfit.tsibin.factory.Suppliers.EngineSupplier;
import ru.nsu.ccfit.tsibin.threadpool.WorkerThreadPool;

public class Main {
    public static void main(String[] args) throws IOException {

        File file = new File("resources\\config.properties");
        BufferedReader fin = new BufferedReader(new FileReader(file));
        String line;

        while ((line = fin.readLine()) != null) System.out.println(line);
        AccessoryStorage accessoryStorage = new AccessoryStorage();
        AccessorySupplier accessorySupplier1 = new AccessorySupplier(accessoryStorage);
        AccessorySupplier accessorySupplier2 = new AccessorySupplier(accessoryStorage);

        BodyStorage bodyStorage = new BodyStorage();
        BodySupplier bodySupplier = new BodySupplier(bodyStorage);

        EngineStorage engineStorage = new EngineStorage();
        EngineSupplier engineSupplier = new EngineSupplier(engineStorage);

        AssembledCarsStorage assembledCarsStorage = new AssembledCarsStorage();

        WorkerThreadPool threadPool = new WorkerThreadPool(accessoryStorage, bodyStorage,
                engineStorage, assembledCarsStorage);


        Controller controller = new Controller(threadPool,accessoryStorage, bodyStorage,
                engineStorage, assembledCarsStorage);

        new Dealer(assembledCarsStorage);
        new Dealer(assembledCarsStorage);
        new Dealer(assembledCarsStorage);
        new Dealer(assembledCarsStorage);

    }
}
