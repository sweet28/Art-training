<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/lib/ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=path%>/lib/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=path%>/lib/ztree/js/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=path%>/lib/ztree/js/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=path%>/lib/ztree/js/jquery.ztree.excheck.js"></script>
	<script type="text/javascript" src="<%=path%>/lib/ztree/js/jquery.ztree.exedit.js"></script>
	<SCRIPT type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url: getUrl
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				expandSpeed: ""
			},
			callback: {
				beforeExpand: beforeExpand,
				onAsyncSuccess: onAsyncSuccess,
				onAsyncError: onAsyncError
			}
		};

		var zNodes =[
			{name:"500个节点", id:"1", count:500, times:1, isParent:true},
			{name:"1000个节点", id:"2", count:1000, times:1, isParent:true},
			{name:"2000个节点", id:"3", count:2000, times:1, isParent:true}
		];

		var log, className = "dark",
		startTime = 0, endTime = 0, perCount = 100, perTime = 100;
		function getUrl(treeId, treeNode) {
			var curCount = (treeNode.children) ? treeNode.children.length : 0;
			var getCount = (curCount + perCount) > treeNode.count ? (treeNode.count - curCount) : perCount;
			var param = "id="+treeNode.id+"_"+(treeNode.times++) +"&count="+getCount;
			return "../asyncData/getNodesForBigData.php?" + param;
		}
		function beforeExpand(treeId, treeNode) {
			if (!treeNode.isAjaxing) {
				startTime = new Date();
				treeNode.times = 1;
				ajaxGetNodes(treeNode, "refresh");
				return true;
			} else {
				alert("zTree 正在下载数据中，请稍后展开节点。。。");
				return false;
			}
		}
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			if (!msg || msg.length == 0) {
				return;
			}
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			totalCount = treeNode.count;
			if (treeNode.children.length < totalCount) {
				setTimeout(function() {ajaxGetNodes(treeNode);}, perTime);
			} else {
				treeNode.icon = "";
				zTree.updateNode(treeNode);
				zTree.selectNode(treeNode.children[0]);
				endTime = new Date();
				var usedTime = (endTime.getTime() - startTime.getTime())/1000;
				className = (className === "dark" ? "":"dark");
				showLog("[ "+getTime()+" ]&nbsp;&nbsp;treeNode:" + treeNode.name );
				showLog("加载完毕，共进行 "+ (treeNode.times-1) +" 次异步加载, 耗时："+ usedTime + " 秒");
			}
		}
		function onAsyncError(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			alert("异步获取数据出现异常。");
			treeNode.icon = "";
			zTree.updateNode(treeNode);
		}
		function ajaxGetNodes(treeNode, reloadType) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			if (reloadType == "refresh") {
				treeNode.icon = "../../../css/zTreeStyle/img/loading.gif";
				zTree.updateNode(treeNode);
			}
			zTree.reAsyncChildNodes(treeNode, reloadType, true);
		}
		function showLog(str) {
			if (!log) log = $("#log");
			log.append("<li class='"+className+"'>"+str+"</li>");
			if(log.children("li").length > 4) {
				log.get(0).removeChild(log.children("li")[0]);
			}
		}
		function getTime() {
			var now= new Date(),
			h=now.getHours(),
			m=now.getMinutes(),
			s=now.getSeconds(),
			ms=now.getMilliseconds();
			return (h+":"+m+":"+s+ " " +ms);
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);

		});
	</SCRIPT>
 </HEAD>

<BODY>
<h1>分批异步加载大数据量</h1>
<h6>[ 文件路径: bigdata/diy_async.html ]</h6>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul>
			<li class="highlight_red">&nbsp;&nbsp;&nbsp;&nbsp;此 Demo 专门用于测试分批异步加载，每次展开节点都要重新进行异步加载。</li>
		</ul>
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	<div class="right">
		<ul class="info">
			<li class="title"><h2>1、大数据量加载说明</h2>
				<ul class="list">
				<li>1)、对于某一级节点数多达几千个的时候，zTree 默认的延迟加载是无效的，此 Demo 演示了一种原先 zTree v2.6 时的分批加载节点的方法。</li>
				<li class="highlight_red">2)、此方法适用于1、2千个节点必须全部显示的需求。</li>
				<li class="highlight_red">3)、此方法并不能解决加载慢的问题，相反只会让最终结果出现的更慢，只是可以有限度的避免浏览器假死，而且显示的节点越多就越慢。</li>
				<li>4)、对于某一级节点数至少几千个的情况，另一个解决方案是：分页异步加载。<br/>
					async load log:<br/>
					<ul id="log" class="log" style="height:85px"></ul></li>
				</ul>
			</li>
			<li class="title"><h2>2、setting 配置信息说明</h2>
				<ul class="list">
				<li>需要设置 setting.async 异步加载部分的参数</li>
				<li>建议关闭动画效果 setting.view.expandSpeed = "";</li>
				<li>其他不需要进行特殊的配置，根据自己的需求自行设置</li>
				</ul>
			</li>
			<li class="title"><h2>3、treeNode 节点数据说明</h2>
				<ul class="list">
				<li>对 节点数据 没有特殊要求，用户可以根据自己的需求添加自定义属性</li>
				</ul>
			</li>
		</ul>
	</div>
</div>
</BODY>
</html>