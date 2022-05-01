package tetris;

import tetris.tetrisblocks.IShapeBlock;
import tetris.tetrisblocks.JShapeBlock;
import tetris.tetrisblocks.LShapeBlock;
import tetris.tetrisblocks.OShapeBlock;
import tetris.tetrisblocks.SShapeBlock;
import tetris.tetrisblocks.TShapeBlock;
import tetris.tetrisblocks.ZShapeBlock;

import javax.swing.JPanel;
import java.awt.*;
import java.util.Random;


public class GameArea extends JPanel {

    private final int gridRows;
    private final int gridColumns;
    private final int gridCellSize;
    private TetrisBlock block;
    private final Color[][] background;
    private TetrisBlock[] blockTypes;


    public GameArea(JPanel placeholder, int columns) {
        blockTypes = new TetrisBlock[]{new IShapeBlock(), new IShapeBlock(), new LShapeBlock(), new JShapeBlock(),
                new OShapeBlock(), new SShapeBlock(), new ZShapeBlock(), new TShapeBlock()};
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBorder(placeholder.getBorder());
        this.setBackground(Color.black);
        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        background = new Color[gridRows][gridColumns];

    }

    private void drawBackground(Graphics g) {
        Color color;
        for (int row = 0; row < gridRows; row++) {
            for (int column = 0; column < gridColumns; column++) {
                color = background[row][column];
                if (color != null) {
                    int x = column * gridCellSize;
                    int y = row * gridCellSize;
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }

    private void drawGridSquare(Graphics g, Color color, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(Color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }

    public void spawnBlock() {
        block = blockTypes[new Random().nextInt(blockTypes.length)];
        block.spawn(gridColumns);
    }

    private void drawBlock(Graphics g) {
        for (int row = 0; row < block.getHeight(); row++) {
            for (int column = 0; column < block.getWidth(); column++) {
                if (block.getShape()[row][column] == 1) {
                    int x = (column + block.getX()) * gridCellSize;
                    int y = (row + block.getY()) * gridCellSize;

                    drawGridSquare(g, block.getColor(), x, y);
                }
            }
        }
    }

    public void moveBlockToBackGround() {
        int[][] shape = block.getShape();
        int height = block.getHeight();
        int width = block.getWidth();

        int xPos = block.getX();
        int yPos = block.getY();

        Color color = block.getColor();

        for (int row = 0; row < block.getHeight(); row++) {
            for (int column = 0; column < block.getWidth(); column++) {
                if (shape[row][column] == 1) {
                    background[yPos + row][xPos + column] = color;
                }
            }
        }

    }


    public boolean bottomReached() {
        if (block.getBottomEdge() == gridRows) {
            return true;
        }
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int column = 0; column < width; column++) {
            for (int row = height - 1; row >= 0; row--) {
                if (shape[row][column] != 0) {
                    int x = column + block.getX();
                    int y = row + block.getY() + 1;

                    if (y < 0) break;

                    if (background[y][x] != null) return true;

                    break;
                }
            }
        }

        return false;
    }

    public boolean leftEdgeReached() {
        if (block.getLeftEdge() == 0) {//0 is left border
            return true;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int column = 0; column < width; column++) {
                if (shape[row][column] != 0) {
                    int x = column + block.getX() - 1;
                    int y = row + block.getY();

                    if (y < 0) break;

                    if (background[y][x] != null) return true;

                    break;
                }
            }
        }


        return false;
    }

    public boolean rightEdgeReached() {
        if (block.getRightEdge() == gridColumns) {
            return true;
        }

        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();

        for (int row = 0; row < height; row++) {
            for (int column = width - 1; column >= 0; column--) {
                if (shape[row][column] != 0) {
                    int x = column + block.getX() + 1;
                    int y = row + block.getY();

                    if (y < 0) break;//while block is spawned in the top

                    if (background[y][x] != null) return true;

                    break;
                }
            }
        }
        return false;
    }

    public boolean isBlockOutOfBounds() {
        if (block.getY() < 0) {
            block = null;
            return true;
        }
        return false;
    }

    public boolean moveBlockDown() {
        if (block == null) return false;
        if (bottomReached()) {

            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }

    public void moveBlockRight() {
        if (block == null) return;
        if (!rightEdgeReached()) {
            block.moveRight();
            repaint();
        }
    }

    public void moveBlockLeft() {
        if (block == null) return;
        if (!leftEdgeReached()) {
            block.moveLeft();
            repaint();
        }
    }


    private boolean checkLayering(){
        int[][] shape = block.getShape();
        int width = block.getWidth();
        int height = block.getHeight();
        if(block.getLeftEdge() < 0 || block.getRightEdge() > gridColumns || block.getBottomEdge() > gridRows){
            return false;
        }

        for (int row = 0; row < height; row++) {
            for (int column = width - 1; column >= 0; column--) {
                if (shape[row][column] != 0) {
                    int x = column + block.getX();
                    int y = row + block.getY();

                    if (y < 0) break;//while block is spawned in the top

                    if (background[y][x] != null) return false;

                    break;
                }
            }
        }
        return true;

    }

    public void rotateBlock() {
        if (block == null) return;

        block.rotate();

        if(!checkLayering()){//если есть наслоение
            block.unrotate();
        }
        repaint();
    }

    public int clearLines() {

        boolean lineFilled;
        int clearedLines = 0;

        for (int row = gridRows - 1; row >= 0; row--) {
            lineFilled = true;
            for (int column = 0; column < gridColumns; column++) {
                if (background[row][column] == null) {
                    lineFilled = false;
                    break;
                }
            }

            if (lineFilled) {
                clearLine(row);
                shiftDown(row);
                clearLine(0);//clear first row

                row++;//so we can clear the same line after, because we moved down current field
                clearedLines += 10;
                repaint();
            }
        }
        return clearedLines;
    }

    private void shiftDown(int row) {
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < gridColumns; j++) {
                background[i][j] = background[i - 1][j];
            }
        }
    }

    private void clearLine(int row) {
        for (int i = 0; i < gridColumns; i++) {
            background[row][i] = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBlock(g);
        drawBackground(g);
    }
}
