package com.xiaoqu.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gexin.rp.sdk.base.IIGtPush;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

public class GetuiService {
	private static final String APPID = "Q6mx59BVt09UShngfnyK6A";
	private static final String APPKEY = "pOEivJ0dGb8ptr35odHfJ7";
	private static final String MASTERSECRET = "rHgKA3D5JLA3eKIhuXboU7";
	//private static final String CLIENTID = "���ͻ��˵�ClientID";
	private static final String API = "http://sdk.open.api.igexin.com/apiex.htm"; 	//OpenService�ӿڵ�ַ

	public String pushMessage(String clientId,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {
		// ��������
		IIGtPush push = new IGtPush(API, APPKEY, MASTERSECRET);

		try {		
			//������Ϣ���� 
			SingleMessage message = new SingleMessage();

			//֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���TransmissionTemplateΪ��
			TransmissionTemplate template = new TransmissionTemplate();
			template.setAppId(APPID);
			template.setAppkey(APPKEY);
			template.setPushInfo("", 1, "{\"title\":\""+title+"\"}", "");
			Long sendTime = new Date().getTime();
			template.setTransmissionContent("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");
			
			//�յ���Ϣ�Ƿ���������Ӧ�ã�1Ϊ����������2��㲥�ȴ��ͻ���������
			template.setTransmissionType(1);					

			message.setData(template);
//			message.setOffline(true);					//�û���ǰ������ʱ���Ƿ����ߴ洢,��ѡ
//			message.setOfflineExpireTime(72 * 3600 * 1000);	//������Чʱ�䣬��λΪ���룬��ѡ
			
			Target target1 = new Target();
			target1.setAppId(APPID);
			target1.setClientId(clientId);

			//����
			IPushResult ret = push.pushMessageToSingle(message, target1);
						
			return ret.getResponse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal exception";
			
		}
	}
	
	public String pushSingleNotification(String clientId,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {
		// ��������
		IIGtPush push = new IGtPush(API, APPKEY, MASTERSECRET);

		try {		
			//������Ϣ���� 
			SingleMessage message = new SingleMessage();

			//֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���TransmissionTemplateΪ��
			NotificationTemplate template = new NotificationTemplate();
			template.setAppId(APPID);							//Ӧ��APPID
			template.setAppkey(APPKEY);							//Ӧ��APPKEY
			
			//֪ͨ�������ã���֪ͨ�ı��⣬����
			template.setTitle(title);					// ֪ͨ����
			template.setText(description);					// ֪ͨ����
//			template.setLogo("push.png");				// ֪ͨͼ�꣬��Ҫ�ͻ��˿���ʱǶ��
//			template.setIsRing(true);					// �յ�֪ͨ�Ƿ����壬��ѡ��Ĭ������
//			template.setIsVibrate(true);					// �յ�֪ͨ�Ƿ��𶯣���ѡ��Ĭ����
//			template.setIsClearable(true);				// ֪ͨ�Ƿ���������ѡ��Ĭ�Ͽ����
			
			template.setTransmissionType(1);				// �յ���Ϣ�Ƿ���������Ӧ�ã�1Ϊ����������2��㲥�ȴ��ͻ���������
			Long nowTime = new Date().getTime();
			Long sendTime = new Date().getTime();
			template.setPushInfo("", 1, "{\"title\":\""+title+"\"}", "");
			template.setTransmissionContent("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");	// ͸�����ݣ����֪ͨ��SDK��͸�����ݴ�����Ŀͻ��ˣ���Ҫ�ͻ�������Ӧ������			

			message.setData(template);
//			message.setOffline(true);					//�û���ǰ������ʱ���Ƿ����ߴ洢,��ѡ
//			message.setOfflineExpireTime(72 * 3600 * 1000);	//������Чʱ�䣬��λΪ���룬��ѡ
			
			Target target1 = new Target();
			target1.setAppId(APPID);
			target1.setClientId(clientId);

			//����
			IPushResult ret = push.pushMessageToSingle(message, target1);
						
			return ret.getResponse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal exception";
			
		}
	}
	
	public String pushListNotification(List<String> clientIds,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {
		// ��������
		IIGtPush push = new IGtPush(API, APPKEY, MASTERSECRET);

		try {
			ListMessage message = new ListMessage();

			//֪ͨģ�棺֧��TransmissionTemplate��LinkTemplate��NotificationTemplate���˴���NotificationTemplateΪ��
			NotificationTemplate template = new NotificationTemplate();

			template.setAppId(APPID);							//Ӧ��APPID
			template.setAppkey(APPKEY);							//Ӧ��APPKEY
			
			//֪ͨ�������ã���֪ͨ�ı��⣬����
			template.setTitle(title);					// ֪ͨ����
			template.setText(description);					// ֪ͨ����
			template.setLogo("push.png");				// ֪ͨͼ�꣬��Ҫ�ͻ��˿���ʱǶ��
//			template.setIsRing(true);					// �յ�֪ͨ�Ƿ����壬��ѡ��Ĭ������
//			template.setIsVibrate(true);					// �յ�֪ͨ�Ƿ��𶯣���ѡ��Ĭ����
//			template.setIsClearable(true);				// ֪ͨ�Ƿ���������ѡ��Ĭ�Ͽ����
			
			template.setTransmissionType(1);				// �յ���Ϣ�Ƿ���������Ӧ�ã�1Ϊ����������2��㲥�ȴ��ͻ���������
			Long sendTime = new Date().getTime();
			template.setPushInfo("", 1, "{\"title\":\""+title+"\"}", "");
			template.setTransmissionContent("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");	// ͸�����ݣ����֪ͨ��SDK��͸�����ݴ�����Ŀͻ��ˣ���Ҫ�ͻ�������Ӧ������			

			message.setData(template);
//			message.setOffline(true);		//�û���ǰ������ʱ���Ƿ����ߴ洢����ѡ��Ĭ�ϲ��洢
//			message.setOfflineExpireTime(72 * 3600 * 1000);		//������Чʱ�䣬��λΪ���룬��ѡ

			// ������
			List<Target> targets = new ArrayList<Target>();
			if(clientIds!=null&&clientIds.size()>0){
			for(int k=0;k<clientIds.size();k++){
				String clientId = clientIds.get(k);
				Target target1 = new Target();
//				Target target2 = new Target();						//�����Ҫ�����ö��������
				target1.setAppId(APPID);							//�����߰�װ��Ӧ�õ�APPID
				target1.setClientId(clientId);		
				targets.add(target1);
			}
			}

			//����ǰͨ���ýӿ����롰ContentID��
			String contentId = push.getContentId(message);	
			IPushResult ret = push.pushMessageToList(contentId, targets);
 
			return ret.getResponse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal exception";
			
		}
	}
}

