$(function(){
	SelectionPic.init();
	ReplyMsg.init();
	SelectZf.init();
});


//阳光卡选择控件
SelectionPic = {
	
	init:function(){
		$("#pic_bt").click(function(){
			SelectionPic.showPic();
		});
		$("#pic_bt_close").click(function(){
			SelectionPic.hidePic();
		});
		$(".yg_card img").click(function(){
			SelectionPic.showPic(1);
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
	}
}

SelectReco = {
	init : function(url,time){
		//$(SelectReco.audio()).attr("src",url);
		this.recordTime = time;
		$("#rec_bt").click(function(){
			SelectReco.showMusic();
		});
		$(".recording_close img").click(function(){
			SelectReco.hideMusic();
		});
		$("#begin_img").click(function(){
			if($("#begin_img").hasClass("begin_img")){
				SelectReco.playMusic();
			}else if($("#begin_img").hasClass("ing_img")){
				SelectReco.stopMusic();
			}
		});
	},
	recordTime:0,
	audio:function(){
		return document.getElementById('ricodAudio');
	},
	setTimer : null,
	showMusic:function(){
		$(".select_recording").show();
		setTimeout(function(){
		 	$(".recording_content").css("margin-top","38%");
		},10);
	},
	hideMusic:function(){
		$(".recording_content").css("margin-top","200%");
		setTimeout(function(){
		 	$(".select_recording").hide();
		},200);
		SelectReco.stopMusic();
	},
	playMusic : function(){
		$("#begin_img").attr("class","ing_img");
		$("#begin_set").html("听...");
		$("#yingui").attr("class","ing_yingui");
		var countdown = this.recordTime;
		//auditionTimer($("#rec_time"));
		BackMusic.stopMusic();
		/*function auditionTimer(val){
			if (0 == countdown-1) { 
				SelectReco.stopMusic();
			} else { 
				countdown--;
				if(countdown<10)
					val.html("0"+countdown+"\"");
				else
					val.html(""+countdown+"\""); 
				if(countdown>0){
					SelectReco.setTimer = setTimeout(function() { 
						auditionTimer(val); 
					},1000);
				}
			} 
	    }*/
		SelectReco.audio().play();
		SelectReco.audio().addEventListener('ended',function(){
			SelectReco.stopMusic();
		},false);

	},
	stopMusic : function(){
		var time = this.recordTime;
		BackMusic.playMusic();
		$("#begin_img").attr("class","begin_img");
		$("#begin_set").html("听");
		$("#yingui").attr("class","yingui");
		if(time<10)
			$("#rec_time").html("0"+time+"\"");
		else
			$("#rec_time").html(""+time+"\""); 
		clearTimeout(SelectReco.setTimer);
		SelectReco.audio().pause();
		SelectReco.audio().currentTime=0;
	}
}

BackMusic = {
	isplay:true,
	inti : function(url){
		//$(BackMusic.audio()).attr("src",url);
		BackMusic.playMusic();
		$("#playImg").click(function(){
			if($("#playImg").hasClass("rotate")){
				BackMusic.stopMusic();
				BackMusic.isplay=false;
			}else{
				BackMusic.playMusic();
				BackMusic.isplay=true;
			}
		});
	},
	audio:function(){
		return document.getElementById('audio');
	},
	playMusic : function(){
		if(BackMusic.isplay){
			BackMusic.audio().play();
			$("#playImg").addClass("rotate");
		}
	},
	stopMusic : function(){
		if(BackMusic.isplay){
			BackMusic.audio().pause();
			$("#playImg").removeClass("rotate");
		}
	}
}

ReplyMsg = {
	init : function(){
		$("#huixin").click(function(){
			ReplyMsg.showMsg();
		});
		$("#colose_er").click(function(){
			ReplyMsg.hideMsg();
		});
	},
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
		$(".zf_postcard img").click(function(){
			SelectZf.showZf(1);
		});
	},
	picIsOpen : false,
	showZf : function(el){
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
		//$.post();
		var urls = new Array();
		$(".zf_content .pic_li img").each(function(){
			urls.push($(this).attr('dt'));
		});
		$(".zf_content .pic_li img").click(function(){
			SelectZf.wxPreviewImage($(this).attr('dt'), urls);
		});
	},
	wxPreviewImage : function(url,myurls){
		wx.previewImage({
		    current: url, // 当前显示图片的http链接
		    urls: myurls // 需要预览的图片http链接列表
		});
	}
}
//信件页面分享
var letterShare = {
	title:wxShare.title,
	imgUrl:wxShare.imgUrl,
	link:wxShare.link,
	content:wxShare.content,
	letterId:'',
	
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
		
		
		$.post(ctx+"/wechat/letter/send", {letterId:letterShare.letterId},function(result){
 	    	  if(result.state==1){
 	    		 $(".tishi_send").remove();
 	    	  }
 	 	}); 
	},
	cancel:function(){
		console.log('出现了点问题！');
	}
}
