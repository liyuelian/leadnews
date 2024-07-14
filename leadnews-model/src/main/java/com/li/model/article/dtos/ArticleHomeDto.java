package com.li.model.article.dtos;

import lombok.Data;

import java.util.Date;

/**
 * @Author: liyuelian
 * @Date: 2024/7/13 22:55
 * @Description:
 **/
@Data
public class ArticleHomeDto {
    // 最大时间
    Date maxBehotTime;
    // 最小时间
    Date minBehotTime;
    // 分页size
    Integer size;
    // 频道ID
    String tag;
}
