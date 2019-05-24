package org.github.immess.console;

import org.github.immess.console.Impl.DynArrayCommandHandler;
import org.github.immess.console.Impl.QueueCommandHandler;
import org.github.immess.console.Impl.RouterCommandHandler;
import org.github.immess.console.Impl.StackCommandHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RouterCommandHandlerTest {
    private RouterCommandHandler handler;

    @Before
    public void init() {
        handler = new RouterCommandHandler();
    }

    @Test
    public void arrayTest() {
        HandleResult result = handler.handle("array", new String[0]);
        Assert.assertTrue(result.next instanceof DynArrayCommandHandler);
        Assert.assertNull(result.result);
    }

    @Test
    public void stackTest() {
        HandleResult result = handler.handle("stack", new String[0]);
        Assert.assertTrue(result.next instanceof StackCommandHandler);
        Assert.assertNull(result.result);
    }

    @Test
    public void queueTest() {
        HandleResult result = handler.handle("queue", new String[0]);
        Assert.assertTrue(result.next instanceof QueueCommandHandler);
        Assert.assertNull(result.result);
    }

    @Test
    public void unknownCommandTest() {

        HandleResult result = handler.handle("wrong", new String[0]);
        Assert.assertNull(result.next);
        Assert.assertNull(result.result);
    }

    @Test
    public void getNameTest() {

        Assert.assertTrue("Router".equals(handler.getName()));
    }

    @Test
    public void getCommandTest() {

        String[] expected = {"array", "stack", "queue"};
        Assert.assertArrayEquals(expected, handler.getCommands());
    }
}
