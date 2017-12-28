$(function(){
	initDataGrid('userTable','users/allUsers',400);
});
/**
 * 新增
 * @returns
 */
function Add(){
	$('#dlg').dialog('open').dialog('setTitle','新建用户');
	$('#fm').form('clear');
	$('#userId').attr('disabled','disabled');
	url = "users/addUser";
}
/**
 * 修改
 * @returns
 */
function Modify(){
	var row = $('#userTable').datagrid('getSelected');
	if(row){
		$('#dlg').dialog('open').dialog('setTitle','修改用户信息');
		$('#userId').val(row.id);
		$('#userId').attr('disabled',false);
		$('#fm').form('load',row);
		url = 'users/updateUser';
	}
}
/**
 * 删除
 * @returns
 */
function Delete(){
	finalDelete('userTable',"users/deleteU");
}
/**
 * 保存
 * @returns
 */
function saveUser(){
	dataGridSave('fm','userTable','dlg');
}
function fmtOperate(value,row){
	return "<a href='#' onclick='callJoin("+row.id+");'>关联</a>";
}
function callJoin(Id){
	JoinTable("joinDlg","joinRole",'users/getRoleByUserId?userId='+Id,'roles/getAllRoles',"设置角色");
}
function joinSave(){
	var row = $('#userTable').datagrid('getSelected');
	updateJoinTable(row.id,'users/updateRoleListForUser',"joinDlg");
}
