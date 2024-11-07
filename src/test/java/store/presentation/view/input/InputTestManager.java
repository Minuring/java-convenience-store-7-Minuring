package store.presentation.view.input;

import java.io.ByteArrayInputStream;

public class InputTestManager {

    public static void setReadLine(String readLine) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(readLine.getBytes());
        System.setIn(byteArrayInputStream);
    }
}
