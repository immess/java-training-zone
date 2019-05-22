package org.github.immess.console;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Handles IO: reads commands, writes result.
 */
public class CommandManager implements Runnable {
    private final Scanner scanner;
    private final PrintWriter writer;
    private final CommandContext context;

    public CommandManager(InputStream inputStream, OutputStream outputStream, CommandContext context) {
        this.scanner = new Scanner(inputStream);
        this.writer = new PrintWriter(outputStream, true);
        this.context = context;
    }

    @Override
    public void run() {
        writer.println("Ready to get commands");

        writer.println("\nWaiting for command...");
        while (scanner.hasNextLine()) {
            String inputLine = scanner.nextLine();
            writer.println("Got this: " + inputLine);

            if (!handleNextCommand(inputLine)) {
                return;
            }

            writer.println("\nWaiting for command...");
        }

        writer.println("Exiting");
    }

    private boolean handleNextCommand(String inputLine) {
        String[] inputParts = inputLine.split(" ");
        String command = inputParts[0];
        String[] args = Arrays.copyOfRange(inputParts, 1, inputParts.length);

        writer.println(String.format("Parsed command `%s` with arguments %s", command, Arrays.toString(args)));

        if (context.shouldEnd(command)) {
            return false;
        }

        writer.println(context.handle(command, args));
        return true;
    }
}
