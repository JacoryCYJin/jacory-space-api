package org.example.jacoryspaceapi.converter;

import org.example.jacoryspaceapi.domain.dto.TagDTO;
import org.example.jacoryspaceapi.domain.dto.WorkDTO;
import org.example.jacoryspaceapi.domain.po.WorkPO;
import org.example.jacoryspaceapi.domain.vo.TagVO;
import org.example.jacoryspaceapi.domain.vo.WorkVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Jacory
 * @date 2025/5/12
 */
@Component
public class WorkConverter {

    /**
     * WorkPO 转 WorkDTO
     */
    public WorkDTO converterWorkPOtoWorkDTO(WorkPO po) {
        if (po == null) {
            return null;
        }

        WorkDTO dto = new WorkDTO();
        dto.setNanoid(po.getNanoid());
        dto.setTitle(po.getTitle());
        dto.setDescription(po.getDescription());
        dto.setCoverImage(po.getCoverImage());
        dto.setLink(po.getLink());
        dto.setLaunchDate(po.getLaunchDate());
        dto.setMood(po.getMood());
        dto.setStatus(po.getStatus());
        dto.setTags(new ArrayList<>());

        return dto;
    }

    /**
     * WorkPOList 转 WorkDTOList
     */
    public List<WorkDTO> converterWorkPOListToWorkDTOList(List<WorkPO> poList) {
        if (poList == null) {
            return new ArrayList<>();
        }

        return poList.stream()
                .map(this::converterWorkPOtoWorkDTO)
                .collect(Collectors.toList());
    }

    /**
     * WorkDTO 转 WorkVO
     */
    public WorkVO converterWorkDTOtoWorkVO(WorkDTO dto) {
        if (dto == null) {
            return null;
        }

        WorkVO vo = new WorkVO();
        vo.setNanoid(dto.getNanoid());
        vo.setTitle(dto.getTitle());
        vo.setDescription(dto.getDescription());
        vo.setCoverImage(dto.getCoverImage());
        vo.setLink(dto.getLink());
        vo.setLaunchDate(dto.getLaunchDate());
        vo.setMood(dto.getMood());
        vo.setStatus(dto.getStatus());

        // 转换标签列表
        if (dto.getTags() != null) {
            List<TagVO> tagVOList = dto.getTags().stream()
                    .map(TagConverter::converterTagDTOtoTagVO)
                    .collect(Collectors.toList());
            vo.setTags(tagVOList);
        } else {
            vo.setTags(new ArrayList<>());
        }

        return vo;
    }

    /**
     * WorkDTOList 转 WorkVOList
     */
    public List<WorkVO> converterWorkDTOListToWorkVOList(List<WorkDTO> dtoList) {
        if (dtoList == null) {
            return new ArrayList<>();
        }

        return dtoList.stream()
                .map(this::converterWorkDTOtoWorkVO)
                .collect(Collectors.toList());
    }

    /**
     * 填充workDTO的标签
     *
     * @param workDTO    作品DTO
     * @param tagMap     标签Map
     * @param workTagMap 作品-标签关系Map
     */
    public void fillWorkDTORelations(
            WorkDTO workDTO,
            Map<String, TagDTO> tagMap,
            Map<String, List<String>> workTagMap) {

        String workNanoid = workDTO.getNanoid();

        // 填充标签
        List<String> tagNanoids = workTagMap.get(workNanoid);
        if (tagNanoids != null && !tagNanoids.isEmpty()) {
            List<TagDTO> tags = tagNanoids.stream()
                    .map(tagMap::get)
                    .filter(tag -> tag != null)
                    .collect(Collectors.toList());
            workDTO.setTags(tags);
        }
    }

    /**
     * 批量填充workDTO的标签
     */
    public void fillWorkDTOListRelations(
            List<WorkDTO> workDTOList,
            Map<String, TagDTO> tagMap,
            Map<String, List<String>> workTagMap) {

        if (workDTOList == null || workDTOList.isEmpty()) {
            return;
        }

        for (WorkDTO workDTO : workDTOList) {
            fillWorkDTORelations(workDTO, tagMap, workTagMap);
        }
    }
}
