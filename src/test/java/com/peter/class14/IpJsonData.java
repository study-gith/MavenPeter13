package com.peter.class14;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : Peter
 * @Filename : IpJsonData
 * @Description :
 * @Date : 8/29/2022
 */


public class IpJsonData {
    String ipString = "jQuery110205420226215397559_1640165829451({\"Srcid\":\"5809\",\"ResultCode\":\"0\",\"status\":\"0\",\"QueryID\":\"1657249693\",\"Result\":[{\"DisplayData\":{\"strategy\":{\"tempName\":\"ip\",\"precharge\":\"0\",\"ctplOrPhp\":\"1\"},\"resultData\":{\"tplData\":{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"58.64.251.110\",\"origipquery\":\"58.64.251.110\",\"query\":\"58.64.251.110\",\"origip\":\"58.64.251.110\",\"location\":\"\",\"userip\":\"\",\"showlamp\":\"1\",\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\",\"data_source\":\"AE\"},\"extData\":{\"tplt\":\"ip\",\"resourceid\":\"5809\",\"OriginQuery\":\"58.64.251.110\"}}},\"ResultURL\":\"http:\\/\\/www.ip138.com\\/\",\"Weight\":\"2\",\"Sort\":\"1\",\"SrcID\":\"5809\",\"ClickNeed\":\"0\",\"SubResult\":[],\"SubResNum\":\"0\",\"ar_passthrough\":[],\"RecoverCacheTime\":\"0\"}],\"data\":[{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"58.64.251.110\",\"origipquery\":\"58.64.251.110\",\"query\":\"58.64.251.110\",\"origip\":\"58.64.251.110\",\"location\":\"\",\"userip\":\"\",\"showlamp\":\"1\",\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\"}],\"ResultNum\":\"1\"})";

    public IpJsonData() {
    }

    public IpJsonData(String ipString) {
        this.ipString = ipString;
    }

    public String getIpString() {
        return ipString;
    }

    public void setIpString(String ipString) {
        this.ipString = ipString;
    }

    //?????????????????????????????????????????????callback??????
    public String jsonData01() {
        String ipSubstring = ipString.substring(ipString.indexOf("(") + 1, ipString.length() - 1);
        return ipSubstring;
    }

    //??????????????????????????????????????????callback??????
    public String jsonData02() {
        Pattern pattern = Pattern.compile(".*?\\((?<ipString>.*?)\\)");
        Matcher matcher = pattern.matcher(ipString);
        matcher.find();
        String ipRegexString = matcher.group("ipString");
        return ipRegexString;
    }

    //????????????????????????data??????Json???????????????Json????????????????????????
    public String subData01() {
        String s = jsonData02();
        Pattern pattern = Pattern.compile(".*?,\"data\".*?(?<subData>[{].*?[}])");
        Matcher matcher = pattern.matcher(s);
        matcher.find();
        String subData = matcher.group("subData");
        return subData;
    }

    //???JSONArray??????data??????Json???????????????Json????????????????????????
    public String subData02() {
        JSONObject s = JSON.parseObject(jsonData01());
        JSONArray dataJSONArray = JSONArray.parseArray(s.get("data").toString());
        String subData = dataJSONArray.get(0).toString();
        return subData;
    }

    //???JSONPath??????data??????Json???????????????Json????????????????????????
    public String subData03() {
        String s = jsonData01();
        String subData = JSONPath.read(s, "$.data[0]").toString();
        return subData;
    }

    //?????????????????????data??????Json?????????Map??????
    public Map<String, String> dataJsonMap() {
        Map<String, String> subDataJsonMap = new HashMap<>();
        JSONObject subDataJSONObject01 = JSON.parseObject(subData01());
        JSONObject subDataJSONObject02 = JSON.parseObject(subData02());
        JSONObject subDataJSONObject03 = JSON.parseObject(subData03());
        for (String key : subDataJSONObject03.keySet()) {
            subDataJsonMap.put(key, subDataJSONObject03.get(key).toString());
        }
        return subDataJsonMap;
    }

    //??????????????????????????????Map???Map?????????????????????JSONObject?????????????????????put????????????????????????????????????JSONObject??????
    public JSONObject dataMapJson01() {
        Map<String, String> stringStringMap = dataJsonMap();
        JSONObject jsonObject = new JSONObject();
        for (String key : stringStringMap.keySet()) {
            jsonObject.put(key, stringStringMap.get(key));
        }
        return jsonObject;
    }

    //????????????????????????Map???Map?????????????????????JSONObject?????????????????????????????????????????????????????????????????????JSONObject??????
    public JSONObject dataMapJson02() {
        Map<String, String> stringStringMap = dataJsonMap();
        String subDataString = "{";
        for (String key : stringStringMap.keySet()) {
            //???????????????????????????????????????????????????????????????????????????????????????????????????
            if (stringStringMap.get(key) instanceof String) {
                subDataString += String.format("\"%s\":\"%s\",", key, stringStringMap.get(key));
            } else {
                subDataString += String.format("\"%s\":%s,", key, stringStringMap.get(key));
            }
        }
        return JSON.parseObject(subDataString.substring(0, subDataString.length() - 1) + "}");
    }

    public static class UnicodeUtil {
        //??????
        public String unicodeToString(String unicode) {
            if (unicode == null || "".equals (unicode)) {
                return null;
            }
            StringBuilder sb = new StringBuilder();
            int i = -1;
            int pos = 0;
            while ((i = unicode.indexOf("\\u", pos)) != -1) {
                sb.append(unicode.substring(pos, i));
                if (i + 5 < unicode.length()) {
                    pos = i + 6;
                    sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
                }
            }
            return sb.toString();
        }

        //??????
        public String stringToUnicode(String string) {
            if (string == null || "".equals(string)) {
                return null;
            }
            StringBuffer unicode = new StringBuffer();
            for (int i = 0; i < string.length(); i++) {
                char c = string.charAt(i);
                unicode.append("\\u" + Integer.toHexString(c));
            }
            return unicode.toString();
        }

        public void main(String[] args) {
            String s = stringToUnicode("??????");
            System.out.println("?????????" + s);
            String s1 = stringToUnicode(s);
            System.out.println("?????????" + s1);

        }

    }


    public static void main(String[] args) {
        IpJsonData ipJsonData = new IpJsonData();
        System.out.println(ipJsonData.jsonData01());
        System.out.println();
        System.out.println(ipJsonData.jsonData02());
        System.out.println();
        System.out.println(ipJsonData.subData01());
        System.out.println();
        System.out.println(ipJsonData.subData02());
        System.out.println();
        System.out.println(ipJsonData.subData03());
        System.out.println();
        System.out.println(ipJsonData.dataJsonMap());
        System.out.println();
        System.out.println(ipJsonData.dataMapJson01());
        System.out.println();
        System.out.println(ipJsonData.dataMapJson02());
        System.out.println();
        System.out.println("==================================?????????==================================");
        System.out.println();
        ipJsonData.setIpString("jQuery110207095381618840191_1661514857449({\"Srcid\":\"5809\",\"ResultCode\":\"0\",\"status\":\"0\",\"QueryID\":\"2507184842\",\"Result\":[{\"DisplayData\":{\"strategy\":{\"tempName\":\"ip\",\"precharge\":\"0\",\"ctplOrPhp\":\"1\"},\"resultData\":{\"tplData\":{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\",\"origipquery\":\"3.3.3.3\",\"query\":\"3.3.3.3\",\"origip\":\"3.3.3.3\",\"location\":\"\\u7f8e\\u56fd \\u4e9a\\u9a6c\\u900a\\u4e91\",\"userip\":\"\",\"showlamp\":123,\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\",\"data_source\":\"AE\"},\"extData\":{\"tplt\":\"ip\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\"}}},\"ResultURL\":\"http:\\/\\/www.ip138.com\\/\",\"Weight\":\"2\",\"Sort\":\"1\",\"SrcID\":\"5809\",\"ClickNeed\":\"0\",\"SubResult\":[],\"SubResNum\":\"0\",\"ar_passthrough\":[],\"RecoverCacheTime\":\"0\"}],\"data\":[{\"srcid\":\"5809\",\"resourceid\":\"5809\",\"OriginQuery\":\"3.3.3.3\",\"origipquery\":\"3.3.3.3\",\"query\":\"3.3.3.3\",\"origip\":\"3.3.3.3\",\"location\":\"\\u7f8e\\u56fd \\u4e9a\\u9a6c\\u900a\\u4e91\",\"userip\":\"\",\"showlamp\":\"1\",\"tplt\":\"ip\",\"titlecont\":\"IP\\u5730\\u5740\\u67e5\\u8be2\",\"realurl\":\"http:\\/\\/www.ip138.com\\/\",\"showLikeShare\":\"1\",\"shareImage\":\"1\"}],\"ResultNum\":\"1\"})");
        System.out.println(ipJsonData.jsonData01());
        System.out.println();
        System.out.println(ipJsonData.jsonData02());
        System.out.println();
        System.out.println(ipJsonData.subData01());
        System.out.println();
        System.out.println(ipJsonData.subData02());
        System.out.println();
        System.out.println(ipJsonData.subData03());
        System.out.println();
        System.out.println(ipJsonData.dataJsonMap());
        System.out.println();
        System.out.println(ipJsonData.dataMapJson01());
        System.out.println();
        System.out.println(ipJsonData.dataMapJson02());
        System.out.println();
        System.out.println("==================================?????????==================================");
        System.out.println();
        IpJsonData ipJsonData01 = new IpJsonData();
        String ipString = ipJsonData01.jsonData02();
        System.out.println(ipString);
        System.out.println();
        //??????Unicode????????????
//        Pattern unicodePattern = Pattern.compile(".*?IP(?<stringChara>.*?)\"");
//        Matcher matcher = unicodePattern.matcher(ipString);
//        String afterReplace = "";
//        while (matcher.find()) {
//            String stringChara = matcher.group("stringChara");
//            IpJson.UnicodeUtil unicodeUtil = new IpJson.UnicodeUtil();
//            String s = unicodeUtil.unicodeToString(stringChara);
//            afterReplace = ipString.replace(stringChara, s);
//        }
//        System.out.println(afterReplace);
//        System.out.println();
//        JSONObject ipJsonObject=JSONObject.parseObject(afterReplace);
//
//        //???
//        System.out.println();
//        System.out.println("==============???==============");
//        System.out.println(ipJsonObject.get("QueryID"));
//        //???
//        System.out.println();
//        System.out.println("==============???==============");
//        ipJsonObject.put("QueryStatus", true);
//        System.out.println(ipJsonObject);
//        //???
//        System.out.println();
//        System.out.println("==============???==============");
//        ipJsonObject.put("QueryStatus", false);
//        System.out.println(ipJsonObject);
//        //???
//        System.out.println();
//        System.out.println("==============???==============");
//        ipJsonObject.remove("QueryStatus");
//        System.out.println(ipJsonObject);
//
//        System.out.println();
//        System.out.println("==============forEach??????==============");
//        for (String key : ipJsonObject.keySet()) {
//            System.out.println("?????????"+key+"????????????"+ipJsonObject.get(key));
//        }
//
//        //???????????????result???????????????JSONArray
//        JSONArray resultArray = (JSONArray)ipJsonObject.get("Result");
//        JSONObject resultObject =(JSONObject) resultArray.get(0);
//        JSONObject displayDataJSONObject=(JSONObject)resultObject.get("DisplayData");;
//        JSONObject resultDataJSONObject = (JSONObject )displayDataJSONObject.get("resultData");
//        JSONObject tplDataJSONObject = (JSONObject)resultDataJSONObject.get("tplData");
//        System.out.println();
//        System.out.println("==============??????IP????????????==============");
//        System.out.println("??????titlecont????????????"+tplDataJSONObject.get("titlecont"));


    }


}
