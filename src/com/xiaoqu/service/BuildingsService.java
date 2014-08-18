package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.BuildingsDAO;
import com.xiaoqu.dao.CommunityDAO;
import com.xiaoqu.model.Buildings;
import com.xiaoqu.model.Community;

@Service  
public class BuildingsService {  
    @Autowired()  
    @Qualifier("buildingsDAO")  
    private BuildingsDAO buildingsDAO;  
  

    public BuildingsDAO getBuildingsDAO() {
		return buildingsDAO;
	}
	public void setBuildingsDAO(BuildingsDAO buildingsDAO) {
		this.buildingsDAO = buildingsDAO;
	}
	@Transactional  
    public void update(Buildings c) throws Exception {  
        this.buildingsDAO.update(c);       
    }  
    @Transactional  
    public void save(Buildings c){  
        this.buildingsDAO.add(c);  
    }  
    @Transactional  
    public void delete(int id) {  
        this.buildingsDAO.delete(this.buildingsDAO.get(id));   
          
    }  
    @Transactional(readOnly=true)  
    public Buildings getBuildings(int id) {  
        return this.buildingsDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<Buildings> all() {  
        return this.buildingsDAO.query("from Buildings ");  
    }  
    
    @Transactional(readOnly=true)  
    public List<Buildings> queryByCommunityID(int id) {  
        return this.buildingsDAO.query("from Buildings where communityId = ?",id);  
    }
          
}  
