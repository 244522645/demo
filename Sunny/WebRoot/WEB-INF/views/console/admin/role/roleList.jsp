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
  	 	var roleUserTree = null;
  	 	//角色人员树
  	 	$().ready(function(){
  	 	 	$.ajaxSetup({ cache: false }); //解决ajax缓存结果问题 
  	 		
  	 		
			if(roleUserTree==null){
				roleUserTree = new myBpmTree({
					url:'${ctx}/console/admin/dept/tree/all',
					title:'角色用户树',
					callback:function(data){
						if(data.legnth=0)
							return ;
						//添加用户
						var userIds = "";
						for( i=0;i<data.length;i++){
							userIds += data[i].id+",";
						}
						var treeObj = $.fn.zTree.getZTreeObj("roleTree");
						var node = treeObj.getSelectedNodes();
						$.ajax({
				 			url:"${ctx}/console/admin/role/jsonAddUser",
				 			type:'post',
				 			data:"roleId="+node[0].id+"&userIds="+userIds,
				 			success:function(mydata){
				 				if(mydata==true){
				 					renderGrid(node.id);
				 					//roleUserTree.replaceTree();
				 				}else{
				 					alert("添加失败");
				 				}
				 			}
				 		});
					}
				});
			}
			//触发添加用户按钮
			$("#addUserBut").click(function(){
				var treeObj = $.fn.zTree.getZTreeObj("roleTree");
				var node = treeObj.getSelectedNodes();
				if(node.length==0){
					alert("请选择一个角色");
					return;
				}
				roleUserTree.showTreeDialog({ids:""});
			});
  	 	});
  	 	
  	 	
  	 	var roleMenuTree = null;
  	 	//角色菜单树
  	 	$().ready(function(){
  	 		
			if(roleMenuTree==null){
				roleMenuTree = new myBpmTree({
					url:'${ctx}/console/admin/menu/jsonMenu',
					title:'菜单树',
					callback:function(data){
						if(data.legnth=0)
							return ;
						//添加菜单
						var menuIds = "";
						for( i=0;i<data.length;i++){
							menuIds += data[i].id+",";
						}
						var treeObj = $.fn.zTree.getZTreeObj("roleTree");
						var node = treeObj.getSelectedNodes();
						$.ajax({
				 			url:"${ctx}/console/admin/role/jsonAddMenu",
				 			type:'post',
				 			data:"roleId="+node[0].id+"&menuIds="+menuIds,
				 			success:function(mydata){
				 				if(mydata==true){
				 					renderMenuGrid(node.id);
				 					roleMenuTree.replaceTree();
				 				}else{
				 					alert("添加失败");
				 				}
				 			}
				 		});
					}
				});
			}
			//触发添加用户按钮
			$("#addMenuBut").click(function(){
				var treeObj = $.fn.zTree.getZTreeObj("roleTree");
				var node = treeObj.getSelectedNodes();
				if(node.length==0){
					alert("请选择一个角色");
					return;
				}
				roleMenuTree.showTreeDialog({ids:""});
			});
  	 	});
  	 	
  	 	
  	 	var roleId = "-1";
  	 	
  	 	function zTreeOnClick(event, treeId, treeNode){
  	 		roleId = treeNode.id;
  	 		renderGrid();
  	 		renderMenuGrid();
  	 	}
  	 	
  	 	
  	 	//获取用户列表并渲染
  	 	function renderGrid(p,ps){
  	 		if(!p)
  	 			p = userPagination.p;
  	 		if(!ps)
  	 			ps = userPagination.ps;
  	 		$("#data").empty();
  	 		$("#pagination").empty();
  	 		$.ajax({
	 			url:"${ctx}/console/admin/role/jsonUser",
	 			data:"roleId="+roleId+"&p="+p+"&ps="+ps,
	 			success:function(data){
	 				  if(data.result!=undefined&&data.result.length>0){
		 				  $.each(data.result, function(i,item){
		 				  	 var name = item.name==null?"":item.name;
		 				  	 var mobile = item.mobile==null?"":item.mobile;
		 				  	 var mail = item.mail==null?"":item.mail;
		 				  	 var html =  $("<tr><td>"+(i+1)+"</td><td>"+name+"</td><td>"+mobile+"</td><td>"+mail+"</td><td><i class='tdico delico' style='cursor: pointer;' onclick=\"delUser('"+item.userId+"')\"></i></td></tr>");
			            		 $("#data").append(html);
			            		 userPagination.setPagination(data);
		 				  });
	 				  }else{
	 				  	      var html =  $("<tr><td colspan='5'>无数据！</td></tr>");
	 				  		 $("#data").append(html);
	 				  }
	 			}
	 		});
  	 	}
  	 	
  	 	//获取菜单列表并渲染
  	 	function renderMenuGrid(p,ps){
  	 		if(!p)
  	 			p = menuPagination.p;
  	 		if(!ps)
  	 			ps = menuPagination.ps;
  	 		$("#dataMenu").empty();
  	 		$("#paginationMenu").empty();
  	 		$.ajax({
	 			url:"${ctx}/console/admin/role/jsonMenu",
	 			data:"roleId="+roleId+"&p="+p+"&ps="+ps,
	 			success:function(data){
	 				  if(data.result!=undefined&&data.result.length>0){
		 				  $.each(data.result, function(i,item){
		 				  	 var name = item.name==null?"":item.name;
		 				  	 var url = item.url==null?"":item.url;
		 				  	 var isdisplay = item.isdisplay==null?"":item.isdisplay;
		 				  	 var sortnum = item.sortnum==null?"":item.sortnum;
		 				  	 var html =  $("<tr><td>"+(i+1)+"</td><td>"+name+"</td><td>"+url+"</td><td>"+isdisplay+"</td><td>"+sortnum+"</td><td><i class='tdico delico' style='cursor: pointer;' onclick=\"delMenu('"+item.menuId+"')\"></i></td></tr>");
			            		 $("#dataMenu").append(html);
			            		 menuPagination.setPagination(data);
		 				  });
	 				  }else{
	 				  	      var html =  $("<tr><td colspan='6'>无数据！</td></tr>");
	 				  		 $("#dataMenu").append(html);
	 				  }
	 			}
	 		});
  	 	}
  	 	
  	 	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			async: {
				enable: true,
				url:"${ctx}/console/admin/role/jsonRole"
			},
			callback: {
				onClick: zTreeOnClick,
				onAsyncSuccess:function(event, treeId, treeNode, msg){
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					var node = treeObj.getNodes();
					if(node==null||node.length==0){
						alert('请初始化角色数据');
					}
					roleId = node[0].children[0].id;
					treeObj.selectNode(node[0].children[0]);
					renderGrid();
					renderMenuGrid();
				}
			},
			view: {
				selectedMulti: false
			}
		};
		
  	 	var userPagination = null;
  	 	var menuPagination = null;
		$().ready(function(){
			userPagination = new Pagination({
  	 				callback:'renderGrid',
  	 				pagination:'pagination'
  	 		});
			
			menuPagination = new Pagination({
  	 				callback:'renderMenuGrid',
  	 				pagination:'paginationMenu'
  	 		});
			
			
			//设置div高度
			var thisheight = $(window.document).height();
			$(".myLeftMain").css("height",thisheight-45);
			$(".myRightMain").css("height",thisheight-50);
			$("#mytabs").css("height",thisheight-50);
			
			//角色树初始化
			$.fn.zTree.init($("#roleTree"), setting);
		});
		
		
		//jquery ui
		$(function() {
			$( "#repeat" ).buttonset();
			
			//新增角色
			$( "#dialog-form" ).dialog({
				autoOpen: false,
				height: 300,
				title:'新增角色',
				width: 350,
				modal: true,
				buttons: {
					"提交": function() {
						$.ajax({
				 			url:"${ctx}/console/admin/role/jsonSaveRole",
				 			data:$('#roleForm').serialize(),
				 			success:function(data){
				 				 alert(data);
				 				 var treeObj = $.fn.zTree.getZTreeObj("roleTree");
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
			
			//修改角色
			$( "#dialog-form-edit" ).dialog({
				autoOpen: false,
				height: 300,
				width: 350,
				modal: true,
				buttons: {
					"提交": function() {
						$.ajax({
				 			url:"${ctx}/console/admin/role/jsonSaveRole",
				 			data:$('#roleEditForm').serialize(),
				 			success:function(data){
				 				 alert(data);
				 				 var treeObj = $.fn.zTree.getZTreeObj("roleTree");
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
		
		
		//打开新增角色dialog
		function showroleDiv(){
			var treeObj = $.fn.zTree.getZTreeObj("roleTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择一个角色");
			}else{
				$("#parentroleName").html(node[0].name);
				$("#roleParentId").val(node[0].id);
				$("#roleRemarks").val("");
				$("#roleName").val("");
				$( "#dialog-form" ).dialog( "open" );
			}
		}
		
		
		//打开修改角色dialog
		function showEditroleDiv(){
			var treeObj = $.fn.zTree.getZTreeObj("roleTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择一个角色");
			}else if(node[0].pId==null){
				alert("根节点不能修改");
			}else{
				
				$.ajax({
		 			url:"${ctx}/console/admin/role/jsonGetRole",
		 			data:"roleId="+node[0].id,
		 			success:function(data){
		 					$("#parentroleName_edit").html(data.parentName);
							$("#roleParentId_edit").val(data.parentId);
							$("#roleRemarks_edit").val(data.remarks);
							$("#roleName_edit").val(data.name);
							$("#roleId_edit").val(data.roleId);
							$("#roleCode_edit").val(data.code);
		 			}
	 			});
				$( "#dialog-form-edit" ).dialog( "open" );
			}
		}
		
		//删除角色
		function delrole(){
		
			var treeObj = $.fn.zTree.getZTreeObj("roleTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择要删除的角色");
			}else if(node[0].pId==null){
				alert("根节点不能删除");
			}else if(node[0].isParent==true){
				alert("请先删除子角色，再删除此角色");
			}else if(node[0].id=='1'){
				alert("admin角色不能删除");
			}else{
				if(confirm("您确定删除 （"+node[0].name+"） 吗？")){
					$.ajax({
			 			url:"${ctx}/console/admin/role/jsonDelRole",
			 			data:"roleId="+node[0].id,
			 			success:function(data){
			 					if(data=='true'){
			 						alert("删除成功");
			 						treeObj.reAsyncChildNodes(null,"refresh",false);
			 					}else if(data=='false'){
			 						alert("删除失败，请从新操作");
			 					}else{
			 						alert(data);
			 					}
			 			}
		 			});
	 			}
	 		}
 			
		}
		
		//删除用户
		function delUser(userId){
			var treeObj = $.fn.zTree.getZTreeObj("roleTree");
			var node = treeObj.getSelectedNodes();
			
			$.ajax({
	 			url:"${ctx}/console/admin/role/jsonDelUser",
	 			data:"userId="+userId+"&roleId="+node[0].id,
	 			success:function(data){
	 					if(data==true){
	 						alert("删除成功");
	 						renderGrid(node[0].id);
	 						roleUserTree.replaceTree();
	 					}else{
	 						alert("删除失败，请从新操作");
	 					}
	 			}
 			});
		}
		
		//删除菜单
		function delMenu(menuId){
			var treeObj = $.fn.zTree.getZTreeObj("roleTree");
			var node = treeObj.getSelectedNodes();
			
			$.ajax({
	 			url:"${ctx}/console/admin/role/jsonDelMenu",
	 			data:"menuId="+menuId+"&roleId="+node[0].id,
	 			success:function(data){
	 					if(data==true){
	 						alert("删除成功");
	 						renderMenuGrid(node[0].id);
	 						roleMenuTree.replaceTree();
	 					}else{
	 						alert("删除失败，请从新操作");
	 					}
	 			}
 			});
		}
		
		
		$(function() {
			$( "#mytabs" ).tabs();
		});
  	 </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav"> 角色管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="myLeftMain">
			<div id="toolbar" class="ui-widget-header ui-corner-all">
				<span id="repeat">
					<input type="radio" id="repeat0" name="repeat" checked="checked" /><label for="repeat0" onclick="showroleDiv()">新增子角色</label>
					<input type="radio" id="repeat1" name="repeat" /><label for="repeat1" onclick="showEditroleDiv()">修改角色</label>
					<input type="radio" id="repeat2" name="repeat" /><label for="repeat2" onclick="delrole()">删除角色</label>
				</span>
			</div>
			<div id="roleTree" class="ztree"></div>
		</div>
		<div class="myRightMain">
			<div id="mytabs">
				<ul>
					<li><a href="#tabs-1">用户列表</a></li>
					<li><a href="#tabs-2">菜单列表</a></li>
					<li><a href="#tabs-3">部门列表</a></li>
				</ul>
				<div id="tabs-1">	
						<table class="ajaxTable">
							<thead>
							<tr>
								<th>序号</th><th>用户名</th><th>手机号</th><th>邮箱</th><th>操作</th>
							</tr>
							</thead>
							<tbody id="data" >
							</tbody>
						</table>
						<div class="addDiv" style="width:150px;float:left;padding:3 0 0 3px;">
						 	<button class="btn btn-primary" id="addUserBut">添加用户</button>
						</div>
						<div class="page">
						  <ul id="pagination"></ul>
						</div>
				</div>
				<div id="tabs-2">
						<table class="ajaxTable">
							<thead>
							<tr>
								<th>序号</th><th>菜单名称</th><th>url</th><th>是否显示</th><th>排序</th><th>操作</th>
							</tr>
							</thead>
							<tbody id="dataMenu" >
							</tbody>
						</table>
						<div class="addDiv" style="width:150px;float:left;padding:3 0 0 3px;">
						 	<button class="btn btn-primary" id="addMenuBut">添加菜单</button>
						</div>
						<div class="page">
						  <ul id="paginationMenu"></ul>
						</div>
				</div>
				<div id="tabs-3">
					<p></p>
				</div>
			</div>
		</div>
	</div>
 </body>
 
 <!-- 新增 角色 -->
 <div id="dialog-form" title="新增角色" class="fromtable">
	<form id="roleForm">
		<table class="contertable" width="100%">
			<tr>
				<td class="label">
					父角色
				</td>
				<td>
					<label id="parentroleName"></label><input type="hidden" name="parentId" id="roleParentId"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					角色名称
				</td>
				<td>
					<input type="text" class="text" name="name" id="roleName"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					角色编码
				</td>
				<td>
					<input type="text" class="text" name="code" id="roleCode"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					角色描述
				</td>
				<td>
					<textarea name="remarks" id="roleRemarks"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
 <!-- 新增 角色 -->


<!-- 修改 角色 -->
 <div id="dialog-form-edit" title="修改角色" class="fromtable">
	<form id="roleEditForm">
		<table class="contertable" width="100%">
			<tr>
				<td class="label">
					父角色
				</td>
				<td>
					<label id="parentroleName_edit"></label><input type="hidden" name="parentId" id="roleParentId_edit"/>
					<input type="hidden" name="roleId" id="roleId_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					角色名称
				</td>
				<td>
					<input type="text" class="text" name="name" id="roleName_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					角色编码
				</td>
				<td>
					<input type="text" class="text" name="code" id="roleCode_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					角色描述
				</td>
				<td>
					<textarea name="remarks" id="roleRemarks_edit"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
 <!-- 修改 角色 -->
</html>
