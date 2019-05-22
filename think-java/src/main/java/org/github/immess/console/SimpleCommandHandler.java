package org.github.immess.console;

public abstract class SimpleCommandHandler implements CommandHandler {
    @Override
    public final HandleResult handle(String command, String[] args) {
        return new HandleResult(doActualHandle(command, args));
    }

    protected abstract String doActualHandle(String command, String[] args);
}
