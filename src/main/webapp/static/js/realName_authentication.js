var testKey = "m5zweEGHgZJ6KZDsRKBsA3fTUlugIAB-";
var testSec = "7ElQMg_G5OfBt_P93-80VAHkPinhiMrx";

var proKey = "8qq0MDKgcfMwOiw7E27tvZ08D6LbErhP";
var proSec = "xJIXFiZV011fBLQVexoS1S1QUYkxLaIz";

var faceUrl = "https://api-cn.faceplusplus.com/cardpp/v1/ocridcard";

var testUrl = "https://cpa.artforyou.cn/img/1.jpg"

$("#file0").change(function(){  
	
//	$.ajax({
//	      type: "post",
//	      multi:true,
//	      url: faceUrl,
//	      data:{
//	    	"api_key":testKey,
//	    	"api_secret":testSec,
//	    	"image_url":testUrl
//	      },
//	      dataType: "jsonp",
//	      success: function (data) {
//	    	  console.log(data);
//	      },
//	      error: function () {
//	    	  console.log("lllllllllllllllllllll000------------");
//	      }
//      });
//	
	
      var objUrl = getObjectURL(this.files[0]) ;
      console.log(this.files[0]+"-------77777");
      // 这句代码没什么作用，删掉也可以  
      // console.log("objUrl = "+objUrl) ;  
      if (objUrl) {  
        // 在这里修改图片的地址属性  
        $("#img0").attr("src", objUrl) ;
        var data = new FormData($('#form0')[0]); 
        
        
        $.ajax({
  	      type: "post",
  	      url: getAPIURL() + "fenuser/updateInfo",
  	      data:{
  	        "attachment": "0",
  	        "phone":getPhone()
  	      },
  	      dataType: "json",
  	      success: function (data) {
  	    	 if(data.status == 200){
  	    		 localStorage.setItem("sec","0");
	  	    	  layer.open({
	  	    	    content: "认证提交完成",
	  	    	    skin: 'msg',
	  	    	    time: 2, //2秒后自动关闭
	  	    	    end: function () {
	  	    	      location.href = '../page/safe_center.html?z';
	  	    	    }
	  	    	  });
  	    	  }else{
  	    		  console.log("=======================098");
  	    	  }
  	      },
  	      error: function () {
  	        $('#mydiv').empty();
  	        var txtsNULL = "<p class='nothing'>网络错误</p>";
  	        $('#mydiv').append(txtsNULL);
  	      },
  	      headers: {
  	        "Authorization": "Bearer " + getTOKEN()
  	      }

        });
      }  
    }) ;  
    //建立一個可存取到該file的url  
    function getObjectURL(file) {  
      var url = null ;   
      // 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已  
      if (window.createObjectURL!=undefined) { // basic  
        url = window.createObjectURL(file) ;  
      } else if (window.URL!=undefined) { // mozilla(firefox)  
        url = window.URL.createObjectURL(file) ;  
      } else if (window.webkitURL!=undefined) { // webkit or chrome  
        url = window.webkitURL.createObjectURL(file) ;  
      }  
      return url ;  
}  


function Verified() {
  var _self = this, _$name, _$cardid, _$cardid_confirm, _hash;

  _hash = window.location.hash;

  _next = function (_this) {
    if(!_this.hasClass("disabled")){
      _this.addClass("disabled");
      loading.open();
      if ($.trim(_$name.val()) == "") {
        loading.close();
        _this.removeClass("disabled");
        layer.open({
          content: '请输入您的真实姓名！'
          , skin: 'msg'
          , time: 2 //2秒后自动关闭
        });
        return false;
      }
      else {
        if ($.trim(_$name.val()).length < 2 || $.trim(_$name.val()).length > 10) {
          loading.close();
          _this.removeClass("disabled");
          layer.open({
            content: '真实姓名长度有误！'
            , skin: 'msg'
            , time: 2 //2秒后自动关闭
          });
          return false;
        }
        else {
          var nameRule = /^[\u4E00-\u9FA5]+$/;
          if (!nameRule.test($.trim(_$name.val()))) {
            loading.close();
            _this.removeClass("disabled");
            layer.open({
              content: '姓名含有非法字符！'
              , skin: 'msg'
              , time: 2 //2秒后自动关闭
            });
            return false;
          }
        }
      }
      if ($.trim(_$cardid.val()) == "") {
        loading.close();
        _this.removeClass("disabled");
        layer.open({
          content: '请输入正确的身份证号！'
          , skin: 'msg'
          , time: 2 //2秒后自动关闭
        });
        return false;
      }
      else if ($.trim(_$cardid_confirm.val()) == "") {
        loading.close();
        _this.removeClass("disabled");
        layer.open({
          content: '请再次输入身份证号！'
          , skin: 'msg'
          , time: 2 //2秒后自动关闭
        });
        return false;
      }
      //  var cardRule = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
      if ($.trim(_$cardid.val()).length != 18 || $.trim(_$cardid_confirm.val()).length != 18) {
        loading.close();
        _this.removeClass("disabled");
        layer.open({
          content: '身份证号码位数有误！'
          , skin: 'msg'
          , time: 2 //2秒后自动关闭
        });
        return false;
      }
      else if ($.trim(_$cardid.val()) != $.trim(_$cardid_confirm.val())) {
        loading.close();
        _this.removeClass("disabled");
        layer.open({
          content: '身份证号确认有误！'
          , skin: 'msg'
          , time: 2 //2秒后自动关闭
        });
        return false;
      }
      var uid = getUIDByJWT().unique_name;
      $.ajax({
        type: "Post",
        url: getAPIURL() + "securitysettings/" + uid + "/bindidcard?realname=" + _$name.val().toString() + "&idcard=" + _$cardid.val().toString(),
        data: null,
        dataType: 'json',
        timeout:6000,
        cache: false,
        async: false,
        success: function (data) {
          loading.close();
          _this.removeClass("disabled");
          if (data.rtn == "1") {
            layer.open({
              content: '实名认证成功！'
              , skin: 'msg'
              , time: 2 //2秒后自动关闭
              , end: function () {
                setTimeout(function () {
                  if (_hash == "#promptly_invest.html") {
                    // window.location = "../page/" + _hash.slice(1);
                    window.location = "../page/" +_hash.slice(1)+ window.location.search;
                  }else if(_hash == "#safe_center.html"){
                    window.location = "../page/" + _hash.slice(1);
                  }
                  else {
                    window.location = "../page/personal.html";
                  }
                }, 2000);
              }
            });
          }
          if (data.rtn == "2") {
            layer.open({
              content: '您输入的名字与身份证号不匹配,请核对您的认证信！',
              skin: 'msg',
              time: 2 //2秒后自动关闭
            });
          }
          if (data.rtn == "3") {
            layer.open({
              content: '该身份证号码已有认证！'
              , skin: 'msg'
              , time: 2 //2秒后自动关闭
            });
          }
          if (data.rtn == "4") {
            layer.open({
              content: '身份证号码输入有误！'
              , skin: 'msg'
              , time: 2 //2秒后自动关闭
            });
          }
          if (data.rtn == "5") {
            layer.open({
              content: '实名认证上传成功，等待审核！'
              , skin: 'msg'
              , time: 2 //2秒后自动关闭
            });
          }
          if (data.rtn == "6") {
            layer.open({
              content: '您输入的身份证号有误,请核对您的认证信息！'
              , skin: 'msg'
              , time: 2 //2秒后自动关闭
            });
          }
          if (data.rtn == "7") {
            layer.open({
              content: '实名认证次数已到上限，请明天再试！'
              , skin: 'msg'
              , time: 2 //2秒后自动关闭
            });
          }
        },
        error : function(XMLHttpRequest,textStatus){
          loading.close();
          _this.removeClass("disabled");
          if(textStatus=='timeout'){
            layer.open({
              content:"操作超时",
              btn:'确定'
            });
          }
          else{
            if(XMLHttpRequest.status == 400) {
              var obj = JSON.parse(XMLHttpRequest.responseText);
              layer.open({
                content:obj.Message,
                btn:'确定'
              });
            }
          }
        },
        headers: {
          "Authorization": "Bearer " + getTOKEN()
        }
      })
    }
  };

  this.next = _next;

  (function () {
    _$name = $("#name");
    _$cardid = $("#cardid");
    _$cardid_confirm = $("#cardid_confirm");
  })();
}

var verified;
$(function () {
  verified = new Verified();
});