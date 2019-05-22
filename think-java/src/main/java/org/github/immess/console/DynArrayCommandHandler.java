package org.github.immess.console;

import org.github.immess.structure.DynArray;

import java.util.Arrays;

public class DynArrayCommandHandler extends SimpleCommandHandler {
    private final DynArray array = new DynArray();

    @Override
    protected String doActualHandle(String command, String[] args) {
        switch (command) {
            case "push": {
                array.push(Integer.parseInt(args[0]));
                return "Pushed element: " + args[0];
            }
            case "get":
                return String.format("Got element `%d` at index `%s`", array.get(Integer.parseInt(args[0])), args[0]);
            case "remove":
                return String.format("Removed element `%d` at index `%s`", array.remove(Integer.parseInt(args[0])), args[0]);
            case "insert":
                array.insert(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                return String.format("Insert element `%s` at index `%s`", args[0], args[1]);
            case "size":
                return "Size is " + array.size();
            case "list":
                return getName() + ": " + Arrays.toString(array.toArray());
            default:
                return "Error: unknown command";
        }
    }

    @Override
    public String getName() {
        return "DynArray";
    }
}
