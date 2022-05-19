package tetris;

public class GameThread extends Thread {

    private final GameArea gameArea;
    private int score = 0;
    private final GameForm gameForm;
    private int scorePerLevel = 20;
    private int level = 1;

    private int pauseTime  = 1000;
    private int speedPerLevel  = 150;


    public GameThread(GameArea gameArea, GameForm gameForm) {
        this.gameArea = gameArea;
        this.gameForm = gameForm;
        gameForm.updateScore(0);
        gameForm.updateLevel(1);
    }

    @Override
    public void run() {

        while (true) {

            gameArea.spawnBlock();

            while (gameArea.moveBlockDown()) {
                try {
                    Thread.sleep(pauseTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }

            }
            if (gameArea.isBlockOutOfBounds()) {
                Main.endGame(score);
                break;
            }
            gameArea.moveBlockToBackGround();
            score += gameArea.clearLines();
            gameForm.updateScore(score);
            int lvl = score/scorePerLevel + 1;
            if(lvl > level){
                level = lvl;
                gameForm.updateLevel(level);
                pauseTime -= speedPerLevel;
            }
        }
    }
}
