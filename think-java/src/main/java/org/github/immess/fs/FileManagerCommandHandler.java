package org.github.immess.fs;

import org.github.immess.console.AbstractCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;
import org.github.immess.utils.Utils;

import java.io.*;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
            Set<String> argsSet = new HashSet<>(Arrays.asList(args));
            boolean listFlag = argsSet.contains("-l");

            StringBuilder builder = new StringBuilder();
            File[] files = file.listFiles();
            if (isDirEmpty(files)) {
                return new HandleResult("There are no files in here");
            }
            for (File file : files) {
                if (listFlag) {
                    builder.append(file.isDirectory() ? "d" : "-")
                        .append(file.canRead() ? "r" : "-")
                        .append(file.canWrite() ? "w" : "-")
                        .append(file.canExecute() ? "x" : "-")
                        .append("\t")
                        .append(Instant.ofEpochMilli(file.lastModified()))
                        .append("\t")
                        .append(file.isDirectory() ? "-" : file.length())
                        .append("\t");
                }

                builder.append(file.getName())
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
            HandleResult argsCheckResult = checkArgsNum(args, 1);
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
            //todo support -p (chain of dirs)
            HandleResult argsCheckResult = checkArgsNum(args, 1);
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
            HandleResult argsCheckResult = checkArgsNum(args, 1);
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
        handlers.put("mv", args -> {
            HandleResult argsCheckResult = checkArgsNum(args, 2);
            if (argsCheckResult != null) {
                return argsCheckResult;
            }
            String sourcePath = args[0];
            String targetPath = args[1];
            File source = new File(file, sourcePath);
            File target = new File(file, targetPath);
            if (!source.exists()) {
                return new HandleResult("Sourse file doesn't exist");
            }
            if (target.exists()) {
                return new HandleResult("Can't move. Target file already exists");
            }

            if (source.renameTo(target)) {
                return new HandleResult("File moved");
            } else {
                return new HandleResult("Something wrong");
            }
        });
        handlers.put("rm", args -> {
            //todo support -r
            HandleResult argsCheckResult = checkArgsNum(args, 1);
            if (argsCheckResult != null) {
                return argsCheckResult;
            }
            File rmFile = new File(file, args[0]);
            if (!rmFile.exists()) {
                return new HandleResult("File doesn't exist");
            }
            if (rmFile.isDirectory() && !isDirEmpty(rmFile.listFiles())) {
                return new HandleResult("Can't remove. Directory isn't empty");
            }
            if (rmFile.delete()) {
                return new HandleResult("File removed");
            } else {
                return new HandleResult("Something wrong");
            }
        });
        handlers.put("cp", args -> {
            HandleResult argsCheckResult = checkArgsNum(args, 2);
            if (argsCheckResult != null) {
                return argsCheckResult;
            }
            File sourceFile = new File(file, args[0]);
            File targetFile = new File(file, args[1]);

            if (!sourceFile.exists()) {
                return new HandleResult("Source file doesn't exist");
            }
            if (!sourceFile.isFile()) {
                return new HandleResult("Can't copy. Not a file");
            }
            try {
                if (!targetFile.createNewFile()) {
                    return new HandleResult("Target file already exists");
                }
            } catch (IOException e) {
                return new HandleResult("Can't create file: " + e);
            }

            try (FileInputStream in = new FileInputStream(sourceFile);
                 FileOutputStream out = new FileOutputStream(targetFile)) {
                out.write(Utils.str(in).getBytes());
            } catch (Exception e) {
                return new HandleResult("Operation failed: " + e);
            }

            return new HandleResult("File copied");
        });
        handlers.put("cat", args -> {
            HandleResult argsCheckResult = checkArgsNum(args, 1);
            if (argsCheckResult != null) {
                return argsCheckResult;
            }
            String path = args[0];
            File openFile = new File(file, path);
            if (!openFile.exists()) {
                return new HandleResult("File doesn't exist");
            }
            if (!openFile.isFile()) {
                return new HandleResult("Not a file");
            }
            try (FileInputStream in = new FileInputStream(openFile)) {
                return new HandleResult(Utils.str(in));
            } catch (IOException e) {
                return new HandleResult("Operation failed: " + e);
            }
        });

        //todo support additional arguments

        //todo tests
    }

    private boolean isDirEmpty(File[] files) {
        return files == null || files.length == 0;
    }

    private HandleResult checkArgsNum(String[] args, int num) {
        if (args.length < num) {
            return new HandleResult("Need more arguments");
        }
        return null;
    }
}
