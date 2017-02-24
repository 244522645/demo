<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
    <title>芸备胎支付-提现</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no">
  </head>
  <style>
	.row{margin:4rem 0.5rem 3rem 0.5rem;background-image:url("${ctx}/static/images/chxt/logo_s.png");
	background-repeat:no-repeat;background-color: #F6F7F4;background-position: center;}
	.top{margin-top:2rem;}
	.top>h5{font-weight: 700;}
	.order-table{
		margin:2rem 0.5rem;
		width:80%;
		font-size: 1.5rem;
		line-height: 1.6rem;
	}
	.order-table tr{
		height: 5rem;
	}
	.btn-block{
		width:60%;
		position:fixed; 
		left:20%;
		bottom:25%;
		height: 4rem;
		font-size: 1.5rem;
	}
</style>
<body>
	<div class="container-fluid" >
		<div class="row">
			<div class="col-xs-12  text-center top">
				<h4>提现(单位：元)</h4>
			</div>
			<div class="col-xs-12">
				<form id="tixian_form">
					<table class="order-table">
					  <tbody>
						  <tr>
					  		<th> 金额(&yen;1≤提现金额≤&yen;${money})</th>
					 	 </tr>
					 	 <tr>
					  		<td id="money">
					  			<input type="text" class="form-control" rule="must_money[1,${money}]" name="money" id="money01">
					  		</td>
					 	 </tr>
					  </tbody>
					</table>
				</form>
			</div>
		</div><!-- 页面主题内容结束  row -->
		<button type="button"  id="payForWeiXinBut" class="btn btn-primary btn-block" onclick="tixian()" >提现</button>
	</div><!-- 外包裹容器结束 container -->
</body>
 <script src="${ctx}/static/js/chxt/validate.js"></script>
 <script type="text/javascript">
$("td#money").attr('class','');
 var openId="${openId}";
 var money_max = "${money}";
 var bid = "${bid}";
 var tixian = function(){
	 var money = $("input[name='money']").val();
	 if(!checkAll("tixian_form")){
			return false;
	 }
	 $ybtCAjax({
			url:"${ctx}/pay/transferApply",
			type:'post',
			dataType: "json",
			data:"money="+money+"&openId="+openId+"&bid="+bid,
			success:function(data){
				if(!isEmpty(data)&&data.s==1){
// 					alert(data+" data.m="+data.m+"====data.b="+data.b+"==data.b.order_no="+data.b.order_no+"==data.b.transaction_no="+data.b.transaction_no);
					alert(data.m);
				}else if(!isEmpty(data)&&!isEmpty(data.m)){
					alert(data.m);
				}else{
					alert("网络错误，提现失败！请联系芸备胎");
				}
				WeixinJSBridge.call('closeWindow');
			}
		});
 };

 </script>
</html>
