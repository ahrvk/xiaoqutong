package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.DymanicDAO;

import com.xiaoqu.model.DymanicContent;


@Service  
public class DymanicService {  
    @Autowired()  
    @Qualifier("dymanicDAO")  
    private DymanicDAO dymanicDAO;  
  

 

    @Transactional  
    public void update(DymanicContent c) throws Exception {  
        this.dymanicDAO.update(c);       
    }  
    @Transactional  
    public DymanicContent save(DymanicContent c){  
        DymanicContent u = this.dymanicDAO.add(c);  
        return u;
    }  
    @Transactional  
    public void delete(int id) {  
        this.dymanicDAO.delete(this.dymanicDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public DymanicContent getDymanicContent(int id) {  
        return this.dymanicDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<DymanicContent> all() {  
        return this.dymanicDAO.query("from DymanicContent ");  
    }  
    @Transactional(readOnly=true)
    public DymanicContent queryRegisteredDymanicContent(
			   int communityId,int periodNo,  int buildingId,
			  String floorNo,  String roomNo){
    	//List<DymanicContent> DymanicContents = this.DymanicContentDAO.query("from DymanicContent u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<DymanicContent> DymanicContents = this.dymanicDAO.query("from DymanicContent u where u.communityId =? and u.periodNo=? and u.buildingId =? and u.floorNo =? and u.roomNo =?", (Object)communityId,(Object)periodNo,(Object)buildingId,(Object)floorNo,(Object)roomNo);

    	if(DymanicContents!=null&&DymanicContents.size()>0){
    		return DymanicContents.get(0);
    	}else{
    		return null;
    	}
    }
    
    @Transactional(readOnly=true)
    public DymanicContent queryDymanicContentBymobile(String mobile){
    	//List<DymanicContent> DymanicContents = this.DymanicContentDAO.query("from DymanicContent u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<DymanicContent> DymanicContents = this.dymanicDAO.query("from DymanicContent u where u.mobile =?",mobile);

    	if(DymanicContents!=null&&DymanicContents.size()>0){
    		return DymanicContents.get(0);
    	}else{
    		return null;
    	}
    }
    
    
    @Transactional(readOnly=true)
    public List<DymanicContent> queryByUserId(int id){
    	List<DymanicContent> DymanicContents = this.dymanicDAO.query("from DymanicContent u where u.userId =? ", id);
        return DymanicContents;
    }
    
    @Transactional(readOnly=true)
    public List<DymanicContent> queryByBuildingsId(int id){
    	List<DymanicContent> DymanicContents = this.dymanicDAO.query("from DymanicContent u where u.buildingId =? ", id);
        return DymanicContents;
    }
          
}  
