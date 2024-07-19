package com.li.wemedia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.li.model.common.dtos.PageResponseResult;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.wemedia.dtos.WmNewsPageReqDto;
import com.li.model.wemedia.pojos.WmNews;
import com.li.utils.thread.WmThreadLocalUtil;
import com.li.wemedia.mapper.WmNewsMapper;
import com.li.wemedia.service.WmNewsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyuelian
 * @Date: 2024/7/18 18:46
 * @Description:
 **/
@Service
@Slf4j
@Transactional
public class WmNewsServiceImpl extends ServiceImpl<WmNewsMapper, WmNews> implements WmNewsService {
    @Override
    public ResponseResult findList(WmNewsPageReqDto dto) {
        //1.条件校验
        //分页条件校验
        dto.checkParam();

        //2.分页查询
        IPage page = new Page<>(dto.getPage(), dto.getSize());

        LambdaQueryWrapper<WmNews> queryWrapper = new LambdaQueryWrapper<>();

        //获取用户id
        queryWrapper.eq(WmNews::getUserId, WmThreadLocalUtil.getUser().getId());

        //状态精确查询
        if (dto.getStatus() != null) {
            queryWrapper.eq(WmNews::getStatus, dto.getStatus());
        }

        //频道精确查询
        if (dto.getChannelId() != null) {
            queryWrapper.eq(WmNews::getChannelId, dto.getChannelId());
        }

        //时间范围查询
        if (dto.getBeginPubDate() != null && dto.getEndPubDate() != null) {
            queryWrapper.between(WmNews::getPublishTime, dto.getBeginPubDate(), dto.getEndPubDate());
        }

        //关键字模糊查询
        if (StringUtils.isNotBlank(dto.getKeyword())) {
            queryWrapper.like(WmNews::getTitle, dto.getKeyword());
        }

        //按照发布时间倒序查询
        queryWrapper.orderByDesc(WmNews::getPublishTime);

        page = page(page, queryWrapper);

        //3.返回数据
        ResponseResult responseResult = new PageResponseResult(dto.getPage(), dto.getSize(), (int) page.getTotal());
        responseResult.setData(page.getRecords());

        return responseResult;
    }
}
