package com.li.user.controller.v1;

import com.li.model.common.dtos.ResponseResult;
import com.li.model.user.dtos.LoginDto;
import com.li.user.service.ApUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController //@RestController 返回的数据会直接作为响应的主体内容（JSON 或 XML），不进行页面跳转或视图解析
@RequestMapping("/api/v1/login")
public class ApUserLoginController {

    @Autowired
    private ApUserService apUserService;

    /**
     * @param dto
     * @return
     */
    @PostMapping("/login_auth")
    public ResponseResult login(@RequestBody @NotNull LoginDto dto) {
        return apUserService.login(dto);
    }
}