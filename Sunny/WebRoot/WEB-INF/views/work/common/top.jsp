<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!-- 顶部用户退出行 -->
	<nav class="navbar navbar-inverse hidden-xs">
		<div class="container-fluid">
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		        <ul class="nav navbar-nav navbar-right">
					<li class="hidden-xs"><a class="exit" href="javascript:quitClick();">退出登录</a></li>
					<li class="hidden-xs"><a class="set">设置</a></li>
				</ul>
		    </div>
		</div>
	</nav>
<script type="text/javascript">
function quitClick(){
       if(confirm("确定退出？")){
       	window.location.href="${ctx}/work/login/quit";
       }
}
</script>
