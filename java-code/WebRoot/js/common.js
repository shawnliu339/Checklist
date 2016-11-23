/**
 * 日期格式化
 */
function dateFormatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d);
};

/**
 * 时间格式化
 */
function dateTimeFormatter(date) {
	var y = date.getFullYear();
	var m = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var min = date.getMinutes();
	var s = date.getSeconds();
	return y + '-' + (m < 10 ? ('0' + m) : m) + '-' + (d < 10 ? ('0' + d) : d)
			+ " " + (h < 10 ? ('0' + h) : h) + ":"
			+ (min < 10 ? ('0' + min) : min) + ":" + (s < 10 ? ('0' + s) : s);
};

/**
 * 自定义的校验器
 */
$.extend($.fn.validatebox.defaults.rules, {
	loginname : {// 验证用户名
		validator : function(value) {
			var str = /^[a-zA-Z][a-zA-Z0-9_]{4,15}$/;
			if(str.test(value)) {
				$.post('user_loginnameIsEqual', {loginname:value}, function(result){
					if(result.success){
						return true;
						
					} else {
						$.fn.validatebox.defaults.rules.loginname.message = "用户名重复";
						return false;
					}
				},'json');
			} else {
				 $.fn.validatebox.defaults.rules.loginname.message = "用户名不合法（字母开头，允许5-16字节，允许字母数字下划线）";
                 return false;
			}
			return str.test(value);
		},
		message : ''
	},

	same : {
		validator : function(value, param) {
			if ($("#" + param[0]).val() != "" && value != "") {
				return $("#" + param[0]).val() == value;
			} else {
				return true;
			}
		},
		message : '两次输入的密码不一致！'
	}
});
