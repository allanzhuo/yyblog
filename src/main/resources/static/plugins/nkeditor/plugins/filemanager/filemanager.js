/**
 * 文件服务器管理
 * @author yangjian <yangjian102621@gmail.com>
 * @since v4.1.12(2017-09-12)
 * @site http://git.oschina.net/blackfox/kindeditor
 */
KindEditor.plugin('filemanager', function(K) {
	var self = this;
	var fileManagerJson = K.undef(self.fileManagerJson, self.basePath + 'php/file_manager_json.php');
	var lang = self.lang('filemanager.');
	if(typeof jQuery == 'undefined') {
		K.options.errorMsgHandler(lang.depJQueryError, "error");
		return;
	} else {
		K.loadScript(K.options.pluginsPath+"filemanager/FManager.min.js");
		K.loadStyle(K.options.pluginsPath+"multiimage/css/upload.min.css");
	}

	self.plugin.filemanagerDialog = function(options) {

		var clickFn = options.clickFn;
		new FManager({
			list_url : fileManagerJson,	//图片列表数据获取url
			lang : lang, //语言包
			fileType : options.dirName,
			top : self.dialogOffset,
			callback : function(data) {
				//console.log(data);
				clickFn.call(this, data[0]);
			}
		});
		//return dialog;
	}

});
