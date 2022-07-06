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
/*        System.out.println("added new body with ID:" + carBody.getID()
                + ",current amount is: " + carBodyList.size());*/
        if (carBodyList.size() == size) {
            isFull = true;
            System.out.println("body storage is full, waiting...");//почему добавляются новые body
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public boolean isEmpty() {
        return carBodyList.isEmpty();
    }

    public synchronized CarBody takeCarBody() {
        notify();
        return carBodyList.remove(0);
    }


}
