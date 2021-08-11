$(function(){
	const username=$.cookie('username');
	const uid=$.cookie('uid');
	if(username==undefined || username==""){
		location.href="../login.html";
	}else{
	    $.post("../../user/finduserbyid",{
	     id:uid
	    },function(data){
	      if(data!=""){
	       let userj=eval('('+data+')');
	        $("#per-img").attr("src","../../img/head-portrait"+userj.head_portrait);
	        $("#sg-hd-username").text(userj.username);
	      }else{
	      
	      }
	    });
	}
	
	layui.use(['layer','table','element'], function(){
	  var table = layui.table
	  ,element = layui.element
	  ,layer=layui.layer;
	  table.render({
	    elem: '#daiban'
	    ,url:'../../user/findalluserfenye'
	    ,cols: [[
	      {field:'id', width:100, title: 'ID', sort: true}
	      ,{field:'username', width:200, title: '用户名'}
	      ,{field:'password', width:200, title: '性别'}
	      ,{field:'email', width:200, title: '城市'}
	      ,{field:'truename',title: '真实姓名'}
	    ]]
	    ,page: {
			layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
			      //,curr: 5 //设定初始在第 5 页
			      ,groups: 3 //只显示 1 个连续页码
		}
	  });
	});
	//退出登录
	$("#a_quit").click(function(){
		layer.confirm('确定退出登录？', {
		  title:'提示',
		  btn: ['确定','取消'] //按钮
		}, function(){
		  $.removeCookie('username', { path: '/' });
		  location.href="../login.html";
		}, function(){
		});
	});
});