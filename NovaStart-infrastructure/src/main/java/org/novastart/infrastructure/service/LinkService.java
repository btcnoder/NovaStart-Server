package org.novastart.infrastructure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.domain.entitty.Link;

/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-09-13 12:22:56
 */
public interface LinkService extends IService<Link> {

    Response getAllLink();
}

