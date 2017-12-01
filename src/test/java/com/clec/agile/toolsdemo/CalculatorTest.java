package com.clec.agile.toolsdemo;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author clairezhang
 * @version Version 1.00
 * @date 2017/Nov/29
 */

public class CalculatorTest {
    @Test
    public void testCalculator() {
        int x = 1;
        int y = 1;

        int z = x + y;
        assertEquals("Test Test！", 2, z);
    }

    @Test
    public void testCalculatorSimple() {
        assertEquals("Test Test！",2, Calculator.add(1, 1));
    }


}