package com.xiaoqu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  
@Table(name="community")  
public class Community  
{  
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private int id;  
    @Column private String communityName;  
    @Column private int provinceId;  
    @Column private int cityId;  
    @Column private int regionId;  
    @Column private String backup;  
    
    
      
    public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getCommunityName() {
		return communityName;
	}



	public void setCommunityName(String communityName) {
		this.communityName = communityName;
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



	public String getBackup() {
		return backup;
	}



	public void setBackup(String backup) {
		this.backup = backup;
	}



	@Override  
    public String toString()  
    {  
        return id + communityName;
    }  
    //setters & getters      
}  
