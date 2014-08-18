package com.xiaoqu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="buildings")  
public class Buildings {
	
	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private int id;  
    @Column private String back;  
    @Column private int buildingNo;  
    @Column private int communityId;  
    @Column private int households;  
    @Column private int appUsers;  
    @Column private String floors;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBack() {
		return back;
	}
	public void setBack(String back) {
		this.back = back;
	}
	public int getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(int buildingNo) {
		this.buildingNo = buildingNo;
	}
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	public int getHouseholds() {
		return households;
	}
	public void setHouseholds(int households) {
		this.households = households;
	}
	
	public int getAppUsers() {
		return appUsers;
	}
	public void setAppUsers(int appUsers) {
		this.appUsers = appUsers;
	}
	public String getFloors() {
		return floors;
	}
	public void setFloors(String floors) {
		this.floors = floors;
	}  
    
    

}
