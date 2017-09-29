package com.fengziguo;

import com.fengziguo.utils.LoadConf;
import com.fengziguo.utils.CheckDomains;
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
        //初始化配置信息
        try {
            loadConf(args[0]);
            while (true) {
                try {
                    CheckDomains xx =  new CheckDomains();
                    xx.run();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                try {
                   Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(e);
        }

        System.out.println(LoadConf.accessKeyId+"ggggggg");

    }
}