package org.github.immess.ch4;

import java.util.Arrays;

public class Ch4 {
    private static void primeNumbers(int ceil) {
        System.out.println("Prime numbers from 1 to " + ceil + " non-including:");
        for (int i = 1; i < ceil; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                System.out.print(i + " ");
            }
        }
        System.out.println(" ");
    }

    private static void fibonacci(int ceil) {
        System.out.println("Fibonacci sequence of " + ceil + " elements:");
        int a = 0;
        int b = 1;
        System.out.print(b + " ");
        for (int i = 0; i < ceil - 1; ++i) {
            int c = a + b;
            a = b;
            b = c;
            System.out.print(b + " ");
        }
        System.out.println(" ");
    }

    private static void vampireNumbers() {
        System.out.println("4-digits vampire numbers:");
        for (int i = 1000; i < 10000; ++i) {
            if (isVampire(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isVampire(int number) {
        for (int i = 1; i < 100; ++i) {
            for (int j = 1; j < 100; ++j) {
                if (i * j == number) {
                    if (checkFangs(i, j, number)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkFangs(int fang1, int fang2, int number) {
        if (fang1 % 10 == 0 && fang2 % 10 == 0) {
            return false;
        }
        int[] a = new int[]{fang1 % 10, fang1 / 10, fang2 % 10, fang2 / 10};
        int[] b = new int[]{number / 1000, number / 100 % 10, number / 10 % 10, number % 10};
        Arrays.sort(a);
        Arrays.sort(b);
        return Arrays.equals(a, b);
    }

    public static void main(String[] args) {
        primeNumbers(31);
        fibonacci(5);
        vampireNumbers();
    }
}
