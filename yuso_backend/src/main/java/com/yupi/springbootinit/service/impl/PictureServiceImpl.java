package com.yupi.springbootinit.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.model.entity.Picture;
import com.yupi.springbootinit.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName：PictureServiceImpl
 * @Author: fmy
 * @Date: 2024/12/7 16:42
 * @Description:
 */
 @Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {
        System.out.println("搜索词***************************" + searchText);
        long current = (pageNum - 1) * pageSize;
        String url = String.format("https://cn.bing.com/images/search?q=%s&first=%s",searchText,current);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BusinessException( ErrorCode.SYSTEM_ERROR, "数据获取异常");
        }
        Elements elements = doc.select(".iuscp.isv");
        List<Picture> pictures = new ArrayList<>();
        for (Element element : elements) {
            // 取图片地址（murl）
            String m = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
            System.out.println(murl);
            // 取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");
//            System.out.println(title);
            Picture picture = new Picture();
            picture.setUrl(murl);
            picture.setTitle(title);
            pictures.add(picture);
            if (pictures.size() >= pageSize) {
                break;
            }
        }
        System.out.println(pictures);

        Page<Picture> picturePage = new Page<>(current, pageSize);
        picturePage.setRecords(pictures);
        return picturePage;
    }
}
