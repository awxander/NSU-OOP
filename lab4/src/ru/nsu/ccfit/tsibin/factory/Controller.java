package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.threadpool.WorkerThreadPool;

public class Controller extends Thread {
    private final AssembledCarsStorage assembledCarsStorage;
    private final WorkerThreadPool threadPool;
    private int totalTasksAmount = 0;
    private final int DEFAULT_SLEEP_TIMEOUT = 2000;

    public Controller(WorkerThreadPool threadPool,
                      AssembledCarsStorage assembledCarsStorage) {
        this.threadPool = threadPool;
        this.assembledCarsStorage = assembledCarsStorage;
        start();
    }

    @Override
    public void run() {
        while (true) {
            synchronized (assembledCarsStorage) {
                while (assembledCarsStorage.getFreeSpace() - threadPool.getUnfinishedTasksAmount() <= 0) {
                    try {
                        assembledCarsStorage.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                while (assembledCarsStorage.getFreeSpace() - threadPool.getUnfinishedTasksAmount() > 0) {
                    sendTask();
                    //System.out.println("task was sent");
                }
            }
            try {
                Thread.sleep(DEFAULT_SLEEP_TIMEOUT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendTask() {
        CarCreateTask task = new CarCreateTask();
        threadPool.execute(task);
    }

}
