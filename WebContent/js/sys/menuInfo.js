var allMenuUrl = "menus/getAllMenus";
$(function(){
	initTreeGrid("menuTable",allMenuUrl,600);
	loadSupMenu();
});
function loadSupMenu(){
	$('#supMenuID').combotree({
		url:allMenuUrl
	});
}
function Add(){
	$('#dlg').dialog('open').dialog('setTitle','新建菜单');
	$('#fm').form('clear');
	$('#menuId').attr('disabled','disabled');
	url = "menus/addMenu";
}
function Modify(){	
	var row = $('#menuTable').datagrid('getSelected');
	if(row){
		$('#dlg').dialog('open').dialog('setTitle','修改菜单信息');
		$('#menuId').attr('disabled',false);
		$('#menuId').val(row.id);
		$('#fm').form('load',row);
		loadSupMenu();
		$('#supMenuID').combotree('setValue',row.supMenuId);
		url = 'menus/updateMenu';
	}
}

function save(){
	finalSave('fm','menuTable','dlg');
}

function Delete(){
	finalDelete('menuTable','menus/deleteMenu');
}

/**
 * 执行关联操作
 * @param value
 * @param row
 * @returns
 */
function fmtJoinButton(value,row){
	if(row.children.length<=0){
		return "<a href='#' onclick='callJoin("+row.id+");'>关联</a>";
	}else{
		return "";
	}
	
}
function callJoin(Id){
	JoinTable("btnDlg","joinBtn",'menus/getBtnsByMenuId?Id='+Id,'btns/getAllBtns',"关联按钮");
}
function joinSave(){
	var row = $('#menuTable').datagrid('getSelected');
	updateJoinTable(row.id,'menus/updateBtnListForMenu',"btnDlg");
}
