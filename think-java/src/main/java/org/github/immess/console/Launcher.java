package org.github.immess.console;

public class Launcher {
    public static void main(String[] args) {
        CommandManager manager = new CommandManager(
            System.in,
            System.out,
        new CommandContext(new RouterCommandHandler()));
        manager.run();

    }
}
