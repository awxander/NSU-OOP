package ru.nsu.ccfit.tsibin.factory.Details;

import ru.nsu.ccfit.tsibin.factory.CarDetail;

public class CarBody implements CarDetail {
    private static int amount = 0;
    private final int ID;

    public CarBody() {

        synchronized (this) {
            amount++;
            ID = amount;
        }
    }

    public int getID() {
        return ID;
    }
}
