package org.github.immess.console.Impl;

import org.github.immess.console.AbstractCommandHandler;
import org.github.immess.console.Command;
import org.github.immess.console.HandleResult;
import org.github.immess.structure.DynArray;

import java.util.Arrays;
import java.util.Map;

public class DynArrayCommandHandler extends AbstractCommandHandler {
    private final DynArray array = new DynArray();

    @Override
    public String getName() {
        return "DynArray";
    }

    @Override
    protected void defineCommands(Map<String, Command> handlers) {
        handlers.put("push", args -> {
            array.push(Integer.parseInt(args[0]));
            return new HandleResult("Pushed element: " + args[0]);
        });
        handlers.put("get", args -> new HandleResult(String.format("Got element `%d` at index `%s`", array.get(Integer.parseInt(args[0])), args[0])));
        handlers.put("remove", args -> new HandleResult(String.format("Removed element `%d` at index `%s`", array.remove(Integer.parseInt(args[0])), args[0])));
        handlers.put("insert", args -> {
            array.insert(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
            return new HandleResult(String.format("Insert element `%s` at index `%s`", args[0], args[1]));
        });
        handlers.put("size",args -> new HandleResult("Size is " + array.size()));
        handlers.put("list", args -> new HandleResult(getName() + ": " + Arrays.toString(array.toArray())));
    }
}
