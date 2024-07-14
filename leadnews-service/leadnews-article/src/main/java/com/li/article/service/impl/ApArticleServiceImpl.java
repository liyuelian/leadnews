package com.li.article.service.impl;

import lombok.extern.slf4j.Slf4j;
import com.li.common.constant.ArticleConstant;
import com.li.model.article.pojos.ApArticle;
import org.apache.commons.lang.StringUtils;
import com.li.article.mapper.ApArticleMapper;
import org.springframework.stereotype.Service;
import com.li.model.common.dtos.ResponseResult;
import com.li.model.article.dtos.ArticleHomeDto;
import com.li.article.service.ApArticleService;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: liyuelian
 * @Date: 2024/7/13 23:38
 * @Description:
 **/
@Service
@Transactional
@Slf4j
public class ApArticleServiceImpl extends ServiceImpl<ApArticleMapper, ApArticle> implements ApArticleService {
    @Resource
    ApArticleMapper apArticleMapper;

    @Override
    public ResponseResult load(ArticleHomeDto dto, Short loadType) {
        //校验参数
        //查询分页校验
        Integer size = dto.getSize();
        if (size == null || size == 0) {
            size = 10;
        }
        Math.min(size, ArticleConstant.MAX_PAGE_SIZE);
        dto.setSize(size);

        //类型操作校验
        if (!ArticleConstant.ARTICLE_SEARCH_LOAD_MORE.equals(loadType) && !ArticleConstant.ARTICLE_SEARCH_LOAD_NEW.equals(loadType)) {
            loadType = ArticleConstant.ARTICLE_SEARCH_LOAD_MORE;
        }

        //文章频道校验
        if (StringUtils.isBlank(dto.getTag())) {
            dto.setTag(ArticleConstant.DEFAULT_TAG);
        }

        //时间校验
        if (dto.getMaxBehotTime() == null) {
            dto.setMaxBehotTime(new Date());
        }

        if (dto.getMinBehotTime() == null) {
            dto.setMinBehotTime(new Date());
        }

        //查询数据
        List<ApArticle> apArticles = apArticleMapper.loadArticleList(dto, loadType);

        //返回数据
        return ResponseResult.okResult(apArticles);
    }

}
