package com.fengziguo;


import com.fengziguo.jdns.CheckDomains;
import org.apache.log4j.Logger;

import static com.fengziguo.utils.LoadConf.loadConf;


/**
 * -------------------------------------------------
 *
 * @project :fengzi
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日11:07
 * @修改 :  who   when    what
 * --------------------------------------------------
 */
public class JdnsMain {

    private static final Logger LOG = Logger.getLogger(JdnsMain.class);
    public static void main(String[] args) {
        try {
            loadConf(args[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            int i= Integer.parseInt(args[1]);
            while (true) {
                try {
                    CheckDomains xx =  new CheckDomains();
                    xx.run();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                try {
                   Thread.sleep(i * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);
        }
    }
}