function checkExists(){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{
		//  IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp=new XMLHttpRequest();
	}
	else
	{
		// IE6, IE5 浏览器执行代码
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function(){
		if(xmlhttp.readyState==4&&xmlhttp.status==200){
			var result = xmlhttp.responseText;
			if(result == 'success'){
				//继续post
				alart("good!");
			}else{
				//弹出提示框
				alart("bad!");
			}
		}
	}
	var phone = document.getElementById("user.phone").value;
	xmlhttp.open("GET","checkExists?user.phone=".phone,true);
	xmlhttp.send();
}