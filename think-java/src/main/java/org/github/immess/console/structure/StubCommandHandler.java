package org.github.immess.console.structure;

import org.github.immess.console.SimpleCommandHandler;

public class StubCommandHandler extends SimpleCommandHandler {
    @Override
    protected String doActualHandle(String command, String[] args) {
        return "Handled this command: " + command;
    }

    @Override
    public String getName() {
        return "Stub";
    }

    @Override
    public String[] getCommands() {
        return new String[]{"Any command"};
    }
}
