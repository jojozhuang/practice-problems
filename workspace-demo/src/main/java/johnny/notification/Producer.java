package johnny.notification;

import java.time.LocalDateTime;
import java.util.Random;

public class Producer implements Runnable {

    private Message msg;

    public Producer(Message msg) {
        this.msg = msg;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": started");
        try {
            while(true) {
                Thread.sleep(2000);
                synchronized (msg) {
                    String newmsg = GetBase62(12);
                    msg.setMsg(newmsg);
                    System.out.println(name + ": message["+newmsg+"] is updated at: " + LocalDateTime.now().toString());

                    msg.notify();
                    //msg.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static char[] base62chars =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static Random random = new Random();

    public static String GetBase62(int length)
    {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            sb.append(base62chars[random.nextInt(62)]);

        return sb.toString();
    }
}
