/**
 * 初始化datagrid列表
 * @param datagridId  table为easyui-datagrid的的id
 * @param url 获取数据的url
 * @param height 列表的高度
 * @returns
 */
function initDataGrid(datagridId,url,height){
	$('#'+datagridId).datagrid({
		url:url,
		method:'GET',
		rownumbers:true,
		pagination:true,
		height:height,
		singleSelect:true,
		striped:true,
		fitColumns:true,
		toolbar:toolbar
	});
}
/**
 * 新增和编辑的form表单提交入口
 * @param fm
 * @param dataGridId
 * @param dlg
 * @returns
 */
function dataGridSave(fm,dataGridId,dlg){
	$("#"+fm).form('submit',{
		url:url,
		onSubmit:function(){
			return $(this).form('validate');
		},
		success:function(result){
			result = JSON.parse(result);
			if (result.code) {
				$('#'+dataGridId).datagrid('reload');
				$('#'+dlg).dialog('close');
				tipBox.Info("提示",result.msg);
			}else{
				tipBox.Error('错误',result.detailMsg);
			}
		}
	});
}
/**
 * 根据选中的行删除相应的项
 * @param treeGridId
 * @param url
 * @returns
 */
function finalDelete(dataGridId,url){
	var row = $('#'+dataGridId).datagrid('getSelected');
	if(row){
		$.ajax({
			url : url,
			method:'post',
			data:{Id:row.id}
		}).done(function(result){
			try{
				result = JSON.parse(result);
			}catch(e){
				result = result;
			}finally{
				if(result.code){
					$('#'+dataGridId).datagrid('reload');
					tipBox.Info('信息',result.msg);
				}else{
					tipBox.Error('错误',result.detailMsg);
				}
			}			
		});
	}
}