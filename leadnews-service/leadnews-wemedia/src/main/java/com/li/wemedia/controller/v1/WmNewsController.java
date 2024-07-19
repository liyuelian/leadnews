package com.li.wemedia.controller.v1;

import com.li.wemedia.service.WmNewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.dtos.WmNewsPageReqDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: liyuelian
 * @Date: 2024/7/18 18:38
 * @Description:
 **/
@RestController
@RequestMapping("/api/v1/news")
@Api(value = "自媒体端-文章管理", tags = "自媒体端-文章管理")
public class WmNewsController {

    @Resource
    private WmNewsService wmNewsService;

    @PostMapping("/list")
    @ApiOperation("文章查询")
    public ResponseResult findList(@RequestBody WmNewsPageReqDto dto) {
        return wmNewsService.findList(dto);
    }
}
