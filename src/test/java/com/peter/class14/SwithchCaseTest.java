package com.peter.class14;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * @Author : Peter
 * @Filename : SwithchCaseTest
 * @Description :
 * @Date : 20:06 2022/9/6
 */

@RunWith(Parameterized.class)
public class SwithchCaseTest {
    private static SwithchCase swithchCase;

    private String weather;
    private String respecValue;
    private String casename;

    public SwithchCaseTest(String weather, String respecValue, String casename) {
        this.weather = weather;
        this.respecValue = respecValue;
        this.casename = casename;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        swithchCase=new SwithchCase();
    }

    @Test
    public void switchTest() {
    }
    @Parameterized.Parameters(name="{2}")
    public static Object[][] switchTestParas(){
        return new Object[][]{
                {"下雪","去滑雪吧","Snowy"},
                {"晴天","去爬山吧","Sunny"},
                {"多云","去野炊吧","Cloudy"},
                {"下雨","去打球吧","Rainy"}
        };
    }
}