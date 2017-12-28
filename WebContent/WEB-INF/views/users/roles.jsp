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
<script type="text/javascript" src="js/sys/roleInfo.js"></script>
<script type="text/javascript" src="js/common/datagrid.js"></script>
<script type="text/javascript" src="js/common/base.js"></script>
<script type="text/javascript" src="js/common/messageBox.js"></script>

<title>角色管理页面</title>
</head>
<body>
	<h1>角色管理页面</h1>
	<table id="roleTable" class="easyui-datagrid">
		<thead>
			<tr>
				<th data-options="field:'id'" hidden="true">角色Id</th>
				<th data-options="field:'roleName',width:100">角色名</th>
				<th data-options="field:'serial',width:60">序列号</th>
				<th data-options="field:'creator',width:100">创建者</th>
				<th data-options="field:'createTime',width:100,formatter:fmtTime">创建时间</th>
				<th data-options="field:'updator',width:80">更新者</th>
				<th data-options="field:'updateTime',width:100,formatter:fmtTime">更新时间</th>
				<th data-options="field:'join',formatter:fmtJoinUser">操作</th>
			</tr>
		</thead>
	</table>
	<div id="dlg" class="easyui-dialog" style="width:400px;height:280px;padding: 10px 20px"
		closed="true" buttons="#dlg-buttons">
		<div class="ftitle">角色基本信息</div>
		<form id="fm" method="post" novalidate>
			<input name="Id" id="roleId" hidden="true" />
			<div class="fitem">
				<label>角色名:</label>
				<input name="roleName" class="easyui-textbox" required="true">
			</div>
			<div class="fitem">
				<label>排序号:</label>
				<input name="serial" class="easyui-numberbox" required="true">
			</div>
		</form>
	</div>
	<div id="userDlg" class="easyui-dialog" style="width:800px;height:600px;padding: 10px 20px"
		closed="true" buttons="#join-buttons">
		<table id="joinUser" class="easyui-datagrid" data-options="selectOnCheck:true,singleSelect:false">
			<thead>
				<tr>
					<th data-options="field:'ck',checkbox:true"></th>
					<th data-options="field:'id'" hidden="true">用户Id</th>
					<th data-options="field:'userName',width:100">用户名</th>
				</tr>
			</thead>
		</table>
	</div>
	<div id="join-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
			onclick="joinSave()" style="width:90px;">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#userDlg').dialog('close')">取消</a>
	</div>
	<div id="dlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6" iconCls="icon-ok"
			onclick="save()" style="width:90px;">Save</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
			onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
</html>