package ru.nsu.ccfit.tsibin.factory.Details;

import ru.nsu.ccfit.tsibin.factory.CarDetail;

public class CarAccessory implements CarDetail {
    private static int amount = 0;
    private final int ID;

    public CarAccessory() {
        increaseAmount();
        ID = amount;


    }

    static private synchronized void increaseAmount() {
        amount++;
    }

    public int getID() {
        return ID;
    }
}
