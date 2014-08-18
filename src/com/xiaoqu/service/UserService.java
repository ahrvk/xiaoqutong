package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.UserDAO;
import com.xiaoqu.model.User;

@Service  
public class UserService {  
    @Autowired()  
    @Qualifier("userDAO")  
    private UserDAO userDAO;  
  

 

    @Transactional  
    public void update(User c) throws Exception {  
        this.userDAO.update(c);       
    }  
    @Transactional  
    public User save(User c){  
        User u = this.userDAO.add(c);  
        return u;
    }  
    @Transactional  
    public void delete(int id) {  
        this.userDAO.delete(this.userDAO.get(id));  
          
    }  
    @Transactional(readOnly=true)  
    public User getUser(int id) {  
        return this.userDAO.get(id);  
    }  
    @Transactional(readOnly=true)  
    public List<User> all() {  
        return this.userDAO.query("from User ");  
    }  
    @Transactional(readOnly=true)
    public User queryRegisteredUser(
			   int communityId,int periodNo,  int buildingId,
			  String floorNo,  String roomNo){
    	//List<User> users = this.userDAO.query("from User u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<User> users = this.userDAO.query("from User u where u.communityId =? and u.periodNo=? and u.buildingId =? and u.floorNo =? and u.roomNo =?", (Object)communityId,(Object)periodNo,(Object)buildingId,(Object)floorNo,(Object)roomNo);

    	if(users!=null&&users.size()>0){
    		return users.get(0);
    	}else{
    		return null;
    	}
    }
    
    @Transactional(readOnly=true)
    public User queryUserBymobile(String mobile){
    	//List<User> users = this.userDAO.query("from User u where u.communityId =:communityId and u.periodNo=:periodNo and u.buildingNo = :buildingNo and u.floorNo = :floorNo and u.roomNo = :roomNo", (Object)communityId,(Object)periodNo,(Object)buildingNo,(Object)floorNo,(Object)roomNo);
    	List<User> users = this.userDAO.query("from User u where u.mobile =?",mobile);

    	if(users!=null&&users.size()>0){
    		return users.get(0);
    	}else{
    		return null;
    	}
    }
    
    
    @Transactional(readOnly=true)
    public List<User> queryByCommunityId(int id){
    	List<User> users = this.userDAO.query("from User u where u.communityId =? ", id);
        return users;
    }
    
    @Transactional(readOnly=true)
    public List<User> queryByBuildingsId(int id){
    	List<User> users = this.userDAO.query("from User u where u.buildingId =? ", id);
        return users;
    }
          
}  
