layui.use(['form', 'layer', 'table', 'element'], function () {
    var table = layui.table
        , element = layui.element
        , layer = layui.layer
        , form = layui.form;
    element.render();

    var commentTable = table.render({
        elem: '#comment-table'
        , url: common.url.prefix + '/comment/list'
        , page: true
        , limit: 10
        , height: 'full'
        , method: 'post'
        , cols: [[
            {field: 'nickname', title: '用户昵称'}
            , {field: 'articleTitle', title: '文章标题', width: '20%'}
            , {
                field: 'comment', title: '评论内容', event: 'detail', templet: function (d) {
                    return d.comment.replace(/<[^<>]+?>/g, '').replace(/(\s| )+/g, ' ');
                }, width: '40%'
            }
            , {
                field: 'post', title: '发布时间', sort: true, templet: function (d) {
                    return common.dateFormatter(d.post);
                }
            }
            , {field: 'ipAddr', title: 'IP地址'}
            , {field: 'ipCnAddr', title: 'IP具体地址'}
            , {field: 'userAgent', title: '用户代理'}
            , {title: '状态', width: 90, align: 'center', toolbar: '#enableTpl'}
        ]]
    });


    form.on('switch(enable)', function (obj) {
        common.ajax(common.url.prefix + "/comment/edit/enable", {id: this.value, enable: obj.elem.checked}, function (json) {
            common.okMsgHandle(json);
            layer.tips('状态：' + ((obj.elem.checked) ? "正常" : "隐藏"), obj.othis);
        });
    });

    //监听单元格事件
    table.on('tool(comment)', function (obj) {
        var data = obj.data;
        if (obj.event === 'detail') {
            layer.open({
                type: 1
                , offset: 'auto'
                , id: 'layerDemo' + data.id //防止重复弹出
                , content: '<div style="padding: 20px;">' + data.comment.replace(/<[^<>]+?>/g, '') + '</div>'
                , btnAlign: 'c' //按钮居中
                , shade: 0 //不显示遮罩
            });
        }
    });

    table.on('sort(comment)', function (obj) {
        commentTable.reload({
            initSort: obj
            , where: {
                sort: obj.field
                , order: obj.type
            }
        });
    });

    var $ = layui.$, active = {
        reload: function () {
            var titleSearch = $('#article-title-search');
            var nickname = $("#nickname-search");
            var comment = $("#comment-search");
            //执行重载
            table.reload('comment-table', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    articleTitle: titleSearch.val()
                    , nickname: nickname.val()
                    , comment: comment.val()
                }
            });
        }
    };

    $('#comment-table-search').find('.layui-btn').on('click', function () {
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });
});







