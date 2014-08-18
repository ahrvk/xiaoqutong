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
	//private static final String CLIENTID = "您客户端的ClientID";
	private static final String API = "http://sdk.open.api.igexin.com/apiex.htm"; 	//OpenService接口地址

	public String pushMessage(String clientId,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {
		// 推送主类
		IIGtPush push = new IGtPush(API, APPKEY, MASTERSECRET);

		try {		
			//单推消息类型 
			SingleMessage message = new SingleMessage();

			//通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以TransmissionTemplate为例
			TransmissionTemplate template = new TransmissionTemplate();
			template.setAppId(APPID);
			template.setAppkey(APPKEY);
			template.setPushInfo("", 1, "{\"title\":\""+title+"\"}", "");
			Long sendTime = new Date().getTime();
			template.setTransmissionContent("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");
			
			//收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
			template.setTransmissionType(1);					

			message.setData(template);
//			message.setOffline(true);					//用户当前不在线时，是否离线存储,可选
//			message.setOfflineExpireTime(72 * 3600 * 1000);	//离线有效时间，单位为毫秒，可选
			
			Target target1 = new Target();
			target1.setAppId(APPID);
			target1.setClientId(clientId);

			//单推
			IPushResult ret = push.pushMessageToSingle(message, target1);
						
			return ret.getResponse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal exception";
			
		}
	}
	
	public String pushSingleNotification(String clientId,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {
		// 推送主类
		IIGtPush push = new IGtPush(API, APPKEY, MASTERSECRET);

		try {		
			//单推消息类型 
			SingleMessage message = new SingleMessage();

			//通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以TransmissionTemplate为例
			NotificationTemplate template = new NotificationTemplate();
			template.setAppId(APPID);							//应用APPID
			template.setAppkey(APPKEY);							//应用APPKEY
			
			//通知属性设置：如通知的标题，内容
			template.setTitle(title);					// 通知标题
			template.setText(description);					// 通知内容
//			template.setLogo("push.png");				// 通知图标，需要客户端开发时嵌入
//			template.setIsRing(true);					// 收到通知是否响铃，可选，默认响铃
//			template.setIsVibrate(true);					// 收到通知是否震动，可选，默认振动
//			template.setIsClearable(true);				// 通知是否可清除，可选，默认可清除
			
			template.setTransmissionType(1);				// 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
			Long nowTime = new Date().getTime();
			Long sendTime = new Date().getTime();
			template.setPushInfo("", 1, "{\"title\":\""+title+"\"}", "");
			template.setTransmissionContent("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");	// 透传内容（点击通知后SDK将透传内容传给你的客户端，需要客户端做相应开发）			

			message.setData(template);
//			message.setOffline(true);					//用户当前不在线时，是否离线存储,可选
//			message.setOfflineExpireTime(72 * 3600 * 1000);	//离线有效时间，单位为毫秒，可选
			
			Target target1 = new Target();
			target1.setAppId(APPID);
			target1.setClientId(clientId);

			//单推
			IPushResult ret = push.pushMessageToSingle(message, target1);
						
			return ret.getResponse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal exception";
			
		}
	}
	
	public String pushListNotification(List<String> clientIds,String title,
    		String description,String senderId,String senderName,String processUrl,String userType,int message_type) {
		// 推送主类
		IIGtPush push = new IGtPush(API, APPKEY, MASTERSECRET);

		try {
			ListMessage message = new ListMessage();

			//通知模版：支持TransmissionTemplate、LinkTemplate、NotificationTemplate，此处以NotificationTemplate为例
			NotificationTemplate template = new NotificationTemplate();

			template.setAppId(APPID);							//应用APPID
			template.setAppkey(APPKEY);							//应用APPKEY
			
			//通知属性设置：如通知的标题，内容
			template.setTitle(title);					// 通知标题
			template.setText(description);					// 通知内容
			template.setLogo("push.png");				// 通知图标，需要客户端开发时嵌入
//			template.setIsRing(true);					// 收到通知是否响铃，可选，默认响铃
//			template.setIsVibrate(true);					// 收到通知是否震动，可选，默认振动
//			template.setIsClearable(true);				// 通知是否可清除，可选，默认可清除
			
			template.setTransmissionType(1);				// 收到消息是否立即启动应用，1为立即启动，2则广播等待客户端自启动
			Long sendTime = new Date().getTime();
			template.setPushInfo("", 1, "{\"title\":\""+title+"\"}", "");
			template.setTransmissionContent("{\"title\":\""+title+"\",\"message_type\":\""+message_type+"\",\"description\":\""+description+"\",\"senderId\":\""+senderId+"\",\"senderName\":\""
                    +senderName+"\",\"userType\":\""+userType+"\",\"sendTime\":\""+sendTime+"\",\"processUrl\":\""+processUrl+"\"}");	// 透传内容（点击通知后SDK将透传内容传给你的客户端，需要客户端做相应开发）			

			message.setData(template);
//			message.setOffline(true);		//用户当前不在线时，是否离线存储，可选，默认不存储
//			message.setOfflineExpireTime(72 * 3600 * 1000);		//离线有效时间，单位为毫秒，可选

			// 接收者
			List<Target> targets = new ArrayList<Target>();
			if(clientIds!=null&&clientIds.size()>0){
			for(int k=0;k<clientIds.size();k++){
				String clientId = clientIds.get(k);
				Target target1 = new Target();
//				Target target2 = new Target();						//如果需要可设置多个接收者
				target1.setAppId(APPID);							//接收者安装的应用的APPID
				target1.setClientId(clientId);		
				targets.add(target1);
			}
			}

			//推送前通过该接口申请“ContentID”
			String contentId = push.getContentId(message);	
			IPushResult ret = push.pushMessageToList(contentId, targets);
 
			return ret.getResponse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Internal exception";
			
		}
	}
}

