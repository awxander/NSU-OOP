package ru.nsu.ccfit.tsibin.factory.Suppliers;

import ru.nsu.ccfit.tsibin.factory.Details.CarEngine;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;
import ru.nsu.ccfit.tsibin.factory.Supplier;

public class EngineSupplier extends Thread implements Supplier {
    private static int productionSpeed = 1;
    private final EngineStorage engineStorage;
    private static Integer producedDetailsAmount = 0;
    private final int DEFAULT_SLEEP_TIMEOUT = 2000;


    public EngineSupplier(EngineStorage engineStorage) {
        this.engineStorage = engineStorage;
        this.start();
    }

    public static Integer getProducedDetailsAmount() {
        return producedDetailsAmount;
    }

    public static void setProductionSpeed(int productionSpeed) {
        EngineSupplier.productionSpeed = productionSpeed;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (engineStorage) {
                if (!engineStorage.isFull()) {
                    sendEngine();
                } else {
                    try {
                        engineStorage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                this.sleep(DEFAULT_SLEEP_TIMEOUT / productionSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendEngine() {

        CarEngine carEngine = new CarEngine();
        synchronized (producedDetailsAmount){
            producedDetailsAmount++;
        }
        engineStorage.addEngine(carEngine);
    }


}
