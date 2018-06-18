/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/

KindEditor.plugin('graft', function(K) {
	var self = this, name = 'graft',
		uploadJson = K.undef(self.uploadJson, self.basePath + 'php/upload_json.php'),
		allowUploadGraft = K.undef(self.allowUploadGraft, true),
		lang = self.lang(name + '.');

	if(typeof jQuery == 'undefined') {
		K.options.errorMsgHandler(lang.depJQueryError, "error");
		return;
	} else {
		K.loadStyle(K.options.pluginsPath+"graft/css/scrawl.css");
		K.loadScript(K.options.pluginsPath+"graft/scrawl.js");
	}

	self.plugin.graftDialog = function(options) {
		var clickFn = options.clickFn;
		var html = [
			'<div class="scrawl-main" id="scrawl-main">',
			//绘图区域
			'<div class="hot">',
			'<div class="drawBoard border_style">',
			'<canvas id="canvas-borad" class="brushBorad">你的浏览器不支持 canvas 绘图</canvas>',
			'<div class="picBoard" id="picBoard" style=""></div>',
			'</div>',
			'<div class="operateBar">',
			'<button id="J_prevStep" class="prevStep" title="上一步">',
			'<em class="icon"></em>',
			'</button>',
			'<button id="J_nextStep" class="nextStep" title="下一步">',
			'<em class="icon"></em>',
			'</button>',
			'<button id="J_clearBoard" class="clearBoard" title="清空">',
			'<em class="icon"></em>',
			'</button>',
			'</div>',
			'</div>',
			//绘图区域 end

			//工具栏
			'<div class="drawToolbar border_style">',
			'<div class="colorBar">',
			'<span data-color="#0099CC" style="background:#0099CC;" class="active"></span>',
			'<span data-color="#003366" style="background:#003366;"></span>',
			'<span data-color="#993333" style="background:#993333;"></span>',
			'<span data-color="#FF9900" style="background:#FF9900;"></span>',
			'<span data-color="#0000CC" style="background:#0000CC;"></span>',
			'<span data-color="#CC3333" style="background:#CC3333;"></span>',

			'<span data-color="#F4D03F" style="background:#641E16;"></span>',
			'<span data-color="#4A235A" style="background:#4A235A;"></span>',
			'<span data-color="#009966" style="background:#009966;"></span>',
			'<span data-color="#ffff00" style="background:#ffff00;"></span>',
			'<span data-color="#7D6608" style="background:#7D6608;"></span>',
			'<span data-color="#FF33CC" style="background:#FF33CC;"></span>',

			'<span data-color="#990066" style="background:#990066;"></span>',
			'<span data-color="#ffffff" style="background:#ffffff;"></span>',
			'<span data-color="#9bbb59" style="background:#9bbb59;"></span>',
			'<span data-color="#CCFFFF" style="background:#CCFFFF;"></span>',
			'<span data-color="#FFCCCC" style="background:#FFCCCC;"></span>',
			'<span data-color="#CC99CC" style="background:#CC99CC;"></span>',
			'</div>',

			//笔刷设置
			'<div class="sectionBar">',
			'<em class="brushIcon"></em>',
			'<a href="javascript:void(0)" class="brush-size size1">1</a>',
			'<a href="javascript:void(0)" class="brush-size size2">3</a>',
			'<a href="javascript:void(0)" class="brush-size size3">5</a>',
			'<a href="javascript:void(0)" class="brush-size size4">7</a>',
			'</div>',
			'<div class="sectionBar">',
			'<em class="eraserIcon"></em>',
			'<a href="javascript:void(0)" class="eraser-size size1">5</a>',
			'<a href="javascript:void(0)" class="eraser-size size2">10</a>',
			'<a href="javascript:void(0)" class="eraser-size size3">15</a>',
			'<a href="javascript:void(0)" class="eraser-size size4">20</a>',
			'</div>',
			'<div class="sectionBar">',
			'<em class="blurIcon"></em>',
			'<a href="javascript:void(0)" class="blur-size size1">2</a>',
			'<a href="javascript:void(0)" class="blur-size size2">4</a>',
			'<a href="javascript:void(0)" class="blur-size size3">6</a>',
			'<a href="javascript:void(0)" class="blur-size size4">8</a>',
			'</div>',
			//end 笔刷设置
			'<div class="sectionBar">',
			'<span id="clearSetting" class="clearSetting">',
			'<em class="icon"></em>',
			'<em class="text">初始化设置</em>',
			'</span>',
			'</div>',
			'<div class="sectionBar">',
			'<div id="J_addImg" class="addImgH">',
			'<em class="icon"></em>',
			'<em class="text">添加背景</em>',
			'<input type="file" class="upload" id="J_canvas_bg" accept="image/gif,image/jpeg,image/png,image/jpg,image/bmp"/>',
			'</div>',
			'</div>',
			'<div class="sectionBar">',
			'<span id="J_removeImg" class="removeImg">',
			'<em class="icon"></em>',
			'<em class="text">删除背景</em>',
			'</span>',
			'</div>',
			'</div>'
		].join('');

		var dialog = self.createDialog({
				name : name,
				width : 750,
				height : 440,
				title : self.lang(name),
				body : html,
				yesBtn : {
					name : lang.btnText,
					click : function(e) {

						if (dialog.isLoading) {
							return;
						}
						if (canvas.isEmpty()) {
							K.options.errorMsgHandler(lang.empty, "error");
							return;
						}
						canvas.save(function(data) {
							//上传涂鸦到服务器
							if (allowUploadGraft) {
								dialog.showLoading(self.lang('uploadLoading'));

								$.post(uploadJson, {
									img_base64_data : data,
									fileType : "image",
									base64 : 1
								}, function(res) {

									dialog.hideLoading();
									if (res.code == "000") {
										K.options.errorMsgHandler(lang.uploadSuccess, "ok");
										clickFn.call(self, res.item);
										self.hideDialog().focus();
									} else {
										K.options.errorMsgHandler(lang.uploadFaild, "error");
									}

								}, "json");

							} else {
								clickFn.call(self, data);
								self.hideDialog().focus();
							}

						});

					}
				}
			});
		//console.log(div);

		//var urlBox = K('[name="url"]', div),
		//	viewServerBtn = K('[name="viewServer"]', div),
		//	titleBox = K('[name="title"]', div);

		var canvas = new Canvas({
			canvasId : "canvas-borad",
			width : 600,
			height : 320
		});



	};

	self.clickToolbar(name, function() {
		self.plugin.graftDialog({
			clickFn : function(url) {
				self.exec('insertimage', url);
			}
		});
	});
});
