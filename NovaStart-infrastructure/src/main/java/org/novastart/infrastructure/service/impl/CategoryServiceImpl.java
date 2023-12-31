package org.novastart.infrastructure.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.novastart.infrastructure.utils.BeanCopyUtils;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.constants.SystemConstants;
import org.novastart.infrastructure.domain.entitty.Article;
import org.novastart.infrastructure.domain.entitty.Category;
import org.novastart.infrastructure.domain.vo.CategoryVo;
import org.novastart.infrastructure.mapper.CategoryMapper;
import org.novastart.infrastructure.service.ArticleService;
import org.novastart.infrastructure.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分类表(Category)表服务实现类
 *
 * @author makejava
 * @since 2023-09-12 13:33:06
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private ArticleService articleService;

    @Override
    public Response getCategoryList() {
        //查询文章表  状态为已发布的文章
        LambdaQueryWrapper<Article> articleWrapper = new LambdaQueryWrapper<>();
        articleWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        List<Article> articleList = articleService.list(articleWrapper);

        //获取文章的分类id，并且去重
        Set<Long> categoryIds = articleList.stream()
            .map(article -> article.getCategoryId())
            .collect(Collectors.toSet());

        //查询分类表
        List<Category> categories = listByIds(categoryIds);
        categories = categories.stream().
            filter(category -> SystemConstants.STATUS_NORMAL.equals(category.getStatus()))
            .collect(Collectors.toList());
        //封装vo
        List<CategoryVo> categoryVos = BeanCopyUtils.copyBeanList(categories, CategoryVo.class);

        return Response.success(categoryVos);
    }
}

