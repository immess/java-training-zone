package org.github.immess.console;

public class RouterCommandHandler implements CommandHandler {
    @Override
    public HandleResult handle(String command, String[] args) {
        switch (command) {
            case "array": return new HandleResult(new DynArrayCommandHandler(), "OK");
            case "stack": return new HandleResult(new StackCommandHandler(), "OK");
            case "queue": return new HandleResult(new QueueCommandHandler(), "OK");
            default: return new HandleResult("Error: unknown command");
        }
    }

    @Override
    public String getName() {
        return "Router";
    }
}
