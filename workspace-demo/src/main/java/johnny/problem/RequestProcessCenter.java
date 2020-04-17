package johnny.problem;

import java.util.LinkedList;
import java.util.List;

public class RequestProcessCenter implements Runnable {
    public List<Request> queue;

    public RequestProcessCenter() {
        queue = new LinkedList<>();
    }

    public void run() {
        while (true) {
            processRequest();
        }
    }

    public void addRequest(Request request) {
        queue.add(request);
    }

    public void removeRequest(Request request) {
        queue.remove(request);
    }

    public Request getNextRequest() {
        Request shortestReq = null;
        int shortest = Integer.MAX_VALUE;
        int curFloor = Elevator.getInstance().getCurrentFloor();
        for (Request item : queue) {
            int distance = Math.abs(curFloor - item.getToFloor());
            if (distance < shortest) {
                shortest = distance;
                shortestReq = item;
            }
        }
        return shortestReq;
    }

    public void processRequest() {
        Request req = getNextRequest();
        if (req != null) {
            int toFloor = req.getToFloor();
            Elevator.getInstance().moveToTargetFloor(toFloor);
            queue.remove(req);
        }

    }
}
