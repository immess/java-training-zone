package org.github.immess.console;

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
