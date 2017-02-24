<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@include file="/WEB-INF/views/work/common/static.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%String path=request.getContextPath(); %>
<!DOCTYPE html>
<html>

  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="keywords" content="${config.keywords}"> 
	<meta name="description" content="${config.description}">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel="shortcut icon" href="/favicon.ico">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>${config.siteName}</title>
    <style>
    	.card{
    		margin:10px 0px 10px 0px;
    	}
    	.content-block{
    		margin-top: 10px
    	}
    	.f-6{
    	font-size: 0.6rem;
    	text-align: right;
    	}
    	.bar .zhizuo{
    	top: 0rem;
		}
    </style>
  </head>
  
  <body>
    <div class="page-group">
        <div class="page" id="myList">
            <!-- 标题栏 -->
            <header class="bar bar-nav">
              <h1 class="title">我的阳光</h1>
              <button class="button button-link button-nav pull-right">
                    <a href="${ctx}/wechat/index" class="button button-fill zhizuo button-success">送祝福</a>
                </button>
            </header>
            <!-- 内容 -->
            <div class="content ">
           
               <style>
			    	.card{
			    		margin:10px 0px 10px 0px;
			    	}
			    	.content-block{
			    		margin-top: 10px
			    	}
			    	.f-6{
			    	font-size: 0.6rem;
			    	text-align: right;
			    	}
			    	.bar .zhizuo{
			    	top: 0rem;
					}
					
.wode-xuanzezhifu {
	background-color: #ffffff;
	margin: 0px auto;
	width: 100%;
	padding-bottom: 5%;
	position: fixed;
	bottom: 0;
	z-index: 2002;
	visibility: hidden;
}

.wode-zhifu-button {
	padding-top: 0.3rem;
	padding-bottom: 0.3rem;
	width: 90%;
	margin-top: 5%;
	margin-left: 5%;
	color: white;
	font-size: 0.73rem;
	background-color: #ff0000;
	border: 1px solid;
	border-radius: 0.4rem;
}


/*选择阳光卡支付后，还未绑定阳光卡*/

.wode-select-sun-card-zhifu {
	padding-bottom: 0.5rem;
	background-color: #FFFFFF;
	width: 100%;
	margin-top: 1rem;
	visibility: visible;
	border: solid 1px #FFFFFF;
	border-radius: 0.3rem;
}

.wode-title-bangding {
	text-align: center;
	font-family: "微软雅黑";
	margin-bottom: 0rem;
}

.wode-input-card-info {
	text-align: center;
	padding-top: 0.3rem;
	padding-bottom: 0.3rem;
	width: 80%;
	margin-top: 5%;
	margin-left: 10%;
	color: #000000;
	font-size: 0.7rem;
	border-style: none;
	border-bottom-style: solid;
	border-bottom-width: 1px;
}

.wode-tishi {
	margin-left: 10%;
	color: #FF0000;
	font-size: 0.55rem;
}

#tisi-weibangding {
	text-align: center;
	font-size: 0.7rem;
}

.wode-input-confirm {
	padding-top: 0.3rem;
	padding-bottom: 0.3rem;
	width: 80%;
	margin-top: 1%;
	margin-left: 10%;
	color: white;
	font-size: 0.73rem;
	background-color: #ff0000;
	border: 1px solid;
	border-radius: 0.4rem;
	text-align: center;
}


/*选择已经绑定的阳光卡*/

.wode-select-sun-card-zhifu-2 {
	background-color: #ffffff;
	margin: 0px auto;
	width: 90%;
	padding-bottom: 5%;
	

	border: solid 1px #FFFFFF;
	border-radius: 0.3rem;
	margin-top: 1rem;
}

#wode-card-icon {
	width: 1rem;
	margin-top: 5%;
	margin-left: 5%;
}

#wode-fault {
	width: 0.5rem;
	position: relative;
	float: right;
	margin-top: 3%;
	margin-right: 3%;
}

#wode-fengexian {
	width: 90%;
	margin-left: 5%;
	border: 1px red;
}

.wode-ygk {
	text-align: left;
	width: 90%;
	padding-top: 1rem;
	padding-bottom: 1rem;
	padding-left: 1rem;
	margin-left: 5%;
	background-color: orange;
	background: url(${ctx}/static/images/sun/card-background.png);
	background-position: center;
	background-size: 100%;
	background-repeat: no-repeat;
	margin-bottom: 0.5rem;
}
.wode-ygk-userd {
	text-align: left;
	width: 90%;
	padding-top: 1rem;
	padding-bottom: 1rem;
	padding-left: 1rem;
	margin-left: 5%;
	background-color: orange;
	background: url(${ctx}/static/images/sun/card-background-is-used.png);
	background-position: center;
	background-size: 100%;
	background-repeat: no-repeat;
	margin-bottom: 0.5rem;
}

#wode-ygk-name {
	font-size: 0.7rem;
}

#wode-ygk-number {
	margin-left: 15%;
	font-size: 0.7rem;
}

.wode-download {
	/*position: relative;*/
	top: -1.5rem;
	float: right;
	padding-top: 0.2rem;
	padding-bottom: 0.2rem;
	padding-left: 0.5rem;
	padding-right: 0.5rem;
	border: solid #FF0000 1px;
	color: white;
	background-color: #FF0000;
	border-radius: 0.2rem;
	font-size: 0.65rem;
	font-family: "微软雅黑";
}

/*下载原图*/
.download-zhezhao {
	text-align: center;
	background-color: #808080;
	width: 100%;
	height: 100%;
	position: absolute;
	z-index: 2001;
	float: left;
	filter: alpha(opacity=50);
	-moz-opacity: 0.5;
	-khtml-opacity: 0.5;
	opacity: 0.5;
	visibility: visible;
}

.download-dialogue {
	width: 90%;
	padding: 0 0;	
	margin-left: 5%;
	background-color: #ffffff;
	position: absolute;
	z-index: 2002;
	align-content: center;
	top: 30%;
	border-radius: 0.3rem;
}
.top-part{
    padding: 1rem 0 0.3rem 0;
	width: 100%;
    background-color: #eeeeee;
	border-top-left-radius: 0.3rem;
	border-top-right-radius: 0.3rem;
}
.email {

	width: 80%;
	height: 1.5rem;
    background: url(${ctx}/static/images/sun/emailtu.png);
    background-repeat: no-repeat;
    background-size: auto 100%;
	margin: 0 auto;
	padding-left: 2rem;
	
}
#fault{
	width: 0.5rem;
	position: relative ;
	float: right;
	margin-top: 3%;
	margin-right: 3%;
}
.introduction {
	width: 85%;


	margin: 0 auto;

	margin-top: 0.5rem;
}

#email-input{
	width: 90%;
	margin-top: 0.4rem;
	background: none;
	border: solid orange 0px;
	font-size: 0.7rem;
	text-align: center;
}
.fgx{
	width: 80%;
	height: 1px;
	margin: 0 auto;
	margin-top: 2px;
	background-color: #909090;
}

.intro{
	font-size: 0.7rem;
}

.wode-email-confirm {
	padding-top: 0.3rem;
	padding-bottom: 0.3rem;
	width: 80%;
	margin-top: 1%;
	margin-left: 10%;
	
	color: white;
	font-size: 0.73rem;
	background-color: #ff0000;
	border: 1px solid;
	border-radius: 0.4rem;
	text-align: center;
	margin-bottom: 1rem;
}
/*下载原图*/
 </style>
			  
			  
			  
			  
               	<div class="buttons-tab">
				    <a href="#tab1" class="tab-link  button <c:if test="${empty param.tab or param.tab eq '1' }">active</c:if>">收到</a>
				    <a href="#tab2" class="tab-link button <c:if test="${param.tab eq '2' }">active</c:if>" >送出</a>
				    <a href="#tab3" class="tab-link button <c:if test="${param.tab eq '3' }">active</c:if>">阳光卡</a>   
				</div>
				
				 <div class="content-block">
                   <div class="tabs">
                   
                       <!-- 统一模板样式_标签页1  收到-->
                     <div id="tab1" class="infinite-scroll <c:if test="${empty param.tab or param.tab eq '1' }">active</c:if> tab">
                      
                         <div class="tabTemp">
                         
    
                           <c:forEach items="${page.result}" var="entity" varStatus="status">
                           
                           <div class="card polaroid-template <c:if test="${entity.status eq 100}">grey</c:if>">
                             <div class="template-top <c:if test="${entity.status eq 100}">Iread</c:if>"  onclick="window.location.href='${ctx}/wechat/order/sendeeinfo?orderId=${entity.id}'">
                          		<img alt="" width="100%" src="${config.imgDomainName}/${entity.cardImage.folder}/${entity.cardImage.name}_500.jpg" >
                             </div>
                             <div class="card-content">
                               <input type="button" value="下载原图" class="wode-download" onclick="downEmail('${entity.id}')"/>
                               <h3>${entity.title }</h3>  
                               <p class="z-text">${entity.message}</p>
                               <p class="color-gray footer-label f-6"><tags:todayDateForamt dateTime="${entity.createTime }"/></p>
                            
                             </div>
                           </div>
                           
                           </c:forEach>
                            
                           </div>
                           
                            <div class="infinite-scroll-preloader" style="display:none;">
				            <div class="preloader"></div>
				        	</div>
				        	
                       </div>
                       
                       <!-- 统一模板样式_标签页2  送出-->
                       <div id="tab2" class="infinite-scroll tab <c:if test="${param.tab eq '2' }">active</c:if>">
                       
	                       <div class="tabTemp">
	                       
	                           <c:forEach items="${page1.result}" var="entity" varStatus="status">
	                           <div class="card polaroid-template <c:if test="${entity.status eq 100}">grey</c:if>">
	                             <div class="template-top <c:if test="${entity.status eq 100}">read</c:if>"  onclick="window.location.href='${ctx}/wechat/order/sendeeinfo?orderId=${entity.id}'">
	                               <img alt="" width="100%"  src="${config.imgDomainName}/${entity.cardImage.folder}/${entity.cardImage.name}_500.jpg">
	                             </div>
	                             <div class="card-content">
	                             <!-- 下载原图 -->
                                <%-- <input type="button" value="下载原图" class="wode-download" onclick="downEmail('${entity.id}')"/> --%>
	                               <h3>祝<span>${entity.sendeeName}</span></h3>
	                               <p class="z-text">${entity.message}</p>
	                               <p class="color-gray footer-label f-6"><tags:todayDateForamt dateTime="${entity.createTime }"/></p>
	                             </div>
	                           </div>
	                           </c:forEach>
	                           
                           </div>
                           
                          
                            <!-- 加载提示符 -->
				            <div class="infinite-scroll-preloader">
				               <div class="preloader"></div>
				            </div>
		
                       </div>
                       
                       
                       
	                  <!-- 统一模板样式_标签页3  阳光卡-->  	
					  <div id="tab3" class="tab" <c:if test="${param.tab eq '3' }">active</c:if>>  <!--开始-->

								<!--已经绑定阳光卡,显示卡列表--> 
								<ul class="sun-card-list" style="list-style-type:none;margin:0;padding:0;">
								
								<li>
									<div id="tisi-weibangding">如果您尚未绑定阳光卡，请输入卡号、密码绑定</div>
									<!--未绑定阳光卡，输入阳光卡卡号、密码-->
									<div class="wode-select-sun-card-zhifu" id="wode-select-sun-card-zhifu">
										<input type="number" class="wode-input-card-info" id="wode-card-number" placeholder="输入8位阳光卡卡号 "/>
									    <input type="password" class="wode-input-card-info" id="wode-card-password" placeholder="输入6位阳光卡密码 "/>
										<p class="wode-tishi" id="check-message"></p>
										<input type="button" class="wode-input-confirm" id="wode-input-confirm" value="确定 "/>
									</div>
								
								</li>
									
								</ul>
					      
					    <!--结束-->
				        </div>
				     
				     
	              		<script type="text/javascript">
	              		var selectOrderid;
	              		function  downEmail(orderid){
	              			 selectOrderid=orderid;
	       		    	 	$.popup('.popup-mail'); 
	       		    	 	//$.closeModal(".popup-card");
	       		     	}
	              		</script>
	              		<style>
	              		.popup-mail{background: transparent;}
	              		</style>
                    </div>
                </div>
           </div>
       </div>
   </div>
   <div class="popup popup-about popup-mail">
      <!--下载原图对话框-->
		<div class="download-dialogue">
		 <img id="fault" class="popup-card-close" src="${ctx}/static/images/sun/fault.png" />
			<div class="top-part">
			    
				<div class="email">

					<input type="email" class="email-input" id="email-input" placeholder="请输入邮箱" />

				</div>
				<div class="fgx"></div>
			</div>
			<div class="introduction">
				<ol class="intro">
					<!-- <li>
						您的朋友“岩谷”已经为您购买了您生日当天的日出照片，价值99元。
					</li> -->
					<li>
						如需下载原图，请输入您的邮箱，我们将在24小时内，把原图发送到您的邮箱,节假日顺延。
					</li>
					<li>
						您也可以致电客服<a href="tel:010-57283847">010-57283847</a>、<a href="tel:010-53464383">010-53464383</a>，与我们取得联系。
					</li>
				</ol>
				
								<input type="button" class="wode-email-confirm" id="wode-email-confirm" value="确定 " />
				
			</div>
		</div>
     
     </div>  
  </body>
    <%@include file="/WEB-INF/views/work/common/staticjs.jsp" %>
  <%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>
     <%@include file="/WEB-INF/views/work/common/pageInit.jsp" %>

</html>
