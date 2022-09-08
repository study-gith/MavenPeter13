package com.peter.class14;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * @Author : Peter
 * @Filename : CalculatorDivTest
 * @Description :
 * @Date : 20:35 2022/9/6
 */

@RunWith(Parameterized.class)
public class CalculatorDivTest {

    private static Calculator calculator;

    private int num01;
    private int num02;
    private int resDiv;
    private String casename;

    public CalculatorDivTest(int num01, int num02, int resDiv, String casename) {
        this.num01 = num01;
        this.num02 = num02;
        this.resDiv = resDiv;
        this.casename = casename;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        calculator = new Calculator();
    }

    @org.junit.Test
    public void div() {
        int div = calculator.div(num01, num02);
        System.out.println(div);
        Assert.assertEquals(resDiv, div);
    }

    @Parameterized.Parameters(name = "{3}")
    public static Object[][] calculatorParas() {
        return new Object[][]{
                {18, 2, 9, "NumOneBiggerThanNumTwo"},
                {2, 18, 0, "NumOneSmallerThanNumTwo"},
                {-30, -15, 2, "NumOneAndNumTwoAreLowerThanZero"},
                {-36, 66, 0, "NumOneIsLowerThanZero"},
                {36, -16, -2, "NumTwoIsLowerThanZero"},
                {36, 0, 0, "NumTwoIsEqualsZero"}
        };
    }

}