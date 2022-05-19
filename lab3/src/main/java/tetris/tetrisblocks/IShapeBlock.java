package tetris.tetrisblocks;

import tetris.TetrisBlock;

public class IShapeBlock extends TetrisBlock {
    public IShapeBlock() {
        super(new int[][]{{1, 1, 1, 1}});
    }

    @Override
    public void rotate(){
        super.rotate();
        if(this.getWidth() == 1){
            this.setX(getX()+1);
            this.setY(getY() - 1);
        }else{
            this.setX(getX() - 1);
            this.setY(getY() + 1);
        }
    }

    @Override
    public void unrotate(){
        super.unrotate();
        if(this.getWidth() == 4){
            this.setX(getX()-1);
            this.setY(getY() + 1);
        }else{
            this.setX(getX() + 1);
            this.setY(getY() - 1);
        }
    }
}
