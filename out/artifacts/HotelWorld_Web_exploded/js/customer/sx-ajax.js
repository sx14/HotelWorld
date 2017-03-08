function checkExists(){
	if(confirm('registerForm') == false){
		return;
	}
    $.ajax({
        type: "post",
        url:"registerQuickly",
        data:$('#registerForm').serialize(),// 你的formid
        datatype:"text/html",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if(data == "success"){
                alert("注册成功！");
                window.location="chooseHotel"
            }else if(data == "exists"){
                alert("用户名已注册，请登录");
            }else{
            	alert("网络故障");
			}
        }
    });
}


function submitOrder(){
	if(confirm('orderForm') == false){
		return;
	}
    $.ajax({
        type: "post",
        url:"saveOrder",
        data:$('#orderForm').serialize(),// 你的formid
        datatype:"text/html",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if(data == "success"){
                alert("预定成功！");
                window.location="personalHome"
            }else if(data == "error"){
                alert("预定失败，请重试");
            }else{
            	alert("网络故障");
			}
        }
    });
}



function login() {
    $.ajax({
        type: "post",
        url:"login",
        data:$('#loginForm').serialize(),// 你的formid
        datatype:"text/html",
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	if(data != "error"){
//                alert(data);
        		window.location=data;
    		}else {
        		alert("登录失败！");
    		}
        }
    });
}



function registerHotel() {
	if(confirm('hotelForm') == false){return;}
	var form = document.getElementById("hotelForm");
	form.action="registerHotel";
	form.method="post";
	form.submit();
}

function confirmAndSubmit(formid,action){
	if(confirm(formid) == false){return;}
	var form = document.getElementById(formid);
	form.action=action;
	form.method="post";
	form.submit();
}

function confirm(formName){
    var form = document.getElementById(formName);
    var inputs = form.getElementsByTagName("input");
    var selects = form.getElementsByTagName("select");
    for(i = 0 ; i < inputs.length ; i++){
        var input = inputs[i];
        if (input.value == ""){
            alert("请完整填写");
            return false;
        }
    }
    for(i = 0 ; i < selects.length ; i++){
        var input = inputs[i];
        if (input.value == ""){
            alert("请完整填写");
            return false;
        }
    }
    return true;
}

function reset(formid){
	var form = document.getElementById(formid);
    var form = document.getElementById(formName);
    var inputs = form.getElementsByTagName("input");
    for(i = 0 ; i < inputs.length ; i++){
        var input = inputs[i];
        input.value = '';
    }
}