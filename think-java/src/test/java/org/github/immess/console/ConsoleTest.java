package org.github.immess.console;

import org.github.immess.console.structure.RouterCommandHandler;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ConsoleTest {
    @Test
    public void stackTest() {
        String source = "stack\n" +
            "help\n" +
            "push 45\n" +
            "push 778\n" +
            "peek\n" +
            "pop\n" +
            "peek\n" +
            "size\n" +
            "list\n" +
            "exit";

        String expected = "Ready to get commands\n" +
            "Current context: Router.\n" +
            "Type\n" +
            "\t'help' to see available commands;\n" +
            "\t'back' to go to previous context;\n" +
            "\t'exit' to exit.\n" +
            "\n" +
            "Waiting for command...\n" +
            "Change context to Stack. Available commands: [help, push, pop, peek, size, list, magic]\n" +
            "Available commands: [help, push, pop, peek, size, list, magic]\n" +
            "Pushed element: 45\n" +
            "Pushed element: 778\n" +
            "Peeked element: 778\n" +
            "Popped element: 778\n" +
            "Peeked element: 45\n" +
            "Size is 1\n" +
            "Stack: [45]\n" +
            "Exiting\n";

        consoleCheck(source, expected);
    }

    @Test
    public void arrayTest() {
        String source = "array\n" +
            "help\n" +
            "push 87\n" +
            "insert 456 0\n" +
            "push 325\n" +
            "list\n" +
            "get 1\n" +
            "remove 1\n" +
            "size\n" +
            "ext\n" +
            "exit";

        String expected = "Ready to get commands\n" +
            "Current context: Router.\n" +
            "Type\n" +
            "\t'help' to see available commands;\n" +
            "\t'back' to go to previous context;\n" +
            "\t'exit' to exit.\n" +
            "\n" +
            "Waiting for command...\n" +
            "Change context to DynArray. Available commands: [help, push, get, remove, insert, size, list]\n" +
            "Available commands: [help, push, get, remove, insert, size, list]\n" +
            "Pushed element: 87\n" +
            "Insert element `456` at index `0`\n" +
            "Pushed element: 325\n" +
            "DynArray: [456, 87, 325]\n" +
            "Got element `87` at index `1`\n" +
            "Removed element `87` at index `1`\n" +
            "Size is 2\n" +
            "Error: unknown command for DynArray. Available commands: [help, push, get, remove, insert, size, list]\n" +
            "Exiting\n";

        consoleCheck(source, expected);
    }

    @Test
    public void queueTest() {
        String source = "help\n" +
            "queue\n" +
            "help\n" +
            "push 4\n" +
            "push 5\n" +
            "peek\n" +
            "pop\n" +
            "insert 8 0\n" +
            "get 1\n" +
            "remove 0\n" +
            "size\n" +
            "list\n" +
            "exit";

        String expected = "Ready to get commands\n" +
            "Current context: Router.\n" +
            "Type\n" +
            "\t'help' to see available commands;\n" +
            "\t'back' to go to previous context;\n" +
            "\t'exit' to exit.\n" +
            "\n" +
            "Waiting for command...\n" +
            "Available commands: [help, array, stack, queue]\n" +
            "Change context to Queue. Available commands: [help, push, peek, pop, insert, remove, get, size, list]\n" +
            "Available commands: [help, push, peek, pop, insert, remove, get, size, list]\n" +
            "Pushed element: 4\n" +
            "Pushed element: 5\n" +
            "Peeked element: 4\n" +
            "Popped element: 4\n" +
            "Inserted element `8` at index `0`\n" +
            "Got element `5` at index `1`\n" +
            "Removed element `8` at index `0`\n" +
            "Size is 1\n" +
            "Queue: [5]\n" +
            "Exiting\n";

        consoleCheck(source, expected);
    }

    @Test
    public void routingTest() {
        String source = "stack\n" +
            "magic\n" +
            "back\n" +
            "back\n" +
            "array\n" +
            "back\n" +
            "queue\n" +
            "back\n" +
            "back";

        String expected = "Ready to get commands\n" +
            "Current context: Router.\n" +
            "Type\n" +
            "\t'help' to see available commands;\n" +
            "\t'back' to go to previous context;\n" +
            "\t'exit' to exit.\n" +
            "\n" +
            "Waiting for command...\n" +
            "Change context to Stack. Available commands: [help, push, pop, peek, size, list, magic]\n" +
            "Change context to Stack. Available commands: [help, push, pop, peek, size, list, magic]\n" +
            "Result: weeee\n" +
            "Back to Stack. Available commands: [help, push, pop, peek, size, list, magic]\n" +
            "Back to Router. Available commands: [help, array, stack, queue]\n" +
            "Change context to DynArray. Available commands: [help, push, get, remove, insert, size, list]\n" +
            "Back to Router. Available commands: [help, array, stack, queue]\n" +
            "Change context to Queue. Available commands: [help, push, peek, pop, insert, remove, get, size, list]\n" +
            "Back to Router. Available commands: [help, array, stack, queue]\n" +
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



