  
<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
  <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="saveCommunity.htm" method="post">
    小区名称：<input type="text" name="communityName" value="communityName" /><br/>
    省份：四川<br/>
    城市:成都<br/>
 区:武侯区<br/>
备注 :<input type="text" name="backup" value="backup" />
    <input type="submit" value="保存" />
</form>
</body>
</html>