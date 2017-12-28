$(function(){
	initTreeGrid("orgTable","orgs/getAllOrgs",400);
	loadSupOrg();
});
function loadSupOrg(){
	$('#supOrgID').combotree({
		url:'orgs/getAllOrgs'
	});
}
function Add(){
	$('#dlg').dialog('open').dialog('setTitle','新建部门');
	$('#fm').form('clear');
	$('#orgId').attr('disabled','disabled');
	url = "orgs/addOrg";
}
function Modify(){
	var row = $('#orgTable').datagrid('getSelected');
	if(row){
		$('#dlg').dialog('open').dialog('setTitle','修改部门信息');
		$('#orgId').attr('disabled',false);
		$('#orgId').val(row.id);
		$('#fm').form('load',row);
		loadSupOrg();
		$('#supOrgID').combotree('setValue',row.supOrgID);
		url = 'orgs/updateOrg';
	}
}

function save(){
	finalSave('fm','orgTable','dlg');
}

function Delete(){
	finalDelete('orgTable','orgs/deleteOrg');
}
/**
 * 执行关联操作
 * @param value
 * @param row
 * @returns
 */
function fmtJoinButton(value,row){
	if(row.children.length>0){
		return "";
	}
	return "<a href='#' onclick='callJoin("+row.id+");'>关联</a>";
}
function callJoin(roleId){
	JoinTable("userDlg","joinUser",'orgs/getUsersByOrgId?Id='+roleId,'users/getAllUsers',"关联用户");
}
function joinSave(){
	var row = $('#orgTable').treegrid('getSelected');
	updateJoinTable(row.id,'orgs/updateUserListForOrg',"userDlg");
}
