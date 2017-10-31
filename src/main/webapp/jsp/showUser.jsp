<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <title>test</title>  
    <script>
    function displayDate(){
    	document.getElementById("demo").innerHTML=Date();
    }
    </script>
  </head>  
    
  <body>  
    ${user.userName} 
    <p id="demo">this is a paragraph</p>
    <button type="button" onclick="displayDate()">displayDate</button>
    
  </body>  
</html>  