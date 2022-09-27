package org.novastart.blog.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/link")
@Api(tags = "友情链接")
public class LinkController {

    @Autowired
    private LinkService linkService;

    @GetMapping("/getAllLink")
    @ApiOperation("所有友链")
    public Response getAllLink(){
        return linkService.getAllLink();
    }
}
