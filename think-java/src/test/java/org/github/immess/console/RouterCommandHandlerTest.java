package org.github.immess.console;

import org.github.immess.console.Impl.DynArrayBetterCommandHandler;
import org.github.immess.console.Impl.QueueBetterCommandHandler;
import org.github.immess.console.Impl.RouterCommandHandler;
import org.github.immess.console.Impl.StackBetterCommandHandler;
import org.junit.Assert;
import org.junit.Test;

public class RouterCommandHandlerTest {
    @Test
    public void arrayTest() {
        RouterCommandHandler handler = new RouterCommandHandler();
        HandleResult result = handler.handle("array", new String[0]);
        Assert.assertTrue(result.next instanceof DynArrayBetterCommandHandler);
        Assert.assertNull(result.result);
    }

    @Test
    public void stackTest() {
        RouterCommandHandler handler = new RouterCommandHandler();
        HandleResult result = handler.handle("stack", new String[0]);
        Assert.assertTrue(result.next instanceof StackBetterCommandHandler);
        Assert.assertNull(result.result);
    }

    @Test
    public void queueTest() {
        RouterCommandHandler handler = new RouterCommandHandler();
        HandleResult result = handler.handle("queue", new String[0]);
        Assert.assertTrue(result.next instanceof QueueBetterCommandHandler);
        Assert.assertNull(result.result);
    }

    @Test
    public void unknownCommandTest() {
        RouterCommandHandler handler = new RouterCommandHandler();
        HandleResult result = handler.handle("wrong", new String[0]);
        Assert.assertNull(result.next);
        Assert.assertNull(result.result);
    }

    @Test
    public void getNameTest() {
        RouterCommandHandler handler = new RouterCommandHandler();
        Assert.assertTrue("Router".equals(handler.getName()));
    }

    @Test
    public void getCommandTest() {
        RouterCommandHandler handler = new RouterCommandHandler();
        String[] expected = {"array", "stack", "queue"};
        Assert.assertArrayEquals(expected, handler.getCommands());
    }
}
