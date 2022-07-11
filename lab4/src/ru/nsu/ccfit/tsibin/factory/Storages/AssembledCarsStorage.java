package ru.nsu.ccfit.tsibin.factory.Storages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ru.nsu.ccfit.tsibin.factory.Car;
import ru.nsu.ccfit.tsibin.factory.Controller;
import ru.nsu.ccfit.tsibin.factory.Storage;

public class AssembledCarsStorage implements Storage {
    private static final String PATH_TO_PROPERTIES = "resources\\config.properties";
    private final int size;
    List<Car> carList;
    private boolean isFull = false;
    private static int currentCarsAmount = 0;
    private static int producedCarsAmount = 0;


    public AssembledCarsStorage() {
        carList = new ArrayList<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.size = Integer.parseInt(properties.getProperty("assembled_car_storage_size"));
    }

    public boolean isFull() {
        return isFull;
    }

    public synchronized void addCar(Car car) {
        carList.add(car);
        producedCarsAmount++;
        currentCarsAmount++;
        System.out.println("added new car with ID:" + car.getID() + "<body ID: " + car.getBodyID()
                + "> <engine ID: " + car.getEngineID() + "> <accessory ID: " + car.getAccessoryID()
                + ">");
        if (carList.size() == size) {
            isFull = true;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isFull = false;
        }
        notify();
    }

    public boolean isEmpty() {
        return carList.isEmpty();
    }

    public static int getCurrentCarsAmount() {
        return currentCarsAmount;
    }

    public static int getProducedCarsAmount() {
        return producedCarsAmount;
    }

    public int getFreeSpace(){
        return size - currentCarsAmount;//че за ебань творится с тасками
    }

    public synchronized Car takeCar() {
        notify();
        currentCarsAmount--;
        return carList.remove(0);
    }


}
