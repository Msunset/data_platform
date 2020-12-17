package com.xiangban.data_platform.domain.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class PageDto implements Serializable {
    private Integer pageNum;
    private Integer pageSize;
}
