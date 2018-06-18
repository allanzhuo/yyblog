/*******************************************************************************
* KindEditor - WYSIWYG HTML Editor for Internet
* Copyright (C) 2006-2011 kindsoft.net
*
* @author Roddy <luolonghao@gmail.com>
* @site http://www.kindsoft.net/
* @licence http://www.kindsoft.net/license.php
*******************************************************************************/

KindEditor.plugin('link', function(K) {
	var self = this, name = 'link';
	self.plugin.link = {
		edit : function() {
			var lang = self.lang(name + '.'),
				html = ['<div class="ke-dialog-content-inner">',
				//url
				'<div class="ke-dialog-row ke-clearfix">',
				'<label for="keUrl" class="row-left">' + lang.url + '：</label>',
				'<div class="row-right">',
				'<input class="ke-input-text" type="text" id="keUrl" name="url" value="" style="width:260px;" />',
				'</div>',
				'</div>',
				//type
				'<div class="ke-dialog-row ke-clearfix"">',
				'<label for="keType" class="row-left">' + lang.linkType + '：</label>',
				'<div class="row-right">',
				'<select id="keType" class="ke-select" name="type"></select>',
				'</div>',
				'</div>',
				'</div>'].join(""),
				dialog = self.createDialog({
					name : name,
					width : 450,
					title : self.lang(name),
					body : html,
					yesBtn : {
						name : self.lang('yes'),
						click : function(e) {
							var url = K.trim(urlBox.val());
							if (url == 'http://' || K.invalidUrl(url)) {
								K.options.errorMsgHandler(self.lang('invalidUrl'), "error");
								urlBox[0].focus();
								return;
							}
							self.exec('createlink', url, typeBox.val()).hideDialog().focus();
						}
					}
				}),
				div = dialog.div,
				urlBox = K('input[name="url"]', div),
				typeBox = K('select[name="type"]', div);
			urlBox.val('http://');
			typeBox[0].options[0] = new Option(lang.newWindow, '_blank');
			typeBox[0].options[1] = new Option(lang.selfWindow, '');
			self.cmd.selection();
			var a = self.plugin.getSelectedLink();
			if (a) {
				self.cmd.range.selectNode(a[0]);
				self.cmd.select();
				urlBox.val(a.attr('data-ke-src'));
				typeBox.val(a.attr('target'));
			}
			urlBox[0].focus();
			urlBox[0].select();
		},
		'delete' : function() {
			self.exec('unlink', null);
		}
	};
	self.clickToolbar(name, self.plugin.link.edit);
});
