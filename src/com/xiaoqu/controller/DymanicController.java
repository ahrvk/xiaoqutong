package com.xiaoqu.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoqu.common.AbstractSuperAct;
import com.xiaoqu.common.UploadUtils;
import com.xiaoqu.model.Buildings;
import com.xiaoqu.model.Community;
import com.xiaoqu.model.DymanicContent;
import com.xiaoqu.model.DymanicImg;
import com.xiaoqu.model.ForumContent;
import com.xiaoqu.model.Region;
import com.xiaoqu.model.Unit;
import com.xiaoqu.model.User;
import com.xiaoqu.service.BuildingsService;
import com.xiaoqu.service.CommunityService;
import com.xiaoqu.service.DummyService;
import com.xiaoqu.service.DymanicImgService;
import com.xiaoqu.service.DymanicService;
import com.xiaoqu.service.ForumService;
import com.xiaoqu.service.RegionService;
import com.xiaoqu.service.UnitService;

@Controller
public class DymanicController extends AbstractSuperAct{
	private final RegionService regionService;
	private final CommunityService communityService;
	private final BuildingsService buildingsService;
	private final UnitService unitService;
	private  final DymanicService dymanicService;
	private  final DymanicImgService dymanicImgService;
	private  final ForumService forumService;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
	
	@Autowired
    public DymanicController(ForumService forumService,DymanicImgService dymanicImgService,DymanicService dymanicService,RegionService regionService,CommunityService communityService,BuildingsService buildingsService,UnitService unitService) { 
        this.regionService = regionService;
		this.communityService = communityService; 
        this.buildingsService = buildingsService;
        this.unitService = unitService;
        this.dymanicService = dymanicService;
        this.dymanicImgService = dymanicImgService;
        this.forumService = forumService;
        
        
    } 
	
	/**
	 * 发布动态
	 * @param content
	 * @param pics
	 * @param userId
	 */
	@RequestMapping(value = "/releaseDynamic.htm")
	public void releaseDynamic(String content,String pics,Integer userId){
		DymanicContent dymanicContent=new DymanicContent();
		dymanicContent.setContent(content);
		dymanicContent.setPublishTime(new Date());
		dymanicContent.setUserId(userId);
		this.dymanicService.save(dymanicContent);
		this.outPrint(this.encapsulationResult(null, STATE_1, "发布成功！"));
	}
	
	
	/**
	 * 我的动态
	 * @param userId
	 * @param pageNo
	 */
	@RequestMapping(value = "/myDynamic.htm")
	public void myDynamic(Integer userId){
		
		List<DymanicContent > result = this.dymanicService.queryByUserId(userId);
		this.outPrint(this.encapsulationResult(result, STATE_1, "请求成功！"));
	}
	
	/**
	 * 发布论坛
	 * @param content
	 * @param pics
	 * @param userId
	 */
	@RequestMapping(value = "/releaseForum.htm")
	public void releaseF(String topic,String content,String pics,Integer userId){
		ForumContent forumContent=new ForumContent();
		forumContent.setTopic(topic);
		forumContent.setContent(content);
		forumContent.setPublishTime(new Date());
		forumContent.setUserId(userId);
		this.forumService.save(forumContent);
		this.outPrint(this.encapsulationResult(null, STATE_1, "发布成功！"));
	}
	
	/**
	 * 论坛列表
	 * @param userId
	 * @param pageNo
	 */
	@RequestMapping(value = "/myForum.htm")
	public void myForum(Integer userId){
		List<ForumContent> result= this.forumService.queryByUserId(userId);
		this.outPrint(this.encapsulationResult(result, STATE_1, "请求成功！"));
	}
	
	
	/**
	 * 上传图片
	 */
	@RequestMapping(value = "/imgUpload.htm")
	public void imgUpload(){
		 //转型为MultipartHttpRequest(重点的所在)  
		String path = request.getRealPath("/u/cms/www");
        MultipartHttpServletRequest multipartRequest  =  (MultipartHttpServletRequest) request;  
		MultipartFile imgFile  =  multipartRequest.getFile("imgFile"); 
		String name = imgFile.getOriginalFilename();
		if (StringUtils.isEmpty(name)) {
			this.outPrint(this.encapsulationResult(null, STATE_2, "上传图片为空！"));
			return ;
		}
		String ext = name.substring(name.lastIndexOf(".")+1);
		String newFileName = UploadUtils.generateFilename(path,ext);
		File file = new File(newFileName);
		File absolute = file.getAbsoluteFile();
		if (!absolute.exists()) {
			absolute.mkdirs();
		}
		
		try {
			imgFile.transferTo(file);
			String url = sdf.format(new Date())+"/"+file.getName();
			DymanicImg pic = new DymanicImg();
			pic.setFilePath(file.getAbsolutePath());
			pic.setUrl(url);
			;
			this.dymanicImgService.save(pic);
			this.outPrint(this.encapsulationResult(pic.getId(), STATE_1, "成功！"));
		} catch (Exception e) {
			this.outPrint(this.encapsulationResult(null, STATE_2, "上传失败！"));
		}
	}
	
	
	/**
	 * 删除图片
	 * @param id
	 */
	@RequestMapping(value = "/deleteImg.htm")
	public void deleteImg(Integer id){
		System.out.println(id);
		if (id == null) {
			this.outPrint(this.encapsulationResult(null, STATE_2, "id不能为空！"));
			return ;
		}
		this.dymanicImgService.delete(id);
		this.outPrint(this.encapsulationResult(null, STATE_1, "删除成功！"));
	}
	
	
	
	
		
		

		
	
	
	
}
