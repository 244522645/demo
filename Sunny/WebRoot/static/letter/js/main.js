var recor;
$(function(){
	recor = new recordingWork();
	recor.init();
	$("#rec_bt").click(function(){
		recor.openRecording();
	});
	
	SelectionPic.init();
	SelectMusic.init();
	SendMsg.init();
	Send.init();
	SelectZf.init();
	
});


//阳光卡选择控件
SelectionPic = {
	
	init:function(){
		SelectionPic.loadPic();
		$("#pic_bt").click(function(){
			SelectionPic.showPic();
		});
		$("#pic_bt_true").click(function(){
			SelectionPic.hidePic();
			SelectionPic.setPic();
		});
		$("#pic_bt_close").click(function(){
			SelectionPic.hidePic();
		});
		
		$("#card_dec_btn").click(function(){
			$(".card_dec").show();
			setTimeout(function(){
			    $(".card_dec").removeClass("modal-out").addClass("modal-in");
	        },100);
		});
		$("#card_dec_bt_true").click(function(){
			$(".card_dec").hide();
			setTimeout(function(){
			    $(".card_dec").removeClass("modal-in").addClass("modal-out");
	        },100);
		});
	},	
	picIsOpen:false,
	showPic:function(el){
		//设置选中状态
		if(!SendMsg.preview){  //非预览模式
			$(".pic_content .pic_li").each(function(i){
				var that  = $(this);
				that.removeClass("active");
				$(".postcard .yg_card").children("img").each(function(j){
					var ti = that.children("img");
					if(ti.attr("id")==$(this).attr("id")){
						that.addClass("active");
					}
				});
			});
		}else if(SendMsg.preview){//预览模式
			$(".pic_content .pic_li").each(function(i){
				var that  = $(this);
				that.removeClass("active");
				var del = true;
				$(".postcard .yg_card").children("img").each(function(j){
					var ti = that.children("img");
					if(ti.attr("id")==$(this).attr("id")){
						that.unbind("click");
						del = false;
					}
				});
				if(del){
					that.remove();
					del = true;
				}
			});
		}
		if(el==1){
			var s = $(".postcard .yg_card img").length;
			$(".postcard .yg_card img").each(function(i){
				var time = i*1/s;
				$(this).css("-webkit-transition-delay",time+"s").css("-moz-transition-delay",time+"s");
				$(this).css("top","-33rem");
			});
			SelectionPic.picIsOpen = false;
			$(".postcard .yg_card").one("webkitTransitionEnd otransitionend transitionend",function(){
				if(SelectionPic.picIsOpen==false){
					$(".select_pic").css("display","block");
					setTimeout(function(){
					    $(".select_pic").removeClass("modal-out").addClass("modal-in");
			        },100);
		        }
			});
		}else{
			$(".select_pic").css("display","block");
			setTimeout(function(){
			    $(".select_pic").removeClass("modal-out").addClass("modal-in");
	        },100);
		}
	},
	hidePic:function(){
        $(".select_pic").removeClass("modal-in").addClass("modal-out");
	    setTimeout(function(){
	    	$(".select_pic").css("display","none");
	    	var s = $(".postcard .yg_card img").length;
			$(".postcard .yg_card img").each(function(i){
				var time = i*1/s;
				$(this).css("-webkit-transition-delay",time+"s").css("-moz-transition-delay",time+"s");
				$(this).css("top",".8rem");
			});
        },400);
        SelectionPic.picIsOpen = true;
	},
	loadPic:function(){
		$.post(ctx+"/wechat/my/card/getSunCard",{},function(result){
			$(".pic_content #loding").remove();
			var html = "";
			var data = result.result;
			if(data.length>0){
				for(i=0;i<data.length;i++){
					html +=  "<li class='pic_li'><img src='"+ctx+"/static/letter/img/yg_card.png' id='"+data[i].id+"' /><span class='select_pic_ico'></span></li>";
				}
				$(".pic_content .pic_prompt").after(html);
				$(".pic_content .pic_li ").click(function(){
					SelectionPic.selection(this);
				});
			}else{
				var html  =  "<li class='kong'>"+
								"<i class='kong_tishi'></i>"+
								"<div class='kong_font'>没有阳光卡！<br/><br/>有很多途径可以领取阳光卡<br/><br/>阳光卡还可以放进简信送人哦"+
								"</div>"+
							"</li>";
				$(".pic_content .pic_prompt").after(html);
			}
		});
	},
	setPic:function(){
		var postcard = $(".yg_card");
		postcard.html("");
		var s = $(".pic_content .pic_li.active").length;
		$(".pic_content .pic_li.active").each(function(i){
			var time = i*1/s;
			var img = $(this).children("img").clone(true);
			img.css("-webkit-transition-delay",time+"s").css("-moz-transition-delay",time+"s");
			postcard.append(img);
			img.click(function(){
				SelectionPic.showPic(1);
			});
		});
		setTimeout(function(){
	    	$(".yg_card img").css("top",".8rem");
        },100);
	},
	selection:function(el){
		if($(el).hasClass("active")){
			$(el).removeClass("active");
		}else{
			$(el).addClass("active");
		}
	},
	previewInit : function(){ //预览模式初始化
		if($(".yg_card img").length==0){
			$("#pic_bt").remove();
		}
		$("#pic_bt_true").remove();
		$(".pic_content .pic_li span").remove();
	}
	
}

/**
 * 录音
 * */
function recordingWork(){
	
	this.recordState = 1; // 0、准备录制   1、 开始录制  2、录制中 3、停止录制  4、试听中  -1 预览
	this.recordTime = 0;
	this.localId = null;
	this.voiceId = null;
	
	var that = this;  //定义自身，给私有方法使用
	var countdown = 0;
	var setTimer;
	
	//录制读秒
	function recordingTimer(val){
		if (countdown-1 == 60) { 
			that.stopRecording();
			countdown = 0;
		} else { 
			if(countdown<10)
				val.html("0"+countdown+"\"");
			else
				val.html(""+countdown+"\""); 
			countdown++;
			if(countdown>0){
				setTimer = setTimeout(function() { 
					recordingTimer(val); 
				},1000) ;
			}
		} 
    }    
    
    //试听读秒
    function auditionTimer(val){
		if (0 == countdown-1) { 
			if(SendMsg.preview){
				that.stopPreviewRecording();
			}else{
				that.stopAudition();
			}
		} else { 
			countdown--;
			if(countdown<10)
				val.html("0"+countdown+"\"");
			else
				val.html(""+countdown+"\""); 
			if(countdown>0){
				setTimer = setTimeout(function() { 
					auditionTimer(val); 
				},1000);
			}
		} 
    }    
	
	//录制按钮切换
	function recordingButtonSwitch(){
		if(that.recordState==0){
			that.recordState=1;
			$("#begin_set").html("开始");
			$("#begin_img").attr("class","begin_img");
			$("#yingui").attr("class","yingui");
			$("#rec_time").html("00\"");
			that.recordTime = 0;
			$("#begin_img").unbind( "click" ).click(function(){
				that.startRecording();
			});
			$("#set_recording").attr("class","set_recording");
		}else if(that.recordState==1){
			that.recordState = 2;
			$("#begin_set").html("录制中...");
			$("#begin_img").attr("class","ing_img");
			$("#yingui").attr("class","ing_yingui");
			recordingTimer($("#rec_time")); //读秒
			$("#begin_img").unbind( "click" ).click(function(){
				that.stopRecording();
			});
		}else if(that.recordState==2){
			that.recordState = 3;
			$("#begin_set").html("试听");
			$("#begin_img").attr("class","begin_img");
			$("#yingui").attr("class","yingui");
			clearTimeout(setTimer);
			if(that.recordTime<10){
				$("#rec_time").html("0"+that.recordTime+"\"");
			}else{
				$("#rec_time").html(""+that.recordTime+"\"");
			}
			$("#begin_img").unbind( "click" ).click(function(){
				that.startAudition();
			}); 
			$("#set_recording").attr("class","set");
		}else if(that.recordState==3){
			that.recordState = 4;
			countdown = that.recordTime+1;
			$("#begin_set").html("试听中...");
			$("#begin_img").attr("class","ing_img");
			$("#yingui").attr("class","ing_yingui");
			auditionTimer($("#rec_time")); //读秒
			$("#begin_img").unbind( "click" ).click(function(){
				that.stopAudition();
			});
			$("#set_recording").attr("class","set_recording");
		}else if(that.recordState==-1){
			countdown = that.recordTime;
			$("#chonglu").remove();
			$("#queding").remove();
			$("#begin_set").html("听");
			$("#begin_img").click(function(){
				if($("#begin_img").hasClass("begin_img")){
					$("#begin_img").removeClass("begin_img").addClass("ing_img");
					$("#yingui").attr("class","ing_yingui");
					$("#begin_set").html("听..");
					wx.playVoice({
						localId: that.localId 
					});
					wx.onVoicePlayEnd({
					    success: function (res) {
					    	that.stopPreviewRecording();
					    }
					});
					auditionTimer($("#rec_time")); 
				}else if($("#begin_img").hasClass("ing_img")){
					that.stopPreviewRecording();
				}
			});
		}
	}
	
	this.init = function(){
		$(".recording_close").click(function(){ //关闭
			that.closeRecording();
		});
		$("#begin_img").click(function(){
			that.startRecording();
		});
		$("#chonglu").click(function(){
			that.resetRecording();
		});
		$("#queding").click(function(){
			that.determineRecording();
		});
		$("#set_recording").attr("class","set_begin");
	}
	//打开录制界面
	this.openRecording =  function(){
		$(".select_recording").show();
		setTimeout(function(){
		 	$(".recording_content").css("margin-top","38%");
		},10);
	}
	//关闭录制界面
	this.closeRecording = function(){
		$(".recording_content").css("margin-top","200%");
		setTimeout(function(){
		 	$(".select_recording").hide();
		},400);
		this.stopAudition();
		//this.uploadRecording();
	}
	
	//开始录制
	this.startRecording = function(){
		if(this.recordState==1){
			countdown = 0;
			that.recordTime = 0;
			recordingButtonSwitch();
			wx.startRecord();
			wx.onVoiceRecordEnd({
			    // 录音时间超过一分钟没有停止的时候会执行 complete 回调
			    complete: function (res) {
			        that.stopRecording(res)
			    }
			});
		}
	}
	
	//停止录制
	this.stopRecording = function(){
		if(this.recordState==2){
			that.recordTime=countdown-1;
			recordingButtonSwitch();
			wx.stopRecord({
			    success: function (res) {
			    	that.localId = res.localId;
			    }
		   });
		}
	}
	
	//开始试听
	this.startAudition = function(){
		if(this.recordState==3){
			recordingButtonSwitch();
			wx.playVoice({
				localId: that.localId 
			});
			wx.onVoicePlayEnd({
			    success: function (res) {
			       that.stopAudition(res)
			    }
			});
		}
	}
	
	//停止试听
	this.stopAudition = function(){
		if(this.recordState==4){
			this.recordState = 2;
			recordingButtonSwitch();
			wx.stopVoice({
				  localId: that.localId
			});
		}
	}
	
	//重录
	this.resetRecording = function(){
		if(this.recordState ==3){
			this.recordState = 0;
			recordingButtonSwitch();
			that.localId = null;
			$("#rec_bt .ico_yy_y").attr("class","icon ico_yy");
		}
	}
	//确定
	this.determineRecording = function(){
		if(this.recordState ==3){
			that.uploadRecording();
		}
	}
	
	//上传语音
	this.uploadRecording = function(){
		if(that.localId==null||that.recordTime==0){
			$("#rec_bt .ico_yy_y").attr("class","icon ico_yy");
			return ;
		}
		$(".recording").css("display","none");
		$("#t_recording").css("display","block");
		wx.uploadVoice({
		    localId: that.localId, 
		    isShowProgressTips: 0,
	        success: function (res) {
	        	$.post(ctx+"/wechat/media/upVoice", {serverId: res.serverId,token:'',longTime:that.recordTime}, function(response){
	        		if(response.state==1){
	        			that.voiceId=response.t.id;
	        		}else{
	        			alert('语音上传失败');
	        		}
	        		$("#voicelong").html(that.recordTime+'"');
	        		that.closeRecording();
        			$("#t_recording").css("display","none");
        			$(".recording").css("display","block");
        			$("#rec_bt .ico_yy").attr("class","icon ico_yy_y");
	     	    })
		    }
		});
	}
	//预览
	this.previewRecording = function(){
		this.recordState = -1;
		recordingButtonSwitch();
	}
	//停止预览
	this.stopPreviewRecording = function(){
		$("#begin_img").removeClass("ing_img").addClass("begin_img");
		$("#yingui").attr("class","yingui");
		$("#begin_set").html("听");
		clearTimeout(setTimer);
		if(that.recordTime<10){
			$("#rec_time").html("0"+that.recordTime+"\"");
		}else{
			$("#rec_time").html(""+that.recordTime+"\"");
		}
		wx.stopVoice({
			  localId: that.localId
		});
		countdown = that.recordTime;
	}
}

SelectMusic = {
	audio:function(){
		return document.getElementById('audio');
	},
	init:function(){
		$("#sel_yy").click(function(){
			SelectMusic.showMusic();
		});
		$("#music_close").click(function(){
			SelectMusic.hideMusic();
		});
		$("#music_no").click(function(){
			SelectMusic.determineMusic();
			SelectMusic.determineMusic(mus);
			SelectMusic.hideMusic();
		});
		var html = "";
		$.get(ctx+"/wechat/letter/music",{},function(data){
			for(i = 0;i<data.length;i++){
				if(data[i].id!=null){
					html += "<li  dd='"+data[i].id+"' url='"+data[i].url+"'><p>"+data[i].name+"</p><i class='play'>确定</i></li>";
				}else{
					return;
				}
			}
			$(".music_ul").append(html);
			$(".music_ul li").click(function(){
				SelectMusic.selMusic($(this));
			});
			$(".music_ul li i").unbind("click").click(function(e){
				var mus = new Object();
				mus.dd = $(this).parent().attr("dd");
				mus.name = $(this).parent().find("p").html();
				if(mus.name.length>8){
					mus.name = mus.name.substr(0, 8)+".."; 
				}
				mus.url = $(this).parent().attr("url");
				SelectMusic.determineMusic(mus);
				e.stopPropagation(); 
			});
		});
	},
	showMusic:function(){
		$(".select_music").css({"display":"block"});
	    setTimeout(function(){
	    	$(".select_music").removeClass("modal-out").addClass("modal-in");
        },100);
        $("#playImg").removeClass("rotate");
	},
	hideMusic:function(){
		$(".select_music").removeClass("modal-in").addClass("modal-out");
	    setTimeout(function(){
	    	$(".select_music").css("display","none");
        },400);
        //SelectMusic.audio().pause();
        //$(".music_ul li").removeClass("active");
	},
	selMusic:function(el){
		if(!$(el).hasClass("active")){
			$(".music_ul li").removeClass("active");
			$(el).addClass("active");
			SelectMusic.auditionMusic(el,true);
		}else{
			$(".music_ul li").removeClass("active");
			SelectMusic.auditionMusic(el,false);
		}
	},
	auditionMusic:function(el,ps){
		var id  = $(el).attr("dd");
		if(ps){
			$(SelectMusic.audio()).attr("src",$(el).attr("url"));
			SelectMusic.audio().play();
		}else{
			SelectMusic.audio().pause();
		}
	},
	determineMusic:function(mus){
		if(mus==null){
			$("#sel_yy").html("选音乐");
			$("#playImg").unbind("click");
			SelectMusic.audio().pause();
			SelectMusic.audio().currentTime=0;
			return;
		}
		SelectMusic.hideMusic();
		$("#sel_yy").html(mus.name);
		$("#sel_yy").attr("dd",mus.dd);
		$(SelectMusic.audio()).attr("src",mus.url);
		SelectMusic.audio().play();
		$("#playImg").addClass("rotate");
		$("#playImg").unbind("click").click(function(){
			if($(this).hasClass("rotate")){
				$(this).removeClass("rotate");
				SelectMusic.audio().pause();
			}else{
				$(this).addClass("rotate");
				SelectMusic.audio().play();
			}
		});
	},
	playMusic : function(){
		if($("#sel_yy").html()=="选音乐"){
			$("#sel_yy").html("");
		}
		$("#sel_yy").unbind("click");
		if($(SelectMusic.audio()).attr("src")!=''){
			SelectMusic.audio().pause();
		    SelectMusic.audio().currentTime=0;
			SelectMusic.audio().play();
			$("#playImg").addClass("rotate");
		}
	}
}

Send = {
	send : false,
	init : function(){
		$("#sender").bind("input propertychange", function() {
			Send.showSendBut();
		});
		$("#addressee").bind("input propertychange", function() {
			Send.showSendBut();
		});
		$("#message").keyup(function() {
			Send.showSendBut();
		});
		$("#shouqi").click(function(){
			if(SendMsg.preview==false){
				Send.send = true;
				Send.showSendBut();
			}
		});
		$("#open").click(function(){
			if(SendMsg.preview==false){
				Send.send = false;
				Send.showSendBut();
			}
		});
		$("#flip2back").click(function(){
			if(SendMsg.preview==false){
				Send.send = true;
				Send.showSendBut();
			}
		});
		$("#flip").click(function(){
			if(SendMsg.preview==false){
				Send.send = true;
				Send.showSendBut();
			}
		});
		$("#send").click(function(){
			$(".tishi_back").show(); 
		});
		$("#send_true").click(function(){
			$(".tishi_form").hide();
			$("#open").hide(); 
			$("#send").hide();
			$(".tishi_content").addClass("top");
			$(".tishi_send").show();
		});
		$("#send_false").click(function(){
			$(".tishi_back").hide(); 
		});
	},
	showSendBut : function(){
		var sender = $("#sender").val().trim();
		var addressee = $("#addressee").val().trim();
		var message = $("#message").val();
		if(sender!=''&&addressee!=''&&message!=''&&Send.send)
			$("#huixin").show();
		else
			$("#huixin").hide();
	}
}
SendMsg = {
	init : function(){
		$("#huixin").click(function(){
			SendMsg.showMsg();
		});
		$("#colose_er").click(function(){
			SendMsg.hideMsg();
		});
		$(".zjcyb").click(function(){
			SendMsg.hideMsg();
		});
		$(".fs").click(function(){
			SendMsg.previewMsg();
		});
	},
	preview : false,
	showMsg : function(){
		$("#contact").removeClass("lo").addClass("up");
		$(".huixin").hide();
		$(".erweima").css("display","block");
		setTimeout(function(){
		    $(".erweima").removeClass("lo").addClass("up");
        },100);
	},
	hideMsg : function(){
		$(".erweima").removeClass("up").addClass("lo");
		setTimeout(function(){
		    $(".erweima").css("display","none");
        },200);
		$("#contact").removeClass("up").addClass("lo");
		$(".huixin").show(400);
	},
	previewMsg : function(){
		SendMsg.preview = true; //打开预览模式
		var sender = $("#sender").val();
		var receiver = $("#addressee").val();
		var message = $("#message").val();
		var orders = new Array();
		$(".zf_postcard img").each(function(){
			orders.push($(this).attr("id"));
		});
		var cards = new Array();
		$(".yg_card img").each(function(){
			cards.push($(this).attr("id"));
		});
		var music = $("#sel_yy").attr("dd");
		Letter.init(null, sender, receiver, message, recor.voiceId, orders, music, null, cards);
		Letter.sendLetter();
	}
}
//选择祝福卡
SelectZf = {
	init : function(){
		SelectZf.loadZf();
		$("#sel_zf").click(function(){
			SelectZf.showZf();
		});
		$("#zf_bt_true").click(function(){
			SelectZf.hideZf();
			SelectZf.setZf();
		});
		$("#zf_bt_close").click(function(){
			SelectZf.hideZf();
		});
	},
	picIsOpen : false,
	showZf : function(el){
        //设置选中状态
		if(!SendMsg.preview){  //非预览模式
			$(".zf_content .pic_li").each(function(i){
				var that  = $(this);
				that.removeClass("active");
				$(".postcard .zf_postcard").children("img").each(function(j){
					var ti = that.children("img");
					if(ti.attr("id")==$(this).attr("id")){
						that.addClass("active");
					}
				});
			});
		}else if(SendMsg.preview){//预览模式
			$(".zf_content .pic_li").each(function(i){
				var that  = $(this);
				that.removeClass("active");
				var del = true;
				$(".postcard .zf_postcard").children("img").each(function(j){
					var ti = that.children("img");
					if(ti.attr("id")==$(this).attr("id")){
						that.unbind("click");
						del = false;
					}
				});
				if(del){
					that.remove();
					del = true;
				}
			});
		}
		if(el==1){
			var s = $(".postcard .zf_postcard img").length;
			$(".postcard .zf_postcard img").each(function(i){
				var time = i*1/s;
				$(this).css("-webkit-transition-delay",time+"s").css("-moz-transition-delay",time+"s");
				$(this).css("top","-33rem");
			});
			SelectZf.picIsOpen = false;
			$(".postcard .zf_postcard").one("webkitTransitionEnd otransitionend transitionend",function(){
				if(SelectZf.picIsOpen==false){
					$(".select_zf").css("display","block");
					setTimeout(function(){
					    $(".select_zf").removeClass("modal-out").addClass("modal-in");
			        },100);
		        }
			});
		}else{
			$(".select_zf").css("display","block");
			setTimeout(function(){
			    $(".select_zf").removeClass("modal-out").addClass("modal-in");
	        },100);
		}
        
	},
	hideZf : function(){
		$(".select_zf").removeClass("modal-in").addClass("modal-out");
	    setTimeout(function(){
	    	$(".select_zf").css("display","none");
	    	var s = $(".postcard .zf_postcard img").length;
			$(".postcard .zf_postcard img").each(function(i){
				var time = i*1/s;
				$(this).css("-webkit-transition-delay",time+"s").css("-moz-transition-delay",time+"s");
				$(this).css("top","0rem");
			});
        },400);
        SelectZf.picIsOpen = true;
	},
	loadZf : function(){
		$.get(ctx+"/wechat/bless/sendable",{page:1,pageSize:100},function(result){
			var html = "";
			var data = result.result;
			$(".zf_content #loding").remove();
			if(data.length>0){
				for(i=0;i<data.length;i++){
					try{
						html +=  "<li class='pic_li'><img dt='"+data[i].cardImage.localPath+"_y1080.jpg' src='"+data[i].cardImage.localPath+"_500.jpg' id='"+data[i].id+"' /><span class='select_zf_ico'></span></li>";
					}catch(e){
						
					}
				}
				$(".zf_content .pic_prompt").after(html);
				$(".zf_content .pic_li span").click(function(){
					SelectZf.selection($(this).parent());
				});
				var urls = new Array();
				$(".zf_content .pic_li img").each(function(){
					urls.push($(this).attr('dt'));
				});
				$(".zf_content .pic_li img").click(function(){
					SelectZf.wxPreviewImage($(this).attr('dt'), urls);
				});
				
				if(letter_orderId!=''){
					//SelectZf.showZf();
					$.get(ctx+"/wechat/bless/getBlessByorder",{orderId:letter_orderId},function(result){
						if(result.state==1){
							
							$(".zf_content .pic_li").each(function(i){
								var that  = $(this);
								that.removeClass("active");
								var ti = that.children("img");
								if(ti.attr("id")==result.t.id){
									that.addClass("active");
									letter_orderId='';
									$("#message").val(result.t.bless);
									$("#addressee").val(result.t.receiver);
									SelectZf.hideZf();
									SelectZf.setZf();
								}
							});
						}
					});
				}
			}else{
				var html = "<li class='kong'>"+
								"<i class='kong_tishi'></i>"+
								"<div class='kong_font'>没有日出明信片！<br/><br/>制作好的明信片可以放进<br/><br/>简信发送给朋友的哦"+
								"</div>"+
							"</li>";
				$(".zf_content .pic_prompt").after(html);
			}
		});
	},
	setZf : function(){
		var postcard = $(".zf_postcard");
		postcard.html("");
		var s = $(".zf_content .pic_li.active").length;
		$(".zf_content .pic_li.active").each(function(i){
			var time = i*1/s;
			var img = $(this).children("img").clone(true);
			img.css("-webkit-transition-delay",time+"s").css("-moz-transition-delay",time+"s");
			postcard.append(img);
			img.unbind("click").click(function(){
				SelectZf.showZf(1);
			});
		});
		setTimeout(function(){
	    	$(".zf_postcard img").css("top","0px");
        },100);
	},
	selection : function(el){
		if($(el).hasClass("active")){
			$(el).removeClass("active");
		}else{
			$(el).addClass("active");
		}
	},
	previewInit : function(){ //预览模式初始化
		if($(".zf_postcard img").length==0){
			$("#sel_zf").remove();
		}
		$("#zf_bt_true").remove();
		$(".zf_content .pic_li span").remove();
	},
	wxPreviewImage : function(url,myurls){
		wx.previewImage({
		    current: url, // 当前显示图片的http链接
		    urls: myurls // 需要预览的图片http链接列表
		});
	}
}
Letter = {
	letterId :'',
	sender :'',
	receiver : '',
	message : '',
	voiceId : '',
	orders : new Array(),
	music : '',
	stamp : '',
	cards : new Array(),
	init : function(letterId,sender,receiver,message,voiceId,orders,music,stamp,cards){
		this.letterId=letterId;
		this.sender=sender;
		this.receiver=receiver;
		this.message=message;
		this.voiceId=voiceId;
		this.orders=orders;
		this.music=music;
		this.stamp=stamp;
		this.cards = cards;
	},
	json : function(){
		return {
			letterId:this.letterId,sender:this.sender,
			receiver:this.receiver,message:this.message,
			voiceId:this.voiceId,orders:this.orders,
			music:this.music,stamp:this.stamp,cards:this.cards
		}
	},
	sendLetter : function(){
		$(".erweima ul li").html("<div class='send_loding'>请稍等...</div>");
		$.post(ctx+"/wechat/letter/put", Letter.json(),function(result){
   	    	  if(result.state==1){
   	    		Letter.sendComplete();
   	    		Letter.letterId=result.t.id;
   	    	 wxShare.isOnlyApp=true;
	   	 	 wxShare.onlyApp={
			   	 			title:userName+"寄来一封简信",
		   					imgUrl:domainName+'/static/letter/img/wechat_jianxin.jpg',
		   					link:domainName+'/wechat/letter/show?letterId='+result.t.id,
		   					content:'你好，我是邮递员小光，你的朋友'+userName+'托我给您寄来一封简信，祝您愉快！',
	   	 					success:letterShare.success,	
	   	 					cancel:letterShare.cancel,
	   	 	 }
   				letterShare.init({
   					title:userName+"寄来一封简信",
   					imgUrl:domainName+'/static/letter/img/wechat_jianxin.jpg',
   					link:domainName+'/wechat/letter/show?letterId='+result.t.id,
   					content:'你好，我是邮递员小光，你的朋友'+userName+'托我给您寄来一封简信，祝您愉快！'
   				});
   				letterShare.share();
   	    	  }else{
   	    		$(".erweima ul li").html("<div class='send_loding'>矮油！出现了点意外，请从写一封吧...</div>");
   	    	  }
   	 	}); 
	},
	sendComplete : function(){
		//替换sender
		var sp = $("#sender").parent();
		sp.append("<label>"+this.sender+"</label>");
		$("#sender").remove();
		//替换addressee
		var ap = $("#addressee").parent();
		ap.append("<label>"+this.receiver+"</label>");
		$("#addressee").remove();
		//替换msg
		var mp = $("#message").parent();
		mp.append("<div class='mes_content'>"+this.message+"</div>");
		$("#message").remove();
		//替换阳光卡
		SelectionPic.previewInit();
		//替换日出祝福卡
		SelectZf.previewInit();
		//替换背景音乐
		SelectMusic.playMusic();
		//替换预览录音试听
		if(recor.recordTime==0){
			$("#rec_bt").remove();
		}else{
			recor.previewRecording();
		}
		$("#huixin").hide();
		SendMsg.hideMsg();
		$(".tishi_send").show(400);
	}
}
//信件页面分享
var letterShare = {
	title:wxShare.title,
	imgUrl:wxShare.imgUrl,
	link:wxShare.link,
	content:wxShare.content,
	
	init:function(json){
		this.title = isNull(json.title)?this.title:json.title;
		this.link = isNull(json.link)?this.link:json.link;
		this.imgUrl = isNull(json.imgUrl)?this.imgUrl:json.imgUrl;
		this.content = isNull(json.content)?this.content:json.content;
	},
	share:function(){
		wxShare.init({
			title:this.title,
			imgUrl:this.imgUrl,
			link:this.link,
			content:this.content
		});
		wxShare.share(letterShare.success,letterShare.cancel);
	},
	success:function(){
		$(".tishi_send").remove();
		$(".erweima ul li").html("<div class='send_loding'>已发送。<br/><a href='"+ctx+"/wechat/me'>个人中心</a></div>");
		SendMsg.showMsg();
		if(isNull(Letter.letterId)){
			return;
		}
		$.post(ctx+"/wechat/letter/send", {letterId:Letter.letterId},function(result){
 	    	  if(result.state==1){
 	    		 console.log('发状态成功');
 	    	  }
 	 	}); 
	},
	cancel:function(){
		console.log('出现了点问题！');
	}
}

