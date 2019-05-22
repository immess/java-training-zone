package org.github.immess.console;

public class RouterCommandHandler implements CommandHandler {
    @Override
    public HandleResult handle(String command, String[] args) {
        switch (command) {
            case "array": return new HandleResult(new DynArrayCommandHandler(), "Change to DynArray");
            case "stack": return new HandleResult(new StackCommandHandler(), "Change to Stack");
            case "queue": return new HandleResult(new QueueCommandHandler(), "Change to Queue");
            default: return new HandleResult(this, "Error: unknown command");
        }
    }
}
