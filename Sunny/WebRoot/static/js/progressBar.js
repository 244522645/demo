
	var startTime;
		$(document).ready(function () {
			$("#subButton").click(function () {
				var myDate = new Date();
				startTime = myDate.getTime();
				$(this).attr("disabled", true);
				window.setTimeout("getProgressBar()", 10);
				$("#progress").show();
		    	$("#myform").submit();
			});
			$("#close").click(function(){$("#progress").hide();});
		});
		function getProgressBar() {
		 	var timestamp = (new Date()).valueOf();
			var bytesReadToShow = 0;
			var contentLengthToShow = 0;
			var bytesReadGtMB = 0;
			var contentLengthGtMB = 0;
			$.getJSON(ctx+"/fileman/myfile/getProgress", {"t":timestamp}, function (json) {
				//$("div#info").html(json.pBytesRead+'/'+json.pContentLength);
				var bytesRead = (json.pBytesRead / 1024).toString();
				 if (bytesRead > 1024) {
					bytesReadToShow = (bytesRead / 1024).toString();
					bytesReadGtMB = 1;
				}else{
					bytesReadToShow = bytesRead.toString();
				}
				var contentLength = (json.pContentLength / 1024).toString();
				if (contentLength > 1024) {
					contentLengthToShow = (contentLength / 1024).toString();
					contentLengthGtMB = 1;
				}else{
					contentLengthToShow= contentLength.toString();
				}
				bytesReadToShow = bytesReadToShow.substring(0, bytesReadToShow.lastIndexOf(".") + 3);
				contentLengthToShow = contentLengthToShow.substring(0, contentLengthToShow.lastIndexOf(".") + 3);
				if (bytesRead == contentLength) {
					$("#close").show();
					$("#uploaded").css("width", "100%");
					if (contentLengthGtMB == 0) {
						$("div#info").html("上传！总共大小" + contentLengthToShow + "KB.完成100%");
					} else {
						$("div#info").html("上传完成！总共大小" + contentLengthToShow + "MB.完成100%");
					}
					window.clearTimeout(interval);
					$("#subButton").attr("disabled", false);
				} else {
					var pastTimeBySec = (new Date().getTime() - startTime) / 1000;
					var sp = (bytesRead / pastTimeBySec).toString();c
					var speed = sp.substring(0, sp.lastIndexOf(".") + 3);
					var percent = Math.floor((bytesRead / contentLength) * 100) + "%";
					$("#uploaded").css("width", percent);
					if (bytesReadGtMB == 0 && contentLengthGtMB == 0) {
						$("div#info").html("上传速度:" + speed + "KB/Sec,已经读取" + bytesReadToShow + "KB,总共大小" + contentLengthToShow + "KB.完成" + percent);
					} else {
						if (bytesReadGtMB == 0 && contentLengthGtMB == 1) {
							$("div#info").html("上传速度:" + speed + "KB/Sec,已经读取" + bytesReadToShow + "KB,总共大小" + contentLengthToShow + "MB.完成" + percent);
						} else {
							if (bytesReadGtMB == 1 && contentLengthGtMB == 1) {
								$("div#info").html("上传速度:" + speed + "KB/Sec,已经读取" + bytesReadToShow + "MB,总共大小" + contentLengthToShow + "MB.完成" + percent);
							}
						}
					}
				}  
			});
			var interval = window.setTimeout("getProgressBar()", 100); 
		}
