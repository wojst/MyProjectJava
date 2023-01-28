package test.AdminGUI;

import main.UserGUI;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class convertTestAdmin {
    @Test
    void convertStrArrtoStrUserGUI() {
        String a = "";
        String[] sArr = new String[2];
        sArr[0] = "Test1";
        sArr[1] = "Test2";

        String result = UserGUI.convertStrArraytoString(sArr, ",");

        Assertions.assertEquals("Test1,Test2", result);
    }
}

