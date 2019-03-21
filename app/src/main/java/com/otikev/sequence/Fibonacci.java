package com.otikev.sequence;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 21/03/19 at 19:44
 */
public class Fibonacci {

    private long fibbonacci(int n) {
        Log.i(getClass().getSimpleName(),""+n);
        if (n <= 1) {
            return n;
        } else {
            return fibbonacci(n - 1) + fibbonacci(n - 2);
        }
    }

    public List<Long> generate(long n) {
        List<Long> items = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            items.add(fibbonacci(i));
        }
        return items;
    }
}
