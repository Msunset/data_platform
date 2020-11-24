package com.xiangban.data_platform.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class PageDto implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
}
