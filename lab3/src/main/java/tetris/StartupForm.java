package tetris;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.SwingConstants.CENTER;

public class StartupForm extends JFrame {
    private JButton startGameButton;
    private JButton leaderBoardButton;
    private JButton exitButton;

    private void setButtonsLocation() {
        startGameButton.setBounds(175, 100, 150, 50);
        leaderBoardButton.setBounds(175, 200, 150, 50);
        exitButton.setBounds(175, 300, 150, 50);
    }

    public void setButtonsActions() {
        startGameButton.addActionListener(e -> {
            setVisible(false);
            Main.start();
        });

        exitButton.addActionListener(e -> {
            System.exit(0);
        });

        leaderBoardButton.addActionListener(e->{
            setVisible(false);
            Main.showLeaderboard();
        });
    }

    public StartupForm() {
        this.setSize(500, 500);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//set frame in the center of the screen

        setButtonsLocation();
        setButtonsActions();
        this.add(startGameButton);
        this.add(leaderBoardButton);
        this.add(exitButton);


    }
}
