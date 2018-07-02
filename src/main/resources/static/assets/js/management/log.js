layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , form = layui.form
        , element = layui.element;

    element.render();
    var taskTable = table.render({
        elem: '#log-table'
        , height: 'full'
        , url: common.url.prefix + "/log/list"
        , cellMinWidth: 90
        , limit: 10
        , size: 'lg'
        , method: "post"
        , cols: [[
             {type:'checkbox'}
            ,{type: 'numbers'}
            ,{field: 'userId', width: '15%', title: '用户ID', align:'center'}
            , {field: 'username', width: '10%', title: '用户名', align:'center'}
            , {field: 'operation', width: '10%', title: '操作', align:'center'}
            , {field: 'time', width: '8%', title: '用时', align:'center'}
            , {field: 'method', width: '37%', title: '方法', align:'center'}
            , {field: 'params', width: '15%', title: '参数一', align:'center'}
            , {field: 'params2', width: '15%', title: '参数二', align:'center'}
            , {field: 'ip', width: '15%', title: 'IP地址', align:'center'}
            , {field: 'createTime', width: '17%', title: '创建时间', align:'center'}
            , {title: '操作', width: 100, align: 'center', toolbar: '#logBar'}
        ]]
        , page: true
    });
    var $ = layui.$, active = {
        reload: function () {
        	var username = $('#log-username-search');
            var operation = $('#log-operation-search');
            //执行重载
            table.reload('log-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                	username: username.val(),
                	operation: operation.val()
                }
            });
        },
    	removeBatch: function () {
    		var checkStatus = table.checkStatus('log-table');
    		var data = checkStatus.data;
    		if (data.length == 0) {
    	        layer.msg("请选择要删除的数据");
    	        return;
    	    }
    		layer.confirm("确认要删除选中的【" + data.length + "】条数据吗?", function (index) {
    			 var ids = new Array();
    		     // 遍历所有选择的行数据，取每条数据对应的ID
    			 for (var i = 0; i < data.length; i++) {
    				 ids[i] = data[i].id;
				 }
                 common.ajax(common.url.prefix + "/log/removeBatch", {ids: ids}, function (json) {
                 	if (json.code === common.status.ok) {
                         layer.msg('删除成功！');
                         active.reload();
                     } else {
                         layer.msg("删除失败！" + json.message);
                     }
                 });
                 layer.close(index);
             });
        }
    };

    $('#log-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听工具条
    table.on('tool(log)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('确认删除吗？', function (index) {
                common.ajax(common.url.prefix + "/log/remove/" + data.id, {}, function (json) {
                	if (json.code === common.status.ok) {
                		obj.del();
                        layer.msg('删除成功！')
                    } else {
                        layer.msg("删除失败！" + json.message);
                    }
                });
                layer.close(index);
            });
        }
    });

});