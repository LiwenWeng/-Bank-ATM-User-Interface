import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Button {
    private Button() {}
    int x = 78;

    private static Map<String, Integer> buttonData = new HashMap<>();
    static {
        buttonData.put("1", 795);
        buttonData.put("2", 1026);
        buttonData.put("3", 1356);
        buttonData.put("4", 1421);
        buttonData.put("5", 1751);
        buttonData.put("6", 1816);
    }

    public static void boldButton(String buttonId, String[][] menuScreen) {
        int value = buttonData.get(buttonId);

    }
}
