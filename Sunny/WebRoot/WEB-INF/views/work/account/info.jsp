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
		<title>芸备胎采购系统</title>
	</head>
	<body>
		<!-- 页面主体内容 -->
		<div class="container-fluid">
			<div class="row">
				<!-- 左侧导航 -->
				<%@include file="/WEB-INF/views/work/common/left.jsp"%>
				<!-- 右侧内容-->
				<div class="col-sm-10 col-sm-offset-2 content">
				<!-- 顶部用户退出行 -->
				<%@include file="/WEB-INF/views/work/common/top.jsp"%>
					<!--当前所在位置-->
					<ol class="breadcrumb hidden-xs">
						当前所在位置：
						<li class="active">/&nbsp;&nbsp;我的钱包</li>
					</ol>
					<!-- 当前位置结束 & 账户可提现总金额开始 -->
			<div class="myAccount">
				<div class="row">
					<div class="col-sm-4">
						<div class="money">
							<div class="ye">
								<h5>账户余额<small class="pull-right">&nbsp;&yen;&nbsp;</small></h5>
							</div>
							<div class="ye-nr">
								<h1 class="text-center"><fmt:formatNumber value="${tradeAccount.totalMoney}" type="currency" pattern="0.00"/></h1>
								<p>
									<span>&nbsp;</span>
									<!-- <span class="pull-right text-success">1,000,00&nbsp;<i class="fa fa-sort-asc"></i></span> -->
								</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="money">
							<div class="ktx">
								<h5>可提现金额<small class="pull-right">&nbsp;&yen;&nbsp;</small></h5>
							</div>
							<div class="ktx-nr">
								<h1 class="text-center"><fmt:formatNumber value="${tradeAccount.money}" type="currency" pattern="0.00"/></h1>
								<p>
								<span>每周五自动转入您的签约账户</span>
									<!-- <abbr title="&ge;10元后每周五之前自动转入您的签约账户"><a class="text-warning" href="#">提现</a></abbr>
									<a class="text-warning" href="#">绑定账户</a> -->
								</p>
							</div>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="money">
							<div class="dd">
								<h5>正在交易金额<small class="pull-right">&nbsp;&yen;&nbsp;</small></h5>
							</div>
							<div class="dd-nr">
								<h1 class="text-center"> <fmt:formatNumber value="${tradeAccount.totalMoney - tradeAccount.money}" type="currency" pattern="0.00"/></h1>
								<p>
									<span>&nbsp;</span>
									<!-- <span class="pull-right text-danger">5&nbsp;<i class="fa fa-sort-desc"></i></span> -->
								</p>
							</div>
						</div>
					</div>
				</div>
				<!-- 账户可提现总金额结束 & 交易记录开始 -->
				<div class="bill">
					<div class="zd-btn">
						<button class="btn btn-primary-outline active">账单明细</button>
					<!-- 	<button class="btn btn-primary-outline">交易明细</button> -->
					</div> 
					<div class="zd" id="account_record">
					</div>
				</div>
				<div class="paging">
					<div class="createPagination col-xs-12 col-sm-6 col-sm-offset-6 text-right">
					</div>
				</div>
			</div>
				</div><!-- 右侧内容结束 -->
			</div><!-- 页面主题内容结束  row -->
		</div>
		
		
		<!-- 密码找回模态框 -->
		<button data-toggle="modal" data-target="#qrcodeModal" class="hide" id="qrcode"></button>
		<div class="modal fade" id="qrcodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 350px;">
				<!-- 模态框标题 -->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel">请用微信扫描二维码提现</h4>
					</div>
					<!-- 内容表单 -->
					<div class="" style="text-align:center;">
						<div id="code" style="text-align:center;margin-top: 2rem;margin-bottom: 2rem"></div>
						<hr/>
						<a href="javascript:history.go(0);" style="width: 70%">
							<a href="javascript:;" class="white_link btn" id="J_getCode">已失效</a>
							<a href="javascript:;" class="white_link btn" id="J_resetCode"><span id="J_second">120</span> 秒内有效</a>
							<br/>
							<button class="btn btn-primary" id="subbtn" style="margin:20px 0 50px;">已提交提现申请</button>
						</a>
					</div>
				</div>
			</div>
		</div>
		
		<!-- 设置提现微信 -->
		<div class="modal fade" id="registerTiXianWX" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2" aria-hidden="true">
			<div class="modal-dialog" style="width: 350px;">
				<!-- 模态框标题 -->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="myModalLabel2">1：使用提现微信扫描</h4>
					</div>
					<!-- 内容表单 -->
					<div class="" style="text-align:center;">
						<div id="tiXianWX" style="text-align:center;margin-top: 2rem;margin-bottom: 2rem"></div>
						<hr/>
						<a href="javascript:;" style="width: 70%">
							<button class="btn btn-primary" onclick="history.go(0);" id="subbtn" style="margin:20px 0 50px;">已设置成功</button>
						</a>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript" src="${ctx}/static/js/jquery.qrcode.min.js"></script> 
	<script type="text/javascript" src="${ctx}/static/js/chxt/order.js"></script> 
	<script type="text/javascript">
	//提交订单 //此二维码是提现二维码，请妥善保管，定时关闭，每次从新请求，30秒失效
	function createQrcode(){
		createQrcodeWX();
	};
	//登记提现二维码
	var registerTiXianWX = function(){
		
	};
	//倒计时
	var timer = null;
	function resetCode(){
		var second = 120;
		$('#J_getCode').hide();
		$('#J_second').html(second);
		$('#J_resetCode').show();
		
		timer = setInterval(function(){
			second -= 1;
			if(second >0 ){
				$('#J_second').html(second);
			}else{
				clearInterval(timer);
				$('#J_resetCode').hide();
				$('#J_getCode').show();
			}
		},1000);
	}
	
	var createQrcodeWX = function(){
		$ybtCAjax({
			url:"${ctx}/work/chxt/account/getTransferUrl",
			type:'post',
			dataType: "json",
			success:function(data){
				if(!isEmpty(data)&&data.s==1){
					$('#code').html("");
					$('#code').qrcode(data.b); //任意字符串 
					$("#qrcode").click(); 
					resetCode();
				}else{
					alert("支付二维码加载失败！");
				}
			},error:function(){
				window.location.href="${ctx}/work/login";
			}
		});
	};
	
	$(document).on("click","#subbtn",function(e){
		$('#qrcodeModal').modal('hide');
		history.go(0);
	});
	
	$('#qrcodeModal').on('hidden.bs.modal', function (e) {
		clearInterval(timer);
	});
	
	
	</script>
	</body>
<script type="text/javascript">
//查询变量
var searchParams = "1=1";
var search = "";
//分页变量
var pageNo=1,pageSize=10,orderBy='',order='';
var count = "0";
var pageCount = Math.floor((parseInt(count) + pageSize -1) / pageSize);
//获取参数列表
var getParamsData = function(){
	searchParams = "1=1";
	if(!isEmpty(search))
		searchParams+="&search="+search;
	if(!isEmpty(pageNo))
		searchParams+="&pageNo="+pageNo;
	if(!isEmpty(pageSize))
		searchParams+="&pageSize="+pageSize;
	if(!isEmpty(order))
		searchParams+="&order="+order;
	if(!isEmpty(orderBy))
		searchParams+="&orderBy="+orderBy;
};

$(document).ready(function(){
	searchStart();
});
//查询 
var  searchStart = function(){
	//获取参数列表
	pageNo=1;
	getParamsData();
	searchCount();
	searchRcord();
};

var createPaging = function(data,fn){
	if(!isEmpty(data)&&data.s>0){
		count = data.s;
		pageCount = Math.floor((parseInt(count) + pageSize -1) / pageSize);
		//分页插件
		var page_div = '<div class="createPagination col-xs-12 col-sm-6 col-sm-offset-6 text-right"></div>';
		$(".createPagination").remove();
		$("div.paging").append(page_div);
		$(".createPagination").createPage({
		    pageCount:pageCount,//总页数
		    current:pageNo,//当前页码
		    backFn:function(page){//点击页码-回调函数
		    	pageNo=page;
		    	fn();
		    }
		});
	}
};


//查询总数
var searchCount = function(){
	$ybtCAjax({
		url:"${ctx}/work/chxt/account/myAccountCount",
		type:'post',
		dataType: "json",
		success:function(data){
			createPaging(data,searchRcord);
		}
	});
};

//查询列表
var searchRcord = function(){
	getParamsData();
	//获取参数列表
	$ybtCAjax({
		url:"${ctx}/work/chxt/account/myAccountBooks",
		type:'get',
		data:searchParams,
		dataType: "html",
		success:function(data){
			if(!isEmpty(data)){
				$("#account_record").empty();
				$("#account_record").append(data);
			}
		}
	});
};
</script>
</html>