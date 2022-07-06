package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;

public class CarCreateTask implements Runnable{
    private final AssembledCarsStorage assembledCarsStorage;
    private final AccessoryStorage accessoryStorage;
    private final EngineStorage engineStorage;
    private final BodyStorage bodyStorage;

    public CarCreateTask(AccessoryStorage accessoryStorage, EngineStorage engineStorage,
                         BodyStorage bodyStorage, AssembledCarsStorage assembledCarsStorage) {
        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.assembledCarsStorage = assembledCarsStorage;
    }

    @Override
    public void run(){
        assembledCarsStorage.addCar
                (new Car(engineStorage.takeCarEngine(),
                        bodyStorage.takeCarBody(),
                        accessoryStorage.takeCarAccessory()));
    }
}
