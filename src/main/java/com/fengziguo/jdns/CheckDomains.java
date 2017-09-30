package com.fengziguo.jdns;


import com.fengziguo.utils.DoMains2Ip;
import com.fengziguo.utils.Jdns2Ali;
import com.fengziguo.utils.LoadConf;

import java.util.*;

/**
 * -------------------------------------------------
 *
 * @project :fzdns
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日13:01
 * @描述 : 修改  域名对应的IP
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
              Jdns2Ali.UpdateDomainRecord(Jdns2Ali.DescribeDomainRecords(),ddnsip);
          } else {
              for (int i = 0; i < list.size(); i++) {
                if(list.get(i).equals(ddnsip)){
                    list.remove(list.get(i));
                   break;
                }else{
                    list.add(ddnsip);
                    //修改阿里云解析dns步骤
                    Jdns2Ali.UpdateDomainRecord(Jdns2Ali.DescribeDomainRecords(),ddnsip);


                  }
              }
          }

      }
}



