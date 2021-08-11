$(document).ready(function(){
	//登录方式切换
	$("#title_np").css("background-color","rgba(0,0,0,.2)");
	$(".nav_logininfo_np").show();
	$("#title_np").click(function(){
		$("#title_phone").css("background-color","rgb(255,255,255)");
		$("#title_np").css("background-color","rgba(0,0,0,.2)");
		$(".nav_logininfo_phone").hide();
		$(".nav_logininfo_np").show();
	});
	$("#title_phone").click(function(){
		$("#title_phone").css("background-color","rgba(0,0,0,.2)");
		$("#title_np").css("background-color","rgb(255,255,255)");
		$(".nav_logininfo_np").hide();
		$(".nav_logininfo_phone").show();
	});
	//登录密码显示切换
	$(".login_i_psd_eye").click(function(){
		$(this).hide();
		$(".login_i_psd_eye_y").show();
		$("#password").attr("type","text");
	});
	$(".login_i_psd_eye_y").click(function(){
		$(this).hide();
		$(".login_i_psd_eye").show();
		$("#password").attr("type","password");
	});
	//发送验证码
	$(".login_i_text").click(function(){
		var yzmtime=60;
		$(".login_i_text").text("已发送"+yzmtime);
		$(".login_i_text").attr("disabled","disabled");
		var yzm=setInterval(function(){
			yzmtime--;
			$(".login_i_text").text("已发送"+yzmtime);
			if(yzmtime==0){
				$(".login_i_text").attr("disabled",false);
				$(".login_i_text").text("发送验证码");
				clearInterval(yzm);
			}else{
				$(".login_i_text").attr("disabled",true);
			}
		},1000);
	});
	//登录按钮点击
	//键盘回车监听
	$(document).keyup(function(event){
		if(event.keyCode ==13){
			$("#login_but_np").trigger("click");
		}
	});
	//账号密码登录
	$("#login_but_np").click(function(){
		var username=$("#username").val().trim();
		if(username==""){
			return;
		}
		var password=$("#password").val();
		if(password==""){
			return;
		}
		console.log(username+"---"+password);
	});
	//邮箱登录
	$("#login_but_phone").click(function(){
		
	});
});
