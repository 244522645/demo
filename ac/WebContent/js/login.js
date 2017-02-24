$(function() {
	$("#u").focusout(function() {

		var name = $("#u").val();
		if (!name) {
			$("#did").text("请输入帐号");
		}
		if (name) {
			$("#did").text("");
		}
	});

	$("#p").focusout(function() {

		var name = $("#p").val();
		if (!name) {
			$("#did").text("请输入密码");
		}
		if (name) {
			$("#did").text("");
		}
	});
	
	$("#loginbutton").click(function() {
		var uname = $("#u").val();
		var pname = $("#p").val();
		if (!uname) {
			$("#did").text("请输入帐号");
		}else if (!pname) {
			$("#did").text("请输入密码");
		}
		
		if(uname&&pname){
			param={
				'username':uname,
				'password':pname
			};
			$.post("./KUser/login.do",param,function(data){
				
				if(data==1){
					window.open("turnindex.do?url=index","_self");
				}else if(data ==2){
					$("#did").text("密码错误");
				}else if(data ==3){
					$("#did").text("帐号错误");
				}
			});
		}
	});
	
});


