<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<script>
	function relationsHtml(list){
		//alert("---"+list);
		var html = "";
		for(var i=0;i<list.length;i++){
				html += '<div class="information">'+
							'<a class="liebiao" href="'+ctx+'/wechat/v2/relation/addRelation?id='+list[i].id+'">'+
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
								'</a>'+
						'</div>';
			}
			return html;
	}
	
	function loadData(){
		$.post(ctx+"/wechat/v2/relation/relationListDetail", 
				function(response){
					var list = eval(response);
					console.log(list);
					var html = relationsHtml(list);
					$("#content").html('');
					$("#content").append(html);
				}
		);
	}
	
	
	$(function(){
		loadData();
	}); 
</script>