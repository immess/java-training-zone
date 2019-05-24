package org.github.immess.console;

import org.github.immess.console.Impl.RouterCommandHandler;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ConsoleTest {
    @Test
    public void test() {
        String source = "stack\n" +
            "push 45\n" +
            "peek\n" +
            "exit";

        String expected = "Ready to get commands\n" +
            "Current context: Router. Available commands: [array, stack, queue]\n" +
            "\n" +
            "Waiting for command...\n" +
            "Change context to Stack. Available commands: [push, pop, peek, size, list, magic]\n" +
            "Pushed element: 45\n" +
            "Peeked element: 45\n" +
            "Exiting\n";

        consoleCheck(source, expected);
    }

    private void consoleCheck(String source, String expected) {
        ByteArrayInputStream input = new ByteArrayInputStream(source.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        CommandContext context = new CommandContext(new RouterCommandHandler());
        CommandManager manager = new CommandManager(input, output, context);

        manager.run();

        String result = output.toString().replace("\r", "");
        Assert.assertEquals(expected, result);
    }
}



