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
    
    <title>智慧仓库管理系统</title>
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
	<style type="text/css">
		.easyui-accordion p {list-style:none;margin:0px;padding:2px}
		.easyui-accordion p a {line-height:24px;display:block;padding-left:10px}
		.easyui-accordion p a:hover{border:1px dashed #99bbe8;background:#e0ecff;color:#416aa3;cursor:pointer;}
		.icon{background:url(<%=path%>/images/tabicons.png) no-repeat;height:18px;width:18px}
		/**
		 * 库存管理模块
		 */
		.icon-instorage{background-position:-200px -80px}
		.icon-outstorage{background-position:-60px -140px}
		.icon-instock{background-position:-0px -40px}
		.icon-outstock{background-position:-100px -40px}
		.icon-warning{background-position:-60px -200px}
		.icon-break{background-position:-320px -160px}
		.icon-over{background-position:-20px -180px}
		/**
		 * 基础资料管理模块
		 */
		.icon-client{background-position:-160px -480px}
		.icon-supplier{background-position:-360px -200px}
		.icon-goods{background-position:-140px -280px}
		/**
		 * 辅助资料
		 */
		.icon-unitcategory{background-position:-340px -260px}
		.icon-goodscategory{background-position:-260px -420px}
		.icon-storage{background-position:-200px -220px}
		/**
		 * 系统管理
		 */
		.icon-user{background-position:-80px -420px}
		.icon-quit{background-position:-60px -40px}
		/**
		 * 公用图标
		 */
		.icon-right{background-position:-380px -220px}
		.icon-searchplus{background-position:-80px -260px}
		.icon-searchminus{background-position:-60px -260px}
		.icon-searchStock{background-position:-40px -100px}
		.icon-back{background-position:-220px -120px}
	</style>
	
	<script type="text/javascript">
		
		
		$(function(){
			
			$('a[title]').click(function(){
				
				var src = $(this).attr('title');
				var title = $(this).html();
				
				if($('#tt').tabs('exists', title)) {
					//选中标签
					$('#tt').tabs('select',title);
					
				} else {
					
					//添加新标签
					$('#tt').tabs('add',{ 
					    title:title,    
					    content:'<iframe frameborder=0 style=width:100%;height:100% src='+ src +' ></iframe>',
					    closable:true 
					});
					
				}
				
			})
			
			/**
			 * 验证是否登陆用户
			 */
			$('#loginDialog').dialog({
				closed:true,
				closable:false,
				draggable:false,
			    modal:true
			});
			$.post('index_isLogin', function(result) {
				if(result.success) {
					$('#loginDialog').dialog('close');
				} else {
					$('#loginDialog').dialog('open');
				}
			},'json');
			
			/**
			 * 登陆表单提交按钮
			 */
			$('#loginButton').click(function(){
				login();
			});
			
			/**
			 * 添加回车事件
			 */
			$('#password').textbox('textbox').keydown(function (e) {
                if (e.keyCode == 13) {
                   login();
                }
            });
			
			
			
		});
		
		/**
		 * 登陆验证
		 */
		function login() {
			$.ajax({
				type: 'post',
				url: 'userAction_login',
				cache: false,
				data: $('#loginForm').serialize(),
				dataType: 'json',
				success: function(result) {
					
					if(result.success){
						$.messager.show ({
							title: "ok!",
							msg: result.message
						});
						$('#loginDialog').dialog('close');
					} else {
						$.messager.show ({
							title: "fail!",
							msg: result.message
						});
					}
					
				}
			});
		}
		
		
		// add a new tab panel    
		$('#tt').tabs('add',{    
		    title:'New Tab',    
		    content:'Tab Body',    
		    closable:true,    
		    tools:[{    
		        iconCls:'icon-mini-refresh',    
		        handler:function(){    
		            alert('refresh');    
		        }    
		    }]    
		});
		
		  
	</script>
  </head>
  
  <body class="easyui-layout">   
  	<!-- 顶部标题 -->
    
     
    <!-- 下部主体内容 -->
    <div border="true" data-options="region:'center'">
        <div border="false" class="easyui-layout" data-options="fit:true">
        	<!-- 左侧导航 -->
			<div border="true" data-options="region:'west',title:'功能导航'"
				split="true" style="width:200px">
				<div id="aa" border="false" class="easyui-accordion" fit=true>
					<div title="库存管理" class="Div10">
						<p>
							<a title="purchaseAction_addPage"><span class="icon icon-searchStock">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询当前库存</a>
						</p>
						<p>
							<a title="stockManagement_stockWarningPage"><span class="icon icon-warning">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>库存报警</a>
						</p>
						<p>
							<a title="stockManagement_reportBreakPage"><span class="icon icon-break">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>商品报损</a>
						</p>
						<p>
							<a title="stockManagement_reportBreakStockOutPage"><span class="icon icon-over">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>报损出库</a>
						</p>
						<p>
							<a title="stockManagement_reportBreakQueryPage"><span class="icon icon-searchminus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询报损退货单据</a>
						</p>
						<p>
							<a title="stockManagement_reportBreakStockOutQueryPage"><span class="icon icon-searchminus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询报损出库单据</a>
						</p>
					</div>
					<div title="销售管理" style="overflow:auto;padding:10px;">
						<p>
							<a title="stockAction_stockQueryPage"><span class="icon icon-searchStock">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询当前库存</a>
						</p>
						<p>
							<a title="saleManagement_saleAddPage"><span class="icon icon-outstorage">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>商品销售</a>
						</p>
						<p>
							<a title="saleManagement_saleReturnPage"><span class="icon icon-back">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>客户退货</a>
						</p>
						<p>
							<a title="saleManagement_saleStockOutPage"><span class="icon icon-outstock">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>销售出库</a>
						</p>
						<p>
							<a title="saleManagement_returnStockInPage"><span class="icon icon-instock">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>退货入库</a>
						</p>
						<p>
							<a title="saleManagement_saleQueryPage"><span class="icon icon-searchminus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询销售单据</a>
						</p>
						<p>
							<a title="saleManagement_saleReturnQueryPage"><span class="icon icon-searchminus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询退货单据</a>
						</p>
						<p>
							<a title="saleManagement_saleStockOutQueryPage"><span class="icon icon-searchminus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询出库单据</a>
						</p>
						<p>
							<a title="saleManagement_returnStockInQueryPage"><span class="icon icon-searchplus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询入库单据</a>
						</p>
					</div>
					<div title="进货管理" class="div10">
						<p>
							<a title="stockAction_stockQueryPage"><span class="icon icon-searchStock">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询当前库存</a>
						</p>
						<p>
							<a title="purchaseAction_addPage"><span class="icon icon-instorage">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>商品进货</a>
						</p>
						<p>
							<a title="returnedAction_managementPage"><span class="icon icon-back">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>商品退货</a>
						</p>
						<p>
							<a title="inStockAction_managementPage"><span class="icon icon-instock">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>入库管理</a>
						</p>
						<p>
							<a title="purchaseManagement_purchaseReturnStockOutPage"><span class="icon icon-outstock">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>退货出库</a>
						</p>
						<p>
							<a title="purchaseAction_queryPage"><span class="icon icon icon-searchplus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询进货单据</a>
						</p>
						<p>
							<a title="returnedAction_purchaseReturnQueryPage"><span class="icon icon-searchminus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询退货单据</a>
						</p>
						<p>
							<a title="inStockAction_queryPage"><span class="icon icon icon-searchplus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询入库单据</a>
						</p>
						<p>
							<a title="returnedAction_returnStockOutQueryPage"><span class="icon icon icon-searchminus">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>查询出库单据</a>
						</p>
					</div>
					<div title="基础资料管理" style="overflow:auto;padding:10px;">
						<p>
							<a title="clientAction_managementPage"><span class="icon icon-client">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>客户管理</a>
						</p>
						<p>
							<a title="supplierAction_managementPage"><span class="icon icon-supplier">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>供应商管理</a>
						</p>
						<p>
							<a title="commodityAction_managementPage"><span
								class="icon icon-goods">&nbsp;&nbsp;&nbsp;&nbsp; </span>商品管理</a>
						</p>
					</div>
					<div title="辅助资料管理" style="overflow:auto;padding:10px;">
						<p>
							<a title="cmdtCtgrAction_managementPage"><span class="icon icon-goodscategory">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>商品类别</a>
						</p>
						<p>
							<a title="unitAction_managementPage"><span
								class="icon icon-unitcategory">&nbsp;&nbsp;&nbsp;&nbsp; </span>计量单位</a>
						</p>
						<p>
							<a title="index_storageManagementPage"><span
								class="icon icon-storage">&nbsp;&nbsp;&nbsp;&nbsp; </span>仓库信息</a>
						</p>
					</div>
					<div title="系统管理">
						<p>
							<a title="index_userManagementPage"><span class="icon icon-user">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>用户管理</a>
						</p>
						<p>
							<a href="index_logout"><span class="icon icon-quit">&nbsp;&nbsp;&nbsp;&nbsp;
							</span>退出登陆</a>
						</p>
					</div>
				</div>

			</div>

			<!-- 中心主体内容 -->
            <div border="false" data-options="region:'center',title:'欢迎使用智慧仓库管理系统'">
            	<!-- 标签 -->
				<div id="tt" class="easyui-tabs" fit=true>
					<div title="首页">
						<iframe frameborder=0 style=width:100%;height:100% src='stockAction_stockQueryPage' ></iframe>
					</div>
				</div>
			</div>
            
            <!-- 下部信息 -->
			<div border="false" region="south"
				style="text-align:center; background:#fafafa;font-weight:bold">Copyright
				&copy; 2015 NCUT All Rights Reserved. 京ICP证1000001号</div>
		</div>
    </div> 
    <div id="loginDialog" title="登陆系统" style="width:400px;padding:30px 70px 20px 70px">
		<form id="loginForm" name="loginForm" action="userAction_login" method="post">
			<div style="margin-bottom:10px">
				<input name="loginname" class="easyui-textbox" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入用户名',iconCls:'icon-man',iconWidth:38">
			</div>
			<div style="margin-bottom:20px">
				<input id="password" name="password" class="easyui-textbox" type="password" style="width:100%;height:40px;padding:12px" data-options="prompt:'请输入密码',iconCls:'icon-lock',iconWidth:38">
			</div>
			<div style="margin-bottom:20px">
				<input type="checkbox" checked="checked">
				<span>记住密码</span>
			</div>
		
			<div>
				<a id="loginButton" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:100%;">
					<span style="font-size:14px;">登陆</span>
				</a>
			</div>
		</form>
	</div>
    
  </body>  

</html>
