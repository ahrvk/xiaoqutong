<!-- Copyright : adobocode.com , 2010 -->
  
<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
  
<html> 
    <head> 
        <title></title> 
    </head> 
    <body> 
    
        <h2>用户列表</h2> 
        <table border="1"> 
          <tr>
				<td>id</td>
				<td style="left-margin:20px;">用户名</td>
				<td style="left-margin:20px;">email</td>
				<td style="left-margin:20px;">手机</td>
				<td style="left-margin:20px;">小区Id</td>
				<td style="left-margin:20px;">栋</td>
				<td style="left-margin:20px;">单元</td>
				<td style="left-margin:20px;">楼层</td>
				<td style="left-margin:20px;">门牌号</td>
				<td style="left-margin:20px;">真名</td>
				<td style="left-margin:20px;">绑定信息</td>
				<td>操作</td>
			</tr>
            <c:forEach var="user" items="${users}">
			<tr>
				<td>${user.id}</td>
				<td style="left-margin:20px;">${user.name}</td>
				<td style="left-margin:20px;">${user.email}</td>
				<td style="left-margin:20px;">${user.mobile}</td>
				<td style="left-margin:20px;">${user.communityId}</td>
				<td style="left-margin:20px;">${user.buildingId}</td>
				<td style="left-margin:20px;">${user.unitId}</td>
				<td style="left-margin:20px;">${user.floorNo}</td>
				<td style="left-margin:20px;">${user.roomNo}</td>
				<td style="left-margin:20px;">${user.realName}</td>
				<td style="left-margin:20px;">${user.bindInfo}</td>
				<td>
				<a href="<c:url value="sendMessage.htm?message_type=0&title=这是来自小区APP测试信息&description=这是来自小区APP测试信息的description&senderId=5&senderName=admin&receiveId=${user.id}&receiveName=${user.name}&userType=1&processUrl=www.baidu.com"/>">测试消息 </a>
				<a href="<c:url value="sendMessage.htm?message_type=1&title=这是来自小区APP测试信息&description=这是来自小区APP测试信息的description&senderId=5&senderName=admin&receiveId=${user.id}&receiveName=${user.name}&userType=1&processUrl=www.baidu.com"/>">测试通知 </a>
				</td>
			</tr>
		</c:forEach> 
        </table> 
        
        
    </body>