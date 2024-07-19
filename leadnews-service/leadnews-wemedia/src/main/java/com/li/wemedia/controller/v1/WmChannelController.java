package com.li.wemedia.controller.v1;

import com.li.model.common.dtos.ResponseResult;
import com.li.wemedia.service.WmChannelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: liyuelian
 * @Date: 2024/7/18 18:04
 * @Description:
 **/
@RestController
@RequestMapping("/api/v1/channel/")
@Api(value = "自媒体端-频道列表管理", tags = "自媒体端-频道列表管理")
public class WmChannelController {
    @Resource
    private WmChannelService wmChannelService;

    @GetMapping("/channels")
    @ApiOperation(value = "频道列表查询")
    public ResponseResult findAll() {
        return wmChannelService.findAll();
    }
}
