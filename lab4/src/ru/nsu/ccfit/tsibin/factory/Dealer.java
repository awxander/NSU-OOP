package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;

public class Dealer extends Thread {
    private int requestSpeed = 1000;
    private AssembledCarsStorage assembledCarsStorage;
    private Car car;

    public Dealer(AssembledCarsStorage assembledCarsStorage) {
        this.assembledCarsStorage = assembledCarsStorage;
        start();
    }

    @Override
    public void run() {

        while (true) {
            synchronized (assembledCarsStorage) {
                while (assembledCarsStorage.isEmpty()) {
                    try {
                        System.out.println("dealer waiting for car...");
                        assembledCarsStorage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("dealer keeps going");
                car = assembledCarsStorage.takeCar();
                System.out.println("car with ID: " + car.getID() + " was sold");
                sellCar();
            }
            try {
                Thread.sleep(requestSpeed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void sellCar() {
        car = null;
    }
}
