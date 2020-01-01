package johnny.problem;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {

    private BlockingQueue<Runnable> bq;
    private boolean isStopped;

    public Worker(BlockingQueue bq){
        this.bq = bq;
        this.isStopped = false;
    }

    public void run(){
        while(!isStopped()){
            try {
                Runnable runnable = bq.take();
                runnable.run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep worker thread alive.
            }
        }
    }

    public synchronized void doStop(){
        if (!this.isInterrupted()) {
            try {
                this.interrupt(); //break worker thread
            } catch (SecurityException ignore) {
            } finally {
            }
        }
        isStopped = true;
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
