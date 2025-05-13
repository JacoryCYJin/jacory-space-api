package org.example.jacoryspaceapi.controller;

import jakarta.annotation.Resource;
import org.example.jacoryspaceapi.common.Result;
import org.example.jacoryspaceapi.converter.WorkConverter;
import org.example.jacoryspaceapi.domain.dto.WorkDTO;
import org.example.jacoryspaceapi.domain.vo.WorkVO;
import org.example.jacoryspaceapi.service.WorkService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.sql.DriverManager.println;

/**
 * @author Jacory
 * @date 2025/5/12
 */
@RequestMapping("/work")
@RestController
public class WorkController {
    @Resource
    private WorkService workService;
    @Resource
    private WorkConverter workConverter;

    /**
     * 获取作品列表
     *
     */
    @GetMapping("/list")
    public Result<List<WorkVO>> list() {
        List<WorkDTO> workDTOList = workService.list();
        if (workDTOList == null) {
            return Result.fail("作品不存在");
        }
        List<WorkVO> workVOList = workConverter.converterWorkDTOListToWorkVOList(workDTOList);
        println("workVOList = " + workVOList);
        return Result.success(workVOList);
    }
}
