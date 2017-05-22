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
				url: 'checklist_subDataGrid?alphaName=${requestScope.alphaName}',
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
						formatter: function(value) {
							
							return '<a href="checklist">'+value+'</a>';
							
						}
				    },{
						title:'アルファ',
						field:'group2Name',
						width:100
					},{
						title:'',
						field:'order',
						width:50,
						formatter:function(value,row,index){
							
							index += 1;
							var rows = $('#checklist').datagrid('getRows');
							rows = rows.length;
							return index + '/' + rows;
						}
					},{
						title:'状態',
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
						title:'判断根拠',
						field:'comment',
						width:100,
						editor:{
							type:'textarea',

						}
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
						text:'ダッシュボードへ',
						iconCls:'icon-back',
						handler:function(){
							window.location.href = "dashboard";
						}
					},'-',{
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
						text:'項目を削除する',
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
					}
				]

			}).datagrid('enableCellEditing');

			/**
			 * 表单提交按钮
			 */
			$('#saveButton').click(function(){
				if($('#addForm').form('validate')){
					$.ajax({
						type: 'post',
						url: flag=='add'? 'commodityAction_add' : 'commodityAction_update',
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
								$('#commodityTable').datagrid('reload');
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


		});

		//合并单元格
		function merge(data) {
		    console.log(data);
		    var rows = data.rows;
		    var set = new Set();
		    var merges = [];
		    var merge = new Object();
		    //存放不同列的分组
		    var map = new Map();
		    var flg = 0;
		    //根据group3(Status)进行跨行分组 可以将这个逻辑抽出为方法复用，传递字符串（例如rows.group3Name）返回merges存入map
		    for (var i = 0; i < rows.length; i++) {

		        if (set.has(rows[i].group3Name) == false) {
		            //用Set寻找相同名字的Item，以此进行分组
		            //如果set内没有对应item代表该为第一个，或相同的item已经分组完毕，set可清空
		            flg++;
		            set.clear();
		            set.add(rows[i].group3Name);

		            //当flg等于2代表扫描完了前面所有相同的item，进入了新的与前面不同的item
		            if (flg == 2) {
		                //用现在所在行i减去第一次进入该if时设定的index可知道cell跨了多少行
		                merge.rowspan = i - merge.index;
		                merges[merges.length] = merge;
		                flg = 1;
		            }
		            merge = new Object();
		            merge.index = i;
		        }

		        //如果是最后一个元素且名字和上一个item一样则加入到上一个item组中
		        if (i == rows.length - 1 && set.has(rows[i].group3Name)) {
		            merge.rowspan = rows.length - merge.index;
		            merges[merges.length] = merge;
		        }

		    }

		    console.log(merges);
		    //合并达成率
		    for (var i = 0; i < merges.length; i++)
		        $('#checklist').datagrid('mergeCells', {
		            index: merges[i].index,
		            field: 'group3Percentage',
		            rowspan: merges[i].rowspan
		        });
		    //合并Status3
		    for (var i = 0; i < merges.length; i++)
		        $('#checklist').datagrid('mergeCells', {
		            index: merges[i].index,
		            field: 'group3Name',
		            rowspan: merges[i].rowspan
		        });
		    //合并排序
		    for (var i = 0; i < merges.length; i++)
		        $('#checklist').datagrid('mergeCells', {
		            index: merges[i].index,
		            field: 'order',
		            rowspan: merges[i].rowspan
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
