package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.xiaoqu.dao.ForumDAO;
import com.xiaoqu.model.ForumContent;


@Service  
public class ForumService {  
    @Autowired()  
    @Qualifier("forumDAO")  
    private ForumDAO forumDAO;  
  

 

    @Transactional  
    public void update(ForumContent c) throws Exception {  
        this.forumDAO.update(c);       
    }  
    @Transactional  
    public ForumContent save(ForumContent c){  
        ForumContent u = this.forumDAO.add(c);  
        return u;
    }  
    @Transactional  
    public void delete(int id) {  
        this.forumDAO.delete(this.forumDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public ForumContent getForumContent(int id) {  
        return this.forumDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<ForumContent> all() {  
        return this.forumDAO.query("from ForumContent ");  
    }  
    @Transactional(readOnly=true)
    public ForumContent queryRegisteredForumContent(
			   int communityId,int periodNo,  int buildingId,
			  String floorNo,  String roomNo){
    	//List<ForumContent> ForumContents = this.ForumContentDAO.query("from ForumContent u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<ForumContent> ForumContents = this.forumDAO.query("from ForumContent u where u.communityId =? and u.periodNo=? and u.buildingId =? and u.floorNo =? and u.roomNo =?", (Object)communityId,(Object)periodNo,(Object)buildingId,(Object)floorNo,(Object)roomNo);

    	if(ForumContents!=null&&ForumContents.size()>0){
    		return ForumContents.get(0);
    	}else{
    		return null;
    	}
    }
    
    @Transactional(readOnly=true)
    public ForumContent queryForumContentBymobile(String mobile){
    	//List<ForumContent> ForumContents = this.ForumContentDAO.query("from ForumContent u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<ForumContent> ForumContents = this.forumDAO.query("from ForumContent u where u.mobile =?",mobile);

    	if(ForumContents!=null&&ForumContents.size()>0){
    		return ForumContents.get(0);
    	}else{
    		return null;
    	}
    }
    
    
    @Transactional(readOnly=true)
    public List<ForumContent> queryByUserId(int id){
    	List<ForumContent> ForumContents = this.forumDAO.query("from ForumContent u where u.userId =? ", id);
        return ForumContents;
    }
    
    @Transactional(readOnly=true)
    public List<ForumContent> queryByBuildingsId(int id){
    	List<ForumContent> ForumContents = this.forumDAO.query("from ForumContent u where u.buildingId =? ", id);
        return ForumContents;
    }
          
}  
