package org.novastart.infrastructure.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.novastart.infrastructure.utils.Response;
import org.novastart.infrastructure.domain.entitty.Category;

/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2023-09-12 13:30:28
 */
public interface CategoryService extends IService<Category> {

    Response getCategoryList();
}

