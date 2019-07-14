package org.github.immess.remote;

import org.github.immess.console.*;
import org.github.immess.fs.FileManagerCommandHandler;
import org.github.immess.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class RemoteCommandHandler extends AbstractCommandHandler {

    @Override
    public String getName() {
        return "Remote";
    }

    @Override
    protected void defineCommands(Map<String, Command> handlers) {
        super.defineCommands(handlers);

        handlers.put("connect", args -> {
            checkArgsNumThenError(args, 2);
            String host = args[0];
            int port = Integer.parseInt(args[1]);

            return connect(host, port);
        });
        handlers.put("start", args -> {
            checkArgsNumThenError(args, 1);
            int port = Integer.parseInt(args[0]);

            return start(port);
        });
    }

    private HandleResult connect(String host, int port) {
        try (Socket client = new Socket(host, port)) {
            try (InputStream in = client.getInputStream();
                 OutputStream out = client.getOutputStream()) {
                while (!client.isClosed()) {
                    if (!client.isInputShutdown()) {
                        Utils.tryCopy(System.in, out);
                    }
                    if (!client.isOutputShutdown()) {
                        Utils.tryCopy(in, System.out);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new HandleResult("connection closed");
    }

    private HandleResult start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Server started!");
            while (true) {
                System.out.println("Awaiting connection");
                try (Socket client = server.accept()) {
                    try (InputStream in = client.getInputStream();
                         OutputStream out = client.getOutputStream()) {
                        CommandManager manager = new CommandManager(
                            in,
                            out,
                            new CommandContext(new FileManagerCommandHandler()));
                        manager.run();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        return new HandleResult("started");
    }
}
