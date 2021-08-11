$(function(){
	const username=$.cookie('username');
	const uid=$.cookie('uid');
	if(username==undefined || username==""){
		//location.href="../login.html";
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
	layui.use(['form','table','layer'], function(){
	  var table = layui.table
	  ,layer = layui.layer
	  ,form = layui.form;
	  table.render({
	    elem: '#daiban'
	    ,url:'../user/findalluser'
	    ,cols: [[
	      {field:'id', width:100, title: 'ID', sort: true}
	      ,{field:'username', width:200, title: '用户名'}
	      ,{field:'password', width:200, title: '性别'}
	      ,{field:'email', width:200, title: '城市'}
	      ,{field:'truename',title: '签名'}
	    ]]
	    ,page: {
			layout: ['count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
			      //,curr: 5 //设定初始在第 5 页
			      ,groups: 3 //只显示 1 个连续页码
		}
	  });
	});
});