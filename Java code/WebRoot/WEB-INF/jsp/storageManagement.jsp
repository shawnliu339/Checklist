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
    
    <title>商品分类管理</title>
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
	var grid;
	var flag;
	$(function(){
		grid = $("#dgtype").datagrid({
			url:'storageAction_getTotalGrid',
			border:false,
			fit:true,
			idField:'storageId',
			pagination:true,
			pageSize:5,
			pageList:[5,15,25],
			rownumbers:true,
			checkOnSelect:false,
			selectOnCheck:false,
			singleSelect:true,
			columns:[[{
				field:'storageId',
				title:'仓库编号',
				width:100,
				resizable:false
			},{
				field:'storageName',
				title:'仓库名称',
				width:150,
				resizable:false,
				editor:{
					type:'validatebox',
					options:{
						required:true
					}
				}
			},{
				field:'address',
				title:'仓库地址',
				width:150,
				resizable:false,
				editor:{
					type:'textbox',
					options:{
						valueField:'cid',
						textField:'cname',
                        url:'cmdtCtgrAction_getCategoryList',
                        required:true
					}
				},
			},{
				field:'action',
				title:'操作',
				resizable:false,
				formatter:function(value,row,index){
					if(row.editing){
						var s = "<a href='javascript:void(0)' onclick='saveType(this)'>保存</a>";
						var c = "<a href='javascript:void(0)' onclick='cancelType(this)'>取消</a>"
						return s+" | "+c;
					}else{
						var e = "<a href='javascript:void(0)' onclick='editType(this)'>修改</a>";
						var d = "<a href='javascript:void(0)' onclick='delType(this)'>删除</a>"
						return e+" | "+d;
					}
				}
			}]],
			onBeforeEdit:function(index,row){
				row.editing = true;								
				updateAction(index);
			},
			onAfterEdit:function(index,row){
				row.editing = false;
				updateAction(index);
			},
			onCancelEdit:function(index,row){
				row.editing = false;
				updateAction(index);
			},
			toolbar:[{
				text:'新增分类',
				iconCls:'icon-add',
				handler:addType
			}]
		})
	})
	
	function updateAction(index){
		$("#dgtype").datagrid('updateRow',{
			index:index,
			row:{}
		})
	}
	
	//获取当前行
	function getRowIndex(target){
		var tr = $(target).closest('tr.datagrid-row');
		return parseInt(tr.attr("datagrid-row-index"));
	}
	
	
	//新增分类
	function addType(){
		var index=0;
		$("#dgtype").datagrid('insertRow',{
			index:index,
			row:{
				
			}
		});
		flag = "add";
		$("#dgtype").datagrid('selectRow',index);
		$("#dgtype").datagrid('beginEdit',index);
	}
	
	//保存分类 cname,pname,
	function saveType(target){
		var rowIndex =  getRowIndex(target);
		$("#dgtype").datagrid('selectRow',rowIndex);
		var row = $("#dgtype").datagrid('getSelected');
		$("#dgtype").datagrid("endEdit", rowIndex);
		if(flag=='add') {
			$.ajax({
				async:false,
				url:'storageAction_add',
				data:{
					storageName:row.storageName,
					address:row.address
				},
				dataType:'json'
			});
			$("#dgtype").datagrid("reload");
		}
		if(flag == 'edit') {
			$("#dgtype").datagrid("endEdit",getRowIndex(target));
			var row = $("#dgtype").datagrid('getSelected');
			$.ajax({
				async:false,
				url:'storageAction_update',
				data:{
					storageId:row.storageId,
					storageName:row.storageName,
					address:row.address
				},
				dataType:'json'
			});
		} 
		flag = '';
		
	}
	
	//取消
	function cancelType(target){
		if(flag == 'edit') {
			$("#dgtype").datagrid("cancelEdit",getRowIndex(target));
		}
		if(flag == 'add') {
			$("#dgtype").datagrid("deleteRow",getRowIndex(target));
		}
	}
	
	//修改分类
	function editType(target){
		flag = "edit";
		$("#dgtype").datagrid("beginEdit",getRowIndex(target));
		
	}
	
	//删除分类
	function delType(target){
		$.messager.confirm("confirm","是否删除该单位?",function(result){
			if(result){
				var row = $("#dgtype").datagrid('getSelected');
				$.ajax({
					async:false,
					url:'storageAction_delete',
					data:{
						storageId:row.storageId,
					},
					dataType:'json'
				});
				$("#dgtype").datagrid("deleteRow",getRowIndex(target));
				flag='';
				$("#dgtype").datagrid("reload");
			}
		});
	}
</script>
</head>

<body>
<div class="easyui-layout" data-options="border:false,fit:true">
	<div data-options="region:'center'">
		<table id="dgtype" class="easyui-datgrid"></table>
	</div>
</div>
</body>
</html>
