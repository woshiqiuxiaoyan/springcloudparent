package com.yan.springcloudprovide.service.impl;

import com.yan.springcloudprovide.dao.ProductDao;
import com.yan.springcloudprovide.entity.ProductEntity;
import com.yan.springcloudprovide.service.IProductService;

import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Author: ChenBJ
 * @Description: TODO
 * @Date: 2018/11/28 10:08
 * @Version:
 */
@Service ("productService")
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductDao productDao;

    @Override
    public void search(String searchContent) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句:"+builder);
        Iterable<ProductEntity> iterable = productDao.search(builder);
        iterable.forEach((e)->{
            System.out.println(e);
        });
    }

}
