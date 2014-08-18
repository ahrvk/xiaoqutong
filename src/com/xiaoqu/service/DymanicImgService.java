package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.xiaoqu.dao.DymanicImgDAO;
import com.xiaoqu.model.DymanicImg;


@Service  
public class DymanicImgService {  
    @Autowired()  
    @Qualifier("dymanicImgDAO")  
    private DymanicImgDAO dymanicImgDAO;  
  

 

    @Transactional  
    public void update(DymanicImg c) throws Exception {  
        this.dymanicImgDAO.update(c);       
    }  
    @Transactional  
    public DymanicImg save(DymanicImg c){  
        DymanicImg u = this.dymanicImgDAO.add(c);  
        return u;
    }  
    @Transactional  
    public void delete(int id) {  
        this.dymanicImgDAO.delete(this.dymanicImgDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public DymanicImg getDymanicImg(int id) {  
        return this.dymanicImgDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<DymanicImg> all() {  
        return this.dymanicImgDAO.query("from DymanicImg ");  
    }  
    @Transactional(readOnly=true)
    public DymanicImg queryRegisteredDymanicImg(
			   int communityId,int periodNo,  int buildingId,
			  String floorNo,  String roomNo){
    	//List<DymanicImg> DymanicImgs = this.DymanicImgDAO.query("from DymanicImg u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<DymanicImg> DymanicImgs = this.dymanicImgDAO.query("from DymanicImg u where u.communityId =? and u.periodNo=? and u.buildingId =? and u.floorNo =? and u.roomNo =?", (Object)communityId,(Object)periodNo,(Object)buildingId,(Object)floorNo,(Object)roomNo);

    	if(DymanicImgs!=null&&DymanicImgs.size()>0){
    		return DymanicImgs.get(0);
    	}else{
    		return null;
    	}
    }
    
    @Transactional(readOnly=true)
    public DymanicImg queryDymanicImgBymobile(String mobile){
    	//List<DymanicImg> DymanicImgs = this.DymanicImgDAO.query("from DymanicImg u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<DymanicImg> DymanicImgs = this.dymanicImgDAO.query("from DymanicImg u where u.mobile =?",mobile);

    	if(DymanicImgs!=null&&DymanicImgs.size()>0){
    		return DymanicImgs.get(0);
    	}else{
    		return null;
    	}
    }
    
    
    @Transactional(readOnly=true)
    public List<DymanicImg> queryByCommunityId(int id){
    	List<DymanicImg> DymanicImgs = this.dymanicImgDAO.query("from DymanicImg u where u.communityId =? ", id);
        return DymanicImgs;
    }
    
    @Transactional(readOnly=true)
    public List<DymanicImg> queryByBuildingsId(int id){
    	List<DymanicImg> DymanicImgs = this.dymanicImgDAO.query("from DymanicImg u where u.buildingId =? ", id);
        return DymanicImgs;
    }
          
}  
