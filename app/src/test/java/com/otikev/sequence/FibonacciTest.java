package com.otikev.sequence;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by kevin on 22/03/19 at 00:11
 */
public class FibonacciTest {

    Fibonacci fibonacci;

    @Before
    public void setUp() throws Exception {
        fibonacci = new Fibonacci();
    }

    @Test
    public void generateRecursive() {
        List<Long> expected = Arrays.asList(0L,1L,1L,2L,3L,5L,8L,13L);

        List<Long> actual = fibonacci.generateRecursive(8);

        assertEquals(expected,actual);
    }

    @Test
    public void generateIterative() {
        List<Long> expected = Arrays.asList(0L,1L,1L,2L,3L,5L,8L,13L);

        List<Long> actual = fibonacci.generateIterative(8);

        assertEquals(expected,actual);
    }
}