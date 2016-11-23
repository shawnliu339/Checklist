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
    
    <title>入库管理</title>
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
				
				idField:'purchaseId',
				//ajax异步后台请求
				url: 'purchaseAction_getDatagrid',
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
				    	title:'进购订单ID',
						field:'purchaseId',
						width:150,
						hidden: false
				    },{
						title:'订单生成日期',
						field:'createDate',
						width:100,
						sortable: false
					},{
						title:'应付金额',
						field:'payablePrice',
						width:100
					},{
						title:'实付金额',
						field:'realPrice',
						width:100
					},{
						title:'是否付款',
						field:'state',
						width:100,
						formatter: function(value,row,index){
							if (row.state == 1){
								return '已付款';
							} else {
								return '未付款';
							}
						}

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
					var ids = rowData.purchaseId;
			        $('#detailGrid').datagrid('options').url = 'purchaseAction_getDetailGrid';
			        $('#detailGrid').datagrid('load', {purchaseId:ids});
			        editIndex = undefined;
				},
				
				//增加工具栏，添加增删改查按钮
				toolbar:[
					{
						text:'确认商品入库',
						iconCls:'icon-add',
						handler:function(){
							
							var rows = $('#detailGrid').datagrid('getRows');
							for(var i=0; i<rows.length; i++) {
								if(rows[i].storageId == undefined) {
									$.messager.show({
										title: '提示信息！',
										msg: '必须选择仓库后才能进行入库！'
									});
									return;
								}
							}
							var arr = $('#supplierTable').datagrid('getSelections');
							if(arr.length != 1) {
								$.messager.show({
									title: '提示信息！',
									msg: '只能选择一行记录进行修改！'
								});
							} else {
								$('#addDialog').dialog('open');
								
							}
							
						}
					}
				]
				
			});
			
			/**
			 * 详单表格初始化
			 */
			$('#detailGrid').datagrid({
				
				idField:'id',
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
				    	title:'进购详单ID',
						field:'id',
						width:100,
						hidden: true
				    },{
				    	title:'进购订单ID',
						field:'purchaseId',
						width:150,
						hidden: true
				    },{
						title:'商品ID',
						field:'commodityId',
						width:50,
						sortable: false
					},{
						title:'商品名称',
						field:'commodityName',
						width:100
					},{
						title:'进购价',
						field:'price',
						width:100
					},{
						title:'进购数量',
						field:'amount',
						width:100,
					},{
						title:'退货数量',
						field:'returnedAmount',
						width:100
					},{
						title:'总价',
						field:'totalPrice',
						width:100,
					},{
						title:'请选择入库仓库',
						field:'storageId',
						width:120,
					 	formatter:function(value,row){
                            return row.storageName;
                        },
						editor:{
							type:'combobox',
							options:{
							 	valueField:'storageId',
                                textField:'storageName',
                                method:'post',
                                url:'storageAction_getStorageList',
                                required:true
							}
						}
					},{
						title:'请选择入库架位',
						field:'shelfId',
						width:120,
					 	formatter:function(value,row){
                            return row.shelfName;
                        },
						editor:{
							type:'combobox',
							options:{
							 	valueField:'shelfId',
                                textField:'shelfName',
                                method:'post',
                                url:'shelfAction_getShelfList',
                                required:true
							}
						}
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
					
					$.messager.confirm('提示信息' , '确认入库后订单将无法修改，是否确认入库？' , function(result){
						if(result) {
							var arr = $('#supplierTable').datagrid('getSelections');
							var ids = '';
							/* for(var i=0; i<arr.length; i++) {
								ids += arr[i].purchaseId + ',';
							}
							ids = ids.substring(0, ids.length-1); */
							ids += arr[0].purchaseId;
							$.ajax({
								type: 'post',
								url: 'inStockAction_purchase',
								cache: false,
								data: $('#addForm').serialize() + '&ids=' + ids + '&inStockgoods=' + JSON.stringify($('#detailGrid').datagrid('getRows')),
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
							return;
						}
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
		});
		//编辑仓库状态
		var editIndex = undefined;
		//详单表格点击事件
		function onClickCell(index,field,value){
			if((field == 'storageId' | field == 'shelfId') & editIndex == undefined) {
				editIndex = index;
				$(this).datagrid('beginEdit', index);
				var ed = $(this).datagrid('getEditor', {index:index,field:field});
				$(ed.target).focus();
			} else {
				return;
			}
			
		}
		//详单保存按钮
		function saveType(target) {
			var index = getRowIndex(target);
			if ($('#detailGrid').datagrid('validateRow', editIndex) & editIndex == index){
				var ed1 = $('#detailGrid').datagrid('getEditor', {index:editIndex,field:'storageId'});
				var ed2 = $('#detailGrid').datagrid('getEditor', {index:editIndex,field:'shelfId'});
			 	var storageName = $(ed1.target).combobox('getText');
			 	var shelfName = $(ed2.target).combobox('getText');
                $('#detailGrid').datagrid('getRows')[editIndex]['storageName'] = storageName;
                $('#detailGrid').datagrid('getRows')[editIndex]['shelfName'] = shelfName;
				$('#detailGrid').datagrid('endEdit', editIndex);
				editIndex = undefined;
				
				
			} else {
				return;
			}
			
			
		}
		//详单取消按钮
		function cancelType(target) {
			var index = getRowIndex(target);
			$('#detailGrid').datagrid('cancelEdit', editIndex);
			editIndex = undefined;
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
		<div region="north" title="进货订单查询" collapsed=false style="height:80px;padding:10px">
			<form id="commoditySearch">
				开始时间：<input id="beginDate" name="beginDate" class="easyui-datebox" />&nbsp;
				结束时间：<input id="endDate" name="endDate" class="easyui-datebox" />&nbsp;
				<a id="searchButton" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="clearButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
			</form>
		</div>
		<div region="center" title="进货订单总单">
			<table id="supplierTable"></table>
		</div>
		<div region="south" title="进货订单详单" collapsed=false style="height:40%">
			<table id="detailGrid"></table>
		</div>
	</div>
	<div id="addDialog" title="确认入库时间" modal=true class="easyui-dialog"
		closed=true style="width:350px;padding:30px;">
		<form id="addForm" method="post">
			<div style="margin:10px;text-align:center">
				设置入库时间：<input id="createDate" name="createDate" />
			</div>
			<div style="margin:10px;text-align:center">
				<a id="saveButton" class="easyui-linkbutton" iconCls="icon-save" style="margin-right:10px">入库</a>
				<a id="cancelButton" class="easyui-linkbutton" iconCls="icon-cancel" style="margin-left:10px">取消</a>
			</div>
			
		</form>
	</div>
</body>
</html>
