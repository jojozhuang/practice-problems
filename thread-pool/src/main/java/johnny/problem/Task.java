package johnny.problem;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {
    private String name;
    private Random random;

    public Task(String name) {
        this.name = name;
        this.random = new Random();
    }

    public String getName() {
        return name;
    }

    public void run() {
        try {
            Long duration = (long) (random.nextInt(10));
            System.out.println("Executing : " + name + " at " + LocalDateTime.now().toString());
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
