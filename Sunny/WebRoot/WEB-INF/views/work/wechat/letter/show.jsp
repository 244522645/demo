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
	<link rel="stylesheet" href="${ctx }/static/letter/css/style.css">
	<script>
		var ctx = '${pageContext.request.contextPath}';
	</script>
	 <script type="text/javascript">
	//朋友圈 参数
	 var com_share_title="${config.siteName}",
	 com_share_content="${config.description}",
	 com_share_link="${config.domainName}/wechat/letter"
	 domainName="${config.domainName}",
	 com_share_imgUrl="${config.domainName}/static/images/sun/logo-icon.png";
	 </script> 
	 <script type="text/javascript" src="${ctx}/static/js/sun/base.js?V=3.o"></script> 
	<script type="text/javascript" src="${ctx }/static/js/jquery-2.1.4.min.js" ></script>
	<script type="text/javascript" src="${ctx }/static/letter/js/shoudao.js" ></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	 <script type="text/javascript">
	 wxShare.isOnlyApp=true;
	 wxShare.onlyApp={
					title:"${letter.sender }寄来一封简信",
					imgUrl:domainName+'/static/letter/img/wechat_jianxin.jpg',
					link:domainName+'/wechat/letter/show?letterId=${letter.id }',
					content:'你好，我是邮递员小光，你的朋友${letter.sender }托我给您寄来一封简信，祝您愉快！',
					success:letterShare.success,	
					cancel:letterShare.cancel,
	 }
	 letterShare.letterId='${letter.id }';
	 </script> 
</head>
<body>		
	<div class="tishi_send" style="<c:if test="${author eq 'true' }">display:block;</c:if>">
		<img src="${ctx}/static/letter/img/tishi.png" />
	</div>
	<section class="container" id="contact">
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
						<c:forEach items="${cards}" var="cards" varStatus="status">
							<img  src="${ctx}/static/letter/img/yg_card.png"  id="${cards.id}" style="top:0.8rem" />
						</c:forEach>
					</div>
					<div class="zf_postcard">
						<c:forEach items="${blesss}" var="entity" varStatus="status">
							<img  src="${entity.bless.cardImage.localPath}_500.jpg"  id="${entity.id}" style="top:0rem" />
						</c:forEach>
					</div>
				</div>
				<div id="letter"> 
					<div class="container">
						<div class="flip">
							<div class="front"></div>
			  				<div class="back"> 
			  				    <c:if test="${letter.isorder==1}">
			  				    	<a id="sel_zf" class="item" href="javascript:void(0)">
										<span class="icon ico_rc"></span>
										<span>日出</span>
									</a>
			  				    </c:if>
			  				    <c:if test="${letter.isvoice==1}">
			  				    	<a id="rec_bt" class="item" href="javascript:void(0)">
										<span class="icon ico_yy"></span>
										<span>语音</span>
									</a>
			  				    </c:if>
			  				    <c:if test="${letter.iscard==1}">
			  				    	<a id="pic_bt" class="item" href="javascript:void(0)">
										<span class="icon ico_card"></span>
										<span>阳光卡</span>
									</a>
			  				    </c:if>
								<a href="#" id="shouqi" class="shouqi">收起</a>
							</div>
						</div>
					</div>
					<div class="mes_content">${letter.bless }</div>
				</div>
				<div id="top">
					<a id="flip" href="#contact"><img  src="${ctx}/static/letter/img/music1.png" style="width: 1.2rem;"/></a>
				</div>
				<div id="lid" class="container">
  					<div class="flip">    
  						<div class="front">
							<div style="line-height: 1.6rem;">
								<label for="name">发信人 : </label>
								<label>${letter.sender }</label>
							</div>
							<div>
								<label for="mail">收信人 : </label>
								<label>${letter.receiver }</label>
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
			<span>日出明信片</span>
			<span class="right_but" id="zf_bt_close" style="position:absolute;"><img src="${ctx}/static/letter/img/close.png" style="width:1rem;margin-top: .1rem;"/></span>
		</div> 
		<div class="gdyg_content zf_content">
			<ul>
				<li class="pic_prompt">
					送来日出明信片
				</li>
				<c:forEach items="${blesss}" var="entity" varStatus="status">
					<li class="pic_li ">
						<img dt="${entity.bless.cardImage.localPath}_y1080.jpg"  src="${entity.bless.cardImage.localPath}_500.jpg"  id="${entity.bless.id}">
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
	<div class="gdyg_popup select_pic" > 
		<div id="dtBox"></div>
		<div class="gdyg_header">
			<span>阳光卡</span>
			<span class="right_but" id="pic_bt_close" style="position:absolute;"><img src="${ctx}/static/letter/img/close.png" style="width:1rem;margin-top: .1rem;"/></span>
		</div> 
		<div class="gdyg_content pic_content">
			<ul>
				<li class="pic_prompt">
					阳光卡可购买日出明信片哦
				</li>
				<c:forEach items="${cards}" var="cards" varStatus="status">
					<li class="pic_li ">
						<img  src="${ctx}/static/letter/img/yg_card.png"  id="${cards.id}">
					</li>
				</c:forEach>
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
				<img src="${letter.userId.wechatHeadUrl }" />	
				<span id="rec_time">
					<c:if test="${letter.voice.time<10}">0${letter.voice.time}"</c:if>
					<c:if test="${letter.voice.time>=10}">${letter.voice.time}"</c:if>
				</span>
			</div>
			<div class="recording_yingui">
				<div id="yingui" class="yingui"></div>
			</div>
			<div class="recording">
				<div class="begin">
					<div id="begin_img"  class="begin_img" ></div>
					<div id="begin_set">播放</div>
				</div>
			</div>
		</div>
	</div>
	<c:if test="${author != 'true' }">
		<div class="huixin" >
		<c:if test="${login != 'no'}">
			<a href="${ctx}/wechat/letter">
				<img   src="${ctx}/static/letter/img/hx.png" style="display: inline;"/>
			</a>
		</c:if>
		<c:if test="${login eq 'no'}">
				<img id="huixin"  src="${ctx}/static/letter/img/hx.png" style="display: inline;"/>
		</c:if>
		</div>
	</c:if>
	<div class="erweima">
		<ul>
			<li>
				<span class="colose" id="colose_er"></span>
				<div class="erweima_container">
					<div class="weima">
						<img  src="${ctx}/static/letter/images/erweima-letter.png" />
					</div>
					<div class="tishi_font">
						<span>长按二维码关注</span>
						<span>关注之后即可写回信</span>
					</div>
				</div>
			</li>
		</ul>
	</div>
<!-- 	<audio id="audio" loop="loop" src="">
		您的浏览器不支持播放歌曲
	</audio>
	<audio id="ricodAudio"  src="">
		您的浏览器不支持播放歌曲
	</audio> -->
	<audio id="audio" preload="auto" loop="loop"  autoplay  >
		<source src="${letter.music.url}" type="audio/mpeg">
		您的浏览器不支持播放歌曲
	</audio>
	<audio id="ricodAudio"  preload="auto" >
		<source src="<c:if test="${letter.isvoice eq 1 and not empty letter.voice}">${letter.voice.url}</c:if>" type="audio/mpeg">
		
		您的浏览器不支持播放歌曲
	</audio>
</body>

<script type="text/javascript">
	$(function(){
		
		SelectReco.init('${letter.voice.url}',<c:if test="${empty letter.voice.time}">0</c:if><c:if test="${not empty letter.voice.time}">${letter.voice.time}</c:if>);
		BackMusic.inti('${letter.music.url}');
	});
</script>
<%@include file="/WEB-INF/views/work/wechat/wxjsConfig.jsp" %>	

</html>
