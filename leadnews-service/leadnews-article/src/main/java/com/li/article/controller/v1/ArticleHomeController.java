package com.li.article.controller.v1;

import com.li.article.service.ApArticleService;
import com.li.model.article.dtos.ArticleHomeDto;
import com.li.model.common.dtos.ResponseResult;
import com.li.common.constant.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: liyuelian
 * @Date: 2024/7/13 22:52
 * @Description:
 **/
@RestController
@RequestMapping("/api/v1/article")
@Api(value = "app端文章刷新", tags = "app端文章刷新")
public class ArticleHomeController {
    @Resource
    ApArticleService apArticleService;

    /**
     * 首页加载
     *
     * @param dto
     * @return
     */
    @PostMapping("/load")
    @ApiOperation("文章首页刷新")
    public ResponseResult load(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto, ArticleConstant.ARTICLE_SEARCH_LOAD_MORE);
    }

    /**
     * 加载更多（下拉）
     *
     * @param dto
     * @return
     */
    @PostMapping("/loadmore")
    @ApiOperation("文章下拉more")
    public ResponseResult loadMore(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto, ArticleConstant.ARTICLE_SEARCH_LOAD_MORE);
    }

    /**
     * 加载最新（上拉）
     *
     * @param dto
     * @return
     */
    @PostMapping("/loadnew")
    @ApiOperation("文章上拉new")
    public ResponseResult loadNew(@RequestBody ArticleHomeDto dto) {
        return apArticleService.load(dto, ArticleConstant.ARTICLE_SEARCH_LOAD_NEW);
    }


}
