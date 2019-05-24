package org.github.immess.console.Impl;

import org.github.immess.console.CommandHandler;
import org.github.immess.console.HandleResult;

public class RouterCommandHandler implements CommandHandler {
    @Override
    public HandleResult handle(String command, String[] args) {
        switch (command) {
            //case "array": return new HandleResult(new DynArrayCommandHandler(), "OK");
            case "array": return new HandleResult(new DynArrayBetterCommandHandler(), null);
            //case "stack": return new HandleResult(new StackCommandHandler(), null);
            case "stack": return new HandleResult(new StackBetterCommandHandler(), null);
            //case "queue": return new HandleResult(new QueueCommandHandler(), null);
            case "queue": return new HandleResult(new QueueBetterCommandHandler(), null);
            default: return new HandleResult(null);
        }
    }

    @Override
    public String getName() {
        return "Router";
    }

    @Override
    public String[] getCommands() {
        return new String[]{"array", "stack", "queue"};
    }
}
