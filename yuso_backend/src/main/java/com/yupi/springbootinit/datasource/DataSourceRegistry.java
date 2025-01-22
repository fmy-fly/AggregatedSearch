package com.yupi.springbootinit.datasource;

import com.yupi.springbootinit.model.enums.SearchTypeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName：DataSourceRegistry
 * @Author: fmy
 * @Date: 2024/12/13 9:51
 * @Description:
 */
@Component
public class DataSourceRegistry {

    @Resource
    private PictureDataSource pictureDataSource;
    @Resource
    private UserDataSource userDataSource;
    @Resource
    private PostDataSource postDataSource;

    private Map<String, DataSource<T>>  typeDataSourceMap;


    @PostConstruct
    public void init() {
        typeDataSourceMap=  new HashMap() {
            {
                put(SearchTypeEnum.POST.getValue(), postDataSource);
                put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
                put(SearchTypeEnum.USER.getValue(), userDataSource);
            }
        };
    }

    public DataSource getDataSourceByType(String type) {
        return
                typeDataSourceMap.get(type);
    }
}
