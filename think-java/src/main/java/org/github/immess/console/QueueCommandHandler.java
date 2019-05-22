package org.github.immess.console;

import org.github.immess.structure.Queue;

import java.util.Arrays;

public class QueueCommandHandler extends SimpleCommandHandler {
    private final Queue queue = new Queue();

    @Override
    protected String doActualHandle(String command, String[] args) {
        switch (command) {
            case "push": {
                queue.push(Integer.parseInt(args[0]));
                return "Pushed: " + args[0];
            }
            case "pop":
                return "Popped element: " + queue.pop();
            case "peek":
                return "Peeked element: " + queue.peek();
            case "get":
                return String.format("Got element `%d` at index `%s`", queue.get(Integer.parseInt(args[0])), args[0]);
            case "remove":
                return String.format("Removed element `%d` at index `%s`", queue.remove(Integer.parseInt(args[0])), args[0]);
            case "insert":
                queue.insert(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                return String.format("Insert element `%s` at index `%s`", args[0], args[1]);
            case "size":
                return "Size is " + queue.size();
            case "list":
                return getName() + ": " + Arrays.toString(queue.toArray());
            default:
                return "Error: unknown command";
        }
    }

    @Override
    public String getName() {
        return "Queue";
    }
}
