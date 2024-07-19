package com.li.model.wemedia.dtos;

import com.li.model.common.dtos.PageRequestDto;
import lombok.Data;

import java.util.Date;

/**
 * @Author: liyuelian
 * @Date: 2024/7/18 18:35
 * @Description:
 **/
@Data
public class WmNewsPageReqDto extends PageRequestDto {
    //状态
    private Short status;
    //开始时间
    private Date beginPubDate;
    //结束时间
    private Date endPubDate;
    //所属频道ID
    private Integer channelId;
    //关键字
    private String keyword;
}
