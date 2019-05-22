package org.github.immess.console;

/**
 * Responsible for command handling.
 */
public interface CommandHandler {

    HandleResult handle(String command, String[] args);

    class HandleResult {
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

    String getName();
}
