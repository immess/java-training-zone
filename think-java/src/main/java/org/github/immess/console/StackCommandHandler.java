package org.github.immess.console;

import org.github.immess.structure.Stack;

import java.util.Arrays;

public class StackCommandHandler implements CommandHandler {
    public Stack stack = new Stack();

    @Override
    public HandleResult handle(String command, String[] args) {
        return new HandleResult(this, doActualHandle(command, args));
    }

    private String doActualHandle(String command, String[] args) {
        switch (command) {
            case "push": {
                stack.push(Integer.parseInt(args[0]));
                return "Pushed " + args[0] + " into a stack";
            }
            case "pop":
                return "Popped element: " + stack.pop();
            case "peek":
                return "Peeked element: " + stack.peek();
            case "size":
                return "Size of stack is " + stack.size();
            case "list":
                return "Stack: " + Arrays.toString(stack.toArray());
            default:
                return "Error: unknown command";
        }
    }
}
