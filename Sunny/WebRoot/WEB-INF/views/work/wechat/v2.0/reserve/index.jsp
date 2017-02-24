<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
  <head>
  <%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
	<%@include file="/WEB-INF/views/work/common/static.jsp"%>
	 <script type="text/javascript" src="${ctx}/static/js/ping_pp/pingpp.js"></script>
	<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>${config.siteName}</title>
    	<meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <style type="text/css">
   /*  .page, .page-group{
        display:block;
    } */
    a{color:#333333}
    .AddressInformation .col-33{
    	margin-bottom: 0.8rem;
    }
    .details span{
    	color: #333333;
		font-family: "Microsoft YaHei";
		font-size: 0.8rem;
    }
    .details .icon,.details .you{
    	color: #666;
		font-size: 0.8rem;
    }
    </style>
</head>
<body  class="v2">
	 <div class="page-group">
	 
	    
         <!-- 选择朋友 -->
	     <div class="page page-current" id='select-friend'>
			   <nav class="bar bar-tab bir  add-friend-new-btn">
			      <span class="tab-label">新增朋友</span>
			   </nav>
	
	            <!-- 这里是页面内容区 -->
	            <div class="content">
					<div class="TopWriting">
						选择一位朋友为TA定制一缕阳光
					</div>
					<div class="kongbai" style="display: none">
						你还没有任何朋友生日信息哦<br />点击下方的“新增朋友“添加吧
					</div>
					<div id="content">
						<!-- 关系列表内容 
						<input type="button" onclick="loadData();">xxxxx</input>  -->
	               </div> 
	            </div>
		</div>
		<!-- 新增朋友 -->
		  <div class="page" id="add-friend">
           <nav class="bar bar-tab bir">
           <div id="save" class="add-friend-save-btn">下一步</div>
           	
           </nav>
            <div class="content birthdaymain">
				<div class="TopWri">
					填写朋友信息，为Ta预约一缕阳光
				</div>
				<div class="birthday">
				<div class="content-block ">
			      <div class="list-block">
			        <ul class="birthday1">
			          <!-- Text inputs -->
			          <li>
			            <div class="item-content">
			              <div class="item-inner work">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/name.png"></div>
			                	姓名</div>
			                <div class="item-input">
			                  <input type="text" value="" id="name"  placeholder="请输入姓名"/>
			                  <input type="text" value="" id="id" style="display: none"/>
			                </div>
			              </div>
			            </div>
			          </li>
			          <li>
			            <div class="item-content">
			              <div class="item-inner work" id="birthday_add">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/shizhong.png"></div>
			                	日期
			                </div>
			                <div class="item-input">
			                  <input  placeholder="请选择预约祝福日期" type="text" id="birthday" readonly="readonly">
			                </div>
			              </div>
			            </div>
			          </li>
			          <p class="tshi" style="text-align:center; margin-bottom: 0.5rem; margin-top: -0.7rem; font-size: 0.7rem; color: red;">
			   			       提示：“大年初一”为1月28日呦~ 
			          </p>
			          
			          <li>
			            <div class="item-content" style="display: none">
			              <div class="item-inner work">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/name.png"></div>
			                	手机号</div>
			                <div class="item-input">
			                  <input type="text" id="mobileNo" placeholder="选填">
			                </div>
			              </div>
			            </div>
			          </li><li>
			            <div class="item-content">
			              <div class="item-inner work1 weibu1">
			                <div class="item-title label word1">
			                	<div class="Sign"><img src="${ctx}/static/v2.0/img/guanxi.png"></div>
			                	关系</div>
			                <div class="item-input">
			                  <select id="relation" class="duiqi" name="selse" >
								<option value="朋友">朋友</option>
								<option value="父亲">父亲</option>
								<option value="母亲">母亲</option>
								<option value="孩子">孩子</option>
								<option value="爱人">爱人</option>
								<option value="恋人">恋人</option>
								<option value="亲人">亲人</option>
			                  </select>
			                </div>
			              </div>
			            </div>
			          </li>
			        </ul>
			      </div>
			    </div>
			   </div>
            </div>
        </div> 
        
         <!-- 预订 -->
        <div class="page" id="reserve">
         	<nav class="bar bar-tab bar-zidong">
			    <div class="Checkout">
						阳光额度：<a>￥99</a><div class="purchase create-actions quzhifu">去支付</div>
				</div>
			</nav>
            <div class="content">
            	<%-- <a href="${ctx }/wechat/relation/relationList"> --%>
            	<!-- <a href="#select-friend"> -->
				<div class="details" style="display: none" id="reserve-add-friend-btn"><span>信息</span>  <div class="you">选择朋友 &nbsp;<span class="icon icon-right"></span></div></div>
			<!-- 	</a> -->
				<div class="detailscContent">
					<div class="row xinxiform">
						<div class="col-40 xinxixi">
							<div class="Sign"><img src="${ctx}/static/v2.0/img/name.png"></div>姓名：
						</div>
						<div class="col-60 xinxiname input-name"></div>
					</div>
					<div class="row xinxiform">
						<div class="col-40 xinxixi" id="birthday_img_show">
							<div class="Sign"><img src="${ctx}/static/v2.0/img/shizhong.png"></div>日期：
						</div>
						<div class="col-60 xinxiname input-birth"></div>
					</div>
					<div class="row xinxiform" style="display: none">
						<div class="col-40 xinxixi">
							<div class="Sign"><img src="${ctx}/static/v2.0/img/dianhua.png"></div>电话：
						</div>
						<div class="col-60 xinxiname input-relation-phone"></div>
					</div>
					<div class="row xinxiform">
						<div class="col-40 xinxixi">
							<div class="Sign"><img src="${ctx}/static/v2.0/img/guanxi.png"></div>关系：
						</div>
						<div class="col-60 xinxiname input-relation"></div>
					</div>
				</div>
				<a href="${ctx }/wechat/v2/attraction">
				<div class="details"> <span>预约城市 </span>  <div class="you">选择城市 &nbsp;<span class="icon icon-right"></span></div></div>
				</a>
				<div class="AddressInformation">
					<div class="row AddressInformation2 reserve-citys">
						<!-- <div class="col-33 AddressInformation1">翡翠岛</div>
						<div class="col-33 AddressInformation1">翡翠岛</div>
						<div class="col-33 AddressInformation1">翡翠岛</div>
						<div class="col-33 AddressInformation1">翡翠岛</div>
						<div class="col-33 AddressInformation1">翡翠岛</div> -->
					</div>
				</div>
				
					<div class="details"><a>祝福语</a></div>
					<div class="wishes">
						<div class="row wishes1">
							<div class="col-20 wishes2"><img src="${ctx}/static/v2.0/img/bi.png"></div>
							<div class="col-80 wishes2">
								<textarea id="input-bless" name="a" style="width:100%;height:100%;"></textarea>
							</div>
						</div>
					</div>
					<!-- <div class="Checkout">
						阳光额度：<a>￥99</a><div class="purchase create-actions quzhifu">去支付</div>
					</div> -->
            </div>
        </div> 
   </div>
   	
  
		
    <!-- popup, panel 等放在这里 --> 
   <!--  <div class="panel-overlay"></div> -->
    <div class="popup popup-xuanz">
		  <div class="content-block heikuai close-popup"></div>
		  <div class="baikuang">
		  	<img src="${ctx}/static/v2.0/img/yanggka.png">
		  	<div class="queding pay-card-btn">确定</div>
		  </div>
	</div>
	<!-- Services Popup -->
	<div class="popup popup-bangding">
          <div class="content-block heikuai close-popup"></div>
		  <div class="baikuang1">
				<p class="meibang">您还没绑定阳光卡，请先绑定</p>
				<div class="row no-gutter kahao">
					<div class="col-25">卡号：</div>
					<div class="col-75">
						<input type="number" id="kahaoma" />
					</div>
				</div>   
				<div class="row no-gutter nima">
					<div class="col-25">密码：</div>
					<div class="col-75">
						<input type="password" id="kamima"/>
					</div>
				</div>
				<div class="tishi"></div>
				<div class="quedinganniu">确定</div>
		  </div>
	 </div>
</body>
<%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
<script type="text/javascript">
$(function(){
	/* 以下为过年临时活动代码【开始】
  	var nowDate = new Date();
	var date = new Date("2017-01-28 02:00:00".replace(/-/g,"/"));  //活动时间
	if(nowDate < date){
		var html = '<div class="item-title label word1"><div class="Sign"><img src="${ctx}/static/v2.0/img/shizhong.png"></div>日期</div>'+
					'<div class="item-input">'+'<input type="text" readonly="readonly" value="大年初一">'+
					'<input type="text" style="display:none" id="birthday" readonly="readonly" value="2017-1-28">'+
					' </div>';
		var html_img_show = '<div class="Sign"><img src="${ctx}/static/v2.0/img/shizhong.png"></div>日期:';
		
		$("#birthday_add").html(html);
		$("#birthday_img_show").html(html_img_show);
	}else{
		var html = '<div class="item-title label word1"><div class="Sign"><img src="${ctx}/static/v2.0/img/shengr.png"></div>生日</div>'+
			'<input  placeholder="请输入日期" type="text" id="birthday" data-toggle="date" readonly="readonly">'+
			' </div>';
		$("#birthday_add").html(html);
	}*/
	/* 以下为过年临时活动代码【结束】*/
	$.init();
});

$("#birthday").calendar({
	onChange:function(p, values, displayValues){
		var nowDate = new Date().Format('yyyy-MM-dd');
		
		if(nowDate == displayValues){
			$.toast("不能预约当日的阳光！");
		}
	},
	onClose:function(){
		var nowDate = new Date().Format('yyyy-MM-dd');
		var inputValue = $("#birthday").val();
		if(nowDate == inputValue){
			$("#birthday").val("");
		}
	}
});


var SelectCard = {
		 num:'',
		 init: function(){
			 $.ajax({  
	        	     type:'POST',  
	        	     url:ctx+"/wechat/my/card/getOneSunCard",  
	        	     success:function(result){
	        	    	 console.log(result);
		        	   	  if(result.state==1){
		        	   		SelectCard.num=result.t.number;
		        	   		Reserve.cardPay(SelectCard.num);
		        	   	  }else{
		        	   		 $.popup('.popup-bangding');
		        	   	  }
	        	     }	
    		 });
			 $(".quedinganniu").unbind( "click" );
			 $(document).on('click','.quedinganniu', function () {
				 SelectCard.banddding();
 			  });
			 $(".pay-card-btn").unbind( "click" );
			 $(document).on('click','.pay-card-btn', function () {
				 Reserve.cardPay(SelectCard.num);
 			 });
		 },
		 banddding: function (){
			 $(".tishi").empty();
			  	var AccountVal = $("#kahaoma").val();
			  	var passVal = $("#kamima").val();
			  	var reg=/^[1-9]\d*$|^0$/; 
				
				if(passVal == ''||AccountVal == '') {
					$(".tishi").html('<span>账号密码不能为空<span>');
					return;
				} 
				if(reg.test(AccountVal)==false){
					$(".tishi").html('<span>账号只能是数字</span>');
					return;
				}
		   		
		   	  $.ajax({  
		   	      type:'POST',  
		   	      url:ctx+"/wechat/my/card/sunCardBandding",  
		   	      data:{number:AccountVal,pwd:passVal},  
		   	      success:function(result){
		   	    	  if(result.state==1){
			   	    		SelectCard.num=result.t.number;
			   	    		$.closeModal(".popup-bangding");
			   	    		
			   	    		  $.toast("绑定成功！");
			   	    		  setTimeout(function() {  
			   	    			  $.popup('.popup-xuanz');
			   	    			}, 1000);
		   	    	  }else{
		   	    		$(".tishi").html('<span>'+result.message+'</span>');
		   	    		$.toast("绑定失败！");
		   	    	  }
		   	      }	
		   	 }); 
		    }
};
//
var friend ={
	id:'',	
	name:'',	
	relation:'',	
	mobileNo:'',	
	birthday:''	
};

//预订 js
 var Reserve = {  
 		isload:false,
 		input:{},
 		relationId:'',
 		bless:'',
 		init: function(){
 			 this.isload=true;
 			 $("#reserve-add-friend-btn").unbind( "click" );
 			$("#reserve-add-friend-btn").on("click",function(){
 	 			Reserve.loadFriends();
 			});
 			 $(".quzhifu").unbind( "click" );
 			$('.quzhifu').on('click', function () {
 			//$(document).on('click','.create-actions', function () {
 			      var buttons1 = [
 			        { text: '请选择', label: true },
 			        {
 			          text: '阳光卡支付',
 			          bold: true,
 			          color: 'danger',
 			          onClick: function() {
 			        		 SelectCard.init();
 			        	}
 			        },
 			        {
 			          text: '微信支付',
 			          onClick: function() {
 			        	 Reserve.wxPay();
 			          }
 			        }
 			      ];
 			      var buttons2 = [
 			        {
 			          text: '取消',
 			          bg: 'danger'
 			        }
 			      ];
 			      var groups = [buttons1, buttons2];
 			     Reserve.bless=$("#input-bless").val();
 			     if(Reserve.validate()){
 			    	 $.actions(groups);
 			     }
 		  });
 			 
 		},
 		validate:function(){
 			var str = $(".input-birth").html();
 			var year = str.split('')[0]+str.split('')[1]+str.split('')[2]+str.split('')[3];
 		    var month = str.split('')[5]+str.split('')[6];
 		    var day = str.split('')[08]+str.split('')[9];
 		    var birthday = year+'-'+month+'-'+day;
			var nowDate_yyyymmdd = new Date().Format("yyyy-MM-dd");
			var endTime =new Date((birthday+" 04:00:00").replace(/-/g,"\/"));
			
			if(birthday == nowDate_yyyymmdd){
				if(new Date() > endTime){
					$.toast("只能在凌晨4点之前预约当天日出！");
					return false;
				}
			}
 			
 			
 			if(isNull(Reserve.relationId)){
 				 $.toast("请选择朋友");
 				return false;
 			}
			if(isNull(Reserve.citys)){
				  $.toast("请选择城市");
				  return false;
 			}
			if(isNull(Reserve.bless)){
				 $.toast("请输入祝福语");
				 return false;
 			}
 			return true;
 		},
 		loadCitys: function(citys){
 			$(".relation-id").html(friend.id);
			 $(".input-relation").html(friend.relation);
			 $(".input-name").html(friend.name);
			 $(".input-birth").html(friend.birthday);
			 $(".input-relation-phone").html(friend.mobileNo);
 			$(".reserve-citys").html("");
 			 if(citys<1){
 				/*  var rec =new Array ("抚远","北京","黄山","苏州","厦门");
 				Reserve.citys=rec;
 				for (var i = 0; i < 5; i++) {
 	 				 $(".reserve-citys").append("<div class=\"col-33 AddressInformation1\">"+rec[i]+"</div>");
 	 			} */
 				return;
 			 }
 			 for (var i = 0; i < citys.length; i++) {
 				 $(".reserve-citys").append("<div class=\"col-33 AddressInformation1\">"+citys[i]+"</div>");
 			 }
 			Reserve.citys=citys;
 		},
 		loadFriends: function(){
 			$.router.load("#select-friend",true);
		},
		//卡支付
		cardPay:function(carnum){
		      	if(isNull(carnum)){
		      		 $.toast("请选择卡！");
		      		return 
		      	}
		     	$.closeModal(".popup");
		      	Reserve.submitOrder('SUNCARD',carnum);
		},
		//微信支付
		wxPay:function(){
		      	Reserve.submitOrder('weixin','');
		},
		// 
		sendNoice:function(id){
			$.post("${ctx}/wechat/v2/reserve/sendNoice?orderId="+id,  function(result){
	    	});   
		},
		//提交订单
		 submitOrder:  function (payType,number){
			  //$.showIndicator() 
		       $.showPreloader('加载中');
		       var ye=1.00;
		       var formdata={
		    		   "relationId":Reserve.relationId,
		    		   "citys":Reserve.citys,
		    		   "bless":Reserve.bless,
		    		   "relationName":"aaa",
		    		   "relationRelation":"aaa",
		    		   "relationPhone":"aaa",
		    		   "relationBirth":"aaa",
		    		   "sunPayType": payType
		    		   };
		       console.log(formdata);
		       $.post("${ctx}/wechat/v2/reserve/saveReserveOrder", formdata,  function(result){
		        	 $.hidePreloader();
		        	 
		        	if(!isNull(result)&&result.s==1){
		        		var order=result.b;
		          			var metering = 0;
		          			var charge = result.m;
		          			
		          			if(order.payment==1){
		          				pingpp.createPayment(charge, function(result, err) {
			           			  if (result=="success") {
			           					//Reserve.sendNoice(order.id);
			      	                	window.location.href="${ctx}/wechat/v2/reserve/showOrder?orderId="+order.id;
			           			  } else {
			           			    console.log(result+" "+err.msg+" "+err.extra);
			           			  }
			           			});
		          			}
		          			if(order.payment==2){
		          				$.ajax({  
		          			        type:'POST',  
		          			        url:"${ctx}/wechat/pay/paySunCardNotice?orderId="+order.id+"&cardNumber="+number,  
		          			        success:function(result){
		          			        	 
		          			        	if(!isNull(result)&&result.state==1){
		          			        		Reserve.sendNoice(order.id);
			      	                	window.location.href="${ctx}/wechat/v2/reserve/showOrder?orderId="+order.id;
		          						}else{
		          						 	console.log(result);
		          						}
		          			        }
		          			    }); 
		          			}
		          			
		        		 metering++;//计次，用于生产订单号
						//跳转到确认页面
					}else{
						$.alert(result.m); 
					}
		    });   
		   }
 		
 };

 //选择朋友
 var SelectFriend = {
		 relationId:'',
		 init: function(){
			 
			 $(".add-friend-save-btn").unbind( "click" );
			 $(".add-friend-save-btn").on("click",function(){
				 SelectFriend.saveRelation();
			 });
			 $(".add-friend-new-btn").unbind( "click" );
			 $(".add-friend-new-btn").on("click",function(){
				 $.router.load("#add-friend");
			 });
			 $(".relation-item").unbind( "click" );
			 $(".relation-item").on("click",function(){
				 var hiddenpp="<input type='hidden' name='relation-id' id='relation-id' value='"+$(this).attr("relation-id")+"'";
				 SelectFriend.relationId=$(this).attr("relation-id");
				 $(".input-relation").html($(this).attr("relation-relation"));
				 $(".input-name").html($(this).attr("relation-name"));
				 $(".input-birth").html($(this).attr("relation-birthday"));
				 $(".input-relation-phone").html($(this).attr("relation-phone"));
				 friend.id = $(this).attr("relation-id")
				 friend.name =$(this).attr("relation-name");
				 friend.birthday = $(this).attr("relation-birthday");
				 friend.relation =$(this).attr("relation-relation")
				 friend.mobileNo =$(this).attr("relation-phone");
				if( SelectCity.citys.length < 5){
					$.router.load(ctx+"/wechat/v2/attraction");
				}else{
					$.router.back();
				}
				 
				 
				// $.router.load('#reserve', true)
				// $.router.back();
			 });
		 },
		 relationsHtml: function (list){
				//alert("---"+list);
			var html = "";
			for(var i=0;i<list.length;i++){
				console.log(list[i]);
					html += '<div id="aaa" class="information relation-item" relation-relation="'+list[i].relation+'" relation-phone="'+list[i].mobileNo+'"  relation-name="'+list[i].name+'"  relation-birthday="'+list[i].birthday+'"  relation-id="'+list[i].id+'"  relation-id="'+list[i].id+'">'+
									'<div class="row no-gutter">'+
										'<div class="col-25 relationship1">'+
											'<div class="kuangkuang">'+
												'<img src="'+ctx+'/static/v2.0/img/kuangkuang.png">'+
											'</div>'+
											'<span class="relationship">'+list[i].relation+'</span>'+
										'</div>'+
										'<div class="col-50 information2">'+
											'<div class="HisName">'+list[i].name+'</div>'+
											'<div class="HisData">'+list[i].birthday+'</div>'+
										'</div>'+
										'<div class="col-25 information3">'+list[i].days+'</div>'+
									'</div>'+
							'</div>';
			}
			return html;
		},
		loadData:function (){
			$.post(ctx+"/wechat/v2/relation/relationListDetail", 
					function(response){
						var list = eval(response);
						if(list.length>0){
							$(".kongbai").hide();
							var html = SelectFriend.relationsHtml(list);
							$("#content").html('');
							$("#content").append(html);
							SelectFriend.init();
							return;
						}else{
							//$.router.load("#add-friend");
							/* var html = '<div>还没有朋友生日信息，请您先去添加一位朋友</div>';
							$("#content").html('');
							$("#content").append(html); */
							$(".kongbai").show();
						}
						
						
					}
			);
		},
		saveRelation :function (){
			var name = $("#name").val();
			var birthday = $("#birthday").val();
			var mobileNo = $("#mobileNo").val();
			var relation = $("#relation").val();
			var id = $("#id").val();
			var nowDate_yyyymmdd = new Date().Format("yyyy-MM-dd");
			var endTime =new Date((birthday+" 04:00:00").replace(/-/g,"\/"));
			
			if(birthday == nowDate_yyyymmdd){
				if(new Date() > endTime){
					$.toast("只能在凌晨4点之前预约当天日出！");
					return;
				}
			}
			
			if(isNull(name)){
				$.toast("请输入姓名");
				return;
			}else if(isNull(birthday)){
				$.toast("请选择生日");
				return;
			}else if(isNull(relation)){
				$.toast("请选择关系");
				return;
			}
			var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
			if(!isNull(mobileNo)){
				if(!myreg.test(mobileNo)) 
				{ 
					$.toast('请输入有效的手机号码！'); 
					return; 
				} 
			}
			
			
			$.post(ctx+"/wechat/v2/relation/addRelationSave",{id:id,name:name,birthday:birthday,mobileNo:mobileNo,relation:relation},function(response){
						if("success" == response.message){
							/* $.toast("保存成功");
							$.router.back(); */
							
							//根据过年流程跳转到选择地址页面；
							var mb = response.t;
							 SelectFriend.relationId = mb.id;
							 friend.id = mb.id;
							 friend.name = name;
							 friend.birthday = birthday;
							 friend.relation = relation;
							 friend.mobileNo = mobileNo;
							 $(".relation-id").html(mb.id);
							 $(".input-relation").html(relation);
							 $(".input-name").html(name);
							 $(".input-birth").html(birthday);
							 $(".input-relation-phone").html(mb.mobileNo);
							 $.router.load(ctx+"/wechat/v2/attraction");
						}else if("empty" == response.message){
							$.toast("数据不全，请重新填写");
						}else{
							$.toast("操作失败");
						}
					}
			);
		}

 }
//选择城市 js
 var SelectCity = {  
 		isload:false,
		citys: new Array(),
		allcitys: new Array(),
		that:this,
       	init: function(){  
       	 this.isload=true;
        	that=this;
          //	that.citys=new Array(),
           $(".ShootingAddress").unbind( "click" );
         	$(".ShootingAddress").on("click",function(){
         		if($(this).hasClass("Selected")){
         			$(this).removeClass("Selected")
         		}else{
         			if($(".Selected").length<5){
         				$(this).addClass("Selected")
         			}else{
         				$.toast("最多可以选择5个城市");
         			}
         		}
         	});
         	$(".select-city-btn").unbind( "click" );
     	    $(".select-city-btn").on("click",function(){
     	    	    that.citys=new Array(),
     	    		$(".Selected").each(function(i){
     	    			var cit = $(this).attr("city");
     	    			that.addCity(cit);
     	    		});
     	    		
     	    		if(that.citys.length!=5){
     	    			$.toast("请选择5个城市");
     	    			return;
     	    		}
     	    		/* if(Reserve.isload){
     	    			$.router.back();
     	    		}else{ */
     	    			that.href(ctx+"/wechat/v2/reserve#reserve");
     	    		/* } */
         	});
         },  
         //添加城市
         addCity: function(city){  
         	that.citys.push(city);
         },
         //删除城市
         removeCity: function(city){  
         } ,
         //加载城市
         loadCitys: function(){
        	 SelectCity.allcitys= new Array();
        	 if(SelectCity.allcitys>=1)
        		 return;
  			$.get(ctx+"/wechat/v2/attraction/data",{},function(result){
  				SelectCity.allcitys=result;
  			});
 		},
 		//加载列表
        htmlCitys: function(obj){
		},
 		//跳转
 		href: function(url){  
         	$.router.load(url); 
         } 
		
    };  

</script>
<script type="text/javascript">
//页面跳转
$(document).on("pageInit", function(e, pageId, $page) {
	if(pageId == "reserve") {
		 Reserve.init();
		 Reserve.relationId=SelectFriend.relationId;
		 Reserve.loadCitys(SelectCity.citys);
	}
	if(pageId == "select-friend") {
		SelectFriend.init();
		SelectFriend.loadData();
	}
	if(pageId == "add-friend") {
		SelectFriend.init();
	 }
	 if(pageId == "selectCity") {
		 //选择 城市
		 SelectCity.isload=true;
		 SelectCity.init();
	 }
});
</script>
</html>