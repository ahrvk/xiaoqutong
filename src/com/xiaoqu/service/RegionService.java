package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.BuildingsDAO;
import com.xiaoqu.dao.CityDAO;
import com.xiaoqu.dao.CommunityDAO;
import com.xiaoqu.dao.RegionDAO;
import com.xiaoqu.dao.UnitDAO;
import com.xiaoqu.model.Buildings;
import com.xiaoqu.model.City;
import com.xiaoqu.model.Community;
import com.xiaoqu.model.Region;
import com.xiaoqu.model.Unit;

@Service  
public class RegionService {  
    @Autowired()  
    @Qualifier("regionDAO")  
    private RegionDAO regionDAO;  
  

	public RegionDAO getRegionDAO() {
		return regionDAO;
	}
	public void setCityDAO(RegionDAO regionDAO) {
		this.regionDAO = regionDAO;
	}
	@Transactional  
    public void update(Region c) throws Exception {  
        this.regionDAO.update(c);       
    }  
    @Transactional  
    public void save(Region c){  
        this.regionDAO.add(c);  
    }  
    @Transactional  
    public void delete(int id) {  
        this.regionDAO.delete(this.regionDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public Region getRegion(int id) {  
        return this.regionDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<Region> all() {  
        return this.regionDAO.query("from Region ");  
    }  
    
    @Transactional(readOnly=true)  
    public List<Region> queryByCityId(int id) {  
        return this.regionDAO.query("from Region where cityId = ?",id);  
    }
          
}  
