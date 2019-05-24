package org.github.immess.console;

import org.github.immess.console.Impl.StackBetterCommandHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackCommandHandlerTest {
    private StackBetterCommandHandler handler;

    @Before
    public void init() {
        handler = new StackBetterCommandHandler();
    }

    @Test
    public void pushTest() {
        String[] args = {"7"};

        HandleResult result = handler.handle("push", args);

        String expected = "Pushed element: " + args[0];
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void peekTest() {
        String[] args = {"0"};
        handler.handle("push", args);

        HandleResult result = handler.handle("peek", args);

        String expected = "Peeked element: " + args[0];
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void popTest() {
        String[] args = {"35"};
        handler.handle("push", args);

        HandleResult result = handler.handle("pop", args);

        String expected = "Popped element: " + args[0];
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void sizeTest() {
        String[] args = {"825"};
        handler.handle("push", args);

        HandleResult result = handler.handle("size", args);

        String expected = "Size is 1";
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void listTest() {
        handler.handle("push", new String[]{"825"});
        handler.handle("push", new String[]{"55"});

        HandleResult result = handler.handle("list", new String[0]);

        String expected = "Stack: [55, 825]";
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void unknownCommandTest() {
        HandleResult result = handler.handle("wrong", new String[0]);
        Assert.assertNull(result.next);
        Assert.assertNull(result.result);
    }

    @Test
    public void getNameTest() {
        Assert.assertTrue("Stack".equals(handler.getName()));
    }

    @Test
    public void getCommandTest() {
        String[] expected = {"push", "pop", "peek", "size", "list", "magic"};
        Assert.assertArrayEquals(expected, handler.getCommands());
    }
}
