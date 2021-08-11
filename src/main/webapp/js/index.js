$(document).ready(function(){
	//判断是否登录
	const username=$.cookie('username');
	const uid=$.cookie('uid');
	if(username==undefined || username==""){
		location.href="html/login.html";
	}else{
	    $.post("user/finduserbyid",{
	     id:uid
	    },function(data){
	      if(data!=""){
	       let userj=eval('('+data+')');
	        $("#per-img").attr("src","img/head-portrait"+userj.head_portrait);
	        $("#sg-hd-username").text(userj.username);
	      }else{
	      
	      }
	    });
	}
	
	layui.use('layer',function(){
		var layer=layui.layer;
	});
	layui.use('element', function(){
	  var element = layui.element;
	});
	//退出登录
	$("#a_quit").click(function(){
		layer.confirm('确定退出登录？', {
		  title:'提示',
		  btn: ['确定','取消'] //按钮
		}, function(){
		  $.removeCookie('username', { path: '/' });
		  $.removeCookie('uid', { path: '/' });
		  location.href="html/login.html";
		}, function(){
		});
	});
	//汽车金融
	var bili=30;
	var qishu=12;
	layui.use('slider', function(){
	  var $ = layui.$
	  ,slider = layui.slider;
	  slider.render({
	      elem: '#slidebl'
		  ,step: 10 //步长10
	      ,value: 20 //初始值
		  ,min:10
		  ,max:70
		  ,setTips: function(value){ //自定义提示文本
		       return value + '%';
		      }
		  ,change: function(value){
		      $('#configure-tips-bl').html(value+'%');
			  bili=value;
		    }
	    });
		slider.render({
		    elem: '#slideqx'
			  ,step: 12 //步长10
		    ,value: 12 //初始值
			  ,min:12
			  ,max:60
			  ,setTips: function(value){ //自定义提示文本
			       return value + '期';
			      }
			  ,change: function(value){
				      $('#configure-tips-qx').html(value+'期');
					  qishu=value;
				    }
		  });
	});
	//轮播图
	var mySwiper = new Swiper ('.swiper-container', {
	    loop: true, // 循环模式选项
		autoplay:{
			delay:5000,
			stopOnLastSlide:true,
			disableOnInteraction:true,
		},
	    cssMode:true,
	    // 如果需要分页器
	    pagination: {
	      el: '.swiper-pagination',
	    }, 
	    // 如果需要前进后退按钮
	    navigation: {
	      nextEl: '.swiper-button-next',
	      prevEl: '.swiper-button-prev',
	    },
		mousewheel:true,
		keyboard:true,
	  }) ;  
	//汽车金融计算
	$("#car-start").click(function(){
		let amount=$("#car-price").text();
		let biliq=bili;
		let qishuq=qishu;
		let pailiang=$("input[name='pailiang']:checked").val();
		let zuowei=$("input[name='zuowei']:checked").val();
		amountCalculation(amount,bili,qishuq,pailiang,zuowei);
	});
});
//金额计算
function amountCalculation(amount,biliq,qishuq,pailiang,zuowei){
	console.log("总价"+amount+"比例"+biliq+"期数"+qishuq+"排量"+pailiang+"座位"+zuowei);
	let baoxian=950;
	let gouzhishui=0;
	let totalamount=0;
	gouzhishui=(amount/1.13*0.1).toFixed(2);
    totalamount=Number(baoxian)+Number(gouzhishui)+Number(amount);
	let shoufueg=(Number(amount)*Number(biliq)/100).toFixed(2);
	let shoufu=((Number(amount)*Number(biliq)/100)+Number(baoxian)+Number(gouzhishui)).toFixed(2);
	let daikuan=(Number(amount)-Number(shoufueg)).toFixed(2);
	let huankuan=((Number(daikuan)+(Number(daikuan)*((Number(qishuq)/12)*0.05)))/Number(qishuq)).toFixed(2);
	$("#tb-carinfo").html("");
	$(".car-info").show();
	$("#tb-carinfo").append("<tr><td class='tb-infotype'>车型</td>"+
								"<td class='tb-infocass'>"+$("#car-type").text()+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>指导价</td>"+
								"<td class='tb-infocass'>"+$("#car-price").text()+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>金融机构</td>"+
								"<td class='tb-infocass'>水管金融</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>排量</td>"+
								"<td class='tb-infocass'>"+pailiang+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>购置税</td>"+
								"<td class='tb-infocass'>"+gouzhishui+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>商业险</td>"+
								"<td class='tb-infocass'>950</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>车辆总价</td>"+
								"<td class='tb-infocass'>"+totalamount+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>首付比例</td>"+
								"<td class='tb-infocass'>"+biliq+"%</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>首付金额</td>"+
								"<td class='tb-infocass'>"+shoufu+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>贷款金额</td>"+
								"<td class='tb-infocass'>"+daikuan+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>还款期数(月)</td>"+
								"<td class='tb-infocass'>"+qishuq+"</td>"+
							"</tr>"+
							"<tr><td class='tb-infotype'>每月还款</td>"+
								"<td class='tb-infocass'>"+huankuan+"</td>"+
							"</tr>");
}
