
$(function(){
	$("#side_switch").click(function(){
		$(".left").hide();
		$("#main").contents().find(".right_body").css('margin-left',0);
		$(this).hide();
		$("#side_switchl").show();
		$(window.parent.document).find("#framesetMainId").attr("cols","10,*");
	});
	
})

$(function(){
	$("#side_switchl").click(function(){
		$(".left").show();
		$("#main").contents().find(".right_body").css('margin-left',200);
		$(this).hide();
		$("#side_switch").show();
		$(window.parent.document).find("#framesetMainId").attr("cols","210,*");
	})
})


//设置menu的选中 样式

function setMenuSelected(el){
	$("#menuUl").children().each(function(e){
		if($(el).parent().attr("id")==$(this).attr("id")){
			$(el).attr("class","selected");
			//设置freameset的宽度,首页时设置 framesetMainId 的cols(0,*)
			if($(el).parent().attr("id")=="index"){
				$(window.parent.document).find("#framesetMainId").attr("cols","0,*");
			}else{
				$(window.parent.document).find("#framesetMainId").attr("cols","210,*");
			}
		}else{
			$(this).children().attr("class","");
			if($(el).parent().attr("id")=="index"){
				$(window.parent.document).find("#framesetMainId").attr("cols","0,*");
			}else{
				$(window.parent.document).find("#framesetMainId").attr("cols","210,*");
			}
		}
	});
}

//Checkbox 选择
function selectedCheckbox(arg,el){
    if($(arg).prop('checked')==true){
		$("input[name='"+el+"']").prop("checked",true);
	}else{
		$("input[name='"+el+"']").prop("checked",false);
	}  
}

//返回
function goToUrl(url){
   window.location.href=url;
}

//msg提示框
function showMsg(is,msg){
	if(is){
		var div = "<div class='formMsgTrue' style='display:none;' id='formMsgShowDiv'>"+msg+"</div>";
	}else{
		var div = "<div class='formMsgFalse' style='display:none;' id='formMsgShowDiv'>"+msg+"</div>";
	}
	$('body').prepend($(div));
	$("#formMsgShowDiv").show("normal");
	setTimeout(function () { 
		$("#formMsgShowDiv").hide("normal");
	 }, 2000);
	$("#formMsgShowDiv").click(function(){
		 $(this).hide("normal");
	});
	
}

//清空 form
function clearForm(el){
	$(':input','#'+el).not(':button, :submit, :reset, :hidden').val('').removeAttr('checked').removeAttr('selected');   
}

//批量删除
function delAll(el){
    if(confirm("确定要删除吗？")){
 	     $("#"+el).submit();
 	     return true; 
 	 }else{
 	     return false; 
 	 } 
 	return false;
 }

