<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>交易中心</title>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/css/mobile.css">
<link rel="stylesheet" href="<%=path%>/cssnew/style.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/sweetalert/css/sweetalert.css">
<script src="<%=path%>/lib/js/jquery-2.1.4.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/lib/js/jquery.base64.js" type="text/javascript" charset="utf-8"></script>
<script src="<%=path%>/lib/js/jquery-2.1.4.min.js" type="text/javascript" charset="utf-8"></script>
<%-- <script type="text/javascript" src="<%=path%>/sweetalert/sweetalert.js"></script> --%>
<script type="text/javascript" src="<%=path%>/sweetalert/sweetalert.min.js"></script>
<script src="<%=path%>/js/wapframwork.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" src="<%=path%>/lib/js/echarts.min.js"></script>

<style>
	body{ background: #f1f0f6; }
</style>
</head>
<body>
	<header>
		<span>交易中心</span>
		<a href="javascript:history.go(-1)" class="history"><img src="<%=path%>/images/go.png" alt=""></a>
		<a href="#" class="username">
			<span id="uname"></span>
		</a>
	</header>
	<div class="cl"></div>
	<div class="deal">
		<div class="deal_text">
			<p>今日最高价($)：<span style="color:red;" id="zgj"></span></p>
			<p>今日最低价($)：<span style="color:green;" id="zdj"></span></p>
			<b>CPA:<span id="jye"></span></b>
		</div>
		<div id="container" style="height: 260%;"></div>
		<div class="deal_button">
			<a href="<%=path%>/cpa/traderCenter" class="d2">买入CPA</a>
			<a href="<%=path%>/cpa/traderCenterSell" class="d1">卖出CPA</a>
		</div>
	</div>
	<div class="cl"></div>
	<div class="row buyin">
		<div class="col-xs-6 buy_input">
			<b>交易数量:</b><input type="number" name="" 
			 onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
			 onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
			 placeholder="请输入交易数量" class="num" id="cpanum"/>
			<b>交易单价:</b><input type="number" name="" placeholder="请输入交易单价" id="cpadj"/>
		</div>
		<div class="col-xs-6 buy_btn">
			<a href="javascript:buyCPA(2);">出售</a>
		</div>
	</div>
	<div class="cl"></div>
	<div class="user_title">
		<ul>
			<li class="on">订单</li>
			<li>CPA数量</li>
			<li>单价（$）</li>
			<li>总价（$）</li>
			<li>操作</li>
		</ul>
	</div>
	<div class="user_list">
		<div class="searchBox">
			<input type="text" class="sea" id="searchPhone" value="" placeholder="买家账号检索">
			<input type="submit" class="subbtn" value="检索" onclick="searchTrader(2);">
		</div>
		<ul id="gift">
			<!-- <li>
				<span class="us1">1520564***...</span>
				<span class="us2">166</span>
				<span class="us3">1.19</span>
				<span class="us4">197.54</span>
				<span class="us5"><a href="">等待中</a></span>
			</li> -->
		</ul>
	</div>

	<div class="space"></div>
	<div class="menu">
		<ul>
			<li><a href="<%=path%>/cpa/main"><img src="<%=path%>/imagenew/menu5.png" alt=""><p>首页</p></a></li>
			<li><a href="<%=path%>/cpa/minerHouse"><img src="<%=path%>/imagenew/menu1.png" alt=""><p>矿机商城</p></a></li>
			<li><a href="<%=path%>/cpa/traderCenter"><img src="<%=path%>/imagenew/menu3_on.png" alt=""><p>交易中心</p></a></li>
			<li><a href="<%=path%>/cpa/myMiner"><img src="<%=path%>/imagenew/menu2.png" alt=""><p>我的矿机</p></a></li>
			<li><a href="<%=path%>/cpa/personal"><img src="<%=path%>/imagenew/menu4.png" alt=""><p>个人中心</p></a></li>
		</ul>
	</div>
</body>

<script src="<%=path%>/js/trader/invest_sell.js" type="text/javascript" charset="utf-8"></script>
</html>