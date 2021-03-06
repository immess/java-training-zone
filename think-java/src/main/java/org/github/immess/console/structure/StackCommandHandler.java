package org.github.immess.console.structure;

import org.github.immess.console.AbstractCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;
import org.github.immess.structure.Stack;

import java.util.Arrays;
import java.util.Map;

public class StackCommandHandler extends AbstractCommandHandler {
    private final Stack stack = new Stack();

    @Override
    public String getName() {
        return "Stack";
    }

    @Override
    protected void defineCommands(Map<String, Command> handlers) {
        super.defineCommands(handlers);

        handlers.put("push", args -> {
            stack.push(Integer.parseInt(args[0]));
            return new HandleResult("Pushed element: " + args[0]);
        });
        handlers.put("pop", args -> new HandleResult("Popped element: " + stack.pop()));
        handlers.put("peek", args -> new HandleResult("Peeked element: " + stack.peek()));
        handlers.put("size", args -> new HandleResult("Size is " + stack.size()));
        handlers.put("list", args -> new HandleResult(getName() + ": " + Arrays.toString(stack.toArray())));
        handlers.put("magic", args -> new HandleResult(new StackCommandHandler(), "weeee"));
    }
}
