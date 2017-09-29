package com.fengziguo.jdns;

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

import java.util.List;

/**
 * -------------------------------------------------
 *
 * @project :fzdns
 * @作者 :fengzijk
 * @email :guozhifengvip@163.com
 * @时间 : 2017年09月29日14:22
 * @修改 :  who   when    what
 * --------------------------------------------------
 */
public class Jdns2Ali {
    private static IAcsClient client = null;
    static {
        String regionId = "cn-hangzhou"; //必填固定值，必须为“cn-hanghou”
        String accessKeyId = "LTAIKhPQCNYWjeuj"; // your accessKey
        String accessKeySecret = "1GHwrmSd5w2CaCFUJCKfoXCPTmDdqT";// your accessSecret
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        // 若报Can not find endpoint to access异常，请添加以下此行代码
        // DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Alidns", "alidns.aliyuncs.com");
        client = new DefaultAcsClient(profile);
    }
    public static void AddDomainRecord(){
        AddDomainRecordRequest request = new AddDomainRecordRequest();
        AddDomainRecordResponse response;
        request.setProtocol(ProtocolType.HTTPS); //指定访问协议
        request.setAcceptFormat(FormatType.JSON); //指定api返回格式
        request.setMethod(MethodType.POST); //指定请求方法
        request.setActionName("AddDomainRecord");
        request.setDomainName("fengziguo.com");
        request.setRR("www");
        request.setType("A");
        request.setValue("110.110.110.1");
        request.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        try {
            response = client.getAcsResponse(request);
            System.out.println(response.getRecordId());

          /*  List<DescribeDomainsResponse.Domain> list = response.getDomains();
            for (DescribeDomainsResponse.Domain domain : list) {
                System.out.println(domain.getDomainName());
            }*/
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
    public static List<DescribeDomainRecordsResponse.Record> DescribeDomainRecords() {
        DescribeDomainRecordsRequest request = new DescribeDomainRecordsRequest();
        DescribeDomainRecordsResponse response;
        request.setProtocol(ProtocolType.HTTPS); //指定访问协议
        request.setAcceptFormat(FormatType.JSON); //指定api返回格式
        request.setMethod(MethodType.POST); //指定请求方法
       // request.setActionName("DescribeDomainRecord");
        request.setDomainName("fengziguo.com");
        request.setRegionId("cn-hangzhou");//指定要访问的Region,仅对当前请求生效，不改变client的默认设置。
        List<DescribeDomainRecordsResponse.Record> list=null;
        try {
            response = client.getAcsResponse(request);
            list = response.getDomainRecords();
            for (DescribeDomainRecordsResponse.Record Record : list) {
                System.out.println(Record.getValue());
                System.out.println(Record.getRecordId());
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return  list;
    }
}
