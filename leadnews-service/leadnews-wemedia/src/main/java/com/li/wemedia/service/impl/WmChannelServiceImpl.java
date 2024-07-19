package com.li.wemedia.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.pojos.WmChannel;
import com.li.wemedia.mapper.WmChannelMapper;
import com.li.wemedia.service.WmChannelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: liyuelian
 * @Date: 2024/7/18 18:08
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class WmChannelServiceImpl extends ServiceImpl<WmChannelMapper, WmChannel> implements WmChannelService {
    @Override
    public ResponseResult findAll() {
        return ResponseResult.okResult(list());
    }
}
