package com.li.wemedia.controller.v1;

import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.dtos.WmLoginDto;
import com.li.wemedia.service.WmUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(value = "自媒体web端用户登录", tags = "自媒体web端用户登录")
public class LoginController {

    @Autowired
    private WmUserService wmUserService;

    @PostMapping("/in")
    @ApiOperation("用户登录")
    public ResponseResult login(@RequestBody WmLoginDto dto) {
        return wmUserService.login(dto);
    }
}
