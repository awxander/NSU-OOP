package ru.nsu.ccfit.tsibin;

import java.awt.Color;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import ru.nsu.ccfit.tsibin.GUI.MainFrame;
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

public class Main extends JFrame {

    private static final String PATH_TO_PROPERTIES = "resources\\config.properties";
    private final static Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    private static void createBodySuppliers(BodyStorage bodyStorage){
        int suppliersAmount = Integer.parseInt(properties.getProperty("car_body_suppliers_amount"));
        for(int i = 0; i < suppliersAmount; i++){
            new BodySupplier(bodyStorage);
        }
    }
    
    private static void createAccessorySuppliers(AccessoryStorage accessoryStorage){
        int suppliersAmount = Integer.parseInt(properties.getProperty("car_accessory_suppliers_amount"));
        for(int i = 0; i < suppliersAmount; i++){
            new AccessorySupplier(accessoryStorage);
        }
    }
    
    private static void createDealers(AssembledCarsStorage assembledCarsStorage){
        int suppliersAmount = Integer.parseInt(properties.getProperty("dealers_amount"));
        for(int i = 0; i < suppliersAmount; i++){
            new Dealer(assembledCarsStorage);
        }
    }
    
    private static void createEngineSuppliers(EngineStorage engineStorage){
        int dealersAmount = Integer.parseInt(properties.getProperty("car_engine_suppliers_amount"));
        for(int i = 0; i < dealersAmount; i++){
            new EngineSupplier(engineStorage);
        }
    }
    
    

    public static void main(String[] args) {

        AccessoryStorage accessoryStorage = new AccessoryStorage();
        BodyStorage bodyStorage = new BodyStorage();
        EngineStorage engineStorage = new EngineStorage();
        AssembledCarsStorage assembledCarsStorage = new AssembledCarsStorage();

        createAccessorySuppliers(accessoryStorage);
        createBodySuppliers(bodyStorage);
        createEngineSuppliers(engineStorage);
        createDealers(assembledCarsStorage);

        WorkerThreadPool threadPool = new WorkerThreadPool(accessoryStorage, bodyStorage,
                engineStorage, assembledCarsStorage);

        Controller controller = new Controller(threadPool, assembledCarsStorage);

        new MainFrame().setVisible(true);
    }
}
