function Gift() {
  var self = this, _$gift;
  var uid = localStorage.getItem("uid");
  
  var flag = checkLogin();
  console.log(100);
  function comptime() {
    $.ajax({
      type: "post",
      url: getAPIURL() + "user/fens/listFens2",
      dataType: "json",
      data: {
    	  "sh": localStorage.getItem("phone")
      },
      success: function (data) {
    	  console.log(data);
		  $("#fenstuan").html(data.fensList);
		  $("#fenssl").html(data.fensuanli);
      },error:function(){
    	  console.log(333);
      }, headers: {
        "Authorization": "Bearer " + getTOKEN()
      }
    });
  }
  console.log(111);
  
  function kuangjiList() {
	    $.ajax({
	      type: "post",
	      url: getAPIURL() + "user/fens/listQINYOU",
	      dataType: "json",
	      data: {
	    	  "sh": localStorage.getItem("phone")
	      },
	      success: function (data) {
			    var list = data.list;
	  	        if (list.length <= 0) {
	  	        	$("#qytuan").html(0);
	  	        	$("#gift").html("暂无数据");
	  	        } else {
	  	        	var html = "";
	  	        	$.each( list, function(index, content){
	  	        		var inph = content.phone;
	  	        		inph = inph.substring(0, 3) + "****" + inph.substring(7, 11);
	  	        		
	  	        		var nm = content.name;
	  	        		nm = "***"+nm.substring(1);
	  				    //html += "<tr><td class='first'>"+(index+1)+"</td><td>"+nm+"</td><td>"+inph+"</td></tr>";
	  				    html += "<ul>" + (index+1) +
			    					"<li>" +
										"<div class='text'>" +
											"<p>名称：<b>"+nm+"</b></p>" +
											"<p>号码：<b>"+inph+"</b></p>" +
										"</div>" +
										"<div class='look'>" +
											"<a href='#'></a>" +
										"</div>" +
									"</li>" +
							   "</ul>";
	  				});
	  	        	$("#qytuan").html(list.length);
	  	        	$("#gift").html(html);
	  	        }
	    	  
	      }, headers: {
	        "Authorization": "Bearer " + getTOKEN()
	      }
	    });
	  }

  (function () {
    _$gift = $("#gift");
    comptime();
    kuangjiList();
  })();
}
var gift;
$(function () {
  gift = new Gift();
});