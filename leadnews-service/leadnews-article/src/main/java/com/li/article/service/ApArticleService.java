package com.li.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.li.model.article.dtos.ArticleHomeDto;
import com.li.model.article.pojos.ApArticle;
import com.li.model.common.dtos.ResponseResult;

public interface ApArticleService extends IService<ApArticle> {
    /**
     * 根据参数加载文章列表
     *
     * @param dto
     * @param loadType 1-加载更多，2-加载最新
     * @return
     */
    public ResponseResult load(ArticleHomeDto dto, Short loadType);
}
