package org.github.immess.fs;

import org.github.immess.console.AbstractCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
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
            String[] files = file.list();
            if (files.length == 0) {
                return new HandleResult("There are no files in here");
            }
            for (String file : files) {
                builder.append(file)
                    .append("\n");
            }
            return new HandleResult(builder.toString());
        });
        handlers.put("pwd", args -> {
            try {
                return new HandleResult(file.getCanonicalPath());
            } catch (IOException e) {
                return new HandleResult("Operation failed: " + e.getMessage());
            }
        });
        handlers.put("cd", args -> {
            HandleResult argsCheckResult = checkSingleArg(args);
            if (argsCheckResult != null) {
                return argsCheckResult;
            }

            String path = args[0];
            File nextFile = new File(file, path);
            if (!nextFile.exists()) {
                return new HandleResult("Path doesn't exist");
            }
            if (!nextFile.isDirectory()) {
                return new HandleResult("Not a directory");
            }

            try {
                file = nextFile.getCanonicalFile();
            } catch (IOException e) {
                return new HandleResult("Can't change dir: " + e.getMessage());
            }

            return new HandleResult("Changed dir to " + file.getPath());
        });
        handlers.put("mkdir", args -> {
            HandleResult argsCheckResult = checkSingleArg(args);
            if (argsCheckResult != null) {
                return argsCheckResult;
            }

            String path = args[0];
            File newDir = new File(file, path);
            if (newDir.exists()) {
                return new HandleResult("Directory already exists");
            }
            if (!newDir.mkdirs()) {
                return new HandleResult("Can't create dir");
            }
            try {
                return new HandleResult("Created dir " + newDir.getCanonicalPath());
            } catch (IOException e) {
                return new HandleResult("Operation failed: " + e.getMessage());
            }
        });
        handlers.put("touch", args -> {
            HandleResult argsCheckResult = checkSingleArg(args);
            if (argsCheckResult != null) {
                return argsCheckResult;
            }

            String path = args[0];
            File newFile = new File(file, path);
            try {
                if (!newFile.createNewFile()) {
                    return new HandleResult("File already exist");
                }
            } catch (IOException e) {
                return new HandleResult("Can't create file: " + e.getMessage());
            }
            return new HandleResult("Created file " + newFile.getName());
        });
        //todo cp file tgt_file
        //todo mv file tgt_file
        //todo rm file
        //todo cat file

        //todo tests
    }

    private HandleResult checkSingleArg(String[] args) {
        if (args.length > 1) {
            return new HandleResult("So much arguments, too many");
        }
        if (args.length < 1) {
            return new HandleResult("Need argument");
        }
        return null;
    }
}
