package ru.nsu.ccfit.tsibin.factory.Storages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ru.nsu.ccfit.tsibin.factory.Details.CarAccessory;
import ru.nsu.ccfit.tsibin.factory.Storage;

public class AccessoryStorage implements Storage {
    public static final String PATH_TO_PROPERTIES = "resources\\config.properties";
    private final int size;
    List<CarAccessory> carAccessoryList;
    private boolean isFull = false;
    private static int currentDetailsAmount = 0;

    public AccessoryStorage() {
        carAccessoryList = new ArrayList<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.size = Integer.parseInt(properties.getProperty("accessory_storage_size"));
    }

    public boolean isFull(){
        return  isFull;
    }

    public synchronized void addAccessory(CarAccessory carAccessory) {
        if (carAccessoryList.size() < size) {
            carAccessoryList.add(carAccessory);
            currentDetailsAmount++;
        }
        else{
            isFull = true;
            System.out.println("accessory storage is full, waiting...");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("accessory storage keep going");
        }
    }


    public boolean isEmpty(){
        return carAccessoryList.isEmpty();
    }

    public static Integer getCurrentDetailsAmount(){
        return currentDetailsAmount;
    }

    public synchronized CarAccessory takeCarAccessory(){
        notify();
        currentDetailsAmount--;
        return carAccessoryList.remove(0);
    }
}
