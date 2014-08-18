package com.xiaoqu.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoqu.common.Data;
import com.xiaoqu.model.Community;
import com.xiaoqu.model.User;
import com.xiaoqu.model.baiduUser;
import com.xiaoqu.service.CommunityService;
import com.xiaoqu.service.DummyService;
import com.xiaoqu.service.UserService;

@Controller
public class UserController {
	
	private final UserService userService;
	
	@Autowired
    public UserController(UserService userService) { 
        this.userService = userService; 
    } 
	/*@RequestMapping(value = "/registerUser.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String registerUser(String name,String password,  String mobile,
			   int communityId,int periodNo,  int buildingId,
			  String floorNo,  String roomNo,  String realName,  String userType,short gender)
	
	{
		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
        if(password==null||"".equals(password)){
        	node.put("result", "-1");
        }else{
        User user = new User();
        user.setName(name);
        user.setMobile(mobile);
        user.setCommunityId(communityId);
        user.setBuildingId(buildingId);
        user.setFloorNo(floorNo);
        user.setRoomNo(roomNo);
        user.setRealName(realName);
        user.setUserType(userType);
        user.setPassword(password);
        user.setPeriodNo(periodNo);
        user.setGender(gender);
        User u = userService.save(user);
        node.put("result", "1");
        node.put("userId", u.getId());
        }
	    	return node.toString();
	}*/
	
	
	@RequestMapping(value = "/registerUser.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String registerUser(String password,  String mobile, String verifyCode,  String userType)
	
	{
		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
        if(password==null||"".equals(password)||mobile==null||"".equals(mobile)||verifyCode==null||"".equals(verifyCode)||userType==null||"".equals(userType)){
        	node.put("result", "-1");
        }else{
        	User registered = userService.queryUserBymobile(mobile);
        	if(registered!=null){
        		node.put("result", "-2");
        	}else{
                User user = new User();
                user.setMobile(mobile);
                user.setUserType(userType);
                user.setPassword(password);
                User u = userService.save(user);
                node.put("result", "1");
                node.put("userId", u.getId());
        	}
        }
	    	return node.toString();
	}
	
	@RequestMapping(value = "/forgetPassword.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String forgetPassword(String password,  String mobile, String verifyCode,  String userType)
	
	{

		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
		try{
        if(password==null||"".equals(password)||mobile==null||"".equals(mobile)||verifyCode==null||"".equals(verifyCode)||userType==null||"".equals(userType)){
        	node.put("result", "-1");
        }else{
        	User user = userService.queryUserBymobile(mobile);
        	if(user!=null){
        		node.put("result", "-2");
                user.setPassword(password);
                userService.update(user);
                node.put("result", "1");
                node.put("userId", user.getId());
        	}
        }
		}catch(Exception e){
			e.printStackTrace();
		}
	    	return node.toString();
	}
	
	@RequestMapping(value = "/updatePassword.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String updatePassword(String oldPassword,String newPassword,  String mobile, String verifyCode,  String userType)
	
	{

		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
		try{
        if(oldPassword==null||"".equals(oldPassword)||mobile==null||"".equals(mobile)||verifyCode==null||"".equals(verifyCode)||userType==null||"".equals(userType)||newPassword==null||"".equals(newPassword)){
        	node.put("result", "-1");
        }else{
        	User user = userService.queryUserBymobile(mobile);
        	if(user!=null&&user.getPassword().equals(oldPassword)){
                user.setPassword(newPassword);
                userService.update(user);
                node.put("result", "1");
                node.put("userId", user.getId());
        	}else{
        		node.put("result", "-2");
        	}
        }
		}catch(Exception e){
			e.printStackTrace();
		}
	    	return node.toString();
	}
	
	@RequestMapping("/viewAllUser.htm")
	public ModelAndView getAllUser(HttpServletRequest request)
	{
	ModelAndView mav = new ModelAndView("showUsers");
	List<User> users = userService.all();
	for(int i=0;i<users.size();i++){
		User user = users.get(i);
		List<baiduUser> busers = Data.IDMAP.get(String.valueOf(user.getId()));
		String bindInfo = "";
		if(busers!=null){
		for(int k=0;k<busers.size();k++){
			baiduUser buser = busers.get(k);
			if(buser.getDevice_id()==3){
				bindInfo = bindInfo+ "Andriod�豸: �ٶ�userId:"+buser.getBaiduUserId()+" .�ٶ�channel_id:"+buser.getChannel_id()+" ***  ";
			}else if(buser.getDevice_id()==4){
				bindInfo = bindInfo+"Ios�豸: �ٶ�userId:"+buser.getBaiduUserId()+" .�ٶ�channel_id:"+buser.getChannel_id()+" ***  ";;
			}
			user.setBindInfo(bindInfo);
		}
		}
		String iosClientId = Data.IOSIDMAP.get(String.valueOf(user.getId()));
		if(iosClientId!=null){
		bindInfo = bindInfo+"Ios�豸: iosClientId:"+iosClientId+" ***  ";
		user.setBindInfo(bindInfo);
		}
	}
	String src = "http://localhost:8088/xiaoqu/upload/1399293565705.jpg";
	mav.addObject("users", users);
	mav.addObject("src", src);
	return mav;
	}
	
	
/*	@RequestMapping(value = "/loadUser.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String loadUser(String password,  
			   int communityId,int periodNo,  int buildingId,
			  String floorNo,  String roomNo)
	
	{
		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
        
        
        
        User user = userService.queryRegisteredUser(communityId, periodNo, buildingId, floorNo, roomNo);
        if(user==null){
        	node.put("result", "-1");
        }else if(password==null||"".equals(password)){
        	node.put("result", "-2");
        }
        else if(!password.equals(user.getPassword())){
        	node.put("result", "-3");
        }else{
        	node.put("result", "1");
        	node.put("userId", user.getId());
        }
        
	    	return node.toString();
	}*/
	
	@RequestMapping(value = "/loadUser.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String loadUser(String mobile,String password)
	
	{
		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
        
        
        
        User user = userService.queryUserBymobile(mobile);
        if(user==null){
        	node.put("result", "-1");
        }else if(password==null||"".equals(password)){
        	node.put("result", "-2");
        }
        else if(!password.equals(user.getPassword())){
        	node.put("result", "-3");
        }else{
        	node.put("result", "1");
        	node.put("userId", user.getId());
        }
        
	    	return node.toString();
	}
	
/*	@RequestMapping(value="/updateUser.htm", method=RequestMethod.POST) 
    public @ResponseBody String updateUser(String name,String token,  String mobile,
			   String communityId,  String buildingId,String unitId,
			  String floorNo,  String roomNo,  String realName,String gender,  @RequestParam(value="myfile", required=false) MultipartFile myfile, HttpServletRequest request) throws IOException{ 
       
		
		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
        
        User user = userService.queryUserBymobile(mobile);
        if(user==null){
        	node.put("result", "-1");
        }else{
        	if(myfile!=null){
             if(myfile.isEmpty()){ 
                 System.out.println("�ļ�δ�ϴ�"); 
             }else{ 
                 System.out.println("�ļ�����: " + myfile.getSize()); 
                 System.out.println("�ļ�����: " + myfile.getContentType()); 
                 System.out.println("�ļ�����: " + myfile.getName()); 
                 System.out.println("�ļ�ԭ��: " + myfile.getOriginalFilename()); 
                 System.out.println("========================================"); 

                 String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/upload"); 
                 File userPath = new File(realPath+"/"+user.getId());
                 if(!userPath.exists()){
                	 userPath.mkdir();
                 }
                 else{
                	 File avatar = new File(userPath+"/"+myfile.getName());
                	 avatar.deleteOnExit();
                 }
                 //����õ���Tomcat�����������ļ����ϴ���\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\�ļ����� 
                 //���ﲻ�ش���IO���رյ����⣬��ΪFileUtils.copyInputStreamToFile()�����ڲ����Զ����õ���IO���ص������ǿ�����Դ���֪���� 
                 FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(userPath+"/"+myfile.getName())); 
                 user.setAvatarUrl(userPath+"/"+myfile.getName());
             }
        	}
        	 try{
                 user.setName(name);
                 user.setMobile(mobile);
                 user.setCommunityId(Integer.valueOf(communityId));
                 user.setBuildingId(Integer.valueOf(buildingId));
                 user.setUnitId(Integer.valueOf(unitId));
                 user.setFloorNo(floorNo);
                 user.setRoomNo(roomNo);
                 user.setRealName(realName);
                 user.setGender(Short.valueOf(gender));
                 userService.update(user);
                 node.put("result", "1");
                 node.put("userId", user.getId());
            	}catch(Exception e){
            		e.printStackTrace();
            	}
        	
        }
       
	    	return node.toString();
		
		
	}*/
	
	@RequestMapping(value="/updateUser.htm", method=RequestMethod.POST) 
	//������servlet����
	public @ResponseBody String doPost(HttpServletRequest request, HttpServletResponse response)  
	           throws ServletException, IOException {  
		
		ObjectMapper mapper = new ObjectMapper();  
        ObjectNode node = mapper.createObjectNode();  
	            
        Map<String,String> valueMap = new HashMap<String,String>();
	           String temp=request.getSession().getServletContext().getRealPath("/")+"temp";   //��ʱĿ¼
	           System.out.println("temp="+temp);
	           String loadpath=request.getSession().getServletContext().getRealPath("/WEB-INF/upload"); ; //�ϴ��ļ����Ŀ¼
	           System.out.println("loadpath="+loadpath);
	           DiskFileUpload fu = new DiskFileUpload();
	           fu.setSizeMax(1*1024*1024);   // ���������û��ϴ��ļ���С,��λ:�ֽ� 
	           fu.setSizeThreshold(4096);   // �������ֻ�������ڴ��д洢������,��λ:�ֽ� 
	           fu.setRepositoryPath(temp); // ����һ���ļ���С����getSizeThreshold()��ֵʱ���ݴ����Ӳ�̵�Ŀ¼ 
	           
	           //��ʼ��ȡ�ϴ���Ϣ 
	           int index=0;
	           List fileItems = null;
	                
	                         
	                                try {
	                                        fileItems = fu.parseRequest(request);
	                                         System.out.println("fileItems="+fileItems);
	                                } catch (Exception e) {
	                                        e.printStackTrace();
	                                }
	                         
	                
	           Iterator iter = fileItems.iterator(); // ���δ���ÿ���ϴ����ļ�
	           while (iter.hasNext())
	           {
	               FileItem item = (FileItem)iter.next();// �������������ļ�������б���Ϣ
	               if (!item.isFormField())
	               {
	                   String name = item.getName();//��ȡ�ϴ��ļ���,����·��
	                   name=name.substring(name.lastIndexOf("\\")+1);//��ȫ·������ȡ�ļ���
	                   long size = item.getSize();
	                   if((name==null||name.equals("")) && size==0) 
	                	   continue; 
	                   int point = name.indexOf(".");
	                   name=(new Date()).getTime()+name.substring(point,name.length());
	                   index++;
	                   File fNew= new File(loadpath, name);
	                   try {
	                                        item.write(fNew);
	                                } catch (Exception e) {
	                                        // TODO Auto-generated catch block
	                                        e.printStackTrace();
	                                }
	                   
	                  
	               }
	               else //ȡ�������ļ�������б���Ϣ
	               {
	            	   
	                   String fieldvalue = item.getString() ;
	                   String fieldName = item.getFieldName();
	                   valueMap.put(fieldName, fieldvalue);
	                   
	                   
	                   System.out.println("*******  fieldvalue  :*********** "+fieldvalue+"  item.getName():"+item.getName()+" item.getFieldName() : "+item.getFieldName());
	            //�����������ӦдΪ��(תΪUTF-8����)
	                   //String fieldvalue = new String(item.getString().getBytes(),"UTF-8");
	               }
	           }
	           
	           if(valueMap.get("mobile")==null||"".equals(valueMap.get("mobile"))){
	        	   node.put("result", "-2");
	           }else{
	        	   User user = userService.queryUserBymobile(valueMap.get("mobile"));
	        	   if(user==null){
	        		   node.put("result", "-3");
	        	   }else{
	        		   if(valueMap.get("floorNo")==null||"".equals(valueMap.get("floorNo"))){
	        			   user.setFloorNo(valueMap.get("floorNo"));
	        		   }
	        		   if(valueMap.get("token")==null||"".equals(valueMap.get("token"))){
	        		   }
	        		   if(valueMap.get("name")==null||"".equals(valueMap.get("name"))){
	        			   user.setName(valueMap.get("name"));
	        		   }
	        		   if(valueMap.get("roomNo")==null||"".equals(valueMap.get("roomNo"))){
	        			   user.setRoomNo(valueMap.get("roomNo"));
	        		   }
	        		   if(valueMap.get("gender")==null||"".equals(valueMap.get("gender"))){
	        			   user.setGender(Short.valueOf(valueMap.get("gender")));
	        		   }
	        		   if(valueMap.get("realName")==null||"".equals(valueMap.get("realName"))){
	        			   user.setRealName(valueMap.get("realName"));
	        		   }
	        		   if(valueMap.get("unitId")==null||"".equals(valueMap.get("unitId"))){
	        			   user.setUnitId(Integer.valueOf(valueMap.get("unitId")));
	        		   }
	        		   if(valueMap.get("buildingId")==null||"".equals(valueMap.get("buildingId"))){
	        			   user.setBuildingId(Integer.valueOf(valueMap.get("buildingId")));
	        		   }
	        		   if(valueMap.get("communityId")==null||"".equals(valueMap.get("communityId"))){
	        			   user.setCommunityId(Integer.valueOf(valueMap.get("communityId")));
	        		   }
	        		   try{
	        		   userService.update(user);
	                   node.put("result", "1");
	                   node.put("userId", user.getId());
	        		   }catch(Exception e){
	        			   e.printStackTrace(); 
	        			   node.put("result", "-4");
	        		   }
	        		   
	        	   }
	           }
	       	return node.toString();
	    }  

	
}
