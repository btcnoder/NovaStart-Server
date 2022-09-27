package org.novastart.infrastructure.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.novastart.infrastructure.utils.BeanCopyUtils;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.constants.SystemConstants;
import org.novastart.infrastructure.domain.entitty.Article;
import org.novastart.infrastructure.domain.entitty.Category;
import org.novastart.infrastructure.domain.vo.ArticleDetailVo;
import org.novastart.infrastructure.domain.vo.ArticleListVo;
import org.novastart.infrastructure.domain.vo.HotArticleVo;
import org.novastart.infrastructure.domain.vo.PageVo;
import org.novastart.infrastructure.mapper.ArticleMapper;
import org.novastart.infrastructure.mapper.CategoryMapper;
import org.novastart.infrastructure.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleService")
@Slf4j
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper,Article> implements ArticleService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Response hotArticle() {
        //查询热门文章 封装成ResponseResult返回
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);
        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
        List<HotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, HotArticleVo.class);
        return Response.success(vs);
    }

    @Override
    public Response articleList(Integer pageNum, Integer pageSize, Long categoryId) {
        //查询条件
        LambdaQueryWrapper<Article> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 如果 有categoryId 就要 查询时要和传入的相同
        lambdaQueryWrapper.eq(Objects.nonNull(categoryId)&&categoryId>0 ,Article::getCategoryId,categoryId);
        // 状态是正式发布的
        lambdaQueryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        // 对isTop进行降序
        lambdaQueryWrapper.orderByDesc(Article::getIsTop);

        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,lambdaQueryWrapper);

        List<Article> articles = page.getRecords();

        //查询categoryName
        articles.stream()
            .map(article ->
                article.setCategoryName(categoryMapper.selectById(article.getCategoryId()).getName())
            )
            .collect(Collectors.toList());

        //封装查询结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);

        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return Response.success(pageVo);
    }

    @Override
    public Response getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);

        //转换成VO
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);

        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryMapper.selectById(categoryId);
        if(category!=null){
            articleDetailVo.setCategoryName(category.getName());
        }
        //封装响应返回
        return Response.success(articleDetailVo);
    }
}
