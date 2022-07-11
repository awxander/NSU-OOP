package ru.nsu.ccfit.tsibin.factory.Suppliers;

import ru.nsu.ccfit.tsibin.factory.Details.CarAccessory;
import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Supplier;

public class AccessorySupplier extends Thread implements Supplier {
    private static int productionSpeed = 1;
    private final AccessoryStorage accessoryStorage;
    private static Integer producedDetailsAmount = 0;
    private final int DEFAULT_SLEEP_TIMEOUT = 2000;


    public AccessorySupplier(AccessoryStorage accessoryStorage) {
        this.accessoryStorage = accessoryStorage;
        this.start();
    }


    public static void setProductionSpeed(int productionSpeed) {
        AccessorySupplier.productionSpeed = productionSpeed;
    }


    @Override
    public void run() {
        while (true) {
            synchronized (accessoryStorage) {
                while (accessoryStorage.isFull()) {

                    try {
                        accessoryStorage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sendAccessory();
            }
                try {
                    this.sleep(DEFAULT_SLEEP_TIMEOUT / productionSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }


    public void sendAccessory() {
        CarAccessory carAccessory = new CarAccessory();
        synchronized (producedDetailsAmount){
            producedDetailsAmount++;
        }
        accessoryStorage.addAccessory(carAccessory);
    }


    public static Integer getProducedDetailsAmount() {
        return producedDetailsAmount;
    }
}
