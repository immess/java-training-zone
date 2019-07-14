package org.github.immess.fs;

import org.github.immess.console.CommandContext;
import org.github.immess.console.CommandManager;

public class FileManagerLauncher {
    public static void main(String[] args) {
        CommandManager manager = new CommandManager(
            System.in,
            System.out,
        new CommandContext(new FileManagerCommandHandler()));
        manager.run();
    }
}
