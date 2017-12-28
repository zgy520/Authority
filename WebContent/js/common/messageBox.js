var tipBox = { //提示框
	Info:function(title,msg){  //信息框
		tipBox.Common(title,msg,"info");
	},
	Warn:function(title,msg){ //警告框
		tipBox.Common(title,msg,'warning');
	},
	Error:function(title,msg){ //错误框
		tipBox.Common(title,msg,'error');
	},
	Common:function(title,msg,icon){
		$.messager.alert({
			title:title,
			icon:icon,
			msg:msg
		});
	}
}