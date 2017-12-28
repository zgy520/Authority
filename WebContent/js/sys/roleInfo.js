$(function(){
	initDataGrid('roleTable','roles/allRoles',400);
});

function Add(){
	$('#dlg').dialog('open').dialog('setTitle','新建角色');
	$('#fm').form('clear');
	$('#roleId').attr('disabled','disabled');
	url = "roles/addRole";
}
function Modify(){
	var row = $('#roleTable').datagrid('getSelected');
	if(row){
		$('#dlg').dialog('open').dialog('setTitle','修改角色信息');
		$('#roleId').val(row.id);
		$('#roleId').attr('disabled',false);
		$('#fm').form('load',row);
		url = 'roles/updateRole';
	}
}
function Delete(){
	finalDelete('roleTable',"roles/deleteRole");
}

function save(){
	dataGridSave('fm','roleTable','dlg');
}
function fmtJoinUser(value,row){
	return "<a href='#' onclick='callJoin("+row.id+");'>关联用户</a>";
}
function callJoin(roleId){
	JoinTable("userDlg","joinUser",'roles/getUsersByRoleId?roleId='+roleId,'users/getAllUsers',"关联用户");
}
function joinSave(){
	var row = $('#roleTable').datagrid('getSelected');
	updateJoinTable(row.id,'roles/updateUserListForRole',"userDlg");
}