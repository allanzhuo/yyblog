layui.use(['table', 'element'], function () {
    var table = layui.table
        , element = layui.element;
    element.render();

    table.render({
        elem: '#tag-table'
        , url: common.url.prefix + '/tag/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cols: [[
            {field: 'id', width: 150, title: 'ID', sort: true}
            , {field: 'name', title: '标签名称', edit: 'text', sort: true}
            , {fixed: 'right', title: '操作', width: 178, align: 'center', toolbar: '#tagTableBar'}
        ]]
    });

    //监听单元格编辑
    table.on('edit(tag)', function (obj) {
        var value = obj.value;
        var data = obj.data;
        common.ajax(common.url.prefix + "/tag/edit", obj.data, function (json) {
            if (json.code === common.status.ok) {
                layer.msg('修改成功！<br/>' + '[ID: ' + data.id + '] 行字段更改为：' + value)
            } else {
                layer.msg("修改出错，错误信息：" + json.message);
            }
        })
    });

    //监听工具条
    table.on('tool(tag)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除么?', function (index) {
                common.ajax(common.url.prefix + "/tag/delete", {id: data.id}, function (json) {
                    common.okMsgHandle(json, "删除成功！");
                    if (json.code === common.status.ok) obj.del();
                    layer.close(index);
                })
            });
        }
    });


    var $ = layui.$, active = {
        reload: function () {
            var tagSearch = $('#tag-search');
            //执行重载
            table.reload('tag-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    name: tagSearch.val()
                }
            });
        }
    };

    $('#tag-table-search').find(".layui-btn").on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    table.on('sort(tag)', function (obj) {
        table.reload('tag-table', {
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });


});







