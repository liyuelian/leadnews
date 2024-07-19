package com.li.wemedia.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.dtos.WmNewsPageReqDto;
import com.li.model.wemedia.pojos.WmNews;

/**
 * @Author: liyuelian
 * @Date: 2024/7/18 18:45
 * @Description:
 **/
public interface WmNewsService extends IService<WmNews> {
    /**
     * 条件查询用户文章列表
     *
     * @param dto
     * @return
     */
    public ResponseResult findList(WmNewsPageReqDto dto);
}
