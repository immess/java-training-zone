package org.github.immess.remote;

import org.github.immess.console.CommandContext;
import org.github.immess.console.CommandManager;
import org.github.immess.fs.FileManagerCommandHandler;

public class RemoteLauncher {
    public static void main(String[] args) {
        CommandManager manager = new CommandManager(
            System.in,
            System.out,
        new CommandContext(new RemoteCommandHandler()));
        manager.run();
    }
}
