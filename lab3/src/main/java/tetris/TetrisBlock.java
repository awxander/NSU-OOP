package tetris;

import java.awt.*;
import java.util.Random;

public class TetrisBlock {

    private int[][] shape;
    private Color color;
    private int x, y;
    private int[][][] shapes;
    private int currentRotation;
    private Color[] availableColors = {Color.red, Color.blue,Color.green, Color.pink, Color.cyan};

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private void initShapes() {
        shapes = new int[4][][];
        for (int i = 0; i < 4; i++) {
            int rows  = shape[0].length;
            int columns = shape.length;
            shapes[i] = new int[rows][columns];
            for(int x = 0; x < columns; x++){
                for(int y = 0; y < rows; y++){
                    shapes[i][y][x] = shape[columns - x -1][y];
                }
            }
            shape = shapes[i];
        }
    }

    public void spawn(int gridWidth) {
        currentRotation = 0;//before we set x,y, because they depends on the shape of the block
        shape = shapes[currentRotation];
        y = -getHeight();//set block to the top of the grid
        x = (gridWidth - getWidth()) / 2;//set block to the center of the row
        color = availableColors[new Random().nextInt(availableColors.length)];
    }

    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        initShapes();
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public int getHeight() {
        return shape.length;
    }

    public int getWidth() {//also number of columns in shape
        return shape[0].length;
    }

    public void moveDown() {
        y++;
    }

    public void moveRight() {
        x++;
    }

    public void moveLeft() {
        x--;
    }

    public void rotate() {
        currentRotation++;
        if (currentRotation > 3) {
            currentRotation = 0;
        }
        shape = shapes[currentRotation];
    }

    public void unrotate(){
        currentRotation--;
        if(currentRotation < 0){
            currentRotation = 3;
        }
        shape = shapes[currentRotation];
    }

    public int getBottomEdge() {//how low block is
        return y + getHeight();
    }

    public int getLeftEdge(){
        return this.getX();
    }

    public int getRightEdge(){
        return this.getX() + this.getWidth();
    }
}
