package com.peter.class14;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * @Author : Peter
 * @Filename : CalculatorTest
 * @Description :
 * @Date : 18:37 2022/9/6
 */

@RunWith(Parameterized.class)
public class CalculatorTest {

    private static Calculator calculator;

    private int num01;
    private int num02;
    private int resSum;
    private int resMinus;
    private int resMulti;
    private int resDiv;
    private String casename;

    public CalculatorTest(int num01, int num02, int resSum, int resMinus, int resMulti, int resDiv, String casename) {
        this.num01 = num01;
        this.num02 = num02;
        this.resSum = resSum;
        this.resMinus = resMinus;
        this.resMulti = resMulti;
        this.resDiv = resDiv;
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

    @org.junit.Test
    public void minus() {
        int minus = calculator.minus(num01, num02);
        System.out.println(minus);
        Assert.assertEquals(resMinus, minus);
    }

    @org.junit.Test
    public void multi() {
        int multi = calculator.multi(num01, num02);
        System.out.println(multi);
        Assert.assertEquals(resMulti, multi);
    }

    @org.junit.Test
    public void div() {
        int div = calculator.div(num01, num02);
        System.out.println(div);
        Assert.assertEquals(resDiv, div);
    }

    @Parameterized.Parameters(name = "{6}")
    public static Object[][] calculatorParas() {
        return new Object[][]{
                {18, 2, 20, 16, 36, 9,"NumOneBiggerThanNumTwo"},
                {2, 18, 20, -16, 36, 0,"NumOneSmallerThanNumTwo"},
                {36, 0, 36, 36, 0, 0,"NumTwoEqualsZero"}
        };
    }
}