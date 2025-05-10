package org.example.jacoryspaceapi.controller;

import org.example.jacoryspaceapi.converter.TagConverter;
import org.example.jacoryspaceapi.domain.vo.Result;
import org.example.jacoryspaceapi.domain.vo.TagVO;
import org.example.jacoryspaceapi.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Jacory
 * @date 2025/5/10
 */

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    /**
     * 获取所有标签
     * @return
     */
    @GetMapping("/list")
    public Result<List<TagVO>> list() {
        List<TagVO> tagVOList = TagConverter.converterTagDTOListToTagVOList(tagService.list());
        return Result.success(tagVOList);
    }
}
