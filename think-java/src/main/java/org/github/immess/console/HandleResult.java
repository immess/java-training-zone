package org.github.immess.console;

public class HandleResult {
    public final CommandHandler next;
    public final String result;

    public HandleResult(CommandHandler next, String result) {
        this.next = next;
        this.result = result;
    }

    public HandleResult(String result) {
        this(null, result);
    }
}
