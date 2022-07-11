package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Details.CarAccessory;
import ru.nsu.ccfit.tsibin.factory.Details.CarBody;
import ru.nsu.ccfit.tsibin.factory.Details.CarEngine;
import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;

public class CarCreateTask implements Runnable{
    private  CarAccessory accessory = null;
    private  CarBody body = null;
    private  CarEngine engine = null;
    private Car car;

    public void setDetails(CarAccessory accessory, CarBody body, CarEngine engine){
        this.accessory = accessory;
        this.body = body;
        this.engine =engine;

    }

    public Car getCar() {
        return car;
    }

    @Override
    public void run(){
        car = new Car(engine,body,accessory);
    }
}
