import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Button {
    private Button() {}
    int x = 78;

    private static Map<String, int[]> buttonData = new HashMap<>();
    static {
        buttonData.put("1", new int[]{11, 20});
        buttonData.put("2", new int[]{11, 58});
        buttonData.put("3", new int[]{16, 4});
        buttonData.put("4", new int[]{16, 42});
        buttonData.put("5", new int[]{21, 4});
        buttonData.put("6", new int[]{21, 42});
    }

    public static String[][] boldButton(String buttonId, String[][] menuScreen) {
        int[] startCoord = buttonData.get(buttonId);
        // bold top
        for (int i = startCoord[1]; i < startCoord[1] + 31; i++) {
            System.out.println(i);
            menuScreen[startCoord[0]][i] = Utils.bold(menuScreen[startCoord[0]][i]);
        }

        // bold sides
        menuScreen[startCoord[0]+1][startCoord[1]] = Utils.bold(menuScreen[startCoord[0]+1][startCoord[1]]);
        menuScreen[startCoord[0]+1][startCoord[1]+31] = Utils.bold(menuScreen[startCoord[0]+1][startCoord[1]+31]);
        menuScreen[startCoord[0]+2][startCoord[1]] = Utils.bold(menuScreen[startCoord[0]+2][startCoord[1]]);
        menuScreen[startCoord[0]+2][startCoord[1]+31] = Utils.bold(menuScreen[startCoord[0]+2][startCoord[1] + 31]);

        // bold bottom
        for (int i = startCoord[1]; i < startCoord[1] + 31; i++) {
            System.out.println(i);
            menuScreen[startCoord[0]+3][i] = Utils.bold(menuScreen[startCoord[0]+3][i]);
        }

        return menuScreen;
    }
}
