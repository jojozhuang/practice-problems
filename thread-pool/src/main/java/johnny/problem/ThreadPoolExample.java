package johnny.problem;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolExample {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(2);

        List<Task> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Task task = new Task("Task" + i);
            list.add(task);
            System.out.println("Created : " + task.getName());
        }

        for (Task task : list) {
            try {
                threadPool.execute(task);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        threadPool.shutdown();
    }
}
