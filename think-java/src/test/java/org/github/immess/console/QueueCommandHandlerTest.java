package org.github.immess.console;

import org.github.immess.console.structure.QueueCommandHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QueueCommandHandlerTest {
    private QueueCommandHandler handler;

    @Before
    public void init(){
        handler = new QueueCommandHandler();
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
        String[] args = {"0"};
        handler.handle("push", args);

        HandleResult result = handler.handle("pop", args);

        String expected = "Popped element: " + args[0];
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void getTest() {
        String[] args = {"0"};
        handler.handle("push", args);

        HandleResult result = handler.handle("get", args);

        String expected = "Got element `" + args[0] + "` at index `0`";
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void removeTest() {
        String[] args = {"0"};
        handler.handle("push", args);

        HandleResult result = handler.handle("remove", args);

        String expected = "Removed element `" + args[0] + "` at index `0`";
        Assert.assertNull(result.next);
        Assert.assertEquals(expected, result.result);
    }

    @Test
    public void insertTest() {
        String[] args = {"35", "0"};
        handler.handle("push", args);

        HandleResult result = handler.handle("insert", args);

        String expected = "Inserted element `" + args[0] + "` at index `" + args[1] + "`";
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
        handler.handle("push", new String[]{"651"});

        HandleResult result = handler.handle("list", new String[0]);

        String expected = "Queue: [825, 651]";
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
        Assert.assertTrue("Queue".equals(handler.getName()));
    }

    @Test
    public void getCommandTest() {
        String[] expected = {"help", "push", "peek", "pop", "insert", "remove", "get", "size", "list"};
        Assert.assertArrayEquals(expected, handler.getCommands());
    }
}

