<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>wekeeper</title>
<script src="/wekeeper/static/js/jquery-1.9.1.min.js" type="text/javascript" ></script>
<link rel="stylesheet" type="text/css" href="/wekeeper/static/css/login.css"/>
</head>
<body>
<div class='signup_container'>
    <h1 class='signup_title'>wekeeper</h1>
    <img src='/wekeeper/static/images/login/people.png' id='admin'/>
    <div id="signup_forms" class="signup_forms clearfix">
            <form class="signup_form_form" id="signup_form" method="post" action="/wekeeper/landing/check">
                    <div class="form_row first_row">
                        <label for="signup_email">请输入用户名</label><div class='tip ok'></div>
                        <input type="text" name="username" placeholder="请输入用户名" id="signup_name" data-required="required">
                    </div>
                    <div class="form_row">
                        <label for="signup_password">请输入密码</label><div class='tip error'></div>
                        <input type="password" name="password" placeholder="请输入密码" id="signup_password" data-required="required">
                    </div>
                    <div class="form_row">
	                        <img id="verifyCode" alt="verifyCode"  src="<%=request.getContextPath()%>/landing/fetchVerifyCode">
	                        <input type="text" name="verifyCode" data-required="required" style="width:50%;display:inline;"/>
                    </div> 
           </form>
    </div>

    <div class="login-btn-set">
    	<div class='rem'>记住我</div> <a href='javascript:void(0)' id="loginSubmit" class='login-btn'></a>
    </div>
    <p class='copyright'>版权所有 sdw2330976</p>
</div>

</body>

<script type="text/javascript">
<!--
function reloadVerifyCode(){
	alert('ok');
	document.getElementById('verifyCode').setAttribute('src','${pageContext.request.contextPath}/landing/fetchVerifyCode');
}
//-->
$(function(){
	$("#verifyCode").click(function(){
		$("#verifyCode").attr("src","/wekeeper/landing/fetchVerifyCode?"+Math.random());
	});
	$("#loginSubmit").click(function(){
		$("#signup_form").submit();
	});

    $('.rem').click(function(){
        $(this).toggleClass('selected');
    });

    $('#signup_select').click(function(){
        $('.form_row ul').show();
        event.cancelBubble = true;
    });

    $('#d').click(function(){
        $('.form_row ul').toggle();
        event.cancelBubble = true;
    });

    $('body').click(function(){
        $('.form_row ul').hide();
    });

    $('.form_row li').click(function(){
        var v  = $(this).text();
        $('#signup_select').val(v);
        $('.form_row ul').hide();
    });


});


</script>

</html>