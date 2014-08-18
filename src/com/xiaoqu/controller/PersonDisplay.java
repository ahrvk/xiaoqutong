package com.xiaoqu.controller;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; 
import org.springframework.ui.ModelMap; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaoqu.common.Data;
import com.xiaoqu.model.Person;
import com.xiaoqu.service.DummyService;
  

  

@Controller
public class PersonDisplay { 
  
     private final DummyService dummyService; 
  
    @Autowired
    public PersonDisplay(DummyService dummyService) { 
        this.dummyService = dummyService; 
    } 
  
    @RequestMapping("/personDisplay.htm")
    public  ModelMap defaultHandler() { 
        return new ModelMap("personMap",  Data.IDMAP); 
    } 
 }