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
				
				idField:'clientId',
				//ajax异步后台请求
				url: 'clientAction_getDatagrid',
				fit: true,
				//自动列间距
				fitColumns: false,
				border: false,
				//分页查询
				pagination: true,
				//加载等待提示
				loadMsg:'数据正在加载中，请耐心等待…',
				//列内容
				columns:[[
				    {
				    	field:'checkbox',
				    	width:50,
				    	checkbox:true
				    },{
				    	title:'客户ID',
						field:'clientId',
						width:100,
						hidden: true
				    },{
						title:'客户名称',
						field:'clientName',
						width:100,
						sortable: true
					},{
						title:'客户等级',
						field:'level',
						width:100,
						sortable: true,
						formatter:function(value,row,index){
							if(value == 0) {
								return '普通客户';
							}
							if(value == 1) {
								return '青铜客户';
							}
							if(value == 2) {
								return '白银客户';
							}
							if(value == 3) {
								return '黄金客户';
							}
						}
					},{
						title:'联系人',
						field:'contactPeople',
						width:100
					},{
						title:'联系电话',
						field:'contactTel',
						width:100
					},{
						title:'地址',
						field:'address',
						width:100,
					},{
						title:'备注',
						field:'remark',
						width:100
					}
				]],
				
				
				//增加工具栏，添加增删改查按钮
				toolbar:[
					{
						text:'添加客户信息',
						iconCls:'icon-add',
						handler:function(){
							
							//标志为添加
							flag = 'add';
							//动态设定对话框标题
							$('#addDialog').panel({
								title: '添加客户信息'
							});
							$('#addDialog').dialog('open');
							
						}
					},'-',{
						text:'删除客户信息',
						iconCls:'icon-remove',
						handler:function(){
							
							var arr = $('#supplierTable').datagrid('getSelections');
							if(arr.length < 1) {
								$.messager.show({
									title: '提示信息！',
									msg: '至少选择一行记录进行修改！'
								});
							} else {
								
								$.messager.confirm('提示信息' , '删除后商品信息无法回复，是否确认删除？' , function(result){
									if(result) {
										
										var ids = '';
										for(var i=0; i<arr.length; i++) {
											ids += arr[i].clientId + ',';
										}
										ids = ids.substring(0, ids.length-1);
										$.post('clientAction_delete', {ids:ids}, function(result){
											if(result){
												//1.刷新数据表格
												$('#supplierTable').datagrid('reload');
												//2.给出提示信息
												$.messager.show ({
													title: 'ok!',
													msg: '客户信息删除成功！'
												});
												//3.清楚数据表格勾选
												$('#supplierTable').datagrid('clearSelections');
												
											} else {
												$.messager.show ({
													title: 'fail!',
													msg: '客户信息删除失败！'
												});
											}
										});
										
									} else {
										return;
									}
								});
								
							}
							
						}						
					},'-',{
						text:'修改客户信息',
						iconCls:'icon-edit',
						handler:function(){
							
							//标志位修改
							flag = 'edit';
							//动态设定对话框标题
							$('#addDialog').panel({
								title: '修改客户信息'
							});
							var arr = $('#supplierTable').datagrid('getSelections');
							if(arr.length != 1) {
								$.messager.show({
									title: '提示信息！',
									msg: '只能选择一行记录进行修改！'
								});
							} else {
								$('#addDialog').dialog('open');
								$('#addForm').form('reset');
								$('#addForm').form('load',{
									clientId: arr[0].clientId,
									clientName: arr[0].clientName,
									level: arr[0].level,
									contactPeople: arr[0].contactPeople,
									contactTel: arr[0].contactTel,
									address: arr[0].address,
									remark: arr[0].remark
								});
							}
							
						}						
					},'-',{
						text:'查询客户信息',
						iconCls:'icon-search',
						handler:function(){
							if(searchStatus == 0) {
								searchStatus = 1;
								$('#lay').layout('expand' , 'north');
							} else {
								searchStatus = 0;
								$('#lay').layout('collapse' , 'north');
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
					$.ajax({
						type: 'post',
						url: flag=='add'? 'clientAction_add' : 'clientAction_update',
						cache: false,
						data: $('#addForm').serialize(),
						dataType: 'json',
						success: function(result) {
							$('#addDialog').dialog('close');
							if(result.success){
								$.messager.show ({
									title: "ok!",
									msg: result.message
								});
								$('#addForm').form('reset');
								$('#supplierTable').datagrid('reload');
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
			});
			
			/**
			 * 清空按钮
			 */
			$('#clearButton').click(function() {
				$('#commoditySearch').form('reset');
			});
			
			
			
			
		});
		
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
		<div region="north" title="客户信息查询" collapsed=true style="height:100px;padding:10px">
			<form id="commoditySearch">
				根据客户名（可支持模糊查询）：<input name="clientName" class="textbox"/>&nbsp;
				<a id="searchButton" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
				<a id="clearButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">清空</a>
			</form>
		</div>
		<div region="center" title="客户信息管理">
			<table id="supplierTable"></table>
		</div>
	</div>
	<div id="addDialog" title="添加客户信息" modal=true class="easyui-dialog"
		closed=true style="width:550px;padding:30px;">
		<form id="addForm" method="post">
			<input type="hidden" name="clientId" class="textbox" />
			<div style="margin:10px">
				<table cellspacing="8px">
					<tr height="30px">
						<td>客户名称：</td>
						<td><input name="clientName" class="easyui-textbox" required=true missingMessage="请填写客户名称"  /></td>
						<td>客户等级：</td>
						<td><input name="level" class="easyui-combobox" data-options="
						valueField:'value',
						textField:'text',
						value:0,
						data:[{
							text:'普通客户',
							value:'0'
						},{
							text:'青铜客户',
							value:'1'
						},{
							text:'白银客户',
							value:'2'
						},{
							text:'黄金客户',
							value:'3'
						}]"/></td>
					</tr>
					<tr height="30px">
						<td>联系人：</td>
						<td><input name="contactPeople" class="easyui-textbox" /></td>
						<td>联系电话：</td>
						<td><input name="contactTel" class="easyui-numberbox" /></td>
					</tr>
					<tr height="30px">
						<td>地址：</td>
						<td><input name="address" class="easyui-textbox" /></td>
					</tr>
				</table>
			</div>
			<div class="clear"></div>
			<div style="margin:10px;">
				<p style="margin:5px">备注：</p>
				<p><input name="remark" class="easyui-textbox" multiline="true"
					style="width:100%;height:100px;" /></p>
			</div>
			<div style="margin:10px;text-align:center">
				<a id="saveButton" class="easyui-linkbutton" iconCls="icon-save" style="margin-right:10px">保存</a>
				<a id="cancelButton" class="easyui-linkbutton" iconCls="icon-cancel" style="margin-left:10px">取消</a>
			</div>
			
		</form>
	</div>
</body>
</html>
