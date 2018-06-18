/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/

KindEditor.plugin('media', function(K) {
	var self = this, name = 'media', lang = self.lang(name + '.'),
		allowMediaUpload = K.undef(self.allowMediaUpload, false),
		allowFileManager = K.undef(self.allowFileManager, false),
		formatUploadUrl = K.undef(self.formatUploadUrl, true),
		extraParams = K.undef(self.extraFileUploadParams, {}),
		filePostName = K.undef(self.filePostName, 'imgFile'),
		uploadJson = K.undef(self.uploadJson, self.basePath + 'php/upload_json.php');

	self.plugin.media = {
		edit : function() {
			var html = [
				'<div class="ke-dialog-content-inner">',
				//url
				'<div class="ke-dialog-row ke-clearfix">',
				'<label for="keUrl" class="row-left">' + lang.url + '：</label>',
				'<div class="row-right">',
				'<input class="ke-input-text" type="text" id="keUrl" name="url" value="" style="width:180px;" /> &nbsp;',
				'<input type="button" class="ke-upload-button" value="' + lang.upload + '" /> &nbsp;',
				'<span class="ke-button-common ke-button-outer">',
				'<input type="button" class="ke-button-common ke-button" name="viewServer" value="' + lang.viewServer + '" />',
				'</span>',
				'</div>',
				'</div>',
				//width
				'<div class="ke-dialog-row ke-clearfix">',
				'<label for="keWidth" class="row-left">' + lang.width + '：</label>',
				'<div class="row-right">',
				'<input type="text" id="keWidth" class="ke-input-text ke-input-number" name="width" value="550" maxlength="4" />',
				'</div>',
				'</div>',
				//height
				'<div class="ke-dialog-row ke-clearfix">',
				'<label for="keHeight" class="row-left">' + lang.height + '：</label>',
				'<div class="row-right">',
				'<input type="text" id="keHeight" class="ke-input-text ke-input-number" name="height" value="400" maxlength="4" />',
				'</div>',
				'</div>',
				//autostart
				'<div class="ke-dialog-row ke-clearfix">',
				'<label for="keAutostart" class="row-left">' + lang.autostart + '：</label>',
				'<div class="row-right">',
				'<input type="checkbox" id="keAutostart" name="autostart" class="ke-input-checkbox" value="" /> ',
				'</div>',
				'</div>',
				'</div>'
			].join('');
			var dialog = self.createDialog({
				name : name,
				width : 450,
				height : 260,
				title : self.lang(name),
				body : html,
				yesBtn : {
					name : self.lang('yes'),
					click : function(e) {
						var url = K.trim(urlBox.val()),
							width = widthBox.val(),
							height = heightBox.val();
						if (url == 'http://' || K.invalidUrl(url)) {
							K.options.errorMsgHandler(self.lang('invalidUrl'), "error");
							urlBox[0].focus();
							return;
						}
						if (!/^\d*$/.test(width)) {
							K.options.errorMsgHandler(self.lang('invalidWidth'), "error");
							widthBox[0].focus();
							return;
						}
						if (!/^\d*$/.test(height)) {
							K.options.errorMsgHandler(self.lang('invalidHeight'), "error");
							heightBox[0].focus();
							return;
						}
						var html = K.mediaImg(self.themesPath + 'common/blank.gif', {
								src : url,
								type : K.mediaType(url),
								width : width,
								height : height,
								autostart : autostartBox[0].checked ? 'true' : 'false',
								loop : 'true'
							});
						self.insertHtml(html).hideDialog().focus();
					}
				}
			}),
			div = dialog.div,
			urlBox = K('[name="url"]', div),
			viewServerBtn = K('[name="viewServer"]', div),
			widthBox = K('[name="width"]', div),
			heightBox = K('[name="height"]', div),
			autostartBox = K('[name="autostart"]', div);
			urlBox.val('http://');

			if (allowMediaUpload) {
				var uploadbutton = K.uploadbutton({
					button : K('.ke-upload-button', div)[0],
					fieldName : filePostName,
					extraParams : extraParams,
					url : K.addParam(uploadJson, 'fileType=media'),
					afterUpload : function(data) {
						dialog.hideLoading();
						if (data.code == "000") {
							var url = data.item.url;
							if (formatUploadUrl) {
								url = K.formatUrl(url, 'absolute');
							}
							urlBox.val(url);

							if (self.afterUpload) {
								self.afterUpload.call(self, url, data, name);
							}
							K.options.errorMsgHandler(self.lang('uploadSuccess'), "ok");
						} else {
							K.options.errorMsgHandler(data.message, "error", "error");
						}
					},
					afterError : function(html) {
						dialog.hideLoading();
						self.errorDialog(html);
					}
				});
				uploadbutton.fileBox.change(function(e) {
					dialog.showLoading(self.lang('uploadLoading'));
					uploadbutton.submit();
				});
			} else {
				K('.ke-upload-button', div).hide();
			}

			if (allowMediaUpload && allowFileManager) {
				viewServerBtn.click(function(e) {
					self.loadPlugin('filemanager', function() {
						self.plugin.filemanagerDialog({
							dirName : 'media',
							clickFn : function(url) {
								K('[name="url"]', div).val(url);
								if (self.afterSelectFile) {
									self.afterSelectFile.call(self, url);
								}
							}
						});
					});
				});
			} else {
				K("#keUrl").css("width", "280px");
				viewServerBtn.hide();
			}

			var img = self.plugin.getSelectedMedia();
			if (img) {
				var attrs = K.mediaAttrs(img.attr('data-ke-tag'));
				urlBox.val(attrs.src);
				widthBox.val(K.removeUnit(img.css('width')) || attrs.width || 0);
				heightBox.val(K.removeUnit(img.css('height')) || attrs.height || 0);
				autostartBox[0].checked = (attrs.autostart === 'true');
			}
			urlBox[0].focus();
			urlBox[0].select();
		},
		'delete' : function() {
			self.plugin.getSelectedMedia().remove();
			// [IE] 删除图片后立即点击图片按钮出错
			self.addBookmark();
		}
	};
	self.clickToolbar(name, self.plugin.media.edit);
});
