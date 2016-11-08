<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>进货退货出库管理</title>
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
	
	<link rel="stylesheet" type="text/css" href="css/common2.css" />
	<script type="text/javascript" src="js/commons.js"></script>
	<script type="text/javascript">
		$(function(){
			
			//添加修改标志
			var flag = '';
			//搜索框展开标志
			var searchStatus = 0;
			
			/**
			 * 表格初始化
			 */
			$('#supplierTable').datagrid({
				
				idField:'orderId',
				//ajax异步后台请求
				url: 'saleManagement_getReturnStockInTotalGrid',
				fit: true,
				//自动列间距
				fitColumns: false,
				border: false,
				//分页查询
				pagination: true,
				//加载等待提示
				loadMsg:'数据正在加载中，请耐心等待…',
				singleSelect:true,
				rownumbers:true,
				//列内容
				columns:[[
				    {
				    	title:'退货入库总单ID',
						field:'orderId',
						width:150,
						hidden: false
				    },{
						title:'单据生成日期',
						field:'createDate',
						width:170,
						sortable: false
					},{
						title:'入库日期',
						field:'receivedDate',
						width:170,
						sortable: false
					},{
						title:'操作员ID',
						field:'userId',
						width:100,
						hidden: true
					},{
						title:'操作员姓名',
						field:'userName',
						width:100,
					},{
						title:'备注',
						field:'remark',
						width:100
					}
				]],
				
				//添加点击事件
				onClickRow:function(rowIndex,rowData){
					var ids = rowData.orderId;
			        $('#detailGrid').datagrid('options').url = 'saleManagement_getReturnStockInDetailGrid';
			        $('#detailGrid').datagrid('load', {orderId:ids}); 
			        editIndex = undefined;
				},
				
				toolbar:[
					{
						text:'确认商品退货',
						iconCls:'icon-remove',
						handler:function(){
							var row = $('#supplierTable').datagrid('getSelected');
							if(row == null) {
								$.messager.show({
									title: '提示信息！',
									msg: '至少选择一行记录进行修改！'
								});
								return;
							}
							stockState = row.stockState;
							
							//判断是否选择退货数量
							var rows = $('#detailGrid').datagrid('getRows');
							var returnedAmount = 0;
							var orderId = '';
							for(var i=0; i<rows.length; i++) {
								orderId = rows[i].orderId;
								if(rows[i].returnedAmount != 0) {
									returnedAmount = rows[i].returnedAmount;
								}
							}
							
							if(returnedAmount == 0) {
								$.messager.show({
									title: '提示信息！',
									msg: '必须选择退货数量后才能申请退货！'
								});
								return;
							} else {
								
								//动态设定对话框标题
								$('#addDialog').panel({
									title: '确认商品退货'
								});
								$('#orderId').val(orderId);
								$('#addDialog').dialog('open');
								currentTime = dateTimeFormatter(new Date())
								$('#createDate').datetimebox('setValue', currentTime);
							}
							
							
							
						}
					}				
				]
				
			});
			
			/**
			 * 详单表格初始化
			 */
			$('#detailGrid').datagrid({
				
				idField:'detailId',
				//ajax异步后台请求
				url: '',
				fit: true,
				//自动列间距
				fitColumns: false,
				border: false,
				//分页查询
				pagination: false,
				//加载等待提示
				loadMsg:'数据正在加载中，请耐心等待…',
				rownumbers:true,
				singleSelect:true,
				//列内容
				columns:[[
				    {
				    	title:'出库详单ID',
						field:'detailId',
						width:100,
						hidden: true
				    },{
				    	title:'出库总单ID',
						field:'orderId',
						width:150,
						hidden: true
				    },{
						title:'商品ID',
						field:'commodityId',
						width:50,
						sortable: false,
						hidden: false
					},{
						title:'商品名称',
						field:'commodityName',
						width:100
					},{
						title:'入库仓库',
						field:'storageName',
						width:100,
					},{
						title:'入库货架',
						field:'shelfName',
						width:100,
					},{
						title:'入库数量',
						field:'amount',
						width:100
					},{
						title:'剩余量',
						field:'visibleRemain',
						width:100
					},{
						title:'请选择退货数量',
						field:'returnedAmount',
						width:100,
						editor:{
							type:'numberbox',
							options:{
                                required:true
							}
						},
					},{
						field:'action',
						title:'操作',
						resizable:false,
						formatter:function(value,row,index){
							
								var s = "<a href='javascript:void(0)' onclick='saveType(this)'>确认</a>";
								var c = "<a href='javascript:void(0)' onclick='cancelType(this)'>取消</a>";
								return s+" | "+c;
							
						}
					}
				]],
				
				onClickCell: onClickCell
				
			});
			
			
			/**
			 * 表单提交按钮
			 */
			$('#saveButton').click(function(){
				if($('#addForm').form('validate')){
					
					$.ajax({
						type: 'post',
						url: 'stockManagement_saveReportBreak',
						cache: false,
						data: $('#addForm').serialize() + '&detailOrder=' + JSON.stringify($('#detailGrid').datagrid('getRows')),
						dataType: 'json',
						success: function(result) {
							$('#addDialog').dialog('close');
							if(result.success){
								//1.刷新数据表格
								$('#supplierTable').datagrid('reload');
								//2.给出提示信息
								$.messager.show ({
									title: "ok!",
									msg: result.message
								});
								//3.清楚数据表格勾选
								$('#supplierTable').datagrid('clearSelections');
								$('#addForm').form('reset');
								$('#supplierTable').datagrid('reload');
								$('#detailGrid').datagrid('loadData',{total:0,rows:[]});
								
							} else {
								$.messager.show ({
									title: "fail!",
									msg: result.message
								});
							}
						},
					});
						
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
			 * 搜索按钮
			 */
			$('#searchButton').click(function() {
				$('#supplierTable').datagrid('load', serializeForm($('#commoditySearch')));
				$('#supplierTable').datagrid('clearSelections');
				$('#detailGrid').datagrid('loadData',{total:0,rows:[]});
			});
			
			/**
			 * 清空按钮
			 */
			$('#clearButton').click(function() {
				$('#commoditySearch').form('reset');
			});
			
			/**
			 * 计量单位下拉菜单
			 */
			$('#unitCombobox').combobox({
				url:'commodityAction_getUnitList',
				editable:false,
			    valueField:'unitId',
			    textField:'unitName',
			});
			
			/**
			 * 设置datebox默认时间
			 */
			$('#beginDate').datebox({
				formatter:myformatter,
				parser:myparser,
				editable:false,
			    required:false,
			    missingMessage:'必须填写日期！'
			});
			/**
			 * 设置datebox默认时间
			 */
			$('#endDate').datebox({
				formatter:myformatter,
				parser:myparser,
				editable:false,
			    required:false,
			    missingMessage:'必须填写日期！'
			});
			
			/**
			 * 入库时间框
			 */
			$('#createDate').datetimebox({    
			    editable: false,   
			    required: true,   
			    missingMessage: '请填写入库时间',
			    showSeconds: true,   
			});
			
			/**
			 * 退货时间框
			 */
			$('#returnedDate').datetimebox({
			    editable: false,   
			    required: true,   
			    missingMessage: '请填写出库时间',
			    showSeconds: true,   
			}); 
		});
		//编辑仓库状态
		var editIndex = undefined;
		//添加修改标志
		var flag = '';
		//详单表格点击事件
		function onClickCell(index,field,value){
			if(field == 'returnedAmount' & editIndex == undefined) {
				editIndex = index;
				$(this).datagrid('selectRow', index);
				$(this).datagrid('beginEdit', index);
				var ed = $(this).datagrid('getEditor', {index:index,field:field});
				var opts = $(ed.target).numberbox('options');
				opts.min = 0;
				opts.max = $(this).datagrid('getSelected').visibleRemain;
				$(ed.target).focus();
				flag = 'edited';
			} else {
				return;
			}
			
		}
		//详单保存按钮
		function saveType(target) {
			var index = getRowIndex(target);
			if ($('#detailGrid').datagrid('validateRow', editIndex) & editIndex == index){
				var ed = $('#detailGrid').datagrid('getEditor', {index:editIndex,field:'returnedAmount'});
				$('#detailGrid').datagrid('endEdit', editIndex);
				editIndex = undefined;
				
				
			} else {
				return;
			}
			
			
		}
		//详单取消按钮
		function cancelType(target) {
			var index = getRowIndex(target);
			if ($('#detailGrid').datagrid('validateRow', editIndex) && editIndex == index){
				$('#detailGrid').datagrid('cancelEdit', editIndex);
				editIndex = undefined;
			} else {
				return;
			}
		}
		//获取当前行
		function getRowIndex(target){
			var tr = $(target).closest('tr.datagrid-row');
			return parseInt(tr.attr("datagrid-row-index"));
		}
		
		//时间格式化
		function myformatter(date){
			var y = date.getFullYear();
			var m = date.getMonth()+1;
			var d = date.getDate();
			return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		}
		//时间解析
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
		<div region="north" title="退货入库单据查询" collapsed=false style="height:80px;padding:10px">
			<form id="commoditySearch">
				开始时间：<input id="beginDate" name="beginDate" class="easyui-datebox" />&nbsp;
				结束时间：<input id="endDate" name="endDate" class="easyui-datebox" />&nbsp;
				<a id="searchButton" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="clearButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
			</form>
		</div>
		<div region="center" title="退货入库总单">
			<table id="supplierTable"></table>
		</div>
		<div region="south" title="退货入库详单" collapsed=false style="height:40%">
			<table id="detailGrid"></table>
		</div>
	</div>
	<div id="addDialog" title="确认出库时间" modal=true class="easyui-dialog"
		closed=true style="width:350px;padding:30px;">
		<form id="addForm" method="post">
			<input type="hidden" id="orderId" name="orderId" class="textbox" />
			<input type="hidden" id="userId" name="userId" class="textbox" value=${sessionScope.user.userid } />
			<div style="margin:10px;">
				<table>
					<tr height="30px">
						<td>操作员：</td>
						<td><input id="userName" name="loginName" class="easyui-textbox" value="${sessionScope.user.loginname }" data-options="editable:false" /></td>
					</tr>
					<tr height="30px">
						<td>设置创表时间：</td>
						<td><input id="createDate" name="createDate" /></td>
					</tr>
					<tr height="30px">
						<td>设置退货时间：</td>
						<td><input id="returnedDate" name="returnedDate" /></td>
					</tr>
				</table>
			</div>
			<div style="margin:10px;">
				<p style="margin:5px">备注：</p>
				<p><input name="remark" class="easyui-textbox" multiline="true"
					style="width:100%;height:100px;" /></p>
			</div>
			<div style="margin:10px;text-align:center">
				<a id="saveButton" class="easyui-linkbutton" iconCls="icon-save" style="margin-right:10px">申请退货</a>
				<a id="cancelButton" class="easyui-linkbutton" iconCls="icon-cancel" style="margin-left:10px">取消退货</a>
			</div>
			
		</form>
	</div>
</body>
</html>
