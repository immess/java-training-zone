package org.github.immess.console;

import java.util.Stack;

/**
 * Controls application context.
 */
public class CommandContext {
    private final Stack<CommandHandler> stack;

    public CommandContext(CommandHandler starter) {
        this.stack = new Stack<>();
        stack.push(starter);
    }

    public String handle(String command, String[] args) {
        if (isExitCommand(command)) {
            if (stack.size() == 1) {
                return "Error: can't exit anymore";
            }
            stack.pop();
            return "Back to " + stack.peek().getName();
        }

        CommandHandler.HandleResult handleResult = stack.peek().handle(command, args);
        if (handleResult.next != stack.peek() && handleResult.next != null) {
            stack.push(handleResult.next);
            return String.format("Change context to %s. Result: %s", handleResult.next.getName(), handleResult.result);
        }

        return handleResult.result;
    }

    public boolean shouldEnd(String command) {
        return stack.size() == 1 && isExitCommand(command);
    }

    private boolean isExitCommand(String command) {
        return "exit".equals(command);
    }
}
