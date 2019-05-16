package org.github.immess.ch2;

import java.util.Arrays;

public class HelloWorld {

    public static void main(String[] args) {
        String user = args.length > 0 ? args[0] : "stranger";
        System.out.println("Hello " + user + "!");
//        System.out.println(Arrays.toString(args));
    }
}
