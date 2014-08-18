package com.xiaoqu.service;


import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.BindInfo;
import com.baidu.yun.channel.model.ChannelMessage;
import com.baidu.yun.channel.model.FetchMessageRequest;
import com.baidu.yun.channel.model.FetchMessageResponse;
import com.baidu.yun.channel.model.InitAppIoscertRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushTagMessageRequest;
import com.baidu.yun.channel.model.PushTagMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.channel.model.QueryBindListRequest;
import com.baidu.yun.channel.model.QueryBindListResponse;
import com.baidu.yun.channel.model.SetTagRequest;
import com.baidu.yun.channel.model.VerifyBindRequest;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;

@Service
public class MessageService {
	 @Autowired()

    @Test
    public void testQueryBindList() {

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "BaGrK5N1OGzscV3OTM03DOP9";
        String secretKey = "ZM68f5ZaDTaUnfubEBsbRH68ioUgLDq2";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. �������������
            QueryBindListRequest request = new QueryBindListRequest();
            request.setUserId("1144280722819924199");

            // 5. ����queryBindList�ӿ�
            QueryBindListResponse response = channelClient
                    .queryBindList(request);

            // 6. �Է��صĽ��������в���
            List<BindInfo> bindInfos = response.getBinds();
            /*for (BindInfo bindInfo : bindInfos) {
                long channelId = bindInfo.getChannelId();
                String userId = bindInfo.getUserId();
                Data.IDMAP.put(userId, String.valueOf(channelId));
                int status = bindInfo.getBindStatus();
                System.out.println("channel_id:" + channelId + ", user_id: "
                        + userId + ", status: " + status);
 
                String bindName = bindInfo.getBindName();
                long bindTime = bindInfo.getBindTime();
                String deviceId = bindInfo.getDeviceId();
                int deviceType = bindInfo.getDeviceType();
                long timestamp = bindInfo.getOnlineTimestamp();
                long expire = bindInfo.getOnlineExpires();

                System.out.println("bind_name:" + bindName + "\t"
                        + "bind_time:" + bindTime);
                System.out.println("device_type:" + deviceType + "\tdeviceId"
                        + deviceId);
                System.out.println(String.format("timestamp: %d, expire: %d",
                        timestamp, expire));

            }*/

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public String testPushUnicastMessage(String userId,Long channel_id,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {

        /*
         * @brief ���͵�����Ϣ(��Ϣ����Ϊ͸�����ɿ�����Ӧ���Լ���������Ϣ����)
         * message_type = 0 (Ĭ��Ϊ0)
         */
        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "TIkzymE0wCx3AwSBLH4pGD5l";
        String secretKey = "5AYummsP9ZMj8wwWyno32CPgLy0rZHtU";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. �������������
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3);
            request.setChannelId(channel_id);
            //request.setUserId("1144280722819924199");
            request.setUserId(userId);
            Long sendTime = new Date().getTime();

            request.setMessage("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");

            // 5. ����pushMessage�ӿ�
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            Assert.assertEquals(1, response.getSuccessAmount());
            
            return response.toString();
        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
            return e.toString();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            return e.toString();
        }

    }

    @Test
    public String testPushUnicastAndroidNotification(String userId,Long channel_id,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type 
    		) {

        /*
         * @brief ��Android���豸���͵�����Ϣ
         * message_type = 1
         * device_type = 3
         */

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "TIkzymE0wCx3AwSBLH4pGD5l";
        String secretKey = "5AYummsP9ZMj8wwWyno32CPgLy0rZHtU";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. �������������
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(3);
            request.setChannelId(channel_id);
            request.setUserId(userId);

            request.setMessageType(1);
            Long sendTime = new Date().getTime();

            request.setMessage("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");

            // 5. ����pushMessage�ӿ�
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);
            Assert.assertEquals(1, response.getSuccessAmount());

            return response.toString();

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
            return e.toString();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            return e.toString();
        }

    }

    @Test
    public String testPushUnicastIosNotification(String userId,Long channel_id,String title,
    		String description,String senderId,String senderName,String receiveId,String receiveName,String processUrl) {

        /*
         * @brief ���͵�����Ϣ(IOS APNS)
         * message_type = 1 
         * device_type = 4
         */

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	// 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "Qw2iVGDPVf0Ys887YOEV7Zeh";
        String secretKey = "OaBYYcDfIfFAZ6xE0CTwOyGX6wSpcBpI";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. �������������
            PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            request.setDeviceType(4);
             request.setChannelId(channel_id);
            request.setUserId(userId);

            request.setMessageType(1);
            request.setMessage("{\"title\":\""+title+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"receiveId\":\""+receiveId+"\",\"receiveName\":\""+receiveName+"\",\"processUrl\":\""+processUrl+"\"}");
            //request.setMessage("{aps}");

            // 5. ����pushMessage�ӿ�
            PushUnicastMessageResponse response = channelClient
                    .pushUnicastMessage(request);

            Assert.assertEquals(1, response.getSuccessAmount());
            
            return response.toString();

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
            return e.toString();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            return e.toString();
        }

    }
    
    
    @Test
    public void SetTagMessage(String baiduUserId,String tagName) {

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "TIkzymE0wCx3AwSBLH4pGD5l";
        String secretKey = "5AYummsP9ZMj8wwWyno32CPgLy0rZHtU";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }  
        });

        try {

        	
        	
        	SetTagRequest request = new SetTagRequest();
        	request.setUserId(baiduUserId);
        	request.setTag(tagName);

            // 5. ����pushMessage�ӿ�
        	 channelClient.setTag(request);

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testPushTagMessage(String tagName,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "TIkzymE0wCx3AwSBLH4pGD5l";
        String secretKey = "5AYummsP9ZMj8wwWyno32CPgLy0rZHtU";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }  
        });

        try {

            // 4. �������������
            PushTagMessageRequest request = new PushTagMessageRequest();
            request.setMessageType(1);
            request.setDeviceType(3);
            request.setTagName(tagName);
            Long sendTime = new Date().getTime();
            request.setMessage("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");

            // 5. ����pushMessage�ӿ�
            PushTagMessageResponse response = channelClient
                    .pushTagMessage(request);
            if (response.getSuccessAmount() == 1) {
                // TODO
            }

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testPushBroadcastMessage() {

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "hfBVOpzl4h1dUmH92y0Kw3Vt";
        String secretKey = "fUkEiyLmhV6LGjrtxSLADNWl82yrQY1P";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. �������������
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setMessageType(0);
            request.setDeviceType(3);
            request.setMessage("{\"title\":\"����\",\"description\":\"�Ƽ�����\"}");

            // 5. ����pushMessage�ӿ�
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);
            if (response.getSuccessAmount() == 1) {
                // TODO
            }

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
    }

    @Test
    public void testBindVerify() {

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "hfBVOpzl4h1dUmH92y0Kw3Vt";
        String secretKey = "fUkEiyLmhV6LGjrtxSLADNWl82yrQY1P";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. �������������
            VerifyBindRequest request = new VerifyBindRequest();
            request.setChannelId(4035698885061886729L);
            request.setUserId("1144280722819924199");

            // 5. ���� verifyBind �ӿ�
            channelClient.verifyBind(request);

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    // ----------------------------------------------------------------------------
    @Test
    public void testFetchMessage() {

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "hfBVOpzl4h1dUmH92y0Kw3Vt";
        String secretKey = "fUkEiyLmhV6LGjrtxSLADNWl82yrQY1P";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. �������������
            FetchMessageRequest request = new FetchMessageRequest();
            request.setUserId("1144280722819924199");

            // 5. ���� fetchMessage �ӿ�
            FetchMessageResponse resp = channelClient.fetchMessage(request);
            List<ChannelMessage> messages = resp.getMessages();
            for (ChannelMessage msg : messages) {
                System.out.println(msg.getData());
            }

        } catch (ChannelClientException e) {
            // ����ͻ��˴����쳣
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }

    @Test
    public void testInitIosCert() {

        // 1. ����developerƽ̨��ApiKey/SecretKey
    	String apiKey = "hfBVOpzl4h1dUmH92y0Kw3Vt";
        String secretKey = "fUkEiyLmhV6LGjrtxSLADNWl82yrQY1P";
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. ����BaiduChannelClient����ʵ��
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. ��Ҫ�˽⽻��ϸ�ڣ���ע��YunLogHandler��
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

            // 4. �������������
            InitAppIoscertRequest request = new InitAppIoscertRequest();
            request.setName("name");
            request.setDescription("description");

            request.setDevCert("");
            request.setReleaseCert("");

            // 5. ���� initAppIoscert �ӿ�
            channelClient.initAppIoscert(request);

        } catch (ChannelClientException e) {
            e.printStackTrace();
            // ����ͻ��˴����쳣
        } catch (ChannelServerException e) {
            // �������˴����쳣
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }

    }
    
    public static void main(String args[]){
    	MessageService ms = new MessageService();
    	/*ms.testPushUnicastAndroidNotification( "738752803071543845", 3595645114187852319l,"xiaoqu test",
    			"xiaoqu test","5","xiaoqu test","19","aa","www.baidu.com"
        		);*/
	 }

}
