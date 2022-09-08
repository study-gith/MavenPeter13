package com.peter.class14;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

/**
 * @Author : Peter
 * @Filename : CalculatorTestSuite
 * @Description :
 * @Date : 20:36 2022/9/6
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({CalculatorSumTest.class,CalculatorMinusTest.class,CalculatorMultiTest.class,CalculatorDivTest.class})
public class CalculatorTestSuite {

}