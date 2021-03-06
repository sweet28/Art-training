var uid = localStorage.getItem("uid");
var str;
(function(){
	var url = location.search; //获取url中"?"符后的字串 
	var theRequest = new Object(); 
	if (url.indexOf("?") != -1) { 
		str = url.substr(1); 
	}
	$.ajax({
	     type: "post",
		 url: getAPIURL() + "quan/"+str,
		 dataType: "json",
		 data:{
			 "id":str
		 },
		 success:function(data){
			 if(data.status == 200){
				 var quan = data.data.ddxx;
				 $("#name").html(quan.name);
				 $("#sybil").html(quan.earnProportion*100+"%");
				 $("#day").html(quan.day+"天");
				 $("#erdu").html("￥："+quan.position);
				 $("#chujued").html("￥："+quan.outPrice);
				 $("#shouyi").html("￥："+(quan.outPrice-quan.position));
				 //截止日期
				 //购买日期
				 $("#jzdate").html(fmtDate(quan.createDate));
				 //计息时间
				 if(quan.interestDate == null || quan.interestDate==""){
					 $("#jxdate").html("尚未开始");
				 }else{
					 $("#jxdate").html(fmtDate(quan.interestDate));
				 }
				 //到期时间
				 if(quan.expiryTime == null || quan.expiryTime==""){
					 $("#daoqi").html("尚未开始");
				 }else{
					 $("#daoqi").html(fmtDate(quan.expiryTime));
				 }
			 }
		 }
	});
	
	$.ajax({
		 type: "post",
		 url: getAPIURL() + "quan/couponOrderList",
		 dataType: "json",
		 data:{
			 "id":str,
			 "type":2,
			 "dakuantype":1
		 },
		 success:function(data){
			 var list = data.data
			 if(data.status == 200){
				 var html="";
				 for(var i=0;i<list.length;i++){
					 var dkType = list[i].dakuanType;
					 var dkTypeStr = "";
					 if(dkType == 1){
						 dkTypeStr = "待打款";
					 }
					 if(dkType == 2){
						 dkTypeStr = "待收款";
					 }
					 if(dkType == 3){
						 dkTypeStr = "已完成";
					 }
					 console.log(list[i].bak3);
					 console.log(list[i]);
					 
					 var otype = "<b style='color:blue;'>券保理进场订单</b>";
					 if(list[i].bak3 == '2'){
						 if(list[i].bak4==str){
							 otype = "<b style='color:red;'>出局订单</b>";
						 }
					 }
					 
					 
					 html += "<li style='margin-top: 18px;'>" +
			 					"<span>订单类型：" + otype + "</span>" +
					 			"<p>状态：" + dkTypeStr + "</p>" +
					 			"<span>订单号：" + list[i].bak2 + "</span>" +
					 			"<span style='float: right; background: #E91E63; display: inline-block; width: 20%; height: 30px; text-align: center; line-height: 30px;'>" +
					 				"<a style='font-weight: bold; color: #fff;' href='quanOrderDetailInfo?" + list[i].id + "'>详情</a>" +
				 				"</span>" +
			 				"</li>";
				 }
				 $("#a_miner").html(html);
			 }
			 
		 }
	});
	
})();

function quanOut(){
	
	$.ajax({
		 type: "post",
		 url: getAPIURL() + "quan/quanOut",
		 dataType: "json",
		 data:{
			 "id":str,
			 "uid":localStorage.getItem("uid")
		 },
		 success:function(data){
			 var list = data.data
			 if(data.status == 200){
				 swal({
					  title: "提示",
					  text:"操作完成",
					  icon: "success",
					  button: "确定",
				  });
			 }else{
				 swal({
					  title: "提示",
					  text: data.msg,
					  icon: "error",
					  button: "确定",
				  });
			 }
			 
		 }
	});
}
