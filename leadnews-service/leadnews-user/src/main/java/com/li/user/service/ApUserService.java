package com.li.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.user.dtos.LoginDto;
import com.li.model.user.pojos.ApUser;

/**
 * @Author: liyuelian
 * @Date: 2024/7/3 18:38
 * @Description: 用户登录接口类
 **/

public interface ApUserService extends IService<ApUser> {

    /**
     * app登录功能
     *
     * @param dto
     * @return
     */
    public ResponseResult login(LoginDto dto);
}
