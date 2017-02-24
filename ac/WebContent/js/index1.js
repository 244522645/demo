var dlgupdate
$(function() {

	showtime();
	dlgupdate = $('#updatepassword').show().dialog({
		modal : true,
		draggable : true,
		closed : true
	});
	
	
	var c=0;
	$.post("/ac/KUserRoles/findbyUser.do", function(data) {
		if(data){
			$.each(data, function(i, n) {		
				if (!n.rightascription) {
					if(c==0){
						c=1;
						var menulist = "";
					
						menulist += '<li><img src="'+n.rightPic+'"  class="icon"  align="center"/><span style="color:rgb(255, 255, 255); margin-left: 20%;">'+n.rightName+'</span></li> ';
						menulist +='<ul class="submenu"><div class="expand-triangle" align="center"></div>';
						$.each(data, function(j, o) {
							var k=0;
							if( o.rightascription==n.rightId){
//								menulist += '<li style="list-style-type:none;margin-bottom: 10px;"  ><div align="left"><a class="easyui-linkbutton" data-options="iconCls:\''+o.rightPic+'\'"  align="center" href="javascript:changeIfarm(\'' + o.rightPage
//								+ '\')" >'
//								+ o.rightName + '</a></div></li> ';
								menulist +='<li  onclick="javascript:changeIfarm(\'' + o.rightPage
								+ '\')" ><span  style="color: black;" align="center" >'
							+ o.rightName +'</span></li>';
								if(k==0){
									$("#mainFrame").attr({"src":o.rightPage});
									k=1;	
								}
							}
							
						})
						menulist += '<ul>';
						
						$(".mainmenu").append(menulist);
						
					}else{
						var menulist = "";
					
						menulist += '<li><img src="'+n.rightPic+'"  class="icon"  align="center"/><span style="color: rgb(255, 255, 255); margin-left: 20%;"  >'+n.rightName+'</span></li> ';
						menulist +='<ul class="submenu" style="display:none;" align="center"><div class="expand-triangle"></div>';
						$.each(data, function(j, o) {
							if( o.rightascription==n.rightId){
//								menulist += '<li style="list-style-type:none;margin-bottom: 10px;"  ><div align="left"><a class="easyui-linkbutton" data-options="iconCls:\''+o.rightPic+'\'"  align="center" href="javascript:changeIfarm(\'' + o.rightPage
//								+ '\')" >'
//								+ o.rightName + '</a></div></li> ';
								menulist +='<li><span style="color: black;" align="center" onclick="javascript:changeIfarm(\'' + o.rightPage
								+ '\')" >'
							+ o.rightName +'</span></li>';
								
							}
							
						})
						menulist += '<ul>';
						
						$(".mainmenu").append(menulist);
					}
					
				}

			})
		}
		
	});
});


function changeIfarm(src){
	$("#mainFrame").attr("src",src);
}
//退出
function out(){
	$.messager.confirm('确认','您确认退出吗？',function(r){    
	    if (r){    
	    	$.post("./KUser/out.do",function(data){
	    		if(data==0){
	    			window.open("login.do",'_self');
	    		}
	    	});    
	    }    
	});  

	
}


//打开修改密码框
function updatePassword(){
	dlgupdate.dialog('open');
	$('#txtNewPass').val("");
	$('#txtRePass').val("");
}

//修改密码
function updateword(){
	var newpass= $('#txtNewPass').val();
	var repass=$('#txtRePass').val();
	if(!newpass&&!repass){
		$.messager.show({
			title:'提示消息',
			msg:'密码不能为空',
			timeout:5000,
			showType:'slide'
		});
		return;
	}else if(newpass!=repass){
		$.messager.show({
			title:'提示消息',
			msg:'输入不一致，请重新输入',
			timeout:5000,
			showType:'slide'
		});
		return;
	}else{
		$.post("./KUser/updatepassword.do",{newpass:newpass},function(data){
			dlgupdate.dialog('close');
			if(data==0){
				$.messager.show({
					title:'提示消息',
					msg:'密码修改成功',
					timeout:5000,
					showType:'slide'
				});
			}else{
				$.messager.show({
					title:'提示消息',
					msg:'密码修改失败，请联系管理员',
					timeout:5000,
					showType:'slide'
				});
			}
			
		});
	}
}

//时间
function showtime()   
{   
	

var today;  
var hour;  
var second;  
var minute;  
var year;  
var month;  
var date;  
var strDate;   
today=new Date();   
var n_day = today.getDay();   
switch (n_day)   
{   
case 0:{   
strDate = "星期日"   
}break;   
case 1:{   
strDate = "星期一"   
}break;   
case 2:{   
strDate ="星期二"   
}break;   
case 3:{   
strDate = "星期三"   
}break;   
case 4:{   
strDate = "星期四"   
}break;   
case 5:{   
strDate = "星期五"   
}break;   
case 6:{   
strDate = "星期六"   
}break;   
case 7:{   
strDate = "星期日"   
}break;   
}   
year = today.getYear()+1900;   
month = today.getMonth()+1;   
date = today.getDate();   
hour = today.getHours();   
minute =today.getMinutes();   
second = today.getSeconds();   
if(month<10) month="0"+month;   
if(date<10) date="0"+date;   
if(hour<10) hour="0"+hour;   
if(minute<10) minute="0"+minute;   
if(second<10) second="0"+second;   
document.getElementById('clock').innerHTML = year + "年" + month + "月" + date + "日 " + strDate +" " + hour + ":" + minute + ":" + second;   
setTimeout("showtime();", 1000);   
} 
