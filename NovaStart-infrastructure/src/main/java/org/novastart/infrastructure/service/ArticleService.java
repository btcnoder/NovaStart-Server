package org.novastart.infrastructure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.domain.entitty.Article;

/**
 * 文章表(Article)表服务接口
 *
 * @author makejava
 * @since 2023-09-12 09:19:12
 */
public interface ArticleService extends IService<Article> {

    Response hotArticle();

    Response articleList(Integer pageNum, Integer pageSize, Long categoryId);

    Response getArticleDetail(Long id);
}

