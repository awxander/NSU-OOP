package ru.nsu.ccfit.tsibin.factory;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;

public class Dealer extends Thread {
    private static int requestSpeed = 1;
    private final AssembledCarsStorage assembledCarsStorage;
    private Car car;
    private final int DEFAULT_SLEEP_TIMEOUT = 2000;
    private static Logger logger = Logger.getLogger(Dealer.class.getName());
    private static FileHandler fileHandler = null;

    static {
        try {
            LogManager.getLogManager().readConfiguration();
            fileHandler = new FileHandler("out\\production\\logging\\logs.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.addHandler(fileHandler);
    }

    public Dealer(AssembledCarsStorage assembledCarsStorage) {
        this.assembledCarsStorage = assembledCarsStorage;
        start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (assembledCarsStorage) {
                while (assembledCarsStorage.isEmpty()) {
                    try {
                        assembledCarsStorage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                car = assembledCarsStorage.takeCar();
                logger.info("dealer number: " + this.getId() + ", car ID:" + car.getID()
                        + "(body ID: " + car.getBodyID() + ", engine ID: " + car.getEngineID()
                        + ", accessory ID: " + car.getAccessoryID() + ")");
                sellCar();
            }
            try {
                Thread.sleep(DEFAULT_SLEEP_TIMEOUT / requestSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void setRequestSpeed(int requestSpeed) {
        Dealer.requestSpeed = requestSpeed;
    }

    private void sellCar() {
        car = null;
    }
}
