package tetris;

import javax.swing.*;
import java.awt.*;

public class Main {
    private static GameForm gameForm;
    private static StartupForm startupForm;
    private static LeaderboardForm leaderboardForm;

    public static void start(){
        gameForm.setVisible(true);
        gameForm.startGame();
    }

    public static void endGame(int score){
        String playerName = JOptionPane.showInputDialog(null,"GAME OVER\n"
                , "", JOptionPane.INFORMATION_MESSAGE);
        leaderboardForm.addPlayer(playerName, score);
        gameForm.setVisible(false);
        startupForm.setVisible(true);
    }

    public static void showLeaderboard(){
        leaderboardForm.setVisible(true);
    }

    public static void showStartupForm(){
        startupForm.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            startupForm = new StartupForm();
            gameForm = new GameForm();
            leaderboardForm = new LeaderboardForm(startupForm);
            startupForm.setVisible(true);
        });
    }
}
