package johnny.problem;

public class Elevator {
    public static Elevator instance = null;
    private int currentFloor;

    public static Elevator getInstance() {
        if (instance == null) {
            synchronized (Elevator.class) {
                instance = new Elevator();
            }
        }
        return instance;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void  moveToTargetFloor(int toFloor) {
        currentFloor = toFloor;
    }

    public void moveUp() {

    }
    public void moveDown() {

    }
}
