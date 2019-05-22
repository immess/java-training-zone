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
        if (isBackCommand(command)) {
            if (stack.size() == 1) {
                return "Error: can't go back anymore";
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
        return isExitCommand(command) || (isBackCommand(command) && stack.size() == 1);
    }

    private boolean isExitCommand(String command) {
        return "exit".equals(command);
    }

    private boolean isBackCommand(String command) {
        return "back".equals(command);
    }
}
