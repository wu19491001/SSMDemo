$(function(){
	layui.use(['form', 'layedit', 'laydate','layer', 'jquery'], function(){
	  var form = layui.form
	  ,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  
	  //日期
	  laydate.render({
	    elem: '#birthday'
	  });
	});
	//用户id
	const uid=$.cookie('uid');
	//获取用户信息
	$.post("../user/finduserbyid",{
		id:uid
	},function(data){
		if(data!=""){
			let userj=eval('('+data+')');
			$("#id").val(userj.id);
			$("#username").val(userj.username);
			$("#truename").val(userj.truename);
			$("#email").val(userj.email);
			$("#createdate").val(userj.createdate);
		}else{
			layer.msg('获取用户信息失败，请联系管理员',{
				icon:2,
				time:1200
			});
		}
	});
	//加载用户信息
	//三级联动
	$.get("../data/sc.json",function(data){
		$("#province").empty();
		$("#province").append("<option value=''>请选择省</option>");
		for(let i=0;i<data.length;i++){
			$("#province").append("<option value='"+i+"'>"+data[i].name+"</option>");
		}
		layui.use(['form'],function(){
			 let form = layui.form;
			 form.render();
		});
			
	});
	var provinceindex;
	layui.use(['form','jquery','layer'],function(){
		let layer = layui.layer,
			$ = layui.jquery,
			form = layui.form;
			form.on('select(province)', function(data){
				$.get("../data/sc.json",function(allcity){
					$("#city").empty();
					$("#city").append("<option value=''>请选择市</option>");
					provinceindex=data.value;
					console.log(allcity[data.value].children.length);
					for(let i=0;i<allcity[data.value].children.length;i++){
						$("#city").append("<option value='"+i+"'>"+allcity[data.value].children[i].name+"</option>");
					}
					layui.use(['form'],function(){
						 let form = layui.form;
						 form.render();
					});		
				});
			});
	});
	layui.use(['form','jquery','layer'],function(){
		let layer = layui.layer,
			$ = layui.jquery,
			form = layui.form;
			form.on('select(city)', function(data){
				$.get("../data/sc.json",function(allcounty){
					$("#county").empty();
					$("#county").append("<option value=''>请选择县/区</option>");
					console.log(allcounty[data.value].children.length);
					for(let i=0;i<allcounty[provinceindex].children[data.value].children.length;i++){
						$("#county").append("<option value='"+i+"'>"+allcounty[provinceindex].children[data.value].children[i].name+"</option>");
					}
					layui.use(['form'],function(){
						 let form = layui.form;
						 form.render();
					});		
				});
			});
	});
	$.get("../user/findalldep",function(data){
		$("#department").empty();
		if(data!=""){
			let alldep=eval('('+data+')');
            $("#department").append("<option value=''>请选择所属部门</option>");
			for(let i=0;i<alldep.length;i++){
				$("#department").append("<option value='"+alldep[i].id+"'>"+alldep[i].depname+"</option>");
			}
				layui.use(['form'],function(){
					 let form = layui.form;
					 form.render();
				});
		}else{
			$("#department").append("<option value=''>获取部门失败，请稍后重试</option>");
				layui.use(['form'],function(){
					 let form = layui.form;
					 form.render();
				});
		}
	})
	//头像预览
	$("#head_portrait").change(function(e){
		let file=e.target.files || e.dataTransfer.files;
		if(file){
			let reader=new FileReader();
			reader.onload=function(){
				$("#img-sel").attr("src", this.result);
			}
			reader.readAsDataURL(file[0]);
		}
	});
	//确认
	$("#btn-per").click(function(){
		let province=$("#province").find("option:selected").text();
		let city=$("#city").find("option:selected").text();
		let county=$("#county").find("option:selected").text();
		let address=(city=="市辖区"?province+"-"+county:province+"-"+city+"-"+county);
		var formdata=new FormData();
		formdata.append("id",uid);
		formdata.append("department",$("#department").find("option:selected").text());
		formdata.append("phone",$("#phone").val());
		formdata.append("sex",$("input[name='sex']:checked").val());
		formdata.append("address",address);
		formdata.append("birthday",$("#birthday").val());
		formdata.append("head_portrait",$("#head_portrait")[0].files[0]);
		$.ajax({
			url:'http://localhost:8080/wu/user/updateuserinfo',	
			type:'post',
			data:formdata,
			processData:false,
			contentType:false,
			success:function(data){
				if(data=="success"){
					layer.msg('更新成功！',{
						icon:1,
						time:1200
					});
				}else{
					layer.msg('更新失败！',{
						icon:2,
						time:1200
					});
				}
				
			}
		});
	});
	
});