(function(){
  $(function(){
	  var tmp = getTimestamp();
      var rad = getRandom();
      var ton = getTom();
      var str = "jddi="+localStorage.getItem("uid")+"trddi="+localStorage.getItem("uid")+"tmp="+tmp+"rad="+rad+"tom="+ton;
      $.ajax({
    	  type: "post",
	      url: getAPIURL() + "kuangjy/jy/dsklb",
	      dataType: "json",
	      data:{
          	  "jddi":localStorage.getItem("uid"),
          	  "trddi":localStorage.getItem("uid"),
              "tmp":tmp,
              "rad":rad,
              "tom":ton,
              "token":commingSoon1(str)
	      },
        success: function (data) {
        	 var list = data;
          if (list.length <= 0) {
            var txtsNULL ="<p class='nothing'>无更多记录</p>";
            $("#a_miner").html(txtsNULL);
          } else {
        	  var txt1 = '';//,txt2='';
              for (var i = 0; i < list.length; i++) {
              	var cpatype ;
              	var mm;
              	
              	if(list[i].traderType==2){
              		mm="卖";
              	}
              	if(list[i].traderType==1){
              		mm="买"
              	}
              	if(list[i].traderState==1){
              		cpatype = "待付款";
              	}
              	if(list[i].traderState==2){
              		cpatype = "待确认收款";
              	}
              	if(list[i].traderState==3){
              		cpatype = "已完成";
              	}
              	
              	var ahref;
          		ahref = "<a href='../page/my_invest2.html?"+list[i].id+"'>详情</a>";
              	
  			    txt1 += "<tr>" +
	  			    		"<td>"+mm+(list[i].id)+"</td>" +
	  			    		"<td>"+list[i].entrustPrice+"</td>" +
	  			    		"<td>"+list[i].traderCount+"</td>" +
	  			    		"<td>" + cpatype +"</td>" +
	  			    		"<td>" + ahref +"</td>" +
  			    		"</tr>";
              }
              $('#a_miner').html(txt1);
          }
        },
        error: function(XMLHttpRequest, textStatus, errorThrown){
        }
      });
  });
})();