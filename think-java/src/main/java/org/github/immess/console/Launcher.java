package org.github.immess.console;

import org.github.immess.console.Impl.RouterCommandHandler;

public class Launcher {
    public static void main(String[] args) {
        CommandManager manager = new CommandManager(
            System.in,
            System.out,
        new CommandContext(new RouterCommandHandler()));
        manager.run();

    }
}
