<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>供应商信息管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="text/javascript" src="js/commons.js"></script>
	
	<link rel="stylesheet" type="text/css" href="css/common2.css" />
	
	
	<script type="text/javascript">
		$(function(){
			
			//添加修改标志
			var flag = '';
			//搜索框展开标志
			var searchStatus = 0;
			//订单号
			var orderCode = '';
			
			/**
			 * 表格初始化
			 */
			$('#grid').datagrid({
				
				//ajax异步后台请求
				fit: true,
				//自动列间距
				fitColumns: false,
				border: false,
				//分页查询
				pagination: false,
				pageSize:5,
				pageList:[5,15,25],
				//加载等待提示
				loadMsg:'数据正在加载中，请耐心等待…',
				rownumbers:true,
				//列内容
				columns:[[
				    {
				    	field:'checkbox',
				    	width:50,
				    	checkbox:true
				    },{
				    	title:'进货单ID',
						field:'orderId',
						width:100,
						hidden: true
				    },{
				    	title:'商品ID',
						field:'commodityId',
						width:100,
						sortable: true
				    },{
						title:'商品名称',
						field:'commodityName',
						width:100,
						sortable: true
					},{
						title:'商品型号',
						field:'commodityType',
						width:100
					},{
						title:'商品类别',
						field:'categoryName',
						width:100
					},{
						title:'计量单位',
						field:'unitName',
						width:100
					},{
						title:'可见库存量',
						field:'visibleStock',
						width:100,
						hidden: true
					},{
						title:'销售价',
						field:'price',
						width:100,
					},{
						title:'数量',
						field:'amount',
						width:100
					},{
						title:'退货数量',
						field:'returnedAmount',
						width:100,
						hidden: true
					},{
						title:'总金额',
						field:'totalPrice',
						width:100
					}
				]],
				
				
				//增加工具栏，添加增删改查按钮
				toolbar:[
					{
						text:'添加商品',
						iconCls:'icon-add',
						handler:function(){
							
							if($('#clientCombobox').combobox("getValue") != "") {
								//标志为添加
								flag = 'add';
								//动态设定对话框标题
								$('#addDialog').panel({
									title: '添加商品详单'
								});
								$('#addDialog').dialog('open');
								$('#addForm').form('load',{
									orderId: orderCode,
								});
							} else {
								$.messager.show({
									title: '提示信息！',
									msg: '请先选择客户再添加商品！'
								});
							}
							
						}
					},'-',{
						text:'删除商品',
						iconCls:'icon-remove',
						handler:function(){
							
							var arr = $('#grid').datagrid('getSelections');
							if(arr.length < 1) {
								$.messager.show({
									title: '提示信息！',
									msg: '至少选择一行记录进行修改！'
								});
							} else {
								
								$.messager.confirm('提示信息' , '删除后商品信息无法回复，是否确认删除？' , function(result){
									if(result) {
										var rowIndex=$('#grid').datagrid('getRowIndex',$('#grid').datagrid('getSelected')) 
										$('#grid').datagrid('deleteRow',rowIndex);
										$('#grid').datagrid('clearSelections');
									} else {
										return;
									}
								});
								
							}
							
						}						
					},'-',{
						text:'修改数量',
						iconCls:'icon-edit',
						handler:function(){
							
							//标志位修改
							flag = 'edit';
							//动态设定对话框标题
							$('#addDialog').dialog({
								title: '修改商品信息'
							});
							var arr = $('#grid').datagrid('getSelections');
							if(arr.length != 1) {
								$.messager.show({
									title: '提示信息！',
									msg: '请选择一行记录进行修改！'
								});
							} else {
								$('#addDialog').dialog('open');
								$('#addForm').form('reset');
								$('#addForm').form('load',{
									orderId: arr[0].orderId,
									commodityId: arr[0].commodityId,
									commodityName: arr[0].commodityName,
									commodityType: arr[0].commodityType,
									categoryName: arr[0].categoryName,
									unitName: arr[0].unitName,
									visibleStock: arr[0].visibleStock,
									price: arr[0].price,
									amount: arr[0].amount
								});
							}
							
						}						
					}
				]
				
			});
			
			/**
			 * 表单提交按钮
			 */
			$('#saveButton').click(function(){
				if($('#addForm').form('validate')){
					if(flag == 'add') {
						//向grid中添加数据
						$('#grid').datagrid('appendRow',{
							orderId: $('#orderId').val(),
							commodityId: $('#commodityCombobox').combobox("getValue"),
							commodityName: $('#commodityCombobox').combobox("getText"),
							commodityType: $('#commodityType').textbox("getValue"),
							categoryName:$('#categoryName').textbox("getValue"),
							unitName: $('#unitName').textbox("getValue"),
							visibleStock: $('#visibleStock').numberbox("getValue"),
							price: $('#price').textbox("getValue"),
							amount: $('#amount').textbox("getValue"),
							totalPrice: $('#price').val() * $('#amount').val()
						});
						
					} else {
						//对grid中的选定行进行修改
						$('#grid').datagrid('updateRow',{
							index: $('#grid').datagrid('getRowIndex', $('#grid').datagrid('getSelected')),
							row: {
								orderId: $('#orderId').val(), 
								commodityId: $('#commodityCombobox').combobox("getValue"),
								commodityName: $('#commodityCombobox').combobox("getText"),
								commodityType: $('#commodityType').textbox("getValue"),
								categoryName:$('#categoryName').textbox("getValue"),
								unitName: $('#unitName').textbox("getValue"),
								visibleStock: $('#visibleStock').numberbox("getValue"),
								price: $('#price').textbox("getValue"),
								amount: $('#amount').textbox("getValue"),
								totalPrice: $('#price').val() * $('#amount').val()
							}
						});
					}
					$('#addForm').form('reset');
					$('#addDialog').dialog('close');
					//计算应付总额
					var rows = $('#grid').datagrid('getRows');
					var pay = 0;
					for(var i=0; i<rows.length; i++) {
						pay += rows[i].totalPrice;
					}
					$('#payablePrice').textbox('setValue',pay);
					$('#realPrice').textbox('setValue',pay);
				} else {
					$.messager.show({
						title: '提示信息' ,
						msg: '数据有误，不能保存！'
					});
				}
			});
			
			/**
			 * 表单取消按钮
			 */
			$('#cancelButton').click(function(){
				$('#addDialog').dialog('close');
			});
			
			/**
			 * 订单保存按钮
			 */
			$('#searchButton').click(function() {
				$.ajax({
					type: 'post',
					url: 'saleManagement_saveSale',
					chache: false,
					async: false,
					data: $('#commoditySearch').serialize() + '&detailOrder=' + JSON.stringify($('#grid').datagrid('getRows')),
					dataType: 'json',
					success: function(result) {
						if(result.success) {
							$.messager.alert('ok!',result.message);
							$('#commoditySearch').form('reset');
							window.location.reload();
							
						} else {
							$.messager.show({
								title: "fail!",
								msg: result.message
							});
						}
					}
				});
			});
			
			/**
			 * 清空按钮
			 */
			$('#clearButton').click(function() {
				$('#commoditySearch').form('reset');
				$('#createDate').datetimebox('setValue', myformatter(new Date()));
			});
			
			/**
			 * 客户下拉菜单
			 */
			$('#clientCombobox').combobox({
				url:'clientAction_getClientList',
				editable:false,
				required:false,
			    missingMessage:'必须选择供应商！',
			    valueField:'clientId',
			    textField:'clientName',
			});
			
			/**
			 * 商品列表下拉菜单
			 */
			$('#commodityCombobox').combobox({
				url:'commodityAction_getCommodityList',
				editable:false,
			    valueField:'commodityId',
			    textField:'commodityName',
			    onSelect: function() {
					$.ajax({
						type: 'post',
						url: 'saleManagement_getCommodityAndStock',
						cache: false,
						async: true,
						data: $('#addForm').serialize() + '&clientId=' + $('#clientCombobox').combobox("getValue"),
						dataType: 'json',
						success: function(c) {
							//载入商品的表单信息
							$('#addForm').form('load',{
								commodityId: c.commodityId,
								commodityName: c.commodityName,
								commodityType: c.commodityType,
								unitName: c.unitName,
								price: c.price,
								visibleStock: c.visibleStock,
								categoryName: c.categoryName
							});
							
							//设置数量的最大值
							var opts = $('#amount').numberbox('options');
							console.log(opts);
							opts.min = 0;
							opts.max = parseInt($('#visibleStock').numberbox('getValue'));
						}
					});
				}
			});
			
			
			/**
			 * 付款标志下拉菜单
			 */
			$('#payState').combobox({
				valueField:'value',
				textField:'text',
				editable:false,
				data:[{
					text:'是',
					value:'1'
				},{
					text:'否',
					value:'2'
				}]
			});
			
			
			/**
			 * 设置datebox默认时间
			 */
			$('#createDate').datetimebox({
				width:150,
				editable:false,
			    required:true,
			    missingMessage:'必须填写日期！'
			});
			var currentDate = dateFormatter(new Date());
			var currentTime = dateTimeFormatter(new Date());
			$('#createDate').datetimebox('setValue', currentTime);
			
			/**
			 * 异步获取订单号
			 */
			$.ajax({
				type: 'post',
				url: 'saleManagement_getSaleCode',
				cache: false,
				async: false,
				data: {createDate:currentDate},
				dataType: 'json',
				success: function(result) {
					orderCode = result.orderId;
					$('#north').panel({
						title:'订单编号：' + orderCode
					});
					$('#orderId2').val(orderCode);
				}
			});
			
		});
		
		
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myformatter_7(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate()+7;
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		function myparser(s){
			if (!s) return new Date();
			var ss = (s.split('-'));
			var y = parseInt(ss[0],10);
			var m = parseInt(ss[1],10);
			var d = parseInt(ss[2],10);
			if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
				return new Date(y,m-1,d);
			} else {
				return new Date();
			}
		}
		
		//js方法：序列化表单 			
		function serializeForm(form) {
			var obj = {};
			$.each(form.serializeArray(), function(index) {
				if (obj[this['name']]) {
					obj[this['name']] = obj[this['name']] + ',' + this['value'];
				} else {
					obj[this['name']] = this['value'];
				}
			});
			return obj;
		}
		
	
	</script>
	
		
  </head>
  
  <body>
  	<div id="lay" class="easyui-layout" fit=true >
		<div id="north" region="north" title="订单编号" style="height:120px;padding:10px">
			<form id="commoditySearch">
				<div class="fl" style="margin:5px">客&nbsp;&nbsp;&nbsp;户：<input id="clientCombobox" name="clientId" class="easyui-combobox"/>&nbsp;</div>
				<div class="fl" style="margin:5px">应付金额：<input id="payablePrice" name="payablePrice" class="easyui-textbox" data-options="editable:false"/>&nbsp;</div>
				<div class="fl" style="margin:5px">实付金额：<input id="realPrice" name="realPrice" class="easyui-textbox"/>&nbsp;</div>
				<div class="fl" style="margin:5px">创表日期：<input id="createDate" name="createDate" /></div>
				<div class="clear"></div>
				<div class="fl" style="margin:5px">备&nbsp;&nbsp;&nbsp;注：<input name="remark" class="easyui-textbox" style="width:330px"/>&nbsp;</div>
				<div class="fl" style="margin:5px">是否已付款：<input id="payState" name="payState" class="easyui-combobox" value="1"/>&nbsp;</div>
				<div class="fl" style="margin:5px">操作员：<input id="userName" name="userName" class="easyui-textbox" value="${sessionScope.user.loginname }" data-options="editable:false" />&nbsp;</div>
				<input type="hidden" id="userId" name="userId" class="textbox" value=${sessionScope.user.userid } />
				<input type="hidden" id="orderId2" name="orderId" class="textbox" />
				<input type="hidden" id="stockState" name="stockState" class="textbox" value="0" />
				<div class="fl" style="margin:5px">
					<a id="searchButton" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存订单</a>
					<a id="clearButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空订单</a>
				</div>
			</form>
		</div>
		<div region="center" title="商品详单">
			<table id="grid"></table>
		</div>
	</div>
	<div id="addDialog" title="添加商品详单" modal=true class="easyui-dialog"
		closed=true style="width:550px;padding:30px;">
		<form id="addForm" method="post">
			<input id="orderId" type="hidden" name="orderId" class="textbox" />
			<div class="fl" style="margin:10px">
				商品名称：<input type="text" id="commodityCombobox" name="commodityId" class="easyui-combobox" required=true missingMessage="请选择商品！"  />
			</div>
			<div class="clear"></div>
			<div style="width:450px;height:100px;margin:2px;border:2px dashed #99bbe8;">
				<div class="fl" style="margin:10px">
					商品型号：<input id="commodityType" name="commodityType" class="easyui-textbox" data-options="readonly:true" />
				</div>
				<div class="fl" style="margin:10px">
					商品类别：<input id="categoryName" name="categoryName" class="easyui-textbox" data-options="readonly:true" />
				</div>
				<div class="fl" style="margin:10px">
					计量单位：<input id="unitName" name="unitName" class="easyui-textbox" data-options="readonly:true" />
				</div>
				<div class="fl" style="margin:10px">
					库存数量：<input id="visibleStock" name="visibleStock" class="easyui-numberbox" data-options="readonly:true" />
				</div>
			</div>
			<div class="clear"></div>
			<div class="fl" style="margin:10px">
				采购价格：<input id="price" name="price" class="easyui-textbox" data-options="readonly:true" />
			</div>
			<div class="fl" style="margin:10px">
				数量：<input id="amount" name=amount class="easyui-numberbox" />
			</div>
			<div class="clear"></div>
			<br>
			<div style="margin:10px;text-align:center">
				<a id="saveButton" class="easyui-linkbutton" iconCls="icon-save" style="margin-right:10px">保存</a>
				<a id="cancelButton" class="easyui-linkbutton" iconCls="icon-cancel" style="margin-left:10px">取消</a>
			</div>
			
		</form>
	</div>
</body>
</html>
