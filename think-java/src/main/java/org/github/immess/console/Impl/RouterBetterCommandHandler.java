package org.github.immess.console.Impl;

import org.github.immess.console.AbstractBetterCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;

import java.util.Map;

public class RouterBetterCommandHandler extends AbstractBetterCommandHandler {
    @Override
    protected void defineCommands(Map<String, Command> handlers) {
        handlers.put("array", args -> new HandleResult(new DynArrayBetterCommandHandler(), null));
        handlers.put("stack", args -> new HandleResult(new StackBetterCommandHandler(), null));
        handlers.put("queue", args -> new HandleResult(new QueueBetterCommandHandler(), null));
    }

    @Override
    public String getName() {
        return "Router";
    }
}
