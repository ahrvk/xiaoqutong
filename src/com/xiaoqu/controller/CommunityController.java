package com.xiaoqu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoqu.model.Buildings;
import com.xiaoqu.model.Community;
import com.xiaoqu.model.Region;
import com.xiaoqu.model.Unit;
import com.xiaoqu.model.User;
import com.xiaoqu.service.BuildingsService;
import com.xiaoqu.service.CommunityService;
import com.xiaoqu.service.DummyService;
import com.xiaoqu.service.RegionService;
import com.xiaoqu.service.UnitService;

@Controller
public class CommunityController {
	private final RegionService regionService;
	private final CommunityService communityService;
	private final BuildingsService buildingsService;
	private final UnitService unitService;
	
	@Autowired
    public CommunityController(RegionService regionService,CommunityService communityService,BuildingsService buildingsService,UnitService unitService) { 
        this.regionService = regionService;
		this.communityService = communityService; 
        this.buildingsService = buildingsService;
        this.unitService = unitService;
        
    } 
	@RequestMapping("/viewAllCommunity.htm")
	public ModelAndView getAllCommunity()
	{
	ModelAndView mav = new ModelAndView("showCommunitys");
	List<Community> communities = communityService.all();
	mav.addObject("communities", communities);
	return mav;
	}
	
	@RequestMapping(value = "/addCommunity.htm", method = RequestMethod.GET)
    public String add() {
        return "/addCommunity";
    }
	
	@RequestMapping(value = "/saveCommunity.htm", method = RequestMethod.POST)
    public String save(Community community) {
		communityService.save(community);
        return "redirect:viewAllCommunity.htm";
    }
	
	
	@RequestMapping(value = "/communityInfos.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String communityInfos(int id)
	
	{
		ObjectMapper mapper = new ObjectMapper();  
		 ObjectNode nodeleve1 = mapper.createObjectNode();  
		 ObjectNode nodeleve2 = mapper.createObjectNode();  
		 ObjectNode nodeleve3 = mapper.createObjectNode();  
  		Community community = this.communityService.getCommunity(id);
  		Map communityMap = new HashMap<>();
		List communitySubList = new ArrayList<>();
		
		communityMap.put("name", community==null?"":community.getCommunityName());
		communityMap.put("buildings", communitySubList);
		List<Buildings> list = buildingsService.queryByCommunityID(id);
		try{
		if(list==null||list.size()==0){
		}else{
			for(int i=0;i<list.size();i++){
				Map buildingMap = new HashMap<>();
				List buildingSubList = new ArrayList<>();
				buildingMap.put("buildingId", list.get(i).getId());
				buildingMap.put("buildingNo", list.get(i).getBuildingNo());
				buildingMap.put("buildingFloor", list.get(i).getFloors());
				buildingMap.put("units", buildingSubList);
				List<Unit> unitList = unitService.queryByBuildingId(list.get(i).getId());
				
				if(unitList==null||unitList.size()==0){
				}else{
					
					
					for(int j=0;j<unitList.size();j++){
						Unit unit = unitList.get(j);
						 Map unitMap = new HashMap<>();
						 unitMap.put("unitId", unit.getId());
						 unitMap.put("unitNo", unit.getUnitNo());
						 unitMap.put("unitRoomMax", unit.getRoomNum());
						 buildingSubList.add(unitMap);
						 
					}
				}
				communitySubList.add(buildingMap);
			}
		}
		return mapper.writeValueAsString(communityMap);
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
		
		
		
	}
		
	
	@RequestMapping(value = "/getCcommunities.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String getCommunities(int id)
	
	{
		ObjectMapper mapper = new ObjectMapper();   
		 List<Community> list = communityService.getCommunitiesByRegion(id);
		 try{
		 return mapper.writeValueAsString(list);
		 }catch(Exception e){
			 e.printStackTrace();

			 return "";
		 }
	}
	@RequestMapping(value = "/getRegions.htm" ,method = RequestMethod.GET)
	@ResponseBody
	public String getRegions(int id)
	
	{
		ObjectMapper mapper = new ObjectMapper();   
		 List<Region> list = regionService.queryByCityId(id);
		 try{
		 return mapper.writeValueAsString(list);
		 }catch(Exception e){
			 e.printStackTrace();

			 return "";
		 }
	}
}
