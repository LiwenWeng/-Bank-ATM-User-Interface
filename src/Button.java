import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Button {
    private Button() {}
    int x = 78;

    private static Map<Integer, int[]> buttonData = new HashMap<>();
    static {
        buttonData.put(1, new int[]{11, 20});
        buttonData.put(2, new int[]{11, 58});
        buttonData.put(3, new int[]{16, 20});
        buttonData.put(4, new int[]{16, 58});
        buttonData.put(5, new int[]{21, 20});
        buttonData.put(6, new int[]{21, 58});
    }

    public static void selectButton(int buttonId, String[][] menuScreen) {
        int[] startCoord = buttonData.get(buttonId);

        // color top and bottom
        for (int i = startCoord[1]; i < startCoord[1] + 30 && i < 79; i++) {
            menuScreen[startCoord[0]][i] = Utils.color(menuScreen[startCoord[0]][i], "Blue");
            menuScreen[startCoord[0]+3][i] = Utils.color(menuScreen[startCoord[0]+3][i], "Blue");
        }

        // color sides
        menuScreen[startCoord[0]+1][startCoord[1]] = Utils.color(menuScreen[startCoord[0]+1][startCoord[1]], "Blue");
        menuScreen[startCoord[0]+2][startCoord[1]] = Utils.color(menuScreen[startCoord[0]+2][startCoord[1]], "Blue");

        if (buttonId % 2 == 0) {
            for (int i = 0; i < 10; i++) {
                menuScreen[startCoord[0]+1][i] = Utils.color(menuScreen[startCoord[0]+1][i], "Blue");
                menuScreen[startCoord[0]+4][i] = Utils.color(menuScreen[startCoord[0]+4][i], "Blue");
            }

            menuScreen[startCoord[0]+2][10] = Utils.color(menuScreen[startCoord[0]+2][10], "Blue");
            menuScreen[startCoord[0]+3][10] = Utils.color(menuScreen[startCoord[0]+3][10], "Blue");

        } else {
            menuScreen[startCoord[0]+1][startCoord[1]+30] = Utils.color(menuScreen[startCoord[0]+1][startCoord[1]+30], "Blue");
            menuScreen[startCoord[0]+2][startCoord[1]+30] = Utils.color(menuScreen[startCoord[0]+2][startCoord[1]+30], "Blue");
        }
    }
}
