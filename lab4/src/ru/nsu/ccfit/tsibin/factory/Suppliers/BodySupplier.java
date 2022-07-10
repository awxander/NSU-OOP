package ru.nsu.ccfit.tsibin.factory.Suppliers;

import ru.nsu.ccfit.tsibin.factory.Details.CarBody;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Supplier;

public class BodySupplier extends Thread implements Supplier {
    private static int productionSpeed = 1;
    private final BodyStorage bodyStorage;
    private static Integer producedDetailsAmount = 0;
    private final int DEFAULT_SLEEP_TIMEOUT = 2000;

    public BodySupplier(BodyStorage bodyStorage) {
        this.bodyStorage = bodyStorage;
        this.start();
    }

    public static Integer getProducedDetailsAmount() {
        return producedDetailsAmount;
    }

    public static void setProductionSpeed(int productionSpeed) {
        BodySupplier.productionSpeed = productionSpeed;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (bodyStorage) {

                if (!bodyStorage.isFull()) {
                    sendBody();
                } else {
                    try {
                        System.out.println("body supplier number " + this.getId() + " waiting");
                        bodyStorage.wait();
                        System.out.println("body supplier number " + this.getId() + " working again");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
            try {
                this.sleep(DEFAULT_SLEEP_TIMEOUT / productionSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


    public void sendBody() {

        CarBody carBody = new CarBody();
        synchronized (producedDetailsAmount){
            producedDetailsAmount++;
        }
        bodyStorage.addBody(carBody);
    }


}
