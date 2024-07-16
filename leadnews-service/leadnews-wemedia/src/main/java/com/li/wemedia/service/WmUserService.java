package com.li.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.dtos.WmLoginDto;
import com.li.model.wemedia.pojos.WmUser;

public interface WmUserService extends IService<WmUser> {

    /**
     * 自媒体端登录
     *
     * @param dto
     * @return
     */
    public ResponseResult login(WmLoginDto dto);

}