package org.github.immess.console;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractCommandHandler implements CommandHandler {
    private final Map<String, Command> handlers = new LinkedHashMap<>();

    public AbstractCommandHandler(){
        defineCommands(handlers);
    }

    protected abstract void defineCommands(Map<String, Command> handlers);

    @Override
    public final HandleResult handle(String command, String[] args) {
        if (!handlers.containsKey(command)) {
            return new HandleResult(null);
        }
        return handlers.get(command).handle(args);
    }

    @Override
    public final String[] getCommands() {
        return handlers.keySet().toArray(new String[0]);
    }
}
