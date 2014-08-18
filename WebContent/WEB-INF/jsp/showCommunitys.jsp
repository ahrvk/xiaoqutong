<!-- Copyright : adobocode.com , 2010 -->
  
<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
  
<html> 
    <head> 
        <title></title> 
    </head> 
    <body> 
    
    <a href="<c:url value="addCommunity.htm"/>">添加小区 </a>
        <h2>所有小区列表</h2> 
        <table border="1"> 
          <tr>
				<td>id</td>
				<td style="left-margin:20px;">小区名称</td>
				<td style="left-margin:20px;">省份</td>
				<td style="left-margin:20px;">城市</td>
				<td style="left-margin:20px;">区</td>
				<td style="left-margin:20px;">备注</td>
			</tr>
            <c:forEach var="community" items="${communities}">
			<tr>
				<td>${community.id}</td>
				<td style="left-margin:20px;">${community.communityName}</td>
				<td style="left-margin:20px;">四川省</td>
				<td style="left-margin:20px;">成都市</td>
				<td style="left-margin:20px;">高新区</td>
				<td style="left-margin:20px;">${community.backup}</td>
			</tr>
		</c:forEach> 
        </table> 
        
        
    </body>