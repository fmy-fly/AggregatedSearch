package com.yupi.springbootinit.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.xml.crypto.Data;

/**
 * @ClassName：VideoDataSource
 * @Author: fmy
 * @Date: 2024/12/12 19:37
 * @Description:
 */
public class VideoDataSource implements DataSource<Object> {
    @Override
    public Page<Object> doSearch(String searchText, long pageNum, long pageSize) {
        return null;
    }
}
