package com.kokonetworks.myapplication;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by kevin on 21/03/19 at 18:24
 */
public class FibbonaciTest {

    Fibbonaci fibbonaci;

    @Before
    public void setup() {
        fibbonaci = new Fibbonaci();
    }

    @Test
    public void fibbonaci(){
        fibbonaci.fibbonaci(0);
    }
}
