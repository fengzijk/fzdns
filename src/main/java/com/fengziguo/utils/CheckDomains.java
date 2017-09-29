package com.fengziguo.utils;

import com.fengziguo.jdns.Jdns2Ali;
import com.sun.xml.internal.bind.v2.TODO;

import java.net.InetAddress;
import java.util.*;

/**
 * -------------------------------------------------
 *
 * @project :fzdns
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日13:01
 * @修改 :  who   when    what
 * --------------------------------------------------
 */
public class CheckDomains extends Thread  {
        @Override
        public void run() {
            loadDdns();
        }
      public void loadDdns() {
          String ddnsip = DoMains2Ip.getServerIP(LoadConf.ddnsDomains);
          List<String> list = new ArrayList();
          if (list == null || list.isEmpty()) {
              list.add(ddnsip);
              Jdns2Ali.DescribeDomainRecords();
          } else {
              for (int i = 0; i < list.size(); i++) {
                if(list.get(i).equals(ddnsip)){
                    list.remove(list.get(i));
                   break;
                }else{
                    list.add(ddnsip);
                    //修改阿里云解析dns步骤
                    //TODO
                    Jdns2Ali.DescribeDomainRecords();

                  }
              }
          }

      }
}



