layui.use(['table', 'element'], function () {
    var table = layui.table
        , element = layui.element;
    element.render();

    table.render({
        elem: '#cate-table'
        , url: common.url.prefix + '/cate/list'
        , page: true
        , limit: 10
        , height: 'full'
        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , cols: [[
            {field: 'id', width: 150, title: 'ID', sort: true}
            , {field: 'code', title: '分类编码', edit: 'text', sort: true}
            , {field: 'name', title: '分类中文名', edit: 'text', sort: true}
            , {fixed: 'right', title: '操作', width: 178, align: 'center', toolbar: '#cateTableBar'}
        ]]
    });

    //监听单元格编辑
    table.on('edit(cate)', function (obj) {
        var value = obj.value;
        var data = obj.data;
        common.ajax(common.url.prefix + "/cate/edit", obj.data, function (json) {
            if (json.code === common.status.ok) {
                layer.msg('修改成功！<br/>' + '[ID: ' + data.id + '] 行字段更改为：' + value)
            } else {
                layer.msg("修改出错，错误信息：" + json.message);
            }
        })
    });

    //监听工具条
    table.on('tool(cate)', function (obj) {
        var data = obj.data;
        if (obj.event === 'del') {
            layer.confirm('真的删除么?', function (index) {
                common.ajax(common.url.prefix + "/cate/delete", {id: data.id}, function (json) {
                    common.okMsgHandle(json, "删除成功");
                    if (json.code === common.status.ok) obj.del();
                    layer.close(index);
                })
            });
        }
    });

    $(".layui-btn[data-type=addCate]").on("click", function () {
        var index = layer.confirm($("#addCatePage").html(), {
            title: '新增分类'
            , type: 1
            , area: '480px'
        }, function () {
            common.ajax(common.url.prefix + "/cate/add", {
                code: $("input.layui-input[name=cateCode]").val()
                , name: $("input.layui-input[name=cateName]").val()
            }, function (json) {
                common.okHandle(json, index, "cate-table");
            })
        });
    });

});







