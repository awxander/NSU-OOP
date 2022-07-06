package ru.nsu.ccfit.tsibin.factory.Suppliers;

import ru.nsu.ccfit.tsibin.factory.Details.CarEngine;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;
import ru.nsu.ccfit.tsibin.factory.Supplier;

public class EngineSupplier extends Thread implements Supplier {
    private int productionSpeed = 1000;
    private final EngineStorage engineStorage;

    public EngineSupplier(EngineStorage engineStorage) {
        this.engineStorage = engineStorage;
        this.start();
    }

    @Override
    public void run() {
        synchronized (engineStorage) {
            while (true) {
                if (!engineStorage.isFull()) {
                    sendEngine();
                } else {
                    try {
                        System.out.println("engine supplier number " + this.getId() + " waiting");
                        engineStorage.wait();
                        System.out.println("engine supplier number " + this.getId() + " working again");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    this.sleep(productionSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }
    }


    public void sendEngine() {

        CarEngine carEngine = new CarEngine();
        engineStorage.addEngine(carEngine);
    }


}
