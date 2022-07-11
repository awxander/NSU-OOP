package ru.nsu.ccfit.tsibin.GUI;


import java.awt.Color;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import ru.nsu.ccfit.tsibin.factory.Dealer;
import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;
import ru.nsu.ccfit.tsibin.factory.Suppliers.AccessorySupplier;
import ru.nsu.ccfit.tsibin.factory.Suppliers.BodySupplier;
import ru.nsu.ccfit.tsibin.factory.Suppliers.EngineSupplier;

public class MainFrame extends JFrame {
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 10;
    private final int FRAME_WIDTH = 800;
    private final int FRAME_HEIGHT = 800;
    private final int PANEL_WIDTH = 700;
    private final int PANEL_HEIGHT = 700;
    private final int SLIDER_WIDTH = 100;
    private final int SLIDER_HEIGHT = 50;
    private final int LABEL_WIDTH = 250;
    private final int LABEL_HEIGHT = 30;
    private final int DEFAULT_DELAY = 500;
    private final JPanel mainPanel;

    public MainFrame() {
        mainPanel = new JPanel();
        setPanel();
        setFrame();
    }

    private void setPanel(){
        mainPanel.setLayout(null);
        this.setLocationRelativeTo(null);
        mainPanel.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        mainPanel.setBackground(Color.CYAN);
        addSliders();
        addLabels();
    }


    private void addBodySlider(){


        JSlider bodySpeedSlider = new JSlider();
        bodySpeedSlider.setBounds(0, 110, SLIDER_WIDTH, SLIDER_HEIGHT);

        bodySpeedSlider.setMaximum(MAX_SPEED);
        bodySpeedSlider.setMinimum(MIN_SPEED);
        bodySpeedSlider.setValue(MIN_SPEED);

        bodySpeedSlider.setName("supplier speed");
        bodySpeedSlider.addChangeListener(e -> {
            int speed = bodySpeedSlider.getValue();
            BodySupplier.setProductionSpeed(speed);
        });
        mainPanel.add(bodySpeedSlider);
    }

    private void addEngineSlider(){
        JSlider engineSpeedSlider = new JSlider();
        engineSpeedSlider.setBounds(0, 190, SLIDER_WIDTH, SLIDER_HEIGHT);

        engineSpeedSlider.setMaximum(MAX_SPEED);
        engineSpeedSlider.setMinimum(MIN_SPEED);
        engineSpeedSlider.setValue(MIN_SPEED);

        engineSpeedSlider.setName("engine supplier speed");
        engineSpeedSlider.addChangeListener(e -> {
            int speed = engineSpeedSlider.getValue();
            EngineSupplier.setProductionSpeed(speed);
        });
        mainPanel.add(engineSpeedSlider);
    }

    private void addAccessorySlider(){
        JSlider accessorySpeedSlider = new JSlider();
        accessorySpeedSlider.setBounds(0, 30, SLIDER_WIDTH, SLIDER_HEIGHT);

        accessorySpeedSlider.setMaximum(MAX_SPEED);
        accessorySpeedSlider.setMinimum(MIN_SPEED);
        accessorySpeedSlider.setValue(MIN_SPEED);

        accessorySpeedSlider.setName("supplier speed");
        accessorySpeedSlider.addChangeListener(e -> {
            int speed = accessorySpeedSlider.getValue();
            AccessorySupplier.setProductionSpeed(speed);
        });
        mainPanel.add(accessorySpeedSlider);
    }

    private void addDealerSpeedSlider(){
        JSlider dealerRequestSpeedSlider = new JSlider();
        dealerRequestSpeedSlider.setBounds(0, 400, SLIDER_WIDTH, SLIDER_HEIGHT);

        dealerRequestSpeedSlider.setMaximum(MAX_SPEED);
        dealerRequestSpeedSlider.setMinimum(MIN_SPEED);
        dealerRequestSpeedSlider.setValue(MIN_SPEED);

        dealerRequestSpeedSlider.setName("dealer request speed slider");
        dealerRequestSpeedSlider.addChangeListener(e -> {
            int speed = dealerRequestSpeedSlider.getValue();
            Dealer.setRequestSpeed(speed);
        });
        mainPanel.add(dealerRequestSpeedSlider);
    }

    private void addSliders() {

        addBodySlider();
        addEngineSlider();
        addAccessorySlider();
        addDealerSpeedSlider();

    }

    private void addEngineLabels(){
        JLabel engineAmountLabel = new JLabel("produced engines amount: 0");
        engineAmountLabel.setBounds(LABEL_WIDTH, LABEL_HEIGHT * 2, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService engineExecutor = Executors.newSingleThreadScheduledExecutor();
        engineExecutor.scheduleWithFixedDelay(() -> {
            engineAmountLabel.setText("produced engines amount: " + EngineSupplier.getProducedDetailsAmount());
        }, 200, 200, TimeUnit.MILLISECONDS);
        mainPanel.add(engineAmountLabel);

        JLabel engineSpeedSliderLabel = new JLabel("engine supplier speed");
        engineSpeedSliderLabel.setBounds(0, 160, LABEL_WIDTH, LABEL_HEIGHT);
        mainPanel.add(engineSpeedSliderLabel);

        JLabel currentEngineAmount = new JLabel("<html>current bodies<br>" +
                "amount in storage: 0</html>");
        currentEngineAmount.setBounds(LABEL_WIDTH, 280, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService engineStorageExecutor = Executors.newSingleThreadScheduledExecutor();
        engineStorageExecutor.scheduleWithFixedDelay(() -> {
            currentEngineAmount.setText("<html>current engines<br>amount in storage: "
                    + EngineStorage.getCurrentDetailsAmount() + "</html>");
        }, DEFAULT_DELAY, DEFAULT_DELAY, TimeUnit.MILLISECONDS);
        mainPanel.add(currentEngineAmount);
    }


    private void addBodyLabels(){
        JLabel bodySpeedSliderLabel = new JLabel("body supplier speed");
        bodySpeedSliderLabel.setBounds(0, 80, LABEL_WIDTH, LABEL_HEIGHT);
        mainPanel.add(bodySpeedSliderLabel);

        JLabel bodyAmountLabel = new JLabel("produced bodies amount: 0");
        bodyAmountLabel.setBounds(LABEL_WIDTH, LABEL_HEIGHT, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService bodyExecutor = Executors.newSingleThreadScheduledExecutor();
        bodyExecutor.scheduleWithFixedDelay(() -> {
            bodyAmountLabel.setText("produced bodies amount: " + BodySupplier.getProducedDetailsAmount());
        }, DEFAULT_DELAY, DEFAULT_DELAY, TimeUnit.MILLISECONDS);
        mainPanel.add(bodyAmountLabel);

        JLabel currentBodyAmount = new JLabel("<html>current bodies<br>" +
                "amount in storage: 0</html>");
        currentBodyAmount.setBounds(LABEL_WIDTH, 220, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService bodyStorageExecutor = Executors.newSingleThreadScheduledExecutor();
        bodyStorageExecutor.scheduleWithFixedDelay(() -> {
            currentBodyAmount.setText("<html>current bodies<br>amount in storage: "
                    + BodyStorage.getCurrentDetailsAmount() + "</html>");
        }, DEFAULT_DELAY, DEFAULT_DELAY, TimeUnit.MILLISECONDS);
        mainPanel.add(currentBodyAmount);
    }

    private void addAccessoryLabels(){
        JLabel accessoryAmountLabel = new JLabel("produced accessories amount: 0");
        accessoryAmountLabel.setBounds(LABEL_WIDTH, 0, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService accessoryExecutor = Executors.newSingleThreadScheduledExecutor();
        accessoryExecutor.scheduleWithFixedDelay(() -> {
            accessoryAmountLabel.setText("produced accessories amount: "
                    + AccessorySupplier.getProducedDetailsAmount());
        }, DEFAULT_DELAY, DEFAULT_DELAY, TimeUnit.MILLISECONDS);
        mainPanel.add(accessoryAmountLabel);

        JLabel accessorySpeedSliderLabel = new JLabel("accessory supplier speed");
        accessorySpeedSliderLabel.setBounds(0, 0, LABEL_WIDTH, LABEL_HEIGHT);
        mainPanel.add(accessorySpeedSliderLabel);

        JLabel currentAccessoryAmount = new JLabel("<html>current accessories<br>" +
                "amount in storage: 0</html>");
        currentAccessoryAmount.setBounds(LABEL_WIDTH, 160, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService accessoryStorageExecutor = Executors.newSingleThreadScheduledExecutor();
        accessoryStorageExecutor.scheduleWithFixedDelay(() -> {
            currentAccessoryAmount.setText("<html>current accessories<br>amount in storage: "
                    + AccessoryStorage.getCurrentDetailsAmount() + "</html>");
        }, DEFAULT_DELAY, DEFAULT_DELAY, TimeUnit.MILLISECONDS);
        mainPanel.add(currentAccessoryAmount);
    }

    private void addDealerLabels(){
        JLabel dealerRequestSpeedLAbel = new JLabel("dealer request speed");
        dealerRequestSpeedLAbel.setBounds(0, 330, LABEL_WIDTH, LABEL_HEIGHT);
        mainPanel.add(dealerRequestSpeedLAbel);
    }

    private void addAssembledCarsLabels(){
        JLabel carsAmountLabel = new JLabel("produced cars amount: 0");
        carsAmountLabel.setBounds(LABEL_WIDTH, 350, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService accessoryExecutor = Executors.newSingleThreadScheduledExecutor();
        accessoryExecutor.scheduleWithFixedDelay(() -> {
            carsAmountLabel.setText("produced cars amount: "
                    + AssembledCarsStorage.getProducedCarsAmount());
        }, DEFAULT_DELAY, DEFAULT_DELAY, TimeUnit.MILLISECONDS);
        mainPanel.add(carsAmountLabel);

        JLabel currentCarsAmount = new JLabel("<html>current cars<br>" +
                "amount in storage: 0</html>");
        currentCarsAmount.setBounds(LABEL_WIDTH, 450, LABEL_WIDTH, LABEL_HEIGHT);
        ScheduledExecutorService carStorageExecutor = Executors.newSingleThreadScheduledExecutor();
        carStorageExecutor.scheduleWithFixedDelay(() -> {
            currentCarsAmount.setText("<html>current cars<br>amount in storage: "
                    + AssembledCarsStorage.getCurrentCarsAmount() + "</html>");
        }, DEFAULT_DELAY, DEFAULT_DELAY, TimeUnit.MILLISECONDS);
        mainPanel.add(currentCarsAmount);
    }

    private void addLabels() {
        addEngineLabels();
        addBodyLabels();
        addAccessoryLabels();
        addDealerLabels();
        addAssembledCarsLabels();
    }

    private void setFrame() {
        this.add(mainPanel);
        this.setLayout(null);
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
