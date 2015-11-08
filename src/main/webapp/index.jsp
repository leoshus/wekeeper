<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>bootstrap demo</title>
	<!-- Bootstrap -->
	<link href="static/css/boostrap.min.css" rel="stylesheet">
	<!-- 以下两个插件用于在IE8以及以下版本浏览器支持HTML5元素和媒体查询，如果不需要用可以移除 -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script type="text/javascript">
    	$("#json").click(function(){
    		$.ajax({
        		url:"/demo/fetchUser",
        		success: function(data){
            		$("#jsoncontent").html(data);
            	},
            	headers:{
            		"Accept":"application/json"
            	}
        		});
    	});
    	
    	$("#xml").click(function(){
    		$.ajax({
        		url:"/demo/fetchUser",
        		success: function(data){
            		$("#xmlcontent").html(data);
            	},
            	headers:{
            		"Accept":"application/json"
            	}
        		});
    	});
    </script>
</head>
<body>
<h2>Hello World!</h2>
<button id='json'>fetchJson</button>
<div id="jsoncontent"></div>
<button id="xml">fetchXml</button>
<div id="xmlContent"></div>
<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
<script src="http://libs.baidu.com/jquery/1.9.0/jquery.min.js"></script>
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> 
</body>
</html>
