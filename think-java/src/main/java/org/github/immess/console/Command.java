package org.github.immess.console;

public interface Command {
    HandleResult handle(String[] args);
}
