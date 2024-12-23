package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.entity.Picture;

/**
 * @ClassName：PictureService
 * @Author: fmy
 * @Date: 2024/12/7 16:37
 * @Description:
 */
public interface PictureService  {
    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);
}
