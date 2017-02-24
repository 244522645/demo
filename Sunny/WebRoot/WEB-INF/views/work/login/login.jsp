<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>${project}</title>
 		 <!--引用地区选择插件-->
 		 	<link rel="stylesheet" type="text/css" href="${ctx}/static/css/chxt/areaselect.css" />
        <script type="text/javascript" src="${ctx}/static/js/chxt/data.js"></script>
        <script type="text/javascript" src="${ctx}/static/js/chxt/areaselect.js"></script>
		<!-- 让IE9一下支持HTML5元素和媒体查询 -->
		<!--[if lt IE 9]>
      		<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      		<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    		<![endif]-->

	</head>

	<body class="body">
		<div class="container-fluid	">
			<!-- 用户登录框 -->
			<div class="row">
				<div class="col-xs-10 col-xs-offset-1 col-sm-4 col-sm-offset-4 loginbox">
					<h3 class="text-center header">欢迎使用${project}</h3>
					<fieldset class="form-horizontal" id="loginInfo">
						<!-- 账号输入框 -->
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-user"></span>
								</div>
								<input type="text" class="form-control" id="user_name" placeholder="账号" rule="_must">
							</div>
						</div>
						<!-- 密码输入框 -->
						<div class="form-group">
							<div class="input-group">
								<div class="input-group-addon">
									<span class="glyphicon glyphicon-lock"></span>
								</div>
								<input type="password" class="form-control" id="password"  placeholder="密码"  rule="_must">
							</div>
						</div>
						<!-- 登录按钮-->
						<div class="form-group">
							<button type="submit" id="submit" class="btn btn-primary btn-block">登录</button>
						</div>
						<!-- 记住密码 | 注册 | 找回密码行-->
						<div class="row">
							<div class="col-sm-6 col-xs-6 footlink">
								<div class="checkbox passwordbtn">
									<label>
										<input type="checkbox"  id="saveCookie">记住密码
									</label>
								</div>
							</div>
							<div class="col-sm-6 col-xs-6 text-right footlink">
								<!-- 点击触发模态框 -->
								<a href="#" data-toggle="modal" data-target="#myModal">注&nbsp;册</a>&nbsp;|
								<a href="#" data-toggle="modal" data-target="#mypassword">找回密码</a>
							</div>
						</div>
					</fieldset>
				</div>
			</div>
		</div>
		<!-- 注册模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!-- 模态框标题 -->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">商家入驻资料提交</h4>
					</div>
					<!-- 内容表单 -->
					<div class="modal-body row">
						<form class="form-horizontal" id="busInfo">
							<div class="form-group">
								<label for="tel" class="col-sm-2 col-sm-offset-1 control-label" >手机号</label>
								<div class="col-sm-8">
									<input type="tel" class="form-control" id="phone" maxlength="11" name="phone" 
									rule="must_accountphone" placeholder="手机号">
								</div>
							</div>
							<div class="form-group">
								<label for="number" class="col-sm-2 col-sm-offset-1 control-label">验证码</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="code"  rule="must_code"
									maxlength="6" name="code" placeholder="输入短信验证码">
								</div>
								<div class="col-sm-4">
									<button type="button" class="btn btn-default" onclick="sendShortMessage('phone')">发送验证码</button>
								</div>
							</div>
							<div class="form-group">
								<label for="password1" class="col-sm-2 col-sm-offset-1 control-label">密码</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="password1" name="password"
									rule="must_password" maxlength="20" placeholder="输入密码至少6位">
								</div>
							</div>
							<div class="form-group">
								<label for="password1" class="col-sm-2 col-sm-offset-1 control-label">确认密码</label>
								<div class="col-sm-8">
									<input type="password" class="form-control" id="password2" placeholder="请再次输入密码"
									rule="must_password" maxlength="20">
								</div>
							</div>
							<div class="form-group">
								<label for="text4" class="col-sm-2 col-sm-offset-1 control-label">联系人</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="person" name="contactPerson" placeholder="联系人"
									 maxlength="20" >
								</div>
							</div>
							<div class="form-group">
								<label for="text3" class="col-sm-2 col-sm-offset-1 control-label">联系电话</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="tel" name="telephone" placeholder="联系电话"
									 maxlength="50" >
								</div>
							</div>
							<div class="form-group">
								<label for="text2" class="col-sm-2 col-sm-offset-1 control-label">商铺名称</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="name" name="name" placeholder="商铺名称"
									 maxlength="100" rule="must">
								</div>
							</div>
							<div class="form-group">
							<label class="col-sm-2 col-sm-offset-1 control-label">所在地区：</label>
								<div class="col-sm-2">
									<input id="pr" type="text" class="form-control" name ="province" placeholder="省份"  rule="must"/>
					            </div>
								<div class="col-sm-3">
						            <input id="ci" type="text" class="form-control" name ="city" placeholder="城市" rule="must"/>
					            </div>
								<div class="col-sm-3">
						            <input id="co" type="text" class="form-control" name ="area" placeholder="县级" rule="must"/>
					            </div>
							</div>
							<div class="form-group">
								<label for="text1" class="col-sm-2 col-sm-offset-1 control-label">经营地址</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" id="address" name="address" placeholder="具体地址"
									maxlength="200" rule="must">
								</div>
							</div>
						</form>
					</div>
					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="save">注册</button>
					</div>
				</div>
			</div>
		</div>
		<!-- 密码找回模态框 -->
		<div class="modal fade" id="mypassword" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<!-- 模态框标题 -->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">密码找回</h4>
					</div>
					<!-- 内容表单 -->
					<div class="modal-body row csmm_sjyz">
						<form class="form-horizontal" id="findCodeForm">
							<div class="form-group">
								<label for="tel" class="col-sm-2 col-sm-offset-1 control-label">手机号</label>
								<div class="col-sm-8">
									<input type="tel" class="form-control" id="findphone" placeholder="手机号" rule="must_noaccountphone">
								</div>
							</div>
							<div class="form-group">
								<label for="number" class="col-sm-2 col-sm-offset-1 control-label">验证码</label>
								<div class="col-sm-4">
									<input type="text" class="form-control" id="findcode" placeholder="输入6位短信验证码" rule="must_code">
								</div>
								<div class="col-sm-4">
									<button type="button" class="btn btn-default" onclick="sendShortMessage('findphone',1)">发送验证码</button>
								</div>
							</div>
						</form>
					</div>

					<!-- 模态框底部 -->
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
						<button type="button" class="btn btn-primary" id="rpassword">重置密码</button>
					</div>
				</div>
			</div>
	</body>
<script src="${ctx}/static/js/chxt/validate.js"></script>
<script type="text/javascript">
     new locationCard({
         ids: ['pr', 'ci', 'co']
     }).init();
 </script>
<script type="text/javascript">
window.onload = function(){
  //******************************************登录***************************************************//
    //分析cookie值，显示上次的登陆信息  
    var userName = getCookieValue("user_name");  
    $w("user_name").value = userName;  
    var passwordValue = getCookieValue("password");  
    $w("password").value = passwordValue; 
    $w("submit").onclick = function(){
    	login();
    };
    $("#password").keydown(function(e){
    	if(e.keyCode==13){
    		login();
    	}
    });
    function login(){
       	if(!checkAll("loginInfo")){
    		return false;
    	}
        var userName = $w("user_name").value;  
        var passwordValue = $w("password").value;  
        var checked = $w("saveCookie").checked;
        
        $ybtCAjax({
			//type验证码类型：1商户注册验证，校验不能有已存在的账户信息；2商户绑定注册，校验被绑定的手机号必须有已存在的账户信息
			url:"${ctx}/work/login/signin",
			type:'POST',
			data: 'userName='+userName+'&password='+passwordValue+'&checked='+checked,
			dataType: "json",
			success:function(result){
				if(!isNull(result)&&result.s!=0){
					if(checked){  
			            if( $w("saveCookie").checked){    
			                setCookie("user_name",$w("user_name").value,24,"/");  
			                setCookie("password",$w("password").value,24,"/");  
			            }
					}
					//已认证
		            setCookie("left_menu_defult","pfcgList",24,"/");
		            self.location.replace("${ctx}/work/chxt/wholesale/index");  
				}else{
					alert("用户名或密码错误，请重新输入！"); 
				}
			}
		});
    }
}; 

//******************************************注册***************************************************
    //发送验证码
    function sendShortMessage(phoneName,type){
    	if(!checkText(phoneName)){
    		return false;
    	}
    	var phone = $w(phoneName).value;
        $ybtCAjax({
			url:"${ctx}/work/login/sendsms",
			type:'POST',
			data: 'phone='+phone+"&type="+type,
			dataType: "json",
			success:function(result){	
				if(!isNull(result)&&result.s==1){
					alert("发送成功");
				}else{
					alert("发送异常");   
				}
			}
		});
    };
    
    //保存注册信息
    $w("save").onclick = function(){
    	if(!checkAll("busInfo")){
    		return false;
    	}
        var formdata=new FormData($w("busInfo"));  
        jQuery.ajax({  
            type:'POST',  
            url:'${ctx}/work/login/save',  
            data:formdata,  
            contentType:false,  
            processData:false,
            success:function(result){	
            	if(!isNull(result)&&result.s==1){
            		//跳转到待审核页面
            		setCookie("left_menu_defult","",24,"/");
					window.location.href="${ctx}/work/chxt/admin/regsiter?accountId="+result.b.id;
    			}else{
    				alert(result.m);   
    			}
			}
        });
        return false;  
    };
   
    
  //******************************************找回密码***************************************************
     
      
   //重置密码界面
$w("rpassword").onclick = function(){
		if(!checkAll("findCodeForm")){
			return false;
		}
    	var phone = $("#findphone").val();
    	var code = $("#findcode").val();
    	
    	$ybtCAjax({
			//type验证码类型：1商户注册验证，校验不能有已存在的账户信息；2商户绑定注册，校验被绑定的手机号必须有已存在的账户信息
			url:"${ctx}/work/login/retrievePwd",
			type:'POST',
			data:"&phone="+phone+"&code="+code,
			dataType: "json",
			success:function(result){
				if(!isNull(result)&&result.s==1){
					setCookie("left_menu_defult",'userInfo',24,"/");
					window.location.href="${ctx}/work/chxt/admin/cpassword";
				}else{
					alert("验证码输入有误");
				}
			}
		});
    };
  
</script>
</html>