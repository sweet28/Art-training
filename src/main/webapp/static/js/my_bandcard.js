/**
 * Created by tim on 2016/6/21.
 */
function Gift() {
  var self = this, _$gift;

  function comptime() {
	  var uid = localStorage.getItem("uid");
	  var page = 100;
	  var row = 0;
	  var tmp = getTimestamp();
	  var rad = getRandom();
	  var ton = getTom();
	  var str = "uid="+uid+"pg=0"+"ts=100"+"tmp="+tmp+"rad="+rad+"tom="+ton;
    $.ajax({
      type: "post",
      url: getAPIURL() + "fs/bank/list",
      dataType: "json",
      data: {
    	  "uid":uid,
    	  "pg":0,
    	  "ts":100,
          "tmp":tmp,
          "rad":rad,
          "tom":ton,
          "token":commingSoon1(str)
      },
      success: function (data) {
        var list = data.list;
        if (list.length <= 0) {
          $("#a_miner").html("<ul><li class='nothing'><p>暂无记录</p></li></ul>");
        } else {
        	var html;
        	$.each( list, function(index, content){
        		var runs = content.isApply;
        		if(runs==0){
        			runs="未使用";
        		}else if(runs==1){
        			runs="使用中";
        		}
        		
			    html += "<tr><td class='first'>"+(index+1)+"</td><td>"+content.bank+"</td><td>"+content.cardNumber+"</td><td>"+runs+"</td></tr>";
			});
        	
        	$("#a_miner").html(html);
        }
      }, headers: {
        "Authorization": "Bearer " + getTOKEN()
      }
    });
  }

  (function () {
    _$gift = $("#a_miner");
    comptime();
  })();
}
var gift;
$(function () {
  gift = new Gift();
});