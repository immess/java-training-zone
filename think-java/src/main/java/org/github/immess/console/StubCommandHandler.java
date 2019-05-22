package org.github.immess.console;

public class StubCommandHandler implements CommandHandler {
    @Override
    public HandleResult handle(String command, String[] args) {
        return new HandleResult(this, "Handled this command: " + command);
    }

    @Override
    public String getName() {
        return "Simple";
    }
}
