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

    <title>チェックリスト一覧</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/datagrid-cellediting.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/json2.js"></script>

	<link rel="stylesheet" type="text/css" href="css/common2.css" />
	<script type="text/javascript" src="js/commons.js"></script>
	<style type="text/css">
		.datagrid-row {
		  height: 35px;
		}
		.datagrid-body td{
			border-style:solid;
			border-color:black;
        }
	</style>
	<script type="text/javascript">
		$(function(){

			//添加修改标志
			var flag = '';
			//搜索框展开标志
			var searchStatus = 0;
			/**
			 * 表格初始化
			 */
			$('#checklist').datagrid({

				idField:'group1Id',
				//ajax异步后台请求
				url: 'checklist_dataGrid',
				fit: true,
				//自动列间距
				border: true,
				//加载等待提示
				loadMsg:'数据正在加载中，请耐心等待…',
				singleSelect: true,
				striped:true,
				nowrap:false,
				//列内容
				columns:[[
				    {
				    	title:'主キー',
						field:'group1ID',
						width:100,
						hidden:true
				    },{
				    	title:'関心領域',
						field:'group1Name',
						width:100,
				    },{
						title:'達成度',
						field:'group1Percentage',
						width:50
					},{
						title:'アルファ',
						field:'group2Name',
						width:100,
						formatter: function(value) {
							
							return '<a href="checklist_goSubChecklist">'+value+'</a>';
							
						}
					},{
						title:'達成度',
						field:'group2Percentage',
						width:50
					},{
						title:'ステータス',
						field:'group3Name',
						width:100
					},{
						title:'達成度',
						field:'group3Percentage',
						width:50
					},{
						title:'チェック項目ID',
						field:'checkitemId',
						width:100,
						hidden:true
					},{
						title:'チェック項目',
						field:'checkitemContent',
						width:100
					},{
						title:'補足説明',
						field:'description',
						width:100,
						editor:{
							type:'textarea',

						}
					},{
						title:'成果物',
						field:'typicalDeliverables',
						width:100,
						editor:{
							type:'textarea',

						}
					},{
				    	title:'チェック項目の状態のID',
						field:'checkitemStatusId',
						width:100,
						hidden:true
				    },{
						title:'ステータス',
						field:'status',
						width:100,
						formatter:function(value){

							var text = '保留';
							if(value==1)
								text = '保留';
							if(value==2)
								text = '対象外';
							if(value==3)
								text = '未着手';
							if(value==4)
								text = '対応中';
							if(value==5)
								text = '完了';
							return text;
						},
						editor:{
							type:'combobox',
							options:{
								valueField: 'value',
								textField: 'text',

								data: [{
									value: '1',
									text: '保留'
								},{
									value: '2',
									text: '対象外'
								},{
									value: '3',
									text: '未着手'
								},{
									value: '4',
									text: '対応中'
								},{
									value: '5',
									text: '完了'
								}]

							}
						}
					},{
						title:'不遵守事項有無',
						field:'problem',
						width:100,
						formatter:function(value){

							var text = 'NO';
							if(value==1)
								text = 'YES';
							if(value==0)
								text = 'NO';
							return text;
						},
						editor:{
							type:'combobox',
							options:{
								valueField: 'value',
								textField: 'text',
								data: [{
									value: '1',
									text: 'YES'
								},{
									value: '0',
									text: 'NO'
								}]
							}
						}
					},{
						title:'コメント記入',
						field:'comment',
						width:100,
						editor:{
							type:'textarea',

						}
					},{
						title:'prjタイプ',
						field:'prjtype',
						width:100
					},{
						title:'重要度',
						field:'importance',
						width:270
					}
				]],
				
				onLoadSuccess:function(data){
					merge(data);
				},
				
				onAfterEdit: function() {
					var data = $('#checklist').datagrid('getData');
					merge(data);
				},


				//增加工具栏，添加增删改查按钮
				toolbar:[
					{
						text:'チェックリスト登録',
						iconCls:'icon-save',
						handler:function(){
							var cell = $(checklist).datagrid('cell');
							$(checklist).datagrid('endEdit',cell.index);
							var rows = $(checklist).datagrid('getRows');

							$.post('checklist_update', {rows:JSON.stringify(rows)}, function(result){
								if(result){
									
									$.messager.show ({
										title: 'ok!',
										msg: '登録成功！'
									});
									$('#checklist').datagrid('reload');
									
								} else {
									$.messager.show ({
										title: 'fail!',
										msg: '登録失敗！'
									});
								}
							})

						}
					},'-',{
						text:'删除商品信息',
						iconCls:'icon-remove',
						handler:function(){

							var arr = $('#commodityTable').datagrid('getSelections');
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
											ids += arr[i].commodityId + ',';
										}
										ids = ids.substring(0, ids.length-1);
										$.post('commodityAction_delete', {ids:ids}, function(result){
											if(result){
												//1.刷新数据表格
												$('#commodityTable').datagrid('reload');
												//2.给出提示信息
												$.messager.show ({
													title: 'ok!',
													msg: '商品信息删除成功！'
												});
												//3.清楚数据表格勾选
												$('#commodityTable').datagrid('clearSelections');

											} else {
												$.messager.show ({
													title: 'fail!',
													msg: '商品信息删除失败！'
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
						text:'修改商品信息',
						iconCls:'icon-edit',
						handler:function(){

							//标志位修改
							flag = 'edit';
							//动态设定对话框标题
							$('#addDialog').dialog({
								title: '修改商品信息'
							});
							var arr = $('#commodityTable').datagrid('getSelections');
							if(arr.length != 1) {
								$.messager.show({
									title: '提示信息！',
									msg: '只能选择一行记录进行修改！'
								});
							} else {
								$('#addDialog').dialog('open');
								$('#addForm').form('reset');
								$('#addForm').form('load',{
									commodityId: arr[0].commodityId,
									commodityName: arr[0].commodityName,
									commodityType: arr[0].commodityType,
									categoryId: arr[0].categoryId,
									unitId: arr[0].unitId,
									salePrice:arr[0].salePrice,
									vip1Price:arr[0].vip1Price,
									vip2Price:arr[0].vip2Price,
									vip3Price:arr[0].vip3Price,
									minimum:arr[0].minimum,
									remark: arr[0].remark
								});
							}

						}
					},'-',{
						text:'查询商品信息',
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

			}).datagrid('enableCellEditing');

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
		
		//合并单元格
		function merge(data){
			console.log(data);
			var rows = data.rows;
			var set = new Set();
			var merges = [];
			var merge = new Object();
			var flg = 0;
			for(var i=0; i<rows.length; i++) {
				
				if(set.has(rows[i].group3Name) == false) {
					flg++;
					set.clear();
					set.add(rows[i].group3Name);
					
					if(flg == 2) {
						merge.rowspan = i - merge.index;
						merges[merges.length] = merge;
						flg = 1;
					}
					merge = new Object();
					merge.index = i;
				}
				
			}
			//添加最后一个元素
			merge.rowspan = rows.length - merge.index;
			merges[merges.length] = merge;
			console.log(merges);
			for(var i=0; i<merges.length; i++)
				$('#checklist').datagrid('mergeCells',{
					index:merges[i].index,
					field:'group3Percentage',
					rowspan:merges[i].rowspan
				});
			for(var i=0; i<merges.length; i++)
				$('#checklist').datagrid('mergeCells',{
					index:merges[i].index,
					field:'group3Name',
					rowspan:merges[i].rowspan
				});
			
		}


	</script>


  </head>

  <body>
  	<div id="lay" class="easyui-layout" fit=true >
		<div region="center" title="チェックリスト一覧">
			<table id="checklist"></table>
		</div>
	</div>

</body>
</html>
