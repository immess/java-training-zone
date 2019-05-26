package org.github.immess.console.structure;

import org.github.immess.console.AbstractCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;

import java.util.Map;

public class RouterCommandHandler extends AbstractCommandHandler {
    @Override
    protected void defineCommands(Map<String, Command> handlers) {
        super.defineCommands(handlers);

        handlers.put("array", args -> new HandleResult(new DynArrayCommandHandler(), null));
        handlers.put("stack", args -> new HandleResult(new StackCommandHandler(), null));
        handlers.put("queue", args -> new HandleResult(new QueueCommandHandler(), null));
    }

    @Override
    public String getName() {
        return "Router";
    }
}
