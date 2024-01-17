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

    public static void boldButton(String buttonId, String menuScreen) {
        int value = buttonData.get(buttonId);

        if (Integer.parseInt(buttonId) % 2 == 0) {
            int startLocation1 = value - 107;
            int endLocation1 = startLocation1 + 30;
            int borderBold1 = value - 29;
            int borderBold2 = value + 2;
            int borderBold3 = value + 50;
            int borderBold4 = value + 81;
            int startLocation2 = value + 130;
            int endLocation2 = startLocation2 + 30;

            System.out.println(
                    menuScreen.substring(0, startLocation1) +
                    Utils.bold(menuScreen.substring(startLocation1, endLocation1)) + // bold top border
                    menuScreen.substring(endLocation1, borderBold1) +
                    Utils.bold(menuScreen.substring(borderBold1)) + // bold first side border
                    menuScreen.substring(borderBold1, borderBold2) +
                    Utils.bold(menuScreen.substring(borderBold2)) + // bold second side border
                    menuScreen.substring(borderBold2, borderBold3) +
                    Utils.bold(menuScreen.substring(borderBold3)) + // bold third side border
                    menuScreen.substring(borderBold3, borderBold4) +
                    Utils.bold(menuScreen.substring(borderBold4)) + // bold fourth side border
                    menuScreen.substring(borderBold4, startLocation2) +
                    Utils.bold(menuScreen.substring(startLocation2, endLocation2)) + // bold bottom border
                    menuScreen.substring(endLocation2)
            );

        } else {
            int startLocation = value - 94;
        }
    }
}
