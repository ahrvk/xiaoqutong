package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.BuildingsDAO;
import com.xiaoqu.dao.CityDAO;
import com.xiaoqu.dao.CommunityDAO;
import com.xiaoqu.dao.UnitDAO;
import com.xiaoqu.model.Buildings;
import com.xiaoqu.model.City;
import com.xiaoqu.model.Community;
import com.xiaoqu.model.Unit;

@Service  
public class CityService {  
    @Autowired()  
    @Qualifier("cityDAO")  
    private CityDAO cityDAO;  
  

	public CityDAO getCityDAO() {
		return cityDAO;
	}
	public void setCityDAO(CityDAO cityDAO) {
		this.cityDAO = cityDAO;
	}
	@Transactional  
    public void update(City c) throws Exception {  
        this.cityDAO.update(c);       
    }  
    @Transactional  
    public void save(City c){  
        this.cityDAO.add(c);  
    }  
    @Transactional  
    public void delete(int id) {  
        this.cityDAO.delete(this.cityDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public City getCity(int id) {  
        return this.cityDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<City> all() {  
        return this.cityDAO.query("from City ");  
    }  
    
    @Transactional(readOnly=true)  
    public List<City> queryByBuildingId(int id) {  
        return this.cityDAO.query("from City where buildingId = ?",id);  
    }
          
}  
