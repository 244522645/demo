/**
*  新树
*/
//全局变量
var TreeObj = null;
//树类
var myBpmTree = function myBpmTree(agr){
	this.dialog = new bpmDialog();
	this.init(agr);
}
//树类初始化方法
myBpmTree.prototype.init = function(agr){
	this.dialog.init(agr);
}
//弹出树框方法
myBpmTree.prototype.showTreeDialog = function(agr){
	this.dialog.buidHtml(agr);
}

//从新加载树
myBpmTree.prototype.replaceTree = function(){
	this.dialog.treeObj.reAsyncChildNodes(null,"refresh",false);
}

//设置返回值
myBpmTree.prototype.setValues = function(a,id,name){
	var valId = '';
	var valName = '';
	for(var i=0;i<a.length;i++){
		if(valId==''||valName == ''){
			valId = a[i].id;
			valName += a[i].name;
		}else{
			valId += ","+a[i].id;
			valName += ","+a[i].name;
		}
	}
	$("#"+id).val(valId);
	$("#"+name).val(valName);
}

//弹出框类
var bpmDialog = function bpmDialog(){
	this.url="";
	this.title = "部门用户树";
	this.minWidth = 500;
	this.maxWidth = 500;
	this.minHeight = 360;
	this.maxHeight = 360;
	this.multiple = "checkbox";//是否多选
	this.treeObj = null; //树对象
	this.dialogDom = ""; //dialog 的div dom
	this.treeDom = ""; //树对象dom
	this.checkNodeDom = "";//右边选择项的dom
	this.treePutNodes = new Array();
	this.callback = null;//回调函数
	this.position  = 'center';//弹出框偏移位置
}

/**
* 从对象数组中删除属性为objPropery，值为objValue元素的对象
* @param Array arrPerson 数组对象
* @param String objPropery 对象的属性
* @param String objPropery 对象的值
* @return Array 过滤后数组
*/
//删除节点
bpmDialog.prototype.delNode = function(node){
	var temp = new Array();
	for(var i=0;i<this.treePutNodes.length;i++){
		if(this.treePutNodes[i].id!=node.id){
			temp.push(this.treePutNodes[i]);
		}
	}
	this.treePutNodes = temp;
	return this.treePutNodes;
}
//添加节点
bpmDialog.prototype.addNode = function(node){
	if(node instanceof Array){
		for(i=0;i<node.length;i++){
			var flag = false;
			for(j=0;j<this.treePutNodes.length;j++){
				if(node[i].id==this.treePutNodes[j].id){
					flag = true;
					break;
				}
			}
			if(!flag)
				this.treePutNodes.push(node[i]);
		}
	}
	else
		this.treePutNodes.push(node);
}
//替换节点
bpmDialog.prototype.replaceNode = function(node){
	if(node instanceof Array){
		this.treePutNodes = node;
	}else{
		this.treePutNodes.push(node);
	}
}

//渲染出选中的对象
bpmDialog.prototype.buidCheckNode = function(){
	var html = "";
	for(var i=0;i<this.treePutNodes.length;i++){
		//html += "<span id='"+this.treePutNodes[i].tId+"'  class=\"label label-important\">              "+this.treePutNodes[i].name+"                <a onclick=\"delClickTreeUse('"+this.treePutNodes[i].tId+"')\" href='#'>x</a></span> ";
		html += "<div id='"+this.treePutNodes[i].tId+"' onclick='checkTreeUser(this)' ondblclick=\"delClickTreeUse('"+this.treePutNodes[i].tId+"')\" style='margin-bottom:1px;cursor:pointer;' class='progress'><div  class='bar' style='width:100%;'>"+this.treePutNodes[i].name+"<a onclick=\"delClickTreeUse('"+this.treePutNodes[i].tId+"')\" href='#'>x</a></div></div>"
	}
	
	$("#"+this.checkNodeDom).html(html);
}

//重新构造选中状态
function newBuidCheckNodes(){
	//取消全部勾选
	TreeObj.checkAllNodes(false);
	//取消全部选中状态
	TreeObj.cancelSelectedNode();
	//重新勾选
	for(i = 0;i<TreeObj.bpmDialog.treePutNodes.length;i++){
		TreeObj.checkNode(TreeObj.bpmDialog.treePutNodes[i],true,false);
	}
	TreeObj.bpmDialog.buidCheckNode();
}


//删除右边选中的节点
function delClickTreeUse(tId){
	if(TreeObj==null)
		return ;
	var node = TreeObj.getNodeByTId(tId);
	//1 删除数组中的node
	TreeObj.bpmDialog.delNode(node);
	newBuidCheckNodes();
}
//选中树的check时的回调函数
onCheckTree = function(a,b,treeNode){
	var obj = this.getZTreeObj(b);
	var nodes = this.getZTreeObj(b).getChangeCheckedNodes();
	obj.bpmDialog.replaceNode(nodes);
	obj.bpmDialog.buidCheckNode();
}

//确定按钮
function subTreeNodes(){
	//console.log(TreeObj.bpmDialog);
	TreeObj.bpmDialog.callback(TreeObj.bpmDialog.treePutNodes);
	$("#"+TreeObj.bpmDialog.dialogDom).dialog("close");
}

//关闭按钮
function closeTreeDialog(){
	$("#"+TreeObj.bpmDialog.dialogDom).dialog("close");
}
//点击选中的节点时定位
function checkTreeUser(el){
	var node = TreeObj.getNodeByTId(el.id);
	TreeObj.cancelSelectedNode();
	if(node){
		TreeObj.selectNode(node,true);
	}
}
//查询按钮
function searchNodes(){
	var name = $("#"+TreeObj.bpmDialog.dialogDom+"S").val();
	if(!name)
		return ;
	var node = TreeObj.getNodesByFilter(function(node){
		 return  node.name.indexOf(name)>-1;
	});
	TreeObj.cancelSelectedNode();
	if(node){
		for(var i=0;i<node.length;i++){
			TreeObj.selectNode(node[i],true);
		}
	}
}


//弹出框类初始化
bpmDialog.prototype.init = function(agr){
	this.dialogDom = bpmUtil.getRandom('dialog');
	this.treeDom = bpmUtil.getRandom('tree');
	this.checkNodeDom = bpmUtil.getRandom('check');
	if(agr.callback && (agr.callback  instanceof Function))
              this.callback = agr.callback;//回调
	if(agr.url){
		//if(!agr.findFlag)
			//agr.findFlag = "all";
		//this.url = agr.url+"/"+agr.findFlag;
		this.url = agr.url;
	}
	if(agr.title)
		this.title = agr.title;
	if(agr.multiple==false)
		this.multiple = "radio";
	if(agr.minWidth&&agr.minWidth>500)
		this.minWidth = agr.minWidth;
	if(agr.minHeight)
		this.minHeight = agr.minHeight;
	if(agr.position)
		this.position = agr.position ;
	var toogHtml = "<table style='margin-bottom:2px;' class='table bpm-table table-bordered table-condensed'><tr><td><span style='float:left'><input id='"+this.dialogDom+"S' placeholder='请输入名称...' type='text' class='text' name=''/>" +
	"				<button type='type' onclick='searchNodes()' class='btn btn-primary'>查询</button>" +
					"</span><span style='float:right'><input type='button' onclick='subTreeNodes()' class='btn btn-primary' value='确定'/><input type='button' onclick='closeTreeDialog();' class='btn btn-primary' value='关闭'/><span></td></tr></table>";
	//1 画 html
	var html = "<div style='display:none;' id="+this.dialogDom+">"+toogHtml+"<table  class='table bpm-table table-bordered table-condensed'><tr>" +
				"<td width='50%' style='vertical-align:top;'><div style='height:"+(this.maxHeight)+"px;overflow:auto;' id='"+this.treeDom+"' class='ztree'></div></td>" +
				"<td width='50%' style='vertical-align:top;'><div id='"+this.checkNodeDom+"'></div></td></tr></table></div>";
	 $(document.body).append(html);

	//2 渲染树
	$.fn.zTree.init($("#"+this.treeDom), {
		check: {
				enable: true,
				chkboxType: { "Y": "", "N": "" },
				chkStyle:this.multiple,
				radioType:'all'
			},
		data: {
				simpleData: {
					enable: true
				}
			},
		callback: {
					onCheck: onCheckTree  //选中树checkbox
		},
		async: {
			enable: true,
			type: 'post',
			url:this.url
		}
	}); 
	//此处很关键， 把两个对象关联起来,这样可以在回调函数里用this
	this.treeObj = $.fn.zTree.getZTreeObj(this.treeDom);
	this.treeObj.bpmDialog = this;
}

bpmDialog.prototype.buidHtml = function(agr){
	$("#"+this.dialogDom).dialog({
		minWidth:this.minWidth,
		maxWidth:this.maxWidth,
		minHeight:this.minHeight,
		title:this.title,
		modal:true, //模态窗口
		position:this.position
	});
	//利用全局变量
	TreeObj = this.treeObj;
	TreeObj.bpmDialog = this;
	//把已选中的node放到树中
	var arrayNodes = new Array();
	if(agr!=null&&agr.ids){
		var tids = agr.ids.split(',');
		for(i =0;i<tids.length;i++){
			var nodes = TreeObj.getNodesByParam("id",tids[i],null);
			arrayNodes.push(nodes[0]);
		}
	}
	this.replaceNode(arrayNodes);
	newBuidCheckNodes();
	
}

//工具类
bpmUtil = {
	getRandom : function(str){
		var num = ""+Math.random();
	    return  str+num.replace('.','');
	}
}
