package com.otikev.sequence;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 21/03/19 at 19:44
 */
public class Fibonacci {

    public List<Long> generateRecursive(long n) {
        List<Long> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            items.add(recursive(i));
        }
        return items;
    }

    public List<Long> generateIterative(long n) {
        List<Long> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            items.add(iterative(i));
        }
        return items;
    }

    private long recursive(int n) {
        if (n <= 1) {
            return n;
        } else {
            return recursive(n - 1) + recursive(n - 2);
        }
    }

    private static long iterative(int n) {
        int x = 1, y = 1, z;

        if (n == 0) {
            return 0;
        } else {
            for (int i = 3; i <= n; i++) {
                z = x + y;
                x = y;
                y = z;
            }
            return y;
        }
    }
}
