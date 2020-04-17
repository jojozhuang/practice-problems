package johnny.problem;

public class User {
    private String name;
    public void pressButton(int toFloor) {
        Request req = new Request(toFloor);
        //RequestProcessCenter center = RequestProcessCenter.getInstance();
        //center.addRequest(req);
    }
}