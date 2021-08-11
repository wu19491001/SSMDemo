$(document).ready(function(){
	layui.use('layer',function(){
		var layer=layui.layer;
	});
	//登录方式切换
	let login_type=0;
	$("#title_np").css("background-color","rgba(0,0,0,.2)");
	$(".nav_logininfo_np").show();
	$("#title_np").click(function(){
		$("#title_email").css("background-color","rgb(255,255,255)");
		$("#title_np").css("background-color","rgba(0,0,0,.2)");
		$(".nav_logininfo_email").hide();
		$(".nav_logininfo_np").show();
		login_type=0;
	});
	$("#title_email").click(function(){
		$("#title_email").css("background-color","rgba(0,0,0,.2)");
		$("#title_np").css("background-color","rgb(255,255,255)");
		$(".nav_logininfo_np").hide();
		$(".nav_logininfo_email").show();
		login_type=1;
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
	
	//登录按钮点击
	//键盘回车监听
	$(document).keyup(function(event){
		if(event.keyCode ==13){
			switch (login_type){
				case 0:
				$("#login_but_np").trigger("click");
				break;
				case 1:
				$("#login_but_email").trigger("click");
				break;
			}	
		}
	});
	//监听输入框
	$("#username").on("input propertychange",function(){
		let usernamec=$(this).val();
		if(usernamec!=""){
			$(this).css("border-color","silver");
		}
	});
	$("#password").on("input propertychange",function(){
		let passwordc=$(this).val();
		if(passwordc!=""){
			$(this).css("border-color","silver");
		}
	});
	$("#email").on("input propertychange",function(){
		let emailc=$(this).val();
		if(emailc!=""){
			$(this).css("border-color","silver");
		}
	});
	$("#email-check").on("input propertychange",function(){
		let email_check=$(this).val();
		if(email_check!=""){
			$(this).css("border-color","silver");
		}
	});
	//账号密码登录
	$("#login_but_np").click(function(){
		console.log("普通登录");
		let username=$("#username").val().trim();
		if(username==""){
			layer.msg('用户名不能为空！',{
				icon:2,
				time:1200
			});
			$("#username").css("border-color","red");
			return;
		}
		let password=$("#password").val();
		if(password==""){
			layer.msg('密码不能为空！',{
				icon:2,
				time:1200
			});
			$("#password").css("border-color","red");
			return;
		}
		var indexload = layer.load(0, {shade: false});
			$.ajax({
			  type:'post',
			  url:'../user/loginuser',
			  data:{username:username,
			          password:password},
			  success:function(data){
			          if(data!=""){
						  let userj=eval('('+data+')');
						  layer.close(indexload);
			              layer.msg('登录成功',{
					                    icon:1,
					                    time:1200,
					                    end:function(){
										$.cookie('username',userj.username,{path:'/'});
										$.cookie('uid',userj.id,{path:'/'});
				                        location.href="../index.html";
				                        }
				         });
			          }else{
						  layer.close(indexload);
			              layer.msg('用户名或密码错误',{
					                    icon:2,
					                    time:1200
				         });
			          }
			  },
			  error: function (jqXHR, textStatus, errorThrown) {
			    console.log(jqXHR.responseText);
			    console.log(jqXHR.status);
			    console.log(jqXHR.readyState);
			    console.log(jqXHR.statusText);
			    console.log(textStatus);
			    console.log(errorThrown);
			}
			});
	});
	//邮箱登录
	//发送验证码
	$("#login_but_emailcaptcha").click(function(){
		let email=$("#email").val().trim();
		if(email==""){
			layer.msg('邮箱不能为空！',{
				icon:2,
				time:1200
			});
			$("#email").css("border-color","red");
			return false;
		}
		var yzmtime=60;
		$(this).text("已发送"+yzmtime);
		$(this).attr("disabled","disabled");
		
		$.ajax({
			type:'post',
			url:'../user/emailcaptcha',
			data:{email:email},
			success:function(data){
				console.log(data);
			}
		});
		var yzm=setInterval(function(){
			yzmtime--;
			$(this).text("已发送"+yzmtime);
			if(yzmtime==0){
				$(this).attr("disabled",false);
				$(this).text("发送验证码");
				clearInterval(yzm);
			}else{
				$(this).attr("disabled",true);
			}
		},1000);
	});
	$("#login_but_email").click(function(){
		let email=$("#email").val().trim();
		let captcha=$("#email-check").val().trim();
		if(email==""){
			layer.msg('邮箱不能为空！',{
				icon:2,
				time:1200
			});
			$("#email").css("border-color","red");
			return false;
		}
		if(captcha==""){
			layer.msg('验证码不能为空！',{
				icon:2,
				time:1200
			});
			$("#email-check").css("border-color","red");
			return false;
		}
		var indexloade = layer.load(0, {shade: false});
		$.ajax({
			type:'post',
			url:'../user/loginemail',
			data:{email:email,
			      captcha:captcha},
			success:function(data){
				if(data!=""){
					layer.close(indexloade);
					layer.msg('登录成功',{
					     icon:1,
					     time:1200,
					     end:function(){
							$.cookie('username',data,{path:'/'});	
					        location.href="../index.html";
					     }
					});
				}else{
					layer.close(indexloade);
					layer.msg('登录失败，验证码不正确或邮箱未注册！',{
						icon:2,
						time:2000
					});
				}
			}
		});
	});
});
