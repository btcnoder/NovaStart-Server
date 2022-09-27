package org.novastart.infrastructure.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.novastart.infrastructure.utils.BeanCopyUtils;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.constants.SystemConstants;
import org.novastart.infrastructure.domain.entitty.Link;
import org.novastart.infrastructure.domain.vo.LinkVo;
import org.novastart.infrastructure.mapper.LinkMapper;
import org.novastart.infrastructure.service.LinkService;
import org.springframework.stereotype.Service;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-09-13 12:22:56
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public Response getAllLink() {
        //查询所有审核通过的
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_STATUS_NORMAL);
        List<Link> links = list(queryWrapper);
        //转换成vo
        List<LinkVo> linkVos = BeanCopyUtils.copyBeanList(links, LinkVo.class);
        //封装返回
        return Response.success(linkVos);
    }
}

