package com.xiaoqu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity  
@Table(name="user") 
public class User {
	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private int id;  
    @Column private String name;  
    @Column private String email;  
    @Column private String mobile;  
    @Column private String icon;  
    @Column private int provinceId;  
    @Column private int cityId;  
    @Column private int regionId;  
    @Column private int communityId;  
    @Column private int buildingId;  
    @Column private int unitId;  
    @Column private String floorNo;  
    @Column private String roomNo;  
    @Column private String realName;  
    @Column private String userType;  
    @Column private String registerTime;  
	@Column private String lastLoginTime;
    @Column private int baiduUserId;
    @Column private int baiduChannelId;
    @Column private int periodNo;
    @Column private String password;
    @Column private short gender;
    @Transient String bindInfo;
    @Transient String avatarUrl;
    
    

    public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public String getBindInfo() {
		return bindInfo;
	}
	public void setBindInfo(String bindInfo) {
		this.bindInfo = bindInfo;
	}
	public short getGender() {
		return gender;
	}
	public void setGender(short gender) {
		this.gender = gender;
	}
	public int getPeriodNo() {
		return periodNo;
	}
	public void setPeriodNo(int periodNo) {
		this.periodNo = periodNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public int getRegionId() {
		return regionId;
	}
	public void setRegionId(int regionId) {
		this.regionId = regionId;
	}
	public int getCommunityId() {
		return communityId;
	}
	public void setCommunityId(int communityId) {
		this.communityId = communityId;
	}
	
	public int getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(int buildingId) {
		this.buildingId = buildingId;
	}
	public String getFloorNo() {
		return floorNo;
	}
	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getBaiduUserId() {
		return baiduUserId;
	}
	public void setBaiduUserId(int baiduUserId) {
		this.baiduUserId = baiduUserId;
	}
	public int getBaiduChannelId() {
		return baiduChannelId;
	}
	public void setBaiduChannelId(int baiduChannelId) {
		this.baiduChannelId = baiduChannelId;
	}  
    
	

}
