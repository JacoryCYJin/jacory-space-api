package org.example.jacoryspaceapi.util;

import com.github.pagehelper.PageInfo;
import org.example.jacoryspaceapi.common.page.PageDTO;

import java.util.List;

/**
 * PageInfo转换工具类
 * @author Jacory
 * @date 2025/5/10
 */
public class PageInfoConverter {
    
    /**
     * 将PageInfo转换为PageDTO
     * @param pageInfo PageHelper的分页结果
     * @param list 转换后的数据列表
     * @param <T> 源数据类型
     * @param <R> 目标数据类型
     * @return 分页DTO
     */
    public static <T, R> PageDTO<R> convertPageInfoToPageDTO(PageInfo<T> pageInfo, List<R> list) {
        PageDTO<R> pageDTO = new PageDTO<>();
        pageDTO.setTotal(pageInfo.getTotal());
        pageDTO.setPages(pageInfo.getPages());
        pageDTO.setList(list);
        return pageDTO;
    }
}