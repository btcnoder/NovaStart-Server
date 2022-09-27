package org.novastart.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
@Api(tags = "博客首页")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/hotArticleList")
    @ApiOperation("热门文章")
    public Response hotArticle(){

        Response response = articleService.hotArticle();

        return response;
    }

    @GetMapping("/articleList")
    @ApiOperation("文章列表")
    public Response articleList(Integer pageNum,Integer pageSize,Long categoryId){
        return articleService.articleList(pageNum,pageSize,categoryId);
    }

    @GetMapping("/{id}")
    @ApiOperation("文章详情")
    public Response getArticleDetail(@PathVariable("id") Long id){
        return articleService.getArticleDetail(id);
    }
}
