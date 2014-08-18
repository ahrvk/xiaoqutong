<!-- Copyright : adobocode.com , 2010 -->
  
<%@ page language="java" session="false" 
    contentType="text/html; charset=UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
  
<html> 
    <head> 
        <title>Adobocode : Sample Spring MVC using JSTL iteration</title> 
    </head> 
    <body> 
        <h2>Message Binding List</h2> 
        <table border="1"> 
          
            <c:forEach var="entry" items="${personMap}">
			<tr>
				<td>${entry.key}</td>
				<td style="left-margin:20px;">${entry.value}</td>

			</tr>
		</c:forEach> 
        </table> 
    </body>