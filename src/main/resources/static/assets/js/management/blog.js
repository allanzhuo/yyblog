var editor;
layui.use(['element', 'form', 'layer', 'upload'], function () {
    var form = layui.form;
    var element = layui.element;
    var $ = layui.$;
    var upload = layui.upload;
    element.render();
    form.render();

    var post = function (data, draft, msg) {
        data.field.draft = draft;
        data.field.content = editor.html();
        data.field.tagName = $("input#tagName").val();
        data.field.cover = $("#coverImg").find("img").attr("src");
        $.ajax({
            type: "post"
            , url: common.url.prefix + "/blog/add"
            , dataType: "json"
            , data: data.field
            , success: function (json) {
                common.okMsgHandle(json, msg);
                if (json.code === common.status.ok) {
                    location.hash = vipspa.stringifyParam("blogs", {});
                }
            }
        });
    };
    //监听提交
    form.on('submit(postSubmit)', function (data) {
        post(data, false, "发布博文成功！");
        return false;
    });

    form.on('submit(draftSubmit)', function (data) {
        post(data, true, "保存草稿成功！");
        window.location.href = common.url.prefix + "/index#blogs";
        return false;
    });

    upload.render({
        elem: '#coverImg' //绑定元素
        , url: common.url.prefix + '/blog/upload/cover' //上传接口
        , done: function (res) {
            if (res.code === 200) {
                $("#coverImg").html('<p><img style="width: 144px;height: 90px;" src="' + res.data + '"></p>');
            }
            layer.msg(res.message);
        }
        , error: function () {
            layer.msg("上传失败！");
        }
    });

});

$(function () {

    $('input#tagName').tagsInput({
        defaultText: "点我新增标签",
        width: "auto",
        height: "32px",
        minChars: 1,
        onChange: function (a, e) {
            var length = $("input#tagName").val().split(",").length;
            if (length > 4) {
                layer.msg("最多只能4个！");
                $("#tagName").removeTag(e)
            }
        }
    });

    editor = KindEditor.create('#editor', {
        cssData: 'body {font-family: "Helvetica Neue", Helvetica, "PingFang SC", 微软雅黑, Tahoma, Arial, sans-serif; font-size: 14px}',
        width: "auto",
        minHeight: 300,
        items: [
            'source', 'preview', 'undo', 'redo', 'code', 'cut', 'copy', 'paste',
            'plainpaste', 'wordpaste', 'justifyleft', 'justifycenter', 'justifyright',
            'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
            'superscript', 'clearhtml', 'quickformat', 'selectall', 'fullscreen', '/',
            'formatblock', 'fontname', 'fontsize', 'forecolor', 'hilitecolor', 'bold',
            'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', 'image', 'graft',
            /*'flash', 'insertfile', */'table', 'hr', 'emoticons', 'pagebreak',
            'link', 'unlink', 'about'
        ],
        uploadJson: common.url.prefix + '/blog/upload',
        dialogOffset: 0, //对话框距离页面顶部的位置，默认为0居中，
        allowImageUpload: true,
        allowMediaUpload: true,
        themeType: 'black',
        fixToolBar: true,
        autoHeightMode: true,
        filePostName: 'uploadFile',//指定上传文件form名称，默认imgFile
        resizeType: 1,//可以改变高度
        afterCreate: function () {
            var self = this;
            KindEditor.ctrl(document, 13, function () {
                self.sync();
                K('form[name=example]')[0].submit();
            });
            KindEditor.ctrl(self.edit.doc, 13, function () {
                self.sync();
                KindEditor('form[name=example]')[0].submit();
            });
        },
        //错误处理 handler
        errorMsgHandler: function (message, type) {
            try {
                JDialog.msg({type: type, content: message, timer: 2000});
            } catch (Error) {
                alert(message);
            }
        }
    });

});






