package com.yupi.springbootinit.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName：Picture
 * @Author: fmy
 * @Date: 2024/12/7 16:01
 * @Description:
 */
@Data
public class Picture implements Serializable {
    private String title;
    private String url;
    private static final long serialVersionUID = 1L;
}
