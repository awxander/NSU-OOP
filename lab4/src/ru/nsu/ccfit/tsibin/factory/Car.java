package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Details.CarAccessory;
import ru.nsu.ccfit.tsibin.factory.Details.CarBody;
import ru.nsu.ccfit.tsibin.factory.Details.CarEngine;

public class Car {
    private final CarEngine carEngine;
    private final CarBody carBody;
    private final CarAccessory carAccessory;
    private static int amount = 0;
    private final int ID;


    public Car(CarEngine carEngine, CarBody carBody, CarAccessory carAccessory) {
        this.carEngine = carEngine;
        this.carBody = carBody;
        this.carAccessory = carAccessory;
        ID = amount;
        increaseAmount();
    }

    private synchronized void increaseAmount(){
        amount++;
    }

    public int getID(){
        return ID;
    }

    public int getBodyID(){
        return carBody.getID();
    }

    public int getAccessoryID(){
        return carAccessory.getID();
    }

    public int getEngineID(){
        return carEngine.getID();
    }

}
