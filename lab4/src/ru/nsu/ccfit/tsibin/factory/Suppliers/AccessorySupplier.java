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
        System.out.println("accessory supplier number " + this.getId() + " start work");

        while (true) {
            synchronized (accessoryStorage) {

                while (accessoryStorage.isFull()) {

                    try {
                        System.out.println("accessory supplier number " + this.getId() + " waiting");
                        accessoryStorage.wait();
                        System.out.println("accessory supplier number " + this.getId() + " awake");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                sendAccessory();//посмотреть, почему 2 потока работают не быстрее, чем 1
            }
                System.out.println(System.currentTimeMillis());

                try {
                    this.sleep(DEFAULT_SLEEP_TIMEOUT / productionSpeed);
                } catch (InterruptedException e) {//wait и notify у suppliers и складов
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
