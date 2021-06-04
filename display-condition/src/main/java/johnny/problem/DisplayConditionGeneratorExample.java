package johnny.problem;

public class DisplayConditionGeneratorExample {
    public static void main(String[] args) {
        System.out.println("Create 5 IDs with length of 6, base62");
        // Create 5 IDs with length of 6, base62
        for (int i = 0; i < 5; i++) {
            System.out.println(DisplayConditionGenerator.combine(new String[]{"buyersAllowForecastCollaboration", "buyersAllowForecastView"}));
        }
    }
}
