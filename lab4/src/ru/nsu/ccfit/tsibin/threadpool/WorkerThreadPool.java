package ru.nsu.ccfit.tsibin.threadpool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.LinkedBlockingQueue;
import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;

public class WorkerThreadPool {
    private final int WORKERS_AMOUNT;
    private final String PATH_TO_PROPERTIES = "resources\\config.properties";
    private final Worker[] workers;
    private final AccessoryStorage accessoryStorage;
    private final AssembledCarsStorage assembledCarsStorage;
    private final BodyStorage bodyStorage;
    private final EngineStorage engineStorage;
    private LinkedBlockingQueue<Runnable> queue;

    public WorkerThreadPool(AccessoryStorage accessoryStorage,
                            BodyStorage bodyStorage,
                            EngineStorage engineStorage,
                            AssembledCarsStorage assembledCarsStorage) {
        queue = new LinkedBlockingQueue<>();

        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
        this.assembledCarsStorage = assembledCarsStorage;


        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PATH_TO_PROPERTIES));
        } catch (IOException e) {
            e.printStackTrace();
        }
        WORKERS_AMOUNT = Integer.parseInt(properties.getProperty("workers_amount"));

        workers = new Worker[WORKERS_AMOUNT];

        for (int i = 0; i < WORKERS_AMOUNT; i++) {
            workers[i] = new Worker();
            workers[i].start();
        }


    }


    public void execute(Runnable task) {
        synchronized (queue) {

            queue.add(task);
            queue.notify();
        }
    }

    public class Worker extends Thread {

        @Override
        public void run() {
            Runnable task;
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {//допиливаем worker
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = queue.poll();
                }
                synchronized (accessoryStorage) {
                    while (accessoryStorage.isEmpty()) {
                        try {
                            accessoryStorage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (bodyStorage) {
                    while (bodyStorage.isEmpty()) {
                        try {
                            bodyStorage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                synchronized (engineStorage) {
                    while (engineStorage.isEmpty()) {
                        try {
                            engineStorage.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                task.run();
            }
        }


    }


}
