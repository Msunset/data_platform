package com.xiangban.data_platform.utils.page;
import com.xiangban.data_platform.domain.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


@Component
public class PageUtil {
    @Autowired
    public HttpServletRequest request;

    public PageDto getPage() {
        String pageNum = request.getHeader("pageNum");
        String pageSize = request.getHeader("pageSize");
        PageDto pageDto = new PageDto();
        pageDto.setPageNum(pageNum == null || "".equals(pageNum) ? 1 : Integer.parseInt(pageNum));
        pageDto.setPageSize(pageSize == null || "".equals(pageSize) ? 10 : Integer.parseInt(pageSize));
        return pageDto;
    }
}
