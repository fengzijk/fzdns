package com.fengziguo.utils;



import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * -------------------------------------------------
 *
 * @project :fzdns
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日12:56
 * @修改 :  who   when    what
 * @说明 :  读取 jar 外部配置文件
 * --------------------------------------------------
 */


public class LoadConf {

    public static  String accessKeyId;
    public static String accessKeySecret;
    //ddns的二级域名
    public static String ddnsDomains;
    //阿里云的顶级域名
    public static String aliDomains;
    //配置文件的路径
    private static String path;
    public LoadConf(String path) {
        this.path=path;
    }

    static {
        try {
            loadConf(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void loadConf(String path) throws Exception {
        Properties props = new Properties();
        InputStream in = new FileInputStream(path);
        props.load(in);
         accessKeyId = props.getProperty("accessKeyId");
        accessKeySecret = props.getProperty("accessKeySecret");
        ddnsDomains = props.getProperty("ddnsDomains");
        aliDomains =props.getProperty("aliDomains");
        System.out.println(ddnsDomains);
        if (StringUtils.isEmpty(accessKeySecret)) {
            String errmsg = "accessKeySecret is null";

            throw new Exception(errmsg);
        }else if (StringUtils.isEmpty(accessKeyId)) {
            String errmsg = "accessKeyId null";

            throw new Exception(errmsg);
        }

    }
}