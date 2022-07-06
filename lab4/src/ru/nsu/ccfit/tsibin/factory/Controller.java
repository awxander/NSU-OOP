package ru.nsu.ccfit.tsibin.factory;

import ru.nsu.ccfit.tsibin.factory.Details.CarAccessory;
import ru.nsu.ccfit.tsibin.factory.Details.CarBody;
import ru.nsu.ccfit.tsibin.factory.Details.CarEngine;
import ru.nsu.ccfit.tsibin.factory.Storages.AccessoryStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.AssembledCarsStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.BodyStorage;
import ru.nsu.ccfit.tsibin.factory.Storages.EngineStorage;
import ru.nsu.ccfit.tsibin.threadpool.WorkerThreadPool;

public class Controller extends Thread{
    private final AssembledCarsStorage assembledCarsStorage;
    private final AccessoryStorage accessoryStorage;
    private final BodyStorage bodyStorage;
    private final EngineStorage engineStorage;
    private final WorkerThreadPool threadPool;

    public Controller(WorkerThreadPool threadPool,
            AccessoryStorage accessoryStorage,
                      BodyStorage bodyStorage,
                      EngineStorage engineStorage,
                      AssembledCarsStorage assembledCarsStorage){
        this.threadPool = threadPool;
        this.accessoryStorage = accessoryStorage;
        this.engineStorage = engineStorage;
        this.bodyStorage = bodyStorage;
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
        CarCreateTask task = new CarCreateTask(accessoryStorage, engineStorage, bodyStorage, assembledCarsStorage);
        threadPool.execute(task);

    }

}
