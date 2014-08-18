package com.xiaoqu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xiaoqu.dao.DymanicDAO;
import com.xiaoqu.dao.UserDAO;
import com.xiaoqu.model.User;

@Service  
public class CopyOfUserService {  
    @Autowired()  
    @Qualifier("dymanicDAO")  
    private DymanicDAO userDAO;  
  

 

   
}  
