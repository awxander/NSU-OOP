package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;

public class Worker extends Thread {
    private final AccessoryStorage accessoryStorage;
    private final AssembledCarsStorage assembledCarsStorage;
    private final BodyStorage bodyStorage;
    private final EngineStorage engineStorage;

    public Worker(AccessoryStorage accessoryStorage,
                  BodyStorage bodyStorage,
                  EngineStorage engineStorage,
                  AssembledCarsStorage assembledCarsStorage) {
        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.assembledCarsStorage = assembledCarsStorage;
    }

    @Override
    public void run() {
        while (true) {
            if (!accessoryStorage.isEmpty() && !bodyStorage.isEmpty()
                    && !engineStorage.isEmpty() && !assembledCarsStorage.isFull()) {
                assembledCarsStorage.addCar(createCar());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Car createCar() {
        return new Car(engineStorage.takeCarEngine(), bodyStorage.takeCarBody(), accessoryStorage.takeCarAccessory());
    }

}
