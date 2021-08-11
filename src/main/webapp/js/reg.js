$(function(){
	//定义变量
	var username_flag=false;
	var password_flag=false;
	var email_flag=false;
	var truename_flag=false;
	//用户名验证
	$("#username").on("input propertychange",function(){
		let username_inp=$(this).val().trim();
		$(".reg_i_name_c").hide();
		if(username_inp!=""){
			$.post("../user/finduserbyname",{
				username:username_inp
			},function(data){
				
				if(data=="success"){
					username_flag=false;
					$(".reg_i_name_no").show();
				}else{
					username_flag=true;
					$(".reg_i_name_yes").show();
				}
			});
		}
	});
	//验证密码
	$("#password").on("input propertychange",function(){
		$(".reg_i_psd_c").hide();
		let password_inp=$("#passwordt").val();
		if(password_inp!=""){
			let password_inpt=$(this).val();
			if(password_inpt!=""){
				if(password_inp!=password_inpt){
					password_flag=false;
					$(".reg_i_psd1_no").show();
					$(".reg_i_psd2_no").show();
				}else{
					password_flag=true;
					$(".reg_i_psd1_yes").show();
					$(".reg_i_psd2_yes").show();
				}
			}
		}
		
	});
	$("#passwordt").on("input propertychange",function(){
		$(".reg_i_psd_c").hide();
		let password_inp=$("#password").val();
		if(password_inp!=""){
			let password_inpt=$(this).val();
			if(password_inpt!=""){
				if(password_inp!=password_inpt){
					password_flag=false;
					$(".reg_i_psd1_no").show();
					$(".reg_i_psd2_no").show();
				}else{
					password_flag=true;
					$(".reg_i_psd1_yes").show();
					$(".reg_i_psd2_yes").show();
				}
			}
		}
		
	});
	
	//邮箱验证
	$("#email").on("input propertychange",function(){
		let email_inp=$(this).val().trim();
		$(".reg_i_email_c").hide();
		if(email_inp!=""){
			$.post("../user/finduserbyemail",{
				email:email_inp
			},function(data){	
				if(data=="success"){
					email_flag=false;
					$(".reg_i_email_no").show();
				}else{
					email_flag=true;
					$(".reg_i_email_yes").show();
				}
			});
		}
	});
	//真实姓名验证
	$("#email").on("input propertychange",function(){
		let truename=$("#truename").val();
		if(truename!=""){
			$(this).css("border-color","silver");
		}
	});
	//提交注册
	$("#reg_but_np").click(function(){
		let truename=$("#truename").val();
		if(truename==""){
			truename_flag=false;
			layer.msg('真实姓名不能为空！',{
				icon:2,
				time:1200
			});
			$("#truename").css("border-color","red");
			return;
		}else{
			truename_flag=true;
		}
		if(username_flag && password_flag && email_flag && truename_flag){
			let username=$("#username").val().trim();
			let password=$("#passwordt").val();
			let email=$("#email").val().trim();
			$.post("../user/insertuser",{
				username:username,
				password:password,
				email:email,
				truename:truename
			},function(data){
				if(data=="success"){
					layer.msg('注册成功！',{
						icon:1,
						time:1200,
						end:function(){
							$("#username").val("");
							$("#password").val("");
							$("#passwordt").val("");
							$("#email").val("");
							$("#truename").val("");
							$(".reg_i_email_c").hide();
							$(".reg_i_psd_c").hide();
							$(".reg_i_name_c").hide();
						}
					});
				}else{
					layer.msg('注册失败，请稍后重试！',{
						icon:2,
						time:1200

					});
				}
			})
		}else{
			layer.msg('提交失败，请检查信息！',{
				icon:2,
				time:1200
			});
		}
	});
	localStorage.setItem("xiaohu","狐臭文");
	console.log(localStorage.getItem("xiaohu"));
	
});