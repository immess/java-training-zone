package org.github.immess.console.Impl;

import org.github.immess.console.AbstractCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;
import org.github.immess.structure.Queue;

import java.util.Arrays;
import java.util.Map;

public class QueueCommandHandler extends AbstractCommandHandler {
    private final Queue queue = new Queue();

    @Override
    protected void defineCommands(Map<String, Command> handlers) {
        handlers.put("push", args -> {
            queue.push(Integer.parseInt(args[0]));
            return new HandleResult("Pushed element: " + args[0]);
        });
        handlers.put("peek", args -> new HandleResult("Peeked element: " + queue.peek()));
        handlers.put("pop", args -> new HandleResult("Popped element: " + queue.pop()));
        handlers.put("insert", args -> {
            queue.insert(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            return new HandleResult(String.format("Inserted element `%s` at index `%s`", args[0], args[1]));
        });
        handlers.put("remove", args -> new HandleResult(String.format("Removed element `%d` at index `%s`", queue.remove(Integer.parseInt(args[0])), args[0])));
        handlers.put("get", args -> new HandleResult(String.format("Got element `%d` at index `%s`", queue.get(Integer.parseInt(args[0])), args[0])));
        handlers.put("size", args -> new HandleResult("Size is " + queue.size()));
        handlers.put("list", args -> new HandleResult(getName() + ": " + Arrays.toString(queue.toArray())));
    }

    @Override
    public String getName() {
        return "Queue";
    }
}
