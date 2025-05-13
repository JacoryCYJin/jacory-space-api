package org.example.jacoryspaceapi.service.impl;

import jakarta.annotation.Resource;
import org.example.jacoryspaceapi.converter.WorkConverter;
import org.example.jacoryspaceapi.domain.dto.TagDTO;
import org.example.jacoryspaceapi.domain.dto.WorkDTO;
import org.example.jacoryspaceapi.domain.po.*;
import org.example.jacoryspaceapi.mapper.WorkMapper;
import org.example.jacoryspaceapi.mapper.WorkTagMapper;
import org.example.jacoryspaceapi.service.WorkService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.sql.DriverManager.println;

/**
 * @author Jacory
 * @date 2025/5/12
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Resource
    private WorkMapper workMapper;
    @Resource
    private WorkTagMapper workTagMapper;
    @Resource
    private WorkConverter workConverter;
    @Resource
    private TagServiceImpl tagService;

    @Override
    public List<WorkDTO> list() {
        // 1. 查询所有作品
        List<WorkPO> workPOList = workMapper.list();
        println("workPOList = " + workPOList);
        List<WorkDTO> workDTOList = workConverter.converterWorkPOListToWorkDTOList(workPOList);
        println("workDTOList = " + workDTOList);

        if (workDTOList.isEmpty()) {
            return workDTOList;
        }

        // 2. 获取所有作品的nanoid
        List<String> workNanoids = workDTOList.stream()
                .map(WorkDTO::getNanoid)
                .collect(Collectors.toList());

        // 3. 批量查询作品-标签关系
        List<WorkTagPO> workTagList = workTagMapper.listByWorkNanoids(workNanoids);
        Map<String, List<String>> workTagMap = workTagList.stream()
                .collect(Collectors.groupingBy(
                        WorkTagPO::getWorkNanoid,
                        Collectors.mapping(WorkTagPO::getTagNanoid, Collectors.toList())
                ));

        List<String> tagNanoids = workTagList.stream()
                .map(WorkTagPO::getTagNanoid)
                .distinct()
                .collect(Collectors.toList());

        // 4. 批量查询标签
        Map<String, TagDTO> tagMap = Collections.emptyMap();
        if (!tagNanoids.isEmpty()) {
            List<TagDTO> tagDTOList = tagService.listByNanoids(tagNanoids);
            tagMap = tagDTOList.stream()
                    .collect(Collectors.toMap(TagDTO::getNanoid, Function.identity()));
        }

        // 5. 填充作品的标签
        workConverter.fillWorkDTOListRelations(
                workDTOList, tagMap, workTagMap);

        return workDTOList;
    }
}
