package ru.nsu.ccfit.tsibin.factory.Storages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ru.nsu.ccfit.tsibin.factory.Details.CarEngine;
import ru.nsu.ccfit.tsibin.factory.Storage;

public class EngineStorage implements Storage {
    public static final String PATH_TO_PROPERTIES = "resources\\config.properties";
    private final int size;
    List<CarEngine> carEngineList;
    private boolean isFull = false;
    private static int currentDetailsAmount = 0;

    public EngineStorage() {
        carEngineList = new ArrayList<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.size = Integer.parseInt(properties.getProperty("engine_storage_size"));
    }

    public boolean isFull(){
        return  isFull;
    }

    public synchronized void addEngine(CarEngine carEngine) {
        if (carEngineList.size() < size) {
            carEngineList.add(carEngine);
            currentDetailsAmount++;
        }
        else{
            isFull = true;
            System.out.println("engine storage is full");
        }
    }


    public boolean isEmpty(){
        return carEngineList.isEmpty();
    }

    public static Integer getCurrentDetailsAmount(){
        return currentDetailsAmount;
    }

    public synchronized CarEngine takeCarEngine(){
        notify();
        currentDetailsAmount--;
        return carEngineList.remove(0);
    }
}
