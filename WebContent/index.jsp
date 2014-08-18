<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

    <head> 
        <title>Adobocode : Sample Spring MVC</title> 
    </head> 
    <body> 
        <h2>Adobocode : Hello World</h2> 
        <br/> 
        
        
        <a href="<c:url value="viewAllCommunity.htm"/>">管理小区 </a>
        
        <a href="<c:url value="viewAllUser.htm"/>">查看用户</a> 
    </body> 
</html>