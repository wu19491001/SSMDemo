$(function(){
	//用户id
	const uid=$.cookie('uid');
	//获取用户信息
	$.post("../user/finduserbyid",{
		id:uid
	},function(data){
		if(data!=""){
			let userj=eval('('+data+')');
			$("#id").text(userj.id);
			$("#username").text(userj.username);
			$("#truename").text(userj.truename);
			$("#email").text(userj.email);
			$("#createdate").text(userj.createdate);
			$("#address").text(userj.address);
			$("#sex").text(userj.sex);
			$("#department").text(userj.department);
			$("#birthday").text(userj.birthday);
			$("#phone").text(userj.phone);
			$("#head_portrait").attr("src","../img/head-portrait"+userj.head_portrait);
		}else{
			layer.msg('获取用户信息失败，请联系管理员',{
				icon:2,
				time:1200
			});
		}
	});
	$("#btn-editper").click(function(){
		location.href="../html/editperinfo.html";
	})
});