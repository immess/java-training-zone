package org.github.immess.console;

import java.util.Arrays;
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
            return String.format("Back to %s. %s", stack.peek().getName(), availableCommands());
        }

        CommandHandler.HandleResult handleResult = stack.peek().handle(command, args);
        if (handleResult.next != stack.peek() && handleResult.next != null) {
            stack.push(handleResult.next);
            String answer = String.format("Change context to %s. %s", stack.peek().getName(), availableCommands());
            if (handleResult.result != null) {
                answer += "\nResult: " + handleResult.result;
            }
            return answer;
        }
        if (handleResult.result == null) {
            return String.format("Error: unknown command for %s. %s", stack.peek().getName(), availableCommands());
        }

        return handleResult.result;
    }

    private String availableCommands() {
        return "Available commands: " + Arrays.toString(stack.peek().getCommands());
    }

    public String getContextInfo() {
        return String.format("Current context: %s. %s", stack.peek().getName(), availableCommands());
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
