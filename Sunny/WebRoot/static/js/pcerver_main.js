/**
* 订单类
*/
var Order = function Order(id,orderNumber,time,cardNumber,serverNames,address,vehicleNumber,userPhone,orderStatus){
	this.id=id;
	this.orderNumber=orderNumber;
	this.time=time;
	this.cardNumber=cardNumber;
	this.serverNames=serverNames;
	this.address=address;
	this.vehicleNumber=vehicleNumber;
	this.userPhone=userPhone;
	this.orderStatus=orderStatus;
}

var timer = 4;
function closepop(){
	timer--;
	if(timer == 0){
		$("#pop").fadeOut(200);
	}
}

function showTakeDialog(div,mssage,btn,sta){
	div.children().eq(0).remove();
	div.prepend("<div class='list_hover'></div>");
	div.children().eq(0).append("<p>"+mssage+"</p>");
	div.children().eq(0).append("<div class='btn_quxiao_queren2'></div>");
	div.children().eq(0).children().eq(1).append("<a class='btn_quxiao_yes'>"+btn+"</a>");
	div.children().eq(0).children().eq(1).children().eq(0).click(function(){
		$(this).parent().parent().remove();
		if(sta==1)
			div.remove();
		else
			div.children().eq(0).show();
	});
}

function showFuseDialog(div,mssage,btn,sta){
	div.prepend("<div class='list_hover'></div>");
	div.children().eq(0).append("<p>"+mssage+"</p>");
	div.children().eq(0).append("<div class='btn_quxiao_queren2'></div>");
	div.children().eq(0).children().eq(1).append("<a class='btn_quxiao_yes'>"+btn+"</a>");
	div.children().eq(0).children().eq(1).children().eq(0).click(function(){
		$(this).parent().parent().remove();
		if(sta==1)
			div.remove();
		else
			div.children().eq(0).show();
	});
}

//弹出提示框
Order.prototype.showDialog = function(message,showTime){
	//提示框关闭事件
	$("#popClose").click(function(){
	  	$('#pop').hide();
	});
	
	$("#popIntro").html(message); 
	$('#pop').slideDown(200);
	//声音提示
	niftyplayer('niftyPlayer1').play();
	setInterval(closepop,showTime);
}

var hideList = [];
var numberofclick_qiangdan = 0;
//抢单
Order.prototype.takeOrder = function(order){
	var div = $("#"+order.id);
	div.prepend("<img src='"+ctx+"/static/images/loading.gif' class='right_load'/>");
	div.children().eq(1).hide();
	$.ajax({
		url:ctx+"/console/pc/rescue/orderHandle.do",
		type:'POST',
		data:"orderNumber="+order.orderNumber+"&action=takeOrder",
		success:function(data){
			if(data instanceof Object ){
				switch (data.status) {
				case 1:
					var list_posX = div.eq(0).offset().left;
					var list_posY = div.eq(0).offset().top;
					div.animate({left:-150+75-list_posX+"px",top:-70+265-list_posY+"px",opacity:"0"},1000,function(){
						numberofclick_qiangdan--;
						hideList.push(div);
						if(numberofclick_qiangdan == 0){
							for(var j=0; j<hideList.length; j++){
								hideList[j].remove();
							}
							hideList = [];
						}
					});
					break;
				case 0:
					showTakeDialog(div,"系统错误","重新抢单");
					break;
				case 2:
					showTakeDialog(div,"订单已被抢","关闭",1);
					break;
				}
			}else{
				alert("抢单失败！，系统返回错误数据："+data);
			}
		}
	});
}

//拒单
Order.prototype.refuseOrder = function(order){
	var div = $("#"+order.id);
	div.prepend("<div class='list_hover'></div>");
	div.children().eq(0).css("height",parseInt(div.css("height").slice(0,-2))+26+"px");
	div.children().eq(0).css("width",parseInt(div.css("width").slice(0,-2))+26+"px");
	div.children().eq(0).append("<p>确定放弃订单吗？</p>");
	div.children().eq(0).append("<div class='btn_quxiao_queren'></div>");
	div.children().eq(0).children().eq(1).append("<a class='btn_quxiao_yes' style='margin-right:20px;'>是</a>");
	div.children().eq(0).children().eq(1).append("<a class='btn_quxiao_yes'>否</a>");
	div.children().eq(0).children().eq(1).children().eq(1).click(function(){
		$(this).parent().parent().remove();
		return;
	});
	div.children().eq(0).children().eq(1).children().eq(0).click(function(){
		$(this).parent().children().eq(1).remove();
		$(this).parent().children().eq(0).remove();
		div.children().eq(0).children().eq(1).prepend("<img src='"+ctx+"/static/images/loading.gif' style='position:relative; width:30px; top:10px; margin:0 auto;'/>");
		$.ajax({
			url:ctx+"/console/pc/rescue/orderHandle.do",
			type:'POST',
			data:"orderNumber="+order.orderNumber+"&action=refuse",
			success:function(data){
				if(data instanceof Object ){
					switch (data.status) {
					case 1:
						div.remove();
						break;
					case 0:
						alert(data.message);
						break;
					case 2:
						alert(data.message);
						div.remove();
						break;
					}
				}else{
					alert("拒单失败！，系统返回错误数据："+data);
				}
			}
		});
	});

}

//派车
Order.prototype.sendCar = function(order){
	var div = $("#"+order.id);
	div.prepend("<div class='list_hover'></div>");
	div.children().eq(0).append("<p>您确定要派车吗？</p>");
	div.children().eq(0).append("<div class='btn_quxiao_queren'></div>");
	div.children().eq(0).children().eq(1).append("<a class='btn_quxiao_yes' style='margin-right:20px;'>是</a>");
	div.children().eq(0).children().eq(1).append("<a class='btn_quxiao_yes'>否</a>");
	div.children().eq(0).children().eq(1).children().eq(1).click(function(){
		$(this).parent().parent().remove();
		return;
	});
	div.children().eq(0).children().eq(1).children().eq(0).click(function(){
		$(this).parent().parent().remove();
		div.children().eq(1).hide();
		div.children().eq(0).hide();
		div.prepend("<img src='"+ctx+"/static/images/loading.gif' class='right_load'/>");
		$.ajax({
			url:ctx+"/console/pc/rescue/orderHandle.do",
			type:'POST',
			data:"orderNumber="+order.orderNumber+"&action=sendCar",
			success:function(data){
				if(data instanceof Object ){
					switch (data.status) {
					case 1:
						div.children(".btn_diaodu").remove();
						div.children(".btn_quxiao").remove();
						div.children(".right_load").remove();
						div.prepend("<div class='btn_dengdai'>请等待用户支付</div>");
						break;
					case 0:
						alert(data.message);
						div.children().eq(0).remove();
						div.children().eq(0).show();
						div.children().eq(1).show();
						break;
					case 2:
						alert(data.message);
						div.remove();
						break;
					}
					
				}else{
					alert("拒单失败！，系统返回错误数据："+data);
				}
			}
		});
	});
}

/**
* 订单列表
*/
orderApply = {
	orderList: new Array(), //本地订单数组
	newOrderList : new Array(),  //更新之后最新的订单数组
	overdueOrderList : new Array(), //更新之后过期的订单数组
	
	
	pushOrder:function(order){
		this.orderList.push(order);
	},
	
	resetOrderList:function(orderList){ //重置订单列表
		this.newOrderList = new Array();
		this.overdueOrderList = new Array();
		for(i=0;i<orderList.length;i++){
			var flag = true;
			for(j=0;j<this.orderList.length;j++){
				if(orderList[i].id == this.orderList[j].id){
					flag = false;
				}
			}
			if(flag){
				this.newOrderList.push(orderList[i]);
			}
		}
		for(i=0;i<this.orderList.length;i++){
			var flag = true;
			for(j=0;j<orderList.length;j++){
				if(this.orderList[i].id == orderList[j].id){
					flag = false;
				}
			}
			if(flag){
				this.overdueOrderList.push(this.orderList[i]);
			}
		}
		this.orderList = orderList;	
	}

}