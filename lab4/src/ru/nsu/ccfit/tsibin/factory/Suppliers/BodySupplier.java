package ru.nsu.ccfit.tsibin.factory.Suppliers;

import ru.nsu.ccfit.tsibin.factory.Details.CarBody;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Supplier;

public class BodySupplier extends Thread implements Supplier {
    private int productionSpeed = 1000;
    private final BodyStorage bodyStorage;

    public BodySupplier(BodyStorage bodyStorage) {
        this.bodyStorage = bodyStorage;
        this.start();
    }

    @Override
    public void run() {
        synchronized (bodyStorage) {

            while (true) {

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
                try {
                    this.sleep(productionSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }
    }


    public void sendBody() {

        CarBody carBody = new CarBody();
        bodyStorage.addBody(carBody);
    }


}
