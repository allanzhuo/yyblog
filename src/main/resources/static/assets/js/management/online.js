layui.use(['form', 'table', 'element'], function () {
    var table = layui.table
        , form = layui.form
        , element = layui.element;

    element.render();
    var taskTable = table.render({
        elem: '#online-table'
        , height: 'full'
        , url: common.url.prefix + "/online/list"
        , cellMinWidth: 90
        , limit: 10
        , size: 'lg'
        , method: "post"
        , cols: [[
            {type: 'numbers'}
            ,{field: 'id', width: '20%', title: 'SessionID', align:'center'}
            , {field: 'username', width: '10%', title: '用户名', align:'center'}
            , {field: 'host', width: '12%', title: 'ip地址', align:'center'}
            , {field: 'startTimestamp', width: '16%', title: '登录时间', align:'center'}
            , {field: 'lastAccessTime', width: '16%', title: '最后访问时间', align:'center'}
            , {field: 'timeout', width: '8%', title: '过期时间', align:'center'}
            , {field: 'status', title: '状态', align: 'center', templet: function (d) {
                return '<span class="layui-badge layui-bg-blue">在线</span>';
                }
            }
            , {title: '操作', width: 120, align: 'center', toolbar: '#onlineBar'}
        ]]
        , page: true
    });
    var $ = layui.$, active = {
        reload: function () {
        	var username = $('#online-username-search');
            //执行重载
            table.reload('online-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                	username: username.val()
                }
            });
        }
    };

    $('#online-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //监听工具条
    table.on('tool(online)', function (obj) {
        var data = obj.data;
        if (obj.event === 'force') {
            layer.confirm('确认强制退出吗？', function (index) {
                common.ajax(common.url.prefix + "/online/remove/" + data.id, {}, function (json) {
                	if (json.code === common.status.ok) {
                		obj.del();
                        layer.msg('退出成功！')
                    } else {
                        layer.msg("退出失败！" + json.message);
                    }
                });
                layer.close(index);
            });
        }
    });

});