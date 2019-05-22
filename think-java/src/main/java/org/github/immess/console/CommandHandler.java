package org.github.immess.console;

/**
 * Responsible for command handling.
 */
public interface CommandHandler {

    HandleResult handle(String command, String[] args);

    String getName();

    String[] getCommands();
}
