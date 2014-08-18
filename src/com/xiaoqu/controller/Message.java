package com.xiaoqu.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xiaoqu.common.Data;
import com.xiaoqu.model.Buildings;
import com.xiaoqu.model.Community;
import com.xiaoqu.model.User;
import com.xiaoqu.model.baiduUser;
import com.xiaoqu.service.BuildingsService;
import com.xiaoqu.service.CommunityService;
import com.xiaoqu.service.GetuiService;
import com.xiaoqu.service.MessageService;
import com.xiaoqu.service.UserService;

@Controller
public class Message {
	
	private final CommunityService communityService;
	private final BuildingsService buildingsService;
	private final UserService userService;
	private final MessageService messageService;
	
	private static String communityPrefix = "paulXiaoquCommunity";
	private static String buildingsPrefix = "paulXiaoquBuildings";
	
	@Autowired
    public Message(CommunityService communityService,BuildingsService buildingsService,UserService userService,MessageService messageService) { 
        this.communityService = communityService; 
        this.buildingsService = buildingsService;
        this.userService = userService;
        this.messageService = messageService;
    } 
	
	
	
	 public CommunityService getCommunityService() {
		return communityService;
	}



	public BuildingsService getBuildingsService() {
		return buildingsService;
	}



	@RequestMapping("/bindBaiduId.htm")
	    public @ResponseBody String defaultHandler(@RequestParam int userId,@RequestParam String baiduUserId,@RequestParam Long channel_id,
	    		@RequestParam int device_id
	    		) {
		 ObjectMapper mapper = new ObjectMapper();  
	     ObjectNode node = mapper.createObjectNode();  
	     User user = this.userService.getUser(userId);
	     Community community = this.communityService.getCommunity(user.getCommunityId());
	     Buildings buildings = this.buildingsService.getBuildings(user.getBuildingId());
		 if(Data.IDMAP.get(String.valueOf(userId))==null){
			 List list = new ArrayList();
			 baiduUser buser = new baiduUser();
			 buser.setBaiduUserId(baiduUserId);
			 buser.setChannel_id(channel_id);
			 buser.setDevice_id(device_id);
			 list.add(buser);
			 Data.IDMAP.put(String.valueOf(userId), list);
		        node.put("result", "1");
			 
		 }else{
			 
			 List list = Data.IDMAP.get(String.valueOf(userId));
			 for(int i=0;i<list.size();i++){
				 baiduUser buser = (baiduUser)list.get(i);
				 if(baiduUserId.equals(buser.getBaiduUserId())&&channel_id.equals(buser.getChannel_id())&&device_id==buser.getDevice_id()){
					 node.put("result", "2");
					 node.toString();
					 return node.toString();
				 }
			 }
			 baiduUser buser = new baiduUser();
			 buser.setBaiduUserId(baiduUserId);
			 buser.setChannel_id(channel_id);
			 buser.setDevice_id(device_id);
			 list.add(buser);
		 }
         if(community!=null){
		 this.messageService.SetTagMessage(baiduUserId, communityPrefix+community.getId());
         }
         if(buildings!=null){
		 this.messageService.SetTagMessage(baiduUserId, buildingsPrefix+buildings.getId());
         }
	    	return node.toString();
	    }
	 
	 @RequestMapping("/bindGetuiId.htm")
	    public @ResponseBody String bindGetuiId(@RequestParam String userId,@RequestParam String getuiId
	    		) {
		 ObjectMapper mapper = new ObjectMapper();  
	     ObjectNode node = mapper.createObjectNode();  
	     Data.IOSIDMAP.put(userId, getuiId);
	     node.put("result", "1");
	    	return node.toString();
	    }
	 
	 @RequestMapping("/sendMessage.htm")
	 @ResponseBody
	 public  String sendMessage(int message_type,String title,String description,String senderId,String senderName,String receiveId,
			 String processUrl,String userType) {
		 ObjectMapper mapper = new ObjectMapper();  
	        ObjectNode node = mapper.createObjectNode(); 
    		MessageService ms = new MessageService();
    		GetuiService gs = new GetuiService();
    		String result = "" ;
		 List list = Data.IDMAP.get(receiveId);
		 if(list==null||list.size()==0){
			 
		 }else{
			 for(int i=0;i<list.size();i++){
				 baiduUser buser = (baiduUser)list.get(i);
				 if(buser.getDevice_id()==3&&message_type==1){
					  result = ms.testPushUnicastAndroidNotification(buser.getBaiduUserId(), buser.getChannel_id(), title,description,senderId,senderName,processUrl,userType,message_type);
				 }else if(buser.getDevice_id()==3&&message_type==0){
					  result = ms.testPushUnicastMessage(buser.getBaiduUserId(), buser.getChannel_id(), title,description,senderId,senderName,processUrl,userType,message_type);
				 }
				 else{
					 result = "暂不支持该种消息类型";
				 }
			 }
		 }
		 
		 String clientId = Data.IOSIDMAP.get(receiveId);
		 if(clientId==null||"".equals(clientId)){
			 node.put("result", "-1");
		 }else{
			 if(message_type==1){
				 result = gs.pushSingleNotification(clientId, title, description, senderId, senderName, processUrl,userType,message_type);
			 }else if(message_type==0){
				 result = gs.pushMessage(clientId, title, description, senderId, senderName, processUrl,userType,message_type);
			 }
		 }
		 
		 
	    			 node.put("result", result);
	 	    	return node.toString();
	    }
	 
	 @RequestMapping("/sendIosNotify.htm")
	 @ResponseBody
	 public  String sendIosNotify(String baiduUserId,String baiduChannelId,String message) {
		 ObjectMapper mapper = new ObjectMapper();  
	        ObjectNode node = mapper.createObjectNode();  
	    		/*MessageService ms = new MessageService();
	    		String result = ms.testPushUnicastIosNotification(baiduUserId,Long.valueOf(baiduChannelId), message);
	    		node.put("result", result);*/
	 	    	return node.toString();
	    }
	 
	 @RequestMapping("/sendAndroidNotify.htm")
	 @ResponseBody
	 public  String sendAndroidNotify(String baiduUserId,String baiduChannelId,String message) {
		 ObjectMapper mapper = new ObjectMapper();  
	        ObjectNode node = mapper.createObjectNode();  
	    		/*MessageService ms = new MessageService();
	    		String result = ms.testPushUnicastAndroidNotification(baiduUserId,Long.valueOf(baiduChannelId), message);
	    		node.put("result", result);*/
	 	    	return node.toString();
	    }
	 
	 
	 
	 
	 
	 
	 @RequestMapping("/sendGroupNotification.htm")
	 @ResponseBody
	 public  String sendGroupNotification(int message_type,int receiveScope,int receiveId,String title,String description,String senderId,String senderName,
			 String processUrl,String userType) {
		 ObjectMapper mapper = new ObjectMapper();  
	        ObjectNode node = mapper.createObjectNode(); 
    		MessageService ms = new MessageService();
    		GetuiService gs = new GetuiService();
    		String result = "" ;
	        String tagName = "";
	        List<String> clientIds = new ArrayList<String>();
    		if(receiveScope == 0){
    			tagName = communityPrefix+receiveId;
    			List<User> users = this.userService.queryByCommunityId(receiveId);
    			if(users!=null){
    				for(int i=0;i<users.size();i++){
    					String clientId = Data.IOSIDMAP.get(String.valueOf(users.get(i).getId()));
    					clientIds.add(clientId);
    				}
    			}
    			
    			
    		}else if(receiveScope == 1){
    			tagName = this.buildingsPrefix + receiveId;
    			List<User> users = this.userService.queryByBuildingsId(receiveId);
    			if(users!=null){
    				for(int i=0;i<users.size();i++){
    					String clientId = Data.IOSIDMAP.get(String.valueOf(users.get(i).getId()));
    					clientIds.add(clientId);
    				}
    			}
    		}else{
    			node.put("error", "no supported receiveScope ");
    			return node.toString();
    		}
    		ms.testPushTagMessage(tagName, title, description, senderId, senderName, processUrl,userType,message_type);
    		
    		gs.pushListNotification(clientIds, title, description, senderId, senderName, processUrl,userType,message_type);
	 	    	return node.toString();
	    }
	 
	 
	 
	 

}
