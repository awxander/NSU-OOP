package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.threadpool.WorkerThreadPool;

public class Controller extends Thread{
    private final AssembledCarsStorage assembledCarsStorage;
    private final WorkerThreadPool threadPool;

    public Controller(WorkerThreadPool threadPool,
                      AssembledCarsStorage assembledCarsStorage){
        this.threadPool = threadPool;
        this.assembledCarsStorage = assembledCarsStorage;
        start();
    }

    @Override
    public void run(){
        synchronized (this){
            while(true){
                if(!assembledCarsStorage.isFull()){
                    sendTask();
                }else{
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void sendTask(){
        CarCreateTask task = new CarCreateTask();
        threadPool.execute(task);
    }

}
