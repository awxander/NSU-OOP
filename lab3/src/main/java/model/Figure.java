package main.java.model;

import java.util.ArrayList;
import java.util.List;

public enum Figure {
    I(0, 0,     1, 0,       2, 0,      3, 0),
    J,
    L,
    O,
    S,
    T,
    Z;
    private List<Coordinates> dot;

    private Figure(int... coordinates) {
        dot = new ArrayList<Coordinates>();
        for (int j = 0; j < coordinates.length; j += 2) {
            dot.add(new Coordinates(coordinates[j],coordinates[j+1]));
        }
    }

    Figure turnLeft(){

    }
    Figure turnRight(){

    }

}
