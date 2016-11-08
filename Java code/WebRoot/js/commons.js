
/**
 * 自定义的校验器
 */
$.extend($.fn.validatebox.defaults.rules, {
	midLength : {
		validator : function(value, param) {
			return value.length >= param[0] && value.length <= param[1];
		},
		message : ''
	},
	equalLength : {
		validator : function(value, param) {
			return value.length == param[0];
		},
		message : '密码必须为4个字符!'
	},
	
	username: {// 验证用户名
        validator: function (value) {
        	var str = /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/;
            return str.test(value);
        },
        message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
});

/**
 * easyui-form增加自定义的二级对象load方法
 */
$.extend($.fn.form.methods, {
	myLoad : function(jq, param) {
		return jq.each(function() {
			load(this, param);
		});

		function load(target, param) {
			if (!$.data(target, "form")) {
				$.data(target, "form", {
					options : $.extend({}, $.fn.form.defaults)
				});
			}
			var options = $.data(target, "form").options;
			if (typeof param == "string") {
				var params = {};
				if (options.onBeforeLoad.call(target, params) == false) {
					return;
				}
				$.ajax({
					url : param,
					data : params,
					dataType : "json",
					success : function(rsp) {
						loadData(rsp);
					},
					error : function() {
						options.onLoadError.apply(target, arguments);
					}
				});
			} else {
				loadData(param);
			}
			function loadData(dd) {
				var form = $(target);
				var formFields = form
						.find("input[name],select[name],textarea[name]");
				formFields
						.each(function() {
							var name = this.name;
							var value = jQuery.proxy(function() {
								try {
									return eval('this.' + name);
								} catch (e) {
									return "";
								}
							}, dd)();
							var rr = setNormalVal(name, value);
							if (!rr.length) {
								var f = form.find("input[numberboxName=\""
										+ name + "\"]");
								if (f.length) {
									f.numberbox("setValue", value);
								} else {
									$("input[name=\"" + name + "\"]", form)
											.val(value);
									$("textarea[name=\"" + name + "\"]", form)
											.val(value);
									$("select[name=\"" + name + "\"]", form)
											.val(value);
								}
							}
							setPlugsVal(name, value);
						});
				options.onLoadSuccess.call(target, dd);
				$(target).form("validate");
			}
			;
			function setNormalVal(key, val) {
				var rr = $(target).find(
						"input[name=\"" + key
								+ "\"][type=radio], input[name=\"" + key
								+ "\"][type=checkbox]");
				rr._propAttr("checked", false);
				rr
						.each(function() {
							var f = $(this);
							if (f.val() == String(val)
									|| $.inArray(f.val(), val) >= 0) {
								f._propAttr("checked", true);
							}
						});
				return rr;
			}
			;
			function setPlugsVal(key, val) {
				var form = $(target);
				var cc = [ "combobox", "combotree", "combogrid", "datetimebox",
						"datebox", "combo" ];
				var c = form.find("[comboName=\"" + key + "\"]");
				if (c.length) {
					for (var i = 0; i < cc.length; i++) {
						var combo = cc[i];
						if (c.hasClass(combo + "-f")) {
							if (c[combo]("options").multiple) {
								c[combo]("setValues", val);
							} else {
								c[combo]("setValue", val);
							}
							return;
						}
					}
				}
			}
			;
		}
		;
	}
});