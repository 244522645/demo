<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<%@include file="/WEB-INF/views/work/common/taglibs.jsp"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>给点儿阳光</title>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
	<script src="${ctx }/static/letter/js/modernizr.js" type="text/javascript" charset="utf-8"></script>
	<link rel="stylesheet" href="${ctx }/static/letter/css/style.css?t=new Date()">
	<script>
		var ctx = '${pageContext.request.contextPath}';
		var userName = '${userInfo.wechatNickname}';
	</script>
	 <script type="text/javascript">
	//朋友圈 参数
	 var com_share_title="${config.siteName}",
	 com_share_content="${config.description}",
	 com_share_link="${config.domainName}/wechat/letter",
	 domainName="${config.domainName}",
	 com_share_imgUrl="${config.domainName}/static/images/sun/logo-icon.png";
	 var letter_orderId='${param.orderId}';
	 </script> 
	 <script type="text/javascript" src="${ctx}/static/js/sun/base.js?V=4.o"></script> 
	<script type="text/javascript" src="${ctx }/static/js/jquery-2.1.4.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/letter/js/main.js?t=new Date()" ></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	
</head>
<body>
	<div class="tishi_send">
		<img src="${ctx}/static/letter/img/tishi.png" />
	</div>
	<section class="container" id="contact" >
		<form class="flip">
			<div class="front">
				<div class="music_back" style="width:3rem;float:left;">
					<i id="playImg" src="${ctx}/static/letter/img/music1.png" style="z-index: 0;"></i><span id="sel_yy">选音乐</span>
				</div>
				<img alt="" src="${ctx}/static/letter/images/stamp.png" style="
				float:right;
				right: 0.2rem;
				top:0.2rem;
				width:4rem;
				height:4rem;">
				<a id="flip2back" style="width:3rem;" href="#">《
				</a>
				<p style="margin-top: -0.6rem;text-align: center;">简信 · 给点儿阳光   </p>
				
			</div>
			<div id="content" class="back">
				<div class="postcard">
					<div class="yg_card">
					</div>
					<div class="zf_postcard">
					</div>
				</div>
				<div id="letter"> 
					<div class="container">
						<div class="flip">
							<div class="front"></div>
			  				<div class="back"> 
			  					<a id="sel_zf" class="item" href="javascript:void(0)">
									<span class="icon ico_rc"></span>
									<span>日出</span>
								</a>
								<a id="rec_bt" class="item" href="javascript:void(0)">
									<span class="icon ico_yy"></span>
									<span>语音</span>
								</a>
								<a id="pic_bt" class="item" href="javascript:void(0)">
									<span class="icon ico_card"></span>
									<span>阳光卡</span>
								</a>
								<a href="#" id="shouqi" class="shouqi">收起</a>
							</div>
						</div>
					</div> 
					<textarea  class="required" id="message" name="message" placeholder="请输入简信内容..."></textarea>	
				</div>
				<div id="top">
					<a id="flip" href="#contact"><img  src="${ctx}/static/letter/img/music1.png" style="width: 1.2rem;"/></a>
				</div>
				<div id="lid" class="container">
  					<div class="flip">    
  						<div class="front">
							<div style="line-height: 1.5rem;">
								<label for="name">发信人 :</label>
								<input type="text"  id="sender" name="sender" value="${userInfo.wechatNickname}">
							</div>
							<div>
								<label for="mail">收信人 :</label>
								<input type="text" id="addressee" name="addressee" >
							</div>
							<a id="open" href="#content">打开</a>
						</div>
						<div class="back"></div>
					</div>
				</div>
			</div>
		</form>
	</section>
	<div class="gdyg_popup select_zf" > 
		<div id="dtBox"></div>
		<div class="gdyg_header">
			<span class="left_but hed_but" id="zf_bt_close">取消</span>
			<span>我的日出明信片</span>
			<span class="right_but hed_but" id="zf_bt_true">确定</span>
		</div> 
		<div class="gdyg_content zf_content">
			<ul>
				<li class="pic_prompt">
					送出我制作的明信片
				</li>
				<li id="loding" style="margin-top: 3rem">
					请稍等，您的日出明信片正在加载中...
				</li>
			</ul>
		</div>
	</div>
	<div class="gdyg_popup select_pic" > 
		<div id="dtBox"></div>
		<div class="gdyg_header">
			<span class="left_but hed_but" id="pic_bt_close">取消</span>
			<span>我的阳光卡</span>
			<span class="right_but hed_but" id="pic_bt_true">确定</span>
		</div> 
		<div class="gdyg_content pic_content">
			<ul>
				<li class="pic_prompt">
					送出我的阳光卡
				</li>
				<li id="loding" style="margin-top: 3rem">
					请稍等，您的阳光卡正在加载中...
				</li>
			</ul>
			
			  <div style="
				    text-align: center;
				    margin: 0 auto;
				">
				  <a style="text-align: center;color: blue;
				    border: 1px solid #d8d4d4;
				    padding: .2rem .4rem;
				    display: block;
				    margin: 0 auto;
				    width: 7rem;border-radius: 0.25rem;
				" id="card_dec_btn"> 查看阳光卡使用说明 <br></a>
				  </div>
				    <p style="
				    text-align: center;
				    color: blue;
				"> &nbsp;</p>
		</div>
	</div>
	<div class="card_dec" > 
		<div id="dtBox"></div>
		<div class="gdyg_header">
			<span>阳光卡说明</span>
			<span class="right_but" id="card_dec_bt_true" style="position:absolute;"><img src="${ctx}/static/letter/img/close.png" style="width:1rem;margin-top: .1rem;"/></span>
		</div> 
		<div class="gdyg_content pic_content">
			<div class="item-inner" style="font-size: .7rem;margin-top:2rem;padding:1rem">
	      		 1、 阳光卡是《给点儿阳光》推出的通用卡<br/><br/>
	      		 2、阳光卡可用来制作日出明信片，也可以通过简信送给其他人<br/><br/>
	      		 3、可通过“我要代言”活动领取阳光卡<br/><br/>
	      		 4、使用方式：选择一张日出照片，点击进行明信片制作，最后支付环节选择“阳光卡支付”即可
	        </div>
		</div>
	</div>
	<div class="select_recording">
		<div class="recording_content">
			<div class="recording_close"><img src="${ctx}/static/letter/img/close.png" /></div>
			<div class="recording_time">
				<img src="${ctx}/static/letter/img/2.jpg" />	
				<span id="rec_time">00"</span>
			</div>
			<div class="recording_yingui">
				<div id="yingui" class="yingui"></div>
			</div>
			<div class="recording">
				<div class="begin">
					<div id="begin_img"  class="begin_img" ></div>
					<div id="begin_set">开始</div>
				</div>
				<div id="set_recording" class="set">
					<span id="chonglu"></span><span id="queding"></span>
				</div>
			</div>
			<div id='t_recording' class='recording_ing'>上传中..</div>
		</div>
	</div>
	<div class="gdyg_popup select_music">
		<div class="gdyg_header">
			<span class="left_but hed_but" id="music_close">关闭</span>
			<span class="right_but hed_but" id="music_no">无音乐</span>
		</div> 
		<div class="gdyg_content music_content">
			<ul class="music_ul">
			</ul>
		</div>
	</div>
	<audio id="audio"  src="">
		您的浏览器不支持播放歌曲
	</audio>
	<div class="huixin">
		<img id="huixin"  src="${ctx}/static/letter/img/send_but.png" />
	</div>
	<div class="erweima">
		<ul>
			<li>
				<span class="colose" id="colose_er"></span>
				<div class="erweima_container">
					<div class="er_msg">确定将这封写好的信发送给朋友吗？</div>
					<div class="er_but">
						<span class="fs"></span>
						<span class="zjcyb"></span>
					</div>
				</div>
			</li>
		</ul>
	</div>
</body>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>	
</html>
