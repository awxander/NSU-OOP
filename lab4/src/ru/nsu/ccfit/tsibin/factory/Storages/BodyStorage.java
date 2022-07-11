package ru.nsu.ccfit.tsibin.factory.Storages;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import ru.nsu.ccfit.tsibin.factory.Details.CarBody;
import ru.nsu.ccfit.tsibin.factory.Storage;

public class BodyStorage implements Storage {
    private static final String PATH_TO_PROPERTIES = "resources\\config.properties";
    private final int size;
    List<CarBody> carBodyList;
    private boolean isFull = false;
    private static int currentDetailsAmount = 0;

    public BodyStorage() {
        carBodyList = new ArrayList<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.size = Integer.parseInt(properties.getProperty("body_storage_size"));
    }

    public boolean isFull() {
        return isFull;
    }

    public synchronized void addBody(CarBody carBody) {
        carBodyList.add(carBody);
        currentDetailsAmount++;
        notify();
        if (carBodyList.size() == size) isFull = true;


    }

    public static Integer getCurrentDetailsAmount(){
        return currentDetailsAmount;
    }

    public boolean isEmpty() {
        return carBodyList.isEmpty();
    }

    public synchronized CarBody takeCarBody() {
        notify();
        currentDetailsAmount--;
        return carBodyList.remove(0);
    }


}
