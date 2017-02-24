<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script>
function detailHtml(list){
	//alert("---"+list);
	var html = "";
	for(var i=0;i<list.length;i++){
		var money = '';
		if(list[i].type == '0'){ //支出
			money = '<div class="jineshuliang_l">-'+list[i].money+'</div>';
		}else{
			money = '<div class="jineshuliang_l">+'+list[i].money+'</div>';
		}
		
		html += '<div class="jinexiangqing_l">'+
						'<div class="jinrongxinxi_l">'+
							'<span class="jinrongxinxi_name_l">'+list[i].payType+'</span><br/>'+
							'<span class="jinrongxinxi_yver_l">'+new Date(list[i].createTime).Format('yyyy.MM.dd')+'</span>'+
						'</div>'+
						money+
				'</div>';
	}
	return html;
}

function loadData_myAccountList(){
	$.post(ctx+"/wechat/v3/myAccount/detail", 
			function(response){
				var list = eval(response);
				console.log(list);
				var html = detailHtml(list);
				if(html == ''){
				}else{
					$(".mingxiliebiao_l").html('');
					$(".mingxiliebiao_l").append(html);
				}
			}
	);
}

function withdrawCash(){
	var withdrawCash = $("#withdrawCash").val();
	if(isNull(withdrawCash)){
		$.toast("请输入提现金额！");
		return;
	}
	var balance = $("#balance").html();
	if(withdrawCash > 2000 ){
		$.toast("每笔提现金额不能够超过2000元！");
		return;
	}else if(withdrawCash > balance){
		$.toast("余额不足！");
		return;
	}else if(withdrawCash < 0){
		$.toast("金额格式错误！");
		return;
	}
	$.post(ctx+"/wechat/v3/myAccount/withdrawCash",{withdrawCash:withdrawCash},function(response){
		if(response == "success"){
			$.alert("提现成功！");
		}else{
			$.alert("提现失败！");
		}		
	}
	);
}

	
//初始化sui框架的方法
$(function(){
	$.init();
});
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	loadData_myAccountList();
});
</script>