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

        handlers.put("ls", args -> new HandleResult(listFiles(args)));
        handlers.put("pwd", args -> new HandleResult(getCurrentDir()));
        handlers.put("cd", args -> new HandleResult(changeDir(args)));
        handlers.put("mkdir", args -> new HandleResult(makeDir(args)));
        handlers.put("touch", args -> new HandleResult(createFile(args)));
        handlers.put("mv", args -> new HandleResult(moveFile(args)));
        handlers.put("rm", args -> new HandleResult(remove(args)));
        handlers.put("cp", args -> new HandleResult(copyFile(args)));
        handlers.put("cat", args -> new HandleResult(printContent(args)));

        //todo tests
        //todo extract functions to class
    }

    private String listFiles(String[] args) {
        Set<String> argsSet = new HashSet<>(Arrays.asList(args));
        boolean listFlag = argsSet.contains("-l");

        StringBuilder builder = new StringBuilder();
        File[] files = file.listFiles();
        if (isDirEmpty(files)) {
            return "There are no files in here";
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
        return builder.toString();
    }

    private String getCurrentDir() {
        try {
            return file.getCanonicalPath();
        } catch (IOException e) {
            return ("Operation failed: " + e.getMessage());
        }
    }

    private String changeDir(String[] args) {
        String argsCheckResult = checkArgsNum(args, 1);
        if (argsCheckResult != null) {
            return argsCheckResult;
        }
        String path = args[0];
        File nextFile = new File(file, path);
        if (!nextFile.exists()) {
            return "Path doesn't exist";
        }
        if (!nextFile.isDirectory()) {
            return "Not a directory";
        }

        try {
            file = nextFile.getCanonicalFile();
        } catch (IOException e) {
            return "Can't change dir: " + e.getMessage();
        }

        return "Changed dir to " + file.getPath();
    }

    private String makeDir(String[] args) {
        Set<String> argsSet = new HashSet<>(Arrays.asList(args));
        boolean chainFlag = argsSet.contains("-p");

        String argsCheckResult = checkArgsNum(args, 1);
        if (argsCheckResult != null) {
            return argsCheckResult;
        }

        String path = args[args.length - 1];
        File newDir = new File(file, path);
        if (newDir.exists()) {
            return "Directory already exists";
        }
        if (chainFlag) {
            if (!newDir.mkdirs()) {
                return "Can't create dir";
            }
        } else {
            if (!newDir.mkdir()) {
                return "Can't create dir";
            }
        }
        try {
            return "Created dir " + newDir.getCanonicalPath();
        } catch (IOException e) {
            return "Operation failed: " + e.getMessage();
        }
    }

    private String createFile(String[] args) {
        String argsCheckResult = checkArgsNum(args, 1);
        if (argsCheckResult != null) {
            return argsCheckResult;
        }

        String path = args[0];
        File newFile = new File(file, path);
        try {
            if (!newFile.createNewFile()) {
                return "File already exist";
            }
        } catch (IOException e) {
            return "Can't create file: " + e.getMessage();
        }
        return "Created file " + newFile.getName();
    }

    private String moveFile(String[] args) {
        String argsCheckResult = checkArgsNum(args, 2);
        if (argsCheckResult != null) {
            return argsCheckResult;
        }
        String sourcePath = args[0];
        String targetPath = args[1];
        File source = new File(file, sourcePath);
        File target = new File(file, targetPath);
        if (!source.exists()) {
            return "Source file doesn't exist";
        }
        if (target.exists()) {
            return "Can't move. Target file already exists";
        }

        if (source.renameTo(target)) {
            return "File moved";
        } else {
            return "Something wrong";
        }
    }

    private String remove(String[] args) {
        Set<String> argsSet = new HashSet<>(Arrays.asList(args));
        boolean recurFlag = argsSet.contains("-r");

        String argsCheckResult = checkArgsNum(args, 1);
        if (argsCheckResult != null) {
            return argsCheckResult;
        }

        File rmFile = new File(file, args[args.length - 1]);
        if (!rmFile.exists()) {
            return "File doesn't exist";
        }

        if (fileDeleted(rmFile, recurFlag)) {
            return "File removed";
        } else {
            return "Operation failed";
        }
    }

    private boolean fileDeleted(File delFile, boolean flag) {
        File[] fileList = delFile.listFiles();
        if (delFile.isFile()) {
            return delFile.delete();
        } else {
            if (isDirEmpty(fileList)) {
                return delFile.delete();
            } else if (!flag) {
                return false;
            } else {
                for (File file : fileList) {
                    fileDeleted(file, true);
                }
                return delFile.delete();
            }
        }
    }

    private String copyFile(String[] args) {
        String argsCheckResult = checkArgsNum(args, 2);
        if (argsCheckResult != null) {
            return argsCheckResult;
        }
        File sourceFile = new File(file, args[0]);
        File targetFile = new File(file, args[1]);

        if (!sourceFile.exists()) {
            return "Source file doesn't exist";
        }
        if (!sourceFile.isFile()) {
            return "Can't copy. Not a file";
        }
        try {
            if (!targetFile.createNewFile()) {
                return "Target file already exists";
            }
        } catch (IOException e) {
            return "Can't create file: " + e;
        }

        try (FileInputStream in = new FileInputStream(sourceFile);
             FileOutputStream out = new FileOutputStream(targetFile)) {
            out.write(Utils.str(in).getBytes());
        } catch (Exception e) {
            return "Operation failed: " + e;
        }

        return "File copied";
    }

    private String printContent(String[] args) {
        String argsCheckResult = checkArgsNum(args, 1);
        if (argsCheckResult != null) {
            return argsCheckResult;
        }
        String path = args[0];
        File openFile = new File(file, path);
        if (!openFile.exists()) {
            return "File doesn't exist";
        }
        if (!openFile.isFile()) {
            return "Not a file";
        }
        try (FileInputStream in = new FileInputStream(openFile)) {
            return Utils.str(in);
        } catch (IOException e) {
            return "Operation failed: " + e;
        }
    }

    private boolean isDirEmpty(File[] files) {
        return files == null || files.length == 0;
    }
}
