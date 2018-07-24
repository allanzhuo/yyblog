package net.laoyeye.yyblog.service;

import java.util.List;

public interface TagReferService {

    List<String> listNameByArticleId(long id);
}
