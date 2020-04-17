package johnny.design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestListener implements Runnable {

    @Override
    public void run() {

        while (true) {
            String floorNumberStr = null;
            try {
                // Read input from console
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
                floorNumberStr = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (isValidFloorNumber(floorNumberStr)) {
                System.out.println("User Pressed : " + floorNumberStr);
                Elevator elevator = Elevator.getInstance();
                elevator.addFloor(Integer.parseInt(floorNumberStr));
            } else {
                System.out.println("Floor Request Invalid : " + floorNumberStr);
            }
        }
    }

    /**
     * This method is used to define maximum floors this elevator can process.
     * @param s - requested floor
     * @return true if requested floor is integer and upto two digits. (max floor = 99)
     */
    private boolean isValidFloorNumber(String s) {
        return (s != null) && s.matches("\\d{1,2}");
    }

}