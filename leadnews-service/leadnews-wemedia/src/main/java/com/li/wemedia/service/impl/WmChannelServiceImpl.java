package com.li.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.pojos.WmChannel;
import com.li.wemedia.mapper.WmChannelMapper;
import com.li.wemedia.service.WmChannelService;
import org.springframework.stereotype.Service;

/**
 * @Author: liyuelian
 * @Date: 2024/7/18 18:08
 * @Description:
 **/
@Service
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }
}
