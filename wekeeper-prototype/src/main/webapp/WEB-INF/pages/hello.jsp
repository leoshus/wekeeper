<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#json").click(function(){
    		$.ajax({
        		url:"/wekeeper/demo/fetchUser",
        		success: function(data){
        			alert(data);
            		$("#jsoncontent").html(data);
            	},
            	headers:{
            		"Accept":"application/json"
            	}
        		});
    	});
    	
    	$("#xml").click(function(){
    		$.ajax({
        		url:"/wekeeper/demo/fetchUser",
        		success: function(data){
        			alert(data);
            		$("#xmlcontent").html(data);
            	},
            	headers:{
            		"Accept":"application/xml"
            	}
        		});
    	});
    	
    	$("#upUser").click(function(){
    		$.ajax({
    			type:"POSTµ",
        		url:"/wekeeper/demo/upUser",
        		data:{user:'{"username":"Jhon","password":"admin","age":23,"address":"beijing . changping"}'},
        		success: function(data){
        			alert(data);
            		
            	},
            	headers:{
            		"Content-Type":"application/json",
            		"Accept":"application/xml"
            	}
        		});
    	});
    });
    	
    </script>
</head>
<body>
	hello world!<br/>
	<button id='json'>fetchJson</button><br/>
<div id="jsoncontent"></div><br/>
<button id="xml">fetchXml</button><br/>
<div id="xmlContent"></div><br/>
<button id="upUser">upUser</button>
</body>
</html>