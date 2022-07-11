package ru.nsu.ccfit.tsibin.TestingGarbage;

import javax.swing.*;

class MyThread extends Thread {
    private Storage storage;
    private int sleepTime = 2000;

    public MyThread(Storage storage) {
        this.storage = storage;
        start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (storage) {
                while (storage.isFull()) {
                    try {
                        storage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                storage.addDetail();
            }
            System.out.println(System.currentTimeMillis());
            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

class Storage extends JFrame {
    private int size = 10;
    private int detailNum = 0;
    private JLabel label;

    public Storage() {
        this.setLayout(null);
        this.setSize(500, 500);
        this.setVisible(true);
        JButton sellButton = new JButton();
        sellButton.setSize(100, 100);
        sellButton.setName("BUTTON");
        sellButton.addActionListener(e -> {
            detailNum = 0;
            System.out.println("details were sold");//тестим notify и wait
            updateLabel();
        });
        this.add(sellButton);
        label = new JLabel("details amount: 0");
        label.setBounds(300, 0, 150, 100);
        this.add(label);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        JButton notifyButton = new JButton("notify button");//допиливаем кнопку notify
        notifyButton.setBounds(0, 200, 100, 100);
        notifyButton.addActionListener(e -> {
            synchronized (Storage.this) {
                Storage.this.notifyAll();
            }
        });
        this.add(notifyButton);

    }

    public synchronized void addDetail() {
        detailNum++;
        updateLabel();
    }

    private void updateLabel() {
        label.setText("details amount: " + detailNum);
    }

    public synchronized boolean isFull() {
        return size == detailNum;
    }
}


public class Testing {
    public static void main(String[] args) {
        Storage storage = new Storage();
        new MyThread(storage);
        new MyThread(storage);

    }
}
