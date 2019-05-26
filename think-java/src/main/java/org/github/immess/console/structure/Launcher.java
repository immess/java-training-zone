package org.github.immess.console.structure;

import org.github.immess.console.CommandContext;
import org.github.immess.console.CommandManager;

public class Launcher {
    public static void main(String[] args) {
        CommandManager manager = new CommandManager(
            System.in,
            System.out,
        new CommandContext(new RouterCommandHandler()));
        manager.run();
    }
}
