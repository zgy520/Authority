$(function(){
	initDataGrid('btnTable','btns/getAllBtnsByPaging',400);
});

function Add(){
	$('#dlg').dialog('open').dialog('setTitle','新建按钮');
	$('#fm').form('clear');
	$('#btnId').attr('disabled','disabled');
	url = "btns/addBtn";
}
function Modify(){
	var row = $('#btnTable').datagrid('getSelected');
	if(row){
		$('#dlg').dialog('open').dialog('setTitle','修改按钮信息');
		$('#btnId').attr('disabled',false);
		$('#btnId').val(row.id);
		$('#fm').form('load',row);
		url = 'btns/updateBtn';
	}
}

function Delete(){
	finalDelete('btnTable',"btns/deleteBtn");
}

function save(){
	dataGridSave('fm','btnTable','dlg');
}


/**
 * 格式化操作按钮（关联菜单）
 * @param value
 * @param rows
 * @returns
 */
function fmtJoinMenu(value,row){
	return "<a href='#' onclick='callJoin("+row.id+");'>关联菜单</a>";
}
function callJoin(btnId){
	JoinTreeTable("menuDlg","joinMenu",'btns/getMenusByBtnId?Id='+btnId,'menus/getAllMenus',"关联菜单");
}
function joinSave(){
	var row = $('#btnTable').datagrid('getSelected');
	updateJoinTable(row.id,'btns/updateMenuListForBtn',"menuDlg");
}