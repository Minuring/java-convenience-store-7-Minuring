package store.presentation.view;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public abstract class IOTest {

    private ByteArrayOutputStream outputStreamCaptor;
    private PrintStream standardOut;

    protected void systemIn(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    @BeforeEach
    void setUp() {
        standardOut = System.out;
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    protected void cleanUp() {
        System.setIn(InputStream.nullInputStream());
        System.setOut(standardOut);

        Console.close();
    }

    protected String getOutput() {
        return outputStreamCaptor.toString();
    }
}
