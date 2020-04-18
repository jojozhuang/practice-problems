package johnny.problem;

import java.util.concurrent.BlockingQueue;

public class Worker extends Thread {
    private int id;
    private BlockingQueue<Task> bq;
    private boolean isStopped;

    public Worker(int id, BlockingQueue bq) {
        this.id = id;
        this.bq = bq;
        this.isStopped = false;
    }

    public void run(){
        while(!isStopped()){
            try {
                Task task = bq.take();
                System.out.println("Worker #" + id + " is working on the task: " + task.getName());
                task.run();
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
