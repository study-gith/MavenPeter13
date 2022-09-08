package com.peter.class14;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * @Author : Peter
 * @Filename : CalculatorMinusTest
 * @Description :
 * @Date : 20:33 2022/9/6
 */

@RunWith(Parameterized.class)
public class CalculatorMinusTest {

    private static Calculator calculator;

    private int num01;
    private int num02;
    private int resMinus;
    private String casename;

    public CalculatorMinusTest(int num01, int num02, int resMinus, String casename) {
        this.num01 = num01;
        this.num02 = num02;
        this.resMinus = resMinus;
        this.casename = casename;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        calculator = new Calculator();
    }

    @org.junit.Test
    public void minus() {
        int minus = calculator.minus(num01, num02);
        System.out.println(minus);
        Assert.assertEquals(resMinus, minus);
    }

    @Parameterized.Parameters(name = "{3}")
    public static Object[][] calculatorParas() {
        return new Object[][]{
                {18, 2, 16, "NumOneBiggerThanNumTwo"},
                {2, 18, -16, "NumOneSmallerThanNumTwo"},
                {-10, -15, 5, "NumOneAndNumTwoAreLowerThanZero"},
                {-36, 66, -102, "NumOneIsLowerThanZero"},
                {36, -16, 52, "NumTwoIsLowerThanZero"}
        };
    }

}