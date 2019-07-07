package org.github.immess.console;

import org.github.immess.console.structure.RouterCommandHandler;
import org.github.immess.utils.Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class ConsoleTest {
    @Test
    public void stackTest() {
        consoleCheckFromFile("stack.txt");
    }

    @Test
    public void arrayTest() {
        consoleCheckFromFile("array.txt");
    }

    @Test
    public void queueTest() {
        consoleCheckFromFile("queue.txt");
    }

    @Test
    public void routingTest() {
        consoleCheckFromFile("routing.txt");
    }

    private void consoleCheckFromFile(String fileName) {
        String data;
        try (InputStream stream = this.getClass().getClassLoader().getResourceAsStream("structure/" + fileName)){
            data = Utils.str(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] source = data.split("----");
        consoleCheck(source[0], source[1]);
    }

    private void consoleCheck(String source, String expected) {
        ByteArrayInputStream input = new ByteArrayInputStream(source.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        CommandContext context = new CommandContext(new RouterCommandHandler());
        CommandManager manager = new CommandManager(input, output, context);

        manager.run();

        Assert.assertEquals(
            expected.replace("\r", ""),
            output.toString().replace("\r", "")
        );
    }
}



