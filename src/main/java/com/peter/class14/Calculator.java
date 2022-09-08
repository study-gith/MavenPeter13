package com.peter.class14;

/**
 * @Author : Peter
 * @Filename : Calculator
 * @Description :
 * @Date : 18:25 2022/9/6
 */


public class Calculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.sum(5, 6));
        System.out.println(calculator.minus(5, 6));
        System.out.println(calculator.multi(5, 6));
        System.out.println(calculator.div(5, 6));
        System.out.println(calculator.div(5, 0));
        System.out.println(calculator.div(0, 6));
        System.out.println(calculator.div(66, 6));
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public int minus(int a, int b) {
        return a - b;
    }

    public int multi(int a, int b) {
        return a * b;
    }

    public int div(int a, int b) {
        int res = 0;
        try {
            res = a / b;
        } catch (Exception e) {
            res=0;
        }
        return res;
    }

}
