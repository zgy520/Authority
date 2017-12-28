<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="Library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="Library/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="Library/easyui/themes/color.css">
<link rel="stylesheet" type="text/css" href="Library/easyui/demo/demo.css">
<link rel="stylesheet" type="text/css" href="css/common/common.css">
<script type="text/javascript" src="Library/jquery-3.2.1/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="Library/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/sys/menuInfo.js"></script>
<script type="text/javascript" src="js/common/treegrid.js"></script>
<script type="text/javascript" src="js/common/base.js"></script>
<script type="text/javascript" src="js/common/messageBox.js"></script>

<title>菜单管理页面</title>
</head>
<body>
	<h1>菜单管理页面</h1>
	<table id="menuTable" class="easyui-treegrid">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true">菜单Id</th>
				<th data-options="field:'supMenuId'" hidden="true">上级菜单Id</th>
				<th data-options="field:'text',width:100">菜单名称</th>
				<th data-options="field:'menuUrl',width:100">菜单URL</th>
				<th data-options="field:'iconName',width:100">图标</th>
				<th data-options="field:'serial',width:60">序列号</th>
				<th data-options="field:'creator',width:100">创建者</th>
				<th data-options="field:'createTime',width:100,formatter:fmtTime">创建时间</th>
				<th data-options="field:'updator',width:80">更新者</th>
				<th data-options="field:'updateTime',width:100,formatter:fmtTime">更新时间</th>
				<th data-options="field:'join',formatter:fmtJoinButton">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:320px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">菜单基本信息</div>
		<form id="fm" method="post" novalidate>
			<input name="Id" id="menuId" hidden="true" />
			<div class="fitem">
				<label>菜单名称:</label>
				<input name="text" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>上级菜单:</label>
				<input id="supMenuID" name="supMenuId" class="easyui-combotree">
			</div>
			<div class="fitem">
				<label>菜单地址:</label>
				<input name="menuUrl" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>图标:</label>
				<input name="iconName" class="easyui-textbox">
			</div>
			<div class="fitem">
				<label>排序号:</label>
				<input name="serial" class="easyui-numberbox" required="true">
			</div>
		</form>
	</div>
	<div id="btnDlg" class="easyui-dialog" style="width:800px;height:600px;padding: 10px 20px"
		closed="true" buttons="#join-buttons">
		<table id="joinBtn" class="easyui-datagrid" data-options="selectOnCheck:true,singleSelect:false">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id'" hidden="true">按钮Id</th>
					<th data-options="field:'btnName',width:100">按钮名称</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="join-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
			onclick="joinSave()" style="width:90px;">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#btnDlg').dialog('close')">取消</a>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
			onclick="save()" style="width:90px;">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
	
</body>
</html>