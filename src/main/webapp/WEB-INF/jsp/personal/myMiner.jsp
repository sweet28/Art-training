<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>我的矿机</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/mobile.css">
<style>
	body{ background: #f1f0f6; }
</style>
</head>
<body>
	<header>
		<span>我的矿机</span>
		<a href="#" class="history"><img src="<%=path%>/images/go.png" alt=""></a>
		<a href="#" class="username">
			<span>G25634845</span>
		</a>
	</header>
	<div class="line"></div>
	<div class="row myshop">
		<ul>
			<li>
				<div class="img">
					<img src="<%=path%>/images/p1.jpg">
				</div>
				<div class="text">
					<a href="">CA1型</a>
					<p>运行周期：1440小时</p>
					<p>产量/小时：0.00......</p>
					<p>矿机编号：H4256655</p>
					<p>矿机状态：正在运行</p>
				</div>
				<div class="look">
					<a href="">查看</a>
				</div>
			</li>
			<li>
				<div class="img">
					<img src="<%=path%>/images/p1.jpg">
				</div>
				<div class="text">
					<a href="">CA1型</a>
					<p>运行周期：1440小时</p>
					<p>产量/小时：0.00......</p>
					<p>矿机编号：H4256655</p>
					<p>矿机状态：正在运行</p>
				</div>
				<div class="look">
					<a href="">查看</a>
				</div>
			</li>
		</ul>
	</div>	
	<div class="cl"></div>
	<div class="space"></div>
	<div class="menu">
		<ul>
			<li class="col-xs-3"><a href="#"><img src="<%=path%>/images/menu1.png" alt=""><p>矿机商城</p></a></li>
			<li class="col-xs-3"><a href="#"><img src="<%=path%>/images/menu2.png" alt=""><p>我的矿机</p></a></li>
			<li class="col-xs-3"><a href="#"><img src="<%=path%>/images/menu3.png" alt=""><p>交易中心</p></a></li>
			<li class="col-xs-3"><a href="#"><img src="<%=path%>/images/menu4.png" alt=""><p>个人中心</p></a></li>
		</ul>
	</div>
</body>
</html>