package com.peter.class14;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * @Author : Peter
 * @Filename : CalculatorMultiTest
 * @Description :
 * @Date : 20:35 2022/9/6
 */

@RunWith(Parameterized.class)
public class CalculatorMultiTest {

    private static Calculator calculator;

    private int num01;
    private int num02;
    private int resMulti;
    private String casename;

    public CalculatorMultiTest(int num01, int num02, int resMulti, String casename) {
        this.num01 = num01;
        this.num02 = num02;
        this.resMulti = resMulti;
        this.casename = casename;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        calculator = new Calculator();
    }

    @org.junit.Test
    public void multi() {
        int multi = calculator.multi(num01, num02);
        System.out.println(multi);
        Assert.assertEquals(resMulti, multi);
    }

    @Parameterized.Parameters(name = "{3}")
    public static Object[][] calculatorParas() {
        return new Object[][]{
                {18, 2, 36, "NumOneBiggerThanNumTwo"},
                {2, 18, 36, "NumOneSmallerThanNumTwo"},
                {-10, -15, 150, "NumOneAndNumTwoAreLowerThanZero"},
                {-36, 66, -2376, "NumOneIsLowerThanZero"},
                {36, -16, -576, "NumTwoIsLowerThanZero"}
        };
    }

}