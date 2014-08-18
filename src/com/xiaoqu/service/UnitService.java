package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.BuildingsDAO;
import com.xiaoqu.dao.CommunityDAO;
import com.xiaoqu.dao.UnitDAO;
import com.xiaoqu.model.Buildings;
import com.xiaoqu.model.Community;
import com.xiaoqu.model.Unit;

@Service  
public class UnitService {  
    @Autowired()  
    @Qualifier("unitDAO")  
    private UnitDAO unitDAO;  
  

	public UnitDAO getUnitDAO() {
		return unitDAO;
	}
	public void setUnitDAO(UnitDAO unitDAO) {
		this.unitDAO = unitDAO;
	}
	@Transactional  
    public void update(Unit c) throws Exception {  
        this.unitDAO.update(c);       
    }  
    @Transactional  
    public void save(Unit c){  
        this.unitDAO.add(c);  
    }  
    @Transactional  
    public void delete(int id) {  
        this.unitDAO.delete(this.unitDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public Unit getUnit(int id) {  
        return this.unitDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<Unit> all() {  
        return this.unitDAO.query("from Unit ");  
    }  
    
    @Transactional(readOnly=true)  
    public List<Unit> queryByBuildingId(int id) {  
        return this.unitDAO.query("from Unit where buildingId = ?",id);  
    }
          
}  
