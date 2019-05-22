package org.github.immess.console.Impl;

import org.github.immess.console.CommandHandler;
import org.github.immess.console.HandleResult;

public abstract class SimpleCommandHandler implements CommandHandler {
    @Override
    public final HandleResult handle(String command, String[] args) {
        return new HandleResult(doActualHandle(command, args));
    }

    protected abstract String doActualHandle(String command, String[] args);
}
