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
  	 	var deptUserTree = null;
  	 	//部门人员树
  	 	$().ready(function(){
  	 		 $.ajaxSetup({ cache: false }); //解决ajax缓存结果问题 
  	 		
			if(deptUserTree==null){
				deptUserTree = new myBpmTree({
					url:'${ctx}/console/admin/dept/tree/all',
					title:'选择人员',
					callback:function(data){
						if(data.legnth=0)
							return ;
						//添加用户
						var userIds = "";
						for( i=0;i<data.length;i++){
							userIds += data[i].id+",";
						}
						var treeObj = $.fn.zTree.getZTreeObj("deptTree");
						var node = treeObj.getSelectedNodes();
						$.ajax({
				 			url:"${ctx}/console/admin/dept/jsonAddUser",
				 			type:'post',
				 			data:"deptId="+node[0].id+"&userIds="+userIds,
				 			success:function(mydata){
				 				if(mydata==true){
				 					renderGrid(node.id);
				 					deptUserTree.replaceTree();
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
				var treeObj = $.fn.zTree.getZTreeObj("deptTree");
				var node = treeObj.getSelectedNodes();
				if(node.length==0){
					alert("请选择一个部门");
					return;
				}
				deptUserTree.showTreeDialog({ids:""});
			});
  	 	});
  	 	
  	 	var deptId = "-1";
  	 	
  	 	function zTreeOnClick(event, treeId, treeNode){
  	 		deptId = treeNode.id;
  	 		renderGrid();
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
	 			url:"${ctx}/console/admin/dept/jsonUser",
	 			data:"deptId="+deptId+"&p="+p+"&ps="+ps,
	 			success:function(data){
	 				  
	 				  if(data.result!=undefined&&data.result.length>0){
	 					// var obj=data.result;
	 					data.result.sort(function (a, b) {
	 						  return a.createtime.localeCompare(b.createtime);
	 						}); 
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
  	 	
  	 	var setting = {
			data: {
				simpleData: {
					enable: true
				}
			},
			async: {
				enable: true,
				url:"${ctx}/console/admin/dept/jsonDept"
			},
			callback: {
				onClick: zTreeOnClick,
				onAsyncSuccess:function(event, treeId, treeNode, msg){
					var treeObj = $.fn.zTree.getZTreeObj(treeId);
					var node = treeObj.getNodes();
					deptId = node[0].children[0].id;
					treeObj.selectNode( node[0].children[0]);
					renderGrid();
				}
				
			},
			view: {
				selectedMulti: false
			}
		};
  	 	
		var userPagination = null;
		$().ready(function(){
			//初始化用户列表
			userPagination = new Pagination({
  	 				callback:'renderGrid',
  	 				pagination:'pagination'
  	 		});
		
			
			
			//设置div高度
			var thisheight = $(window.document).height();
			$(".myLeftMain").css("height",thisheight-50);
			$(".myRightMain").css("height",thisheight-50);
			//部门树初始化
			$.fn.zTree.init($("#deptTree"), setting);
			
		});
		
		
		//jquery ui
		$(function() {
			$( "#repeat" ).buttonset();
			
			//新增部门
			$( "#dialog-form" ).dialog({
				autoOpen: false,
				height: 300,
				width: 350,
				modal: true,
				buttons: {
					"提交": function() {
						$.ajax({
				 			url:"${ctx}/console/admin/dept/jsonSaveDept",
				 			data:$('#deptForm').serialize(),
				 			success:function(data){
				 				 alert(data);
				 				 var treeObj = $.fn.zTree.getZTreeObj("deptTree");
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
			
			//修改部门
			$( "#dialog-form-edit" ).dialog({
				autoOpen: false,
				height: 300,
				width: 350,
				modal: true,
				buttons: {
					"提交": function() {
						$.ajax({
				 			url:"${ctx}/console/admin/dept/jsonSaveDept",
				 			data:$('#deptEditForm').serialize(),
				 			success:function(data){
				 				 alert(data);
				 				 var treeObj = $.fn.zTree.getZTreeObj("deptTree");
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
		
		
		//打开新增部门dialog
		function showDeptDiv(){
			var treeObj = $.fn.zTree.getZTreeObj("deptTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择一个部门");
			}else{
				$("#parentDeptName").html(node[0].name);
				$("#deptParentId").val(node[0].id);
				$("#deptRemarks").val("");
				$("#deptName").val("");
				$( "#dialog-form" ).dialog( "open" );
			}
		}
		
		
		//打开修改部门dialog
		function showEditDeptDiv(){
			var treeObj = $.fn.zTree.getZTreeObj("deptTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择一个部门");
			}else if(node[0].pId==null){
				alert("根节点不能修改");
			}else{
				
				$.ajax({
		 			url:"${ctx}/console/admin/dept/jsonGetDept",
		 			data:"deptId="+node[0].id,
		 			success:function(data){
		 					$("#parentDeptName_edit").html(data.parentName);
							$("#deptParentId_edit").val(data.parentId);
							$("#deptRemarks_edit").val(data.remarks);
							$("#deptName_edit").val(data.name);
							$("#deptId_edit").val(data.deptId);
		 			}
	 			});
				$( "#dialog-form-edit" ).dialog( "open" );
			}
		}
		
		//删除部门
		function delDept(){
		
			var treeObj = $.fn.zTree.getZTreeObj("deptTree");
			var node = treeObj.getSelectedNodes();
			if(node.length==0){
				alert("请选择要删除的部门");
			}else if(node[0].pId==null){
				alert("根节点不能删除");
			}else if(node[0].isParent==true){
				alert("请先删除子部门，再删除此部门");
			}else{
				if(confirm("您确定删除 （"+node[0].name+"） 吗？")){
					$.ajax({
			 			url:"${ctx}/console/admin/dept/jsonDelDept",
			 			data:"deptId="+node[0].id,
			 			success:function(data){
			 					if(data=='true'){
			 						alert("删除成功");
			 						treeObj.reAsyncChildNodes(null,"refresh",false);
			 					}else if(data=='false'){
			 						alert("删除失败，请从新操作");
			 					}else {
			 						alert(data);
			 					}
			 			}
		 			});
	 			}
	 		}
 			
		}
		
		//删除用户
		function delUser(userId){
			$.ajax({
	 			url:"${ctx}/console/admin/dept/jsonDelUser",
	 			data:"userIds="+userId,
	 			success:function(data){
	 					if(data==true){
	 						alert("删除成功");
	 						var treeObj  = $.fn.zTree.getZTreeObj("deptTree");
							var node = treeObj.getSelectedNodes();
	 						renderGrid();
	 						deptUserTree.replaceTree();
	 					}else{
	 						alert("删除失败，请从新操作");
	 					}
	 			}
 			});
		}
  	 </script>
  </head>
  
 <body>
	<div class="bodyMain">
		<div class="top_subnav"> 部门管理</div>
		<p class="mylinep" style="margin-top:0;"></p>
		<div class="myLeftMain">
			<div id="toolbar" class="ui-widget-header ui-corner-all">
				<span id="repeat">
					<input type="radio" id="repeat0" name="repeat" checked="checked" /><label for="repeat0" onclick="showDeptDiv()">新增子部门</label>
					<input type="radio" id="repeat1" name="repeat" /><label for="repeat1" onclick="showEditDeptDiv()">修改部门</label>
					<input type="radio" id="repeat2" name="repeat" /><label for="repeat2" onclick="delDept()">删除部门</label>
				</span>
			</div>
			<div id="deptTree" class="ztree"></div>
		</div>
		<div class="myRightMain">
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
	</div>
 </body>
 
 <!-- 新增 部门 -->
 <div id="dialog-form" title="新增部门" class="fromtable">
	<form id="deptForm">
		<table class="contertable" width="100%">
			<tr>
				<td class="label">
					父部门
				</td>
				<td>
					<label id="parentDeptName"></label><input type="hidden" name="parentId" id="deptParentId"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					部门名称
				</td>
				<td>
					<input type="text" class="text" name="name" id="deptName"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					部门描述
				</td>
				<td>
					<textarea name="remarks" id="deptRemarks"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
 <!-- 新增 部门 -->


<!-- 修改 部门 -->
 <div id="dialog-form-edit" title="修改部门" class="fromtable">
	<form id="deptEditForm">
		<table class="contertable" width="100%">
			<tr>
				<td class="label">
					父部门
				</td>
				<td>
					<label id="parentDeptName_edit"></label><input type="hidden" name="parentId" id="deptParentId_edit"/>
					<input type="hidden" name="deptId" id="deptId_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					部门名称
				</td>
				<td>
					<input type="text" class="text" name="name" id="deptName_edit"/>
				</td>
			</tr>
			<tr>
				<td class="label">
					部门描述
				</td>
				<td>
					<textarea name="remarks" id="deptRemarks_edit"></textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
 <!-- 修改 部门 -->
</html>
