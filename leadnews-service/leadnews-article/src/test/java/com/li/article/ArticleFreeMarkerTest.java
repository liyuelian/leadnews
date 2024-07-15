package com.li.article;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.li.article.mapper.ApArticleContentMapper;
import com.li.article.service.ApArticleService;
import com.li.file.service.FileStorageService;
import com.li.model.article.pojos.ApArticle;
import com.li.model.article.pojos.ApArticleContent;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.util.HashMap;

/**
 * @Author: liyuelian
 * @Date: 2024/7/15 21:02
 * @Description:
 **/
@SpringBootTest(classes = ArticleApplication.class)
@RunWith(SpringRunner.class)
public class ArticleFreeMarkerTest {
    @Resource
    private ApArticleContentMapper apArticleContentMapper;

    @Resource
    private Configuration configuration;

    @Resource
    private FileStorageService fileStorageService;

    @Resource
    private ApArticleService apArticleService;

    @Test
    public void createStaticUrlTest() throws Exception {
        //1.获取文章内容
        // (实际的文章内容应在用户发布文章的时获取)
        ApArticleContent apArticleContent = apArticleContentMapper.selectOne
                (Wrappers.<ApArticleContent>lambdaQuery()
                        .eq(ApArticleContent::getArticleId, "1302977178887004162L")
                );
        if (apArticleContent != null && StringUtils.isNotBlank(apArticleContent.getContent())) {
            //2.通过FreeMarker生成html
            Template template = configuration.getTemplate("article.ftl");//默认从类路径的templates中获取

            //数据模型
            HashMap<String, Object> contentMap = new HashMap<>();
            //apArticleContent.getContent()以json形式存储
            //[{"type":"text","value":"内容1"},{"type":"image","value":"http://xx.xx.xx.xx/img/123.jpg"},{"type":"text","value":"内容2"}]
            JSONArray jsonArray = JSON.parseArray(apArticleContent.getContent());
            contentMap.put("content", jsonArray);

            //合成
            StringWriter out = new StringWriter();
            template.process(contentMap, out);

            //3.上传到MinIO
            ByteArrayInputStream in = new ByteArrayInputStream(out.toString().getBytes());//将StringWriter缓存的字符串数据转换为一个字节数组
            String filePath = fileStorageService.uploadHtmlFile("", apArticleContent.getArticleId() + ".html", in);

            //4.将路径保存到ap_article.static_url字段
            apArticleService.update
                    (Wrappers.<ApArticle>lambdaUpdate()
                            .eq(ApArticle::getId, apArticleContent.getArticleId())//根据文章id查询
                            .set(ApArticle::getStaticUrl, filePath)//更改对应记录的static_url
                    );
        }
    }
}
