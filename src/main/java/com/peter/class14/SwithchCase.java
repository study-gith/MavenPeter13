package com.peter.class14;

/**
 * @Author : Peter
 * @Filename : SwithchCase
 * @Description :
 * @Date : 19:57 2022/9/6
 */


public class SwithchCase {
    String weather;

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
    public String switchTest(){
        String doSomething;
        switch (weather){
            case "晴天":
                doSomething="去爬山吧";
                break;
            case "下雨":
                doSomething="去打球吧";
                break;
            case  "阴天":
                doSomething="去看电影吧";
                break;
            case "下雪" :
                doSomething="去滑雪吧";
                break;
            default:
                doSomething="去野炊吧";
        }
        return doSomething;
    }
}
