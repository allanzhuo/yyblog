package net.laoyeye.yyblog.service.impl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.laoyeye.yyblog.common.Constant;
import net.laoyeye.yyblog.common.NKBlogResult;
import net.laoyeye.yyblog.common.YYBlogResult;
import net.laoyeye.yyblog.common.utils.COSClientUtils;
import net.laoyeye.yyblog.enums.SettingCosEnum;
import net.laoyeye.yyblog.mapper.SettingMapper;
import net.laoyeye.yyblog.model.SettingDO;
import net.laoyeye.yyblog.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService{
	
	@Autowired
	private SettingMapper settingMapper;

	@Override
	public YYBlogResult upload(MultipartFile file) {
		boolean contains = file.getContentType().contains("image/");
		if (!contains) {
			return YYBlogResult.build(500, "上传失败，图片格式不合法！");
		}
		String name = null;
		try {
			name = this.handlerCos(file);
		} catch (Exception e) {
			return YYBlogResult.build(500, e.getMessage());
		}  
		return YYBlogResult.ok(Constant.ACCESS_URL + name);
	}

	@Override
	public YYBlogResult uploadQrcode(MultipartFile file, String type) {
		boolean contains = file.getContentType().contains("image/");
		if (!contains) {
			return YYBlogResult.build(500, "上传失败，图片格式不合法！");
		}
		String name = null;
		String path = null;
		try {
			name = this.handlerCos(file);
			path = Constant.ACCESS_URL + name;
			SettingDO set = new SettingDO();
			set.setCode(type);
			set.setValue(path);
			int count = settingMapper.updateValueByCode(set);
			if (count != 1) {
				YYBlogResult.build(500, "上传图片失败！");
			}
		} catch (Exception e) {
			return YYBlogResult.build(500, e.getMessage());
		}  
		return YYBlogResult.ok(Constant.ACCESS_URL + name);
	}

	@Override
	public NKBlogResult uploadNK(MultipartFile file) {
		boolean contains = file.getContentType().contains("image/");
		if (!contains) {
			return NKBlogResult.build(001, "上传失败，图片格式不合法！");
		}
		String name = null;
		try {
			name = this.handlerCos(file);
		} catch (Exception e) {
			return NKBlogResult.build(001, "上传失败"+e.getMessage());
		}  

		HashMap<String, Object> image = new HashMap<>();
		image.put("url", Constant.ACCESS_URL+name);
		return NKBlogResult.ok(image);
	}
	
	private String handlerCos(MultipartFile file) throws Exception{
		String secretId = settingMapper.getValueByCode(SettingCosEnum.SECRET_ID.getCode());
		String secretKey = settingMapper.getValueByCode(SettingCosEnum.SECRET_KEY.getCode());
		String bucket = settingMapper.getValueByCode(SettingCosEnum.BUCKET.getCode());
		String region = settingMapper.getValueByCode(SettingCosEnum.REGION.getCode());
		COSClientUtils cosClientUtil = new COSClientUtils(secretId, secretKey, region);   
		String name = cosClientUtil.uploadFile2Cos(file, bucket);
		cosClientUtil.destory();
		return name;
	} 

}
