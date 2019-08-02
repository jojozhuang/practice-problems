package johnny.problem.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private BlockingQueue bq ;
    private List<Worker> threads;
    private boolean isStopped;
    private int capacity;

    public ThreadPool(int numOfThreads){
        isStopped = false;
        capacity = 100;
        bq = new ArrayBlockingQueue(capacity);
        threads = new ArrayList<>();
        for (int i = 0; i < numOfThreads; i++){
            threads.add(new Worker(bq));
        }
        for (Worker worker : threads) {
            worker.start();
        }
    }

    public synchronized void execute(Runnable task) throws Exception{
        if (this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }

        this.bq.put(task);
    }

    public synchronized void shutdown(){
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.isStopped = true;
        for (Worker thread : threads) {
            thread.doStop();
        }
    }
}
