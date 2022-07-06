package ru.nsu.ccfit.tsibin.factory.Suppliers;

import ru.nsu.ccfit.tsibin.factory.Details.CarAccessory;
import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Supplier;

public class AccessorySupplier extends Thread implements Supplier {
    private int productionSpeed = 1000;
    private final AccessoryStorage accessoryStorage;

    public AccessorySupplier(AccessoryStorage accessoryStorage) {
        this.accessoryStorage = accessoryStorage;
        this.start();
    }

    @Override
    public void run() {
        synchronized (accessoryStorage) {

            while (true) {
                if (!accessoryStorage.isFull()) {
                    sendAccessory();
                } else {
                    try {
                        System.out.println("accessory supplier number " + this.getId() + " waiting");
                        accessoryStorage.wait();
                        System.out.println("accessory supplier number " + this.getId() + " working again");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    this.sleep(productionSpeed);
                } catch (InterruptedException e) {//wait и notify у suppliers и складов
                    e.printStackTrace();
                }
            }
        }
    }


    public void sendAccessory() {
        CarAccessory carAccessory = new CarAccessory();
        accessoryStorage.addAccessory(carAccessory);
    }


}
