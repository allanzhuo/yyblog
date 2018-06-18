package net.laoyeye.yyblog.service;

import org.springframework.web.multipart.MultipartFile;
import net.laoyeye.yyblog.common.NKBlogResult;
import net.laoyeye.yyblog.common.YYBlogResult;

public interface UploadService {
	YYBlogResult upload(MultipartFile file);
	
	YYBlogResult uploadQrcode(MultipartFile file, String type);
	
	NKBlogResult uploadNK(MultipartFile file);
}
