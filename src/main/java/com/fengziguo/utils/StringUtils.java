package com.fengziguo.utils;

/**
 * -------------------------------------------------
 *
 * @project :fzdns
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日15:15
 * @修改 :  who   when    what
 * --------------------------------------------------
 */
public class StringUtils {
    public static boolean isEmpty(String value) {
        if (value == null) {
            return true;
        }
        value = trim(value);
        if (value.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 去除前后空格,以及字符串中换行等符号,字符串中连续空格,只保留一个
     * @param value
     * @return
     */
    public static String trimWrap(String value) {
        value = trim(value);
        char[] val = value.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        char pre = 0;
        for (char c : val) {
            if (c >= ' ' || c == '　') {
                if (!((pre == ' ' || pre == '　') && (c == ' ' || c == '　'))) {
                    stringBuffer.append(c);
                }
                pre = c;
            }
        }
        return stringBuffer.toString();
    }

    /**
     * 去除前后空格,包括中文空格
     * @param value
     * @return
     */
    public static String trim(String value) {
        if (value == null) {
            return "";
        }
        int len = value.length();
        int st = 0;
        char[] val = value.toCharArray();

        while ((st < len) && (val[st] <= ' ')) {
            st++;
        }
        while ((st < len) && (val[st] == '　')) {
            st++;
        }
        while ((st < len) && (val[len - 1] <= ' ')) {
            len--;
        }
        while ((st < len) && (val[len - 1] == '　')) {
            len--;
        }
        return ((st > 0) || (len < value.length())) ? value.substring(st, len) : value;
    }

}
