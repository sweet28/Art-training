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
<title>矿机商城</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/mobile.css">
<script type="text/javascript" src="<%=path%>/lib/js/jquery-2.1.4.js"></script>
<script src="<%=path%>/lib/js/layer.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/lib/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/js/wapframwork.js"></script>
<script src="<%=path%>/js/loading.js"></script>
<style>
	body{ background: #f1f0f6; }
</style>
</head>
<body>
	<header>
		<span>交易中心</span>
		<a href="javascript:history.go(-1)" class="history"><img src="<%=path%>/images/go.png" alt=""></a>
		<a href="#" class="share"><img src="<%=path%>/images/share.png" alt=""></a>
	</header>
	<div class="row pro">
		<a href="">G25634845</a>
	</div>
	<div class="cl"></div>
	<div class="row shop_title">
		<span>A矿场</span>
	</div>
	<div class="row shop_list">
		<ul>
			<li>
				<div class="img">
					<a href=""><img src="<%=path%>/images/p1.jpg"></a>
				</div>
				<div class="text">
					<p><a href="">CA1型</a> <span>价格：10CPA</span></p>
					<p>产量/小时：0.128956333</p>
					<p>运行周期：1440小时</p>
				</div>
				<div class="more">
					<a href="javascript:agoumai(1,1);">购买</a>
				</div>
			</li>
			<li>
				<div class="img">
					<a href=""><img src="<%=path%>/images/p2.jpg"></a>
				</div>
				<div class="text">
					<p><a href="">CA2型</a> <span>价格：100CPA</span></p>
					<p>产量/小时：0.128956333</p>
					<p>运行周期：1440小时</p>
				</div>
				<div class="more">
					<a href="">购买</a>
				</div>
			</li>
			<li>
				<div class="img">
					<a href=""><img src="<%=path%>/images/p3.jpg"></a>
				</div>
				<div class="text">
					<p><a href="">CA3型</a> <span>价格：10CPA</span></p>
					<p>产量/小时：0.128956333</p>
					<p>运行周期：1440小时</p>
				</div>
				<div class="more">
					<a href="">购买</a>
				</div>
			</li>
		</ul>
	</div>
	<div class="cl"></div>
	<div class="row shop_title">
		<span>B矿场</span>
	</div>
	<div class="row shop_list">
		<ul>
			<li>
				<div class="img">
					<a href=""><img src="<%=path%>/images/p1.jpg"></a>
				</div>
				<div class="text">
					<p><a href="">CA1型</a> <span>价格：10CPA</span></p>
					<p>产量/小时：0.128956333</p>
					<p>运行周期：1440小时</p>
				</div>
				<div class="more">
					<a href="">购买</a>
				</div>
			</li>
			<li>
				<div class="img">
					<a href=""><img src="<%=path%>/images/p2.jpg"></a>
				</div>
				<div class="text">
					<p><a href="">CA2型</a> <span>价格：10CPA</span></p>
					<p>产量/小时：0.128956333</p>
					<p>运行周期：1440小时</p>
				</div>
				<div class="more">
					<a href="">购买</a>
				</div>
			</li>
			<li>
				<div class="img">
					<a href=""><img src="<%=path%>/images/p3.jpg"></a>
				</div>
				<div class="text">
					<p><a href="">CA3型</a> <span>价格：10CPA</span></p>
					<p>产量/小时：0.128956333</p>
					<p>运行周期：1440小时</p>
				</div>
				<div class="more">
					<a href="">购买</a>
				</div>
			</li>
		</ul>
	</div>
	<div class="cl"></div>
	<div class="space"></div>
	<div class="menu">
		<ul>
			<li class="col-xs-3"><a href="<%=path%>/cpa/minerBuy"><img src="<%=path%>/images/menu1.png" alt=""><p>矿机商城</p></a></li>
			<li class="col-xs-3"><a href="<%=path%>/cpa/myMiner"><img src="<%=path%>/images/menu2.png" alt=""><p>我的矿机</p></a></li>
			<li class="col-xs-3"><a href="<%=path%>/cpa/traderCenter"><img src="<%=path%>/images/menu3.png" alt=""><p>交易中心</p></a></li>
			<li class="col-xs-3"><a href="<%=path%>/cpa/personal"><img src="<%=path%>/images/menu4.png" alt=""><p>个人中心</p></a></li>
		</ul>
	</div>
</body>
<script type="text/javascript" src="<%=path%>/js/miner/invest_kj.js"></script>
</html>