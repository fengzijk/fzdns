package com.fengziguo.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * -------------------------------------------------
 *
 * @project :fzdns
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日15:27
 * @描述 : 通过域名获取IP
 * --------------------------------------------------
 */
public class DoMains2Ip {
    private static InetAddress myServer;
    public static  String getServerIP(String domainName) {
        try {
            myServer = InetAddress.getByName(domainName);
        } catch (UnknownHostException e) {
        }
         return (myServer.getHostAddress());
    }
}
