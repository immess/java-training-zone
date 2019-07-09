package org.github.immess.fs;

import org.github.immess.console.AbstractCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileManagerCommandHandler extends AbstractCommandHandler {
    private File file = new File(System.getProperty("user.dir", "."));

    @Override
    public String getName() {
        return "File Manager";
    }

    @Override
    protected void defineCommands(Map<String, Command> handlers) {
        super.defineCommands(handlers);

        handlers.put("ls", args -> {
            StringBuilder builder = new StringBuilder();
            if (file.list() == null) {
                return new HandleResult("There are no files in here");
            }
            for (String x : file.list()) {
                builder.append(x)
                    .append("\n");
            }
            return new HandleResult(builder.toString());
        });
        handlers.put("pwd", args -> {
            try {
                return new HandleResult(file.getCanonicalPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        handlers.put("cd", args -> {
            if (args.length > 1) {
                return new HandleResult("So much arguments, too many");
            }
            if (args.length < 1) {
                return new HandleResult("Need new path");
            }
            String path = args[0];
            File nextFile = new File(file, path);
            if (!nextFile.exists()) {
                return new HandleResult("Path doesn't exist");
            }
            if (!nextFile.isDirectory()) {
                return new HandleResult("Not a directory");
            }
            file = nextFile;
            try {
                return new HandleResult("Changed dir to " + file.getCanonicalPath());
            } catch (IOException e) {
                throw  new RuntimeException(e);
            }
        });
        //todo mkdir dir
        //todo touch file_name
        //todo cp file tgt_file
        //todo mv file tgt_file
        //todo rm file
        //todo cat file

        //todo tests
    }
}
