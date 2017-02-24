<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/views/console/common/taglibs.jsp" %>
<%@include file="/WEB-INF/views/console/common/static.jsp" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
  <head>
  	<style type="text/css">
		.ztree li span.button.switch.level0 {visibility:hidden; width:1px;}
		.ztree li ul.level0 {padding:0; background:none;}
	</style>
  	 <script>
  	 	
  	 	function zTreeOnClick(event, treeId, treeNode){
  	 		//renderGrid(treeNode.id);
  	 	}
  	 	
  	 	
  	 	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			async: {
				enable: true,
				url:"${ctx}/console/admin/menu/jsonMenu"
			},
			callback: {
				onClick: zTreeOnClick
			},
			view: {
				selectedMulti: false
			}
		};
		
		$().ready(function(){
			$.ajaxSetup({ cache: false }); //解决ajax缓存结果问题 
			
			//设置div高度
			var thisheight = $(window.document).height();
			$(".myLeftMain").css("height",thisheight-50);
			//菜单树初始化
			$.fn.zTree.init($("#menuTree"), setting);
		});
		
		
		//jquery ui
		$(function() {
			$( "#repeat" ).buttonset();
			//新增菜单
			$( "#dialog-form" ).dialog({
				autoOpen: false,
				height: 350,
				width: 350,
				modal: true,
				buttons: {
					"提交": function() {
						$.ajax({
				 			url:"${ctx}/console/admin/menu/jsonSaveMenu",
				 			data:$('#menuForm').serialize(),
				 			success:function(data){
				 				 alert(data);
				 				 var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				 				 treeObj.reAsyncChildNodes(null,"refresh",false);
				 			}
				 		});
				 		$( this ).dialog( "close" );
					},
					"关闭": function() {
						$( this ).dialog( "close" );
					}
				}
			});
			
			//修改菜单
			$( "#dialog-form-edit" ).dialog({
				autoOpen: false,
				height: 350,
				width: 350,
				modal: true,
				buttons: {
					"提交": function() {
						$.ajax({
				 			url:"${ctx}/console/admin/menu/jsonSaveMenu",
				 			data:$('#menuEditForm').serialize(),
				 			success:function(data){
				 				 alert(data);
				 				 var treeObj = $.fn.zTree.getZTreeObj("menuTree");
				 				 treeObj.reAsyncChildNodes(null,"refresh",false);
				 			}
				 		});
				 		$( this ).dialog( "close" );
					},
					"关闭": function() {
						$( this ).dialog( "close" );
					}
				}
			});
		});
		
		
		//打开新增菜单dialog
		function showmenuDiv(){
			var treeObj = $.fn.zTree.getZTreeObj("menuTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择一个菜单");
			}else{
				$("#parentmenuName").html(node[0].name);
				$("#menuParentId").val(node[0].id);
				$("#menuName").val("");
				$("#menuUrl").val("");
				$("#menusortnum").val("");
				$("#menuisdisplay1").prop("checked",true);
				$("#menuisdisplay2").prop("checked",false);
				$("#dialog-form" ).dialog( "open" );
			}
		}
		
		
		//打开修改菜单dialog
		function showEditmenuDiv(){
			var treeObj = $.fn.zTree.getZTreeObj("menuTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择一个菜单");
			}else if(node[0].pId==null){
				alert("根节点不能修改");
			}else{
				
				$.ajax({
		 			url:"${ctx}/console/admin/menu/jsonGetMenu",
		 			data:"menuId="+node[0].id,
		 			success:function(data){
		 					$("#parentmenuName_edit").html(data.parentName);
							$("#menuParentId_edit").val(data.parentId);
							$("#menuName_edit").val(data.name);
							$("#menuId_edit").val(data.menuId);
							$("#menuUrl_edit").val(data.url);
							$("#menusortnum_edit").val(data.sortnum);
							if(data.isdisplay=='1'){
								$("#menuisdisplay1_edit").prop("checked",false);
								$("#menuisdisplay2_edit").prop("checked",true);
							}
							if(data.isdisplay=='0'){
								$("#menuisdisplay1_edit").prop("checked",true);
								$("#menuisdisplay2_edit").prop("checked",false);
							}
							
							$( "#dialog-form-edit" ).dialog( "open" );
		 			}
	 			});
				
			}
		}
		
		//删除菜单
		function delmenu(){
		
			var treeObj = $.fn.zTree.getZTreeObj("menuTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择要删除的菜单");
			}else if(node[0].pId==null){
				alert("根节点不能删除");
			}else if(node[0].isParent==true){
				alert("请先删除子菜单，再删除此菜单");
			}else{
				if(confirm("您确定删除 （"+node[0].name+"） 吗？")){
					$.ajax({
			 			url:"${ctx}/console/admin/menu/jsonDelMenu",
			 			data:"menuId="+node[0].id,
			 			success:function(data){
			 					if(data==true){
			 						alert("删除成功");
			 						treeObj.reAsyncChildNodes(null,"refresh",false);
			 					}else{
			 						alert("删除失败，请从新操作");
			 					}
			 			}
		 			});
	 			}
	 		}
 			
		}
		
  	 </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav"> 菜单管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="myLeftMain" style="width:100%">
			<div id="toolbar" class="ui-widget-header ui-corner-all">
				<span id="repeat">
					<input type="radio" id="repeat0" name="repeat" checked="checked" /><label for="repeat0" onclick="showmenuDiv()">新增子菜单</label>
					<input type="radio" id="repeat1" name="repeat" /><label for="repeat1" onclick="showEditmenuDiv()">修改菜单</label>
					<input type="radio" id="repeat2" name="repeat" /><label for="repeat2" onclick="delmenu()">删除菜单</label>
				</span>
			</div>
			<div id="menuTree" class="ztree"></div>
		</div>
	</div>
 </body>
 
 <!-- 新增 菜单 -->
 <div id="dialog-form" title="新增菜单" class="fromtable">
	<form id="menuForm">
		<table class="contertable" width="100%">
			<tr>
				<td class="label">
					父菜单
				</td>
				<td>
					<label id="parentmenuName"></label><input type="hidden" name="parentId" id="menuParentId"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					菜单名称
				</td>
				<td>
					<input type="text" class="text" name="name" id="menuName"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					菜单url
				</td>
				<td>
					<input type="text" class="text" name="url" id="menuUrl"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					是否显示
				</td>
				<td>
					是:<input type="radio" id="menuisdisplay1"  checked class="text" name="isdisplay" value="0"/> 否:<input id="menuisdisplay2"  type="radio" class="text" name="isdisplay" value="1"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					顺序
				</td>
				<td>
					<input type="text"   class="text" name="sortnum"  id="menusortnum"/>
				</td>
			</tr>
			
		</table>
	</form>
</div>
 <!-- 新增 菜单 -->


<!-- 修改 菜单 -->
 <div id="dialog-form-edit" title="修改菜单" class="fromtable">
	<form id="menuEditForm">
		<table class="contertable" width="100%">
			<tr>
				<td class="label">
					父菜单
				</td>
				<td>
					<label id="parentmenuName_edit"></label><input type="hidden" name="parentId" id="menuParentId_edit"/>
					<input type="hidden" name="menuId" id="menuId_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					菜单名称
				</td>
				<td>
					<input type="text" class="text" name="name" id="menuName_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					菜单url
				</td>
				<td>
					<input type="text" class="text" name="url" id="menuUrl_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					是否显示
				</td>
				<td>
					是:<input type="radio" id="menuisdisplay1_edit"  checked class="text" name="isdisplay" value="0"/> 否:<input id="menuisdisplay2_edit"  type="radio" class="text" name="isdisplay" value="1"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					顺序
				</td>
				<td>
					<input type="text"   class="text" name="sortnum"  id="menusortnum_edit"/>
				</td>
			</tr>
			
		</table>
	</form>
</div>
 <!-- 修改 菜单 -->
</html>
