package com.li.wemedia.controller.v1;

import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.dtos.WmMaterialDto;
import com.li.wemedia.service.WmMaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @Author: liyuelian
 * @Date: 2024/7/16 23:58
 * @Description:
 **/
@RestController
@RequestMapping("/api/v1/material")
@Api(value = "自媒体素材管理", tags = "自媒体素材管理")
public class WmMaterialController {
    @Resource
    private WmMaterialService wmMaterialService;

    @PostMapping("/upload_picture")
    @ApiOperation("素材图片上传")
    public ResponseResult uploadPicture(MultipartFile multipartFile) {
        return wmMaterialService.uploadPicture(multipartFile);

    }

    @PostMapping("/list")
    @ApiOperation("素材图片查询")
    public ResponseResult findPicList(@RequestBody WmMaterialDto dto) {
        return wmMaterialService.findPicList(dto);
    }
}
