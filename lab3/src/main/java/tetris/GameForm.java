package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class GameForm extends JFrame {

    private JPanel gameAreaPlaceHolder;
    private JLabel scoreLabel;
    private JLabel levelLabel;
    private final GameArea gameArea;
    private GameThread gameThread;


    private void initControls() {
        InputMap inputMap = this.getRootPane().getInputMap();
        ActionMap actionMap = this.getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke("RIGHT"), "right");// RIGHT means right keyboard arrow
        inputMap.put(KeyStroke.getKeyStroke("LEFT"), "left");
        inputMap.put(KeyStroke.getKeyStroke("DOWN"), "down");
        inputMap.put(KeyStroke.getKeyStroke("UP"), "up");
        inputMap.put(KeyStroke.getKeyStroke("ESCAPE"), "esc");

        actionMap.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockRight();
            }
        });
        actionMap.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockLeft();
            }
        });
        actionMap.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.moveBlockDown();
            }
        });
        actionMap.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameArea.rotateBlock();
            }
        });
        actionMap.put("esc", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                gameThread.interrupt();
                Main.showStartupForm();
            }
        });
    }

    public void startGame() {
        gameArea.initBackGroundArray();
        gameThread = new GameThread(gameArea, this);
        gameThread.start();
    }

    public void endGame(){

    }

    private void setScoreLabel(){
        scoreLabel.setBounds(425,100,200,50);
        scoreLabel.setText("score: 0");
        scoreLabel.setFont(new Font("Verdana",Font.BOLD,20));
        scoreLabel.setBorder(BorderFactory.createEtchedBorder());
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
    }

    private void setLevelLabel(){
        levelLabel.setBounds(425,200,200,50);
        levelLabel.setText("level: 1");
        levelLabel.setFont(new Font("Verdana",Font.BOLD,20));
        levelLabel.setBorder(BorderFactory.createEtchedBorder());
        levelLabel.setHorizontalAlignment(SwingConstants.CENTER);

    }

    public void updateScore(int score){
        scoreLabel.setText("score: " + score);
    }

    public void updateLevel(int level){
        levelLabel.setText("level: " + level);
    }

    public GameForm() {
        initControls();
        gameAreaPlaceHolder = new JPanel();
        gameAreaPlaceHolder.setBounds(50,50,350,500);
        this.setSize(700, 700);
        this.setLocationRelativeTo(null);//set frame in the center of the screen
        this.setResizable(false);

        scoreLabel = new JLabel();
        setScoreLabel();
        this.add(scoreLabel);

        levelLabel = new JLabel();
        setLevelLabel();
        this.add(levelLabel);

        System.out.println(this.getBackground());
        this.setLayout(null);//turn off layout manager
        gameArea = new GameArea(gameAreaPlaceHolder, 10);
        this.add(gameArea);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(false);
    }





}
