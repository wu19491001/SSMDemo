$(document).ready(function(){
	$(".cal-edit button").click(function(){
		let cal_view_inp=$("#cal-view-inp").val();
		$("#cal-view-inp").val(cal_view_inp+$(this).text());
	});
});