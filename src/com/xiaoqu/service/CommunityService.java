package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.CommunityDAO;
import com.xiaoqu.model.Community;

@Service  
public class CommunityService {  
    @Autowired()  
    @Qualifier("communityDAO")  
    private CommunityDAO communityDAO;  
  

    public CommunityDAO getCommunityDAO() {
		return communityDAO;
	}
	public void setCommunityDAO(CommunityDAO communityDAO) {
		this.communityDAO = communityDAO;
	}

    @Transactional  
    public void update(Community c) throws Exception {  
        this.communityDAO.update(c);       
    }  
    @Transactional  
    public void save(Community c){  
        this.communityDAO.add(c);  
    }  
    @Transactional  
    public void delete(int id) {  
        this.communityDAO.delete(this.communityDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public Community getCommunity(int id) {  
        return this.communityDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<Community> all() {  
        return this.communityDAO.query("from Community ");  
    }  
    
    @Transactional(readOnly=true)  
    public List<Community> getCommunitiesByRegion(int id) {  
        return this.communityDAO.query("from Community where regionId = ?",id);  
    }
          
}  
