package com.peter.class14;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * @Author : Peter
 * @Filename : CalculatorSumTest
 * @Description :
 * @Date : 20:32 2022/9/6
 */

@RunWith(Parameterized.class)
public class CalculatorSumTest {
    private static Calculator calculator;

    private int num01;
    private int num02;
    private int resSum;
    private String casename;

    public CalculatorSumTest(int num01, int num02, int resSum, String casename) {
        this.num01 = num01;
        this.num02 = num02;
        this.resSum = resSum;
        this.casename = casename;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        calculator = new Calculator();
    }

    @org.junit.Test
    public void sum() {
        int sum = calculator.sum(num01, num02);
        System.out.println(sum);
        Assert.assertEquals(resSum, sum);
    }

    @Parameterized.Parameters(name = "{3}")
    public static Object[][] calculatorParas() {
        return new Object[][]{
                {18, 2, 20, "NumOneBiggerThanNumTwo"},
                {2, 18, 20, "NumOneSmallerThanNumTwo"},
                {-10, -15, -25, "NumOneAndNumTwoAreLowerThanZero"},
                {-36, 66, 30, "NumOneIsLowerThanZero"},
                {36, -16, 20, "NumTwoIsLowerThanZero"}
        };
    }


}