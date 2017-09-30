package com.fengziguo.utils;


import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static jdk.nashorn.internal.objects.NativeString.trim;

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
    private static final Logger log = Logger.getLogger(LoadConf.class);
    public static void loadConf(String path) throws Exception {
        List<String>  list =new ArrayList();
        Properties props = new Properties();
        InputStream in = new FileInputStream(path);
        props.load(in);
        accessKeyId = props.getProperty("accessKeyId");
        list.add(accessKeyId);
        accessKeySecret = props.getProperty("accessKeySecret");
        list.add(accessKeySecret);
        ddnsDomains = props.getProperty("ddnsDomains");
        list.add(ddnsDomains);
        aliDomains =props.getProperty("aliDomains");
        list.add(aliDomains);
        //校验参数
        log.info("accessKeyId----->"+accessKeyId);
        log.info("accessKeySecret----->"+accessKeySecret);
        log.info("aliDomains----->"+aliDomains);
        log.info("ddnsDomains----->"+ddnsDomains);
        checkParams(list);
    }
    public  static void   checkParams(List<String> Vlist) throws Exception {
        String errmsg="";
        for (String value:Vlist){
            if (value == null) {
                errmsg = value +"IS NULL";
                log.error(errmsg);
                throw new Exception(errmsg);
            }
            value = trim(value);
            if (value.length() == 0) {
                errmsg = value +"IS empty";
                log.error(errmsg);
                throw new Exception(errmsg);
            }
        }
    }
}