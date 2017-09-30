package com.fengziguo.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alidns.model.v20150109.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * -------------------------------------------------
 *
 * @project :fzdns
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日14:22
 * @描述 :
 * --------------------------------------------------
 */
public class Jdns2Ali {
    private static final Logger log = Logger.getLogger(Jdns2Ali.class);
    private static IAcsClient client = null;
    static {
        String regionId = "cn-hangzhou"; //必填固定值，必须为“cn-hanghou”
        IClientProfile profile = DefaultProfile.getProfile(regionId, LoadConf.accessKeyId, LoadConf.accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }
    public static void UpdateDomainRecord(List<DescribeDomainRecordsResponse.Record> list,String ddsip){
        for (int i=0;i<list.size();i++){
            UpdateDomainRecordRequest request = new UpdateDomainRecordRequest();
            UpdateDomainRecordResponse response;
            request.setProtocol(ProtocolType.HTTPS); //指定访问协议
            request.setAcceptFormat(FormatType.JSON); //指定api返回格式
            request.setMethod(MethodType.POST); //指定请求方法
            request.setRecordId(list.get(i).getRecordId());
            request.setRR(list.get(i).getRR());
            request.setType(list.get(i).getType());
            request.setValue(ddsip);
            request.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
           if(!list.get(i).getValue().equals(ddsip)){
               try {
                   response = client.getAcsResponse(request);
                   if(response.getRecordId()!=null){
                       log.info("修改成功");
                   }
               } catch (ServerException e) {
                   e.printStackTrace();
               } catch (ClientException e) {
                   e.printStackTrace();
               }
           }
        }

    }
    public static List<DescribeDomainRecordsResponse.Record> DescribeDomainRecords() {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        DescribeDomainRecordsResponse response;
        request.setProtocol(ProtocolType.HTTPS); //指定访问协议
        request.setAcceptFormat(FormatType.JSON); //指定api返回格式
        request.setMethod(MethodType.POST); //指定请求方法
        request.setDomainName(LoadConf.aliDomains);
        request.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        List<DescribeDomainRecordsResponse.Record> list=null;
        try {
            response = client.getAcsResponse(request);
            list = response.getDomainRecords();
            for (DescribeDomainRecordsResponse.Record Record : list) {
                /*log.info("域名::"+Record.getDomainName());
                log.info("记录::"+Record.getRR());
                log.info("类型::"+Record.getType());
                log.info("IP-->"+Record.getValue());*/
                log.info("域名::"+Record.getDomainName()+"-"+"记录:"+Record.getRR()+"-"+"类型:"+Record.getType()+"-"+"IP:"+Record.getValue());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return  list;
    }
}
