package org.github.immess.console;

import org.github.immess.structure.Stack;

import java.util.Arrays;

public class StackCommandHandler extends SimpleCommandHandler {
    private final Stack stack = new Stack();

    @Override
    protected String doActualHandle(String command, String[] args) {
        switch (command) {
            case "push":
                stack.push(Integer.parseInt(args[0]));
                return "Pushed element: " + args[0];
            case "pop":
                return "Popped element: " + stack.pop();
            case "peek":
                return "Peeked element: " + stack.peek();
            case "size":
                return "Size is " + stack.size();
            case "list":
                return getName() + ": " + Arrays.toString(stack.toArray());
            default:
                return null;
        }
    }

    @Override
    public String getName() {
        return "Stack";
    }

    @Override
    public String[] getCommands() {
        return new String[]{"push", "pop", "peek", "size", "list"};
    }
}
